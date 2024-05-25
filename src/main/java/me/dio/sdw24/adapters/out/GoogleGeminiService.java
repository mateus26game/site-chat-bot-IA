package me.dio.sdw24.adapters.out;


import feign.FeignException;
import feign.RequestInterceptor;
import me.dio.sdw24.domain.ports.GenerativeAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@ConditionalOnProperty(name = "generative-ia", havingValue = "GEMINI",matchIfMissing = true)
@FeignClient(name = "GeminiApi",url = "${gemini.base-url}", configuration = GoogleGeminiService.Config.class)
public interface GoogleGeminiService extends GenerativeAiService {


    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    GoogleGeminiResp chatCompletion(GoogleGeminiRep req);
    @Override
    default String generateContent(String objective, String context){
        String prompt = """
                %s
                %s
                """.formatted(objective,context);

        GoogleGeminiRep req = new GoogleGeminiRep(
                List.of(new Content(List.of(new Part(prompt))))
        );
        try {

            GoogleGeminiResp resp = chatCompletion(req);
            return resp.candidates().getFirst().content().parts().getFirst().text();
        } catch (FeignException httpErrors){
            return "Deu ruim! Erro de comunicação com a API do Google gemini.";
        }catch (Exception unexpectedError){
            return "Deu mais ruim, O retorno da API  do Google gemini não contem os dados esperaos ";
        }
    }



    record GoogleGeminiRep(List<Content> contents){}
    record Content(List<Part> parts){ }
    record Part(String text){}
    record  GoogleGeminiResp(List<Candidates> candidates){ }
    record Candidates(Content content){}

    class Config{
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey){
            return requestTemplate -> requestTemplate.query("key", apiKey);
        }
    }

}
