package me.dio.sdw24;

import me.dio.sdw24.application.ListChampionsUseCase;
import me.dio.sdw24.domain.ports.ChampionsRepositoty;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@org.springframework.boot.autoconfigure.SpringBootApplication

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepositoty Repository){
		return  new ListChampionsUseCase(Repository);

	}

}
