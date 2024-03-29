package me.dio.sdw24;

import me.dio.sdw24.application.application.AskChampionUseCase;
import me.dio.sdw24.application.application.ListChampionsUseCase;
import me.dio.sdw24.domain.ports.ChampionsRepositoty;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

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
	public AskChampionUseCase provideAskChampionUseCase(ChampionsRepositoty Repository){
		return  new AskChampionUseCase(Repository);

	}

}
