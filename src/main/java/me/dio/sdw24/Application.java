package me.dio.sdw24;

import me.dio.sdw24.application.application.AskChampionUseCase;
import me.dio.sdw24.application.application.ListChampionsUseCase;
import me.dio.sdw24.domain.ports.ChampionsRepositoty;
import me.dio.sdw24.domain.ports.GenerativeAiService;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@org.springframework.boot.autoconfigure.SpringBootApplication

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepositoty Repository){
		return  new ListChampionsUseCase(Repository);

	}

	@Bean
	public AskChampionUseCase provideAskChampionUseCase(ChampionsRepositoty Repository, GenerativeAiService genAiService){
		return  new AskChampionUseCase(Repository, genAiService);

	}

}
