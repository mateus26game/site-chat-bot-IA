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

@ConditionalOnProperty(name = "generative-ia.provider", havingValue = "OPENIA")
@FeignClient(name = "openAiChatApi",url = "${openai.api-key}", configuration = OpenAiChatApi.Config.class)
public interface OpenAiChatApi extends GenerativeAiService {


    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResp chatCompletion(OpenAiChatCompletionRep req);
    @Override
    default String generateContent(String objective, String context){
        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(
                new Message("system", objective),
                new Message("user",context)
        );
        OpenAiChatCompletionRep req = new OpenAiChatCompletionRep(model, messages);

        try {

            OpenAiChatCompletionResp resp = chatCompletion(req);
            return resp.choices().getFirst().message().content();

        } catch (FeignException httpErrors){
            return "Deu ruim! Erro de comunicação com a API do Google gemini.";
        }catch (Exception unexpectedError){
            return "Deu mais ruim, O retorno da API  do Google gemini não contem os dados esperaos ";
        }



    }

    record OpenAiChatCompletionRep(String model, List<Message> messages){}
    record Message(String role, String content) { }
    record  OpenAiChatCompletionResp(List<Choice>choices){ }
    record Choice(Message message){}

    class Config{
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey){
            return requestTemplate -> requestTemplate.header
                    (HttpHeaders.AUTHORIZATION,"Bearer %s". formatted(apiKey));
        }
    }

}
