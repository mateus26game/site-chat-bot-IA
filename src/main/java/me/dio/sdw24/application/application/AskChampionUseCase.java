package me.dio.sdw24.application.application;

import me.dio.sdw24.domain.exception.ChampionNotFoundException;
import me.dio.sdw24.domain.model.Champion;
import me.dio.sdw24.domain.ports.ChampionsRepositoty;

public record AskChampionUseCase(ChampionsRepositoty repositoty) {


    public String askChampion(Long championId, String question){

        Champion champion = repositoty.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

       String championContext = champion.generateContexByQuestion(question);


        return championContext;
    }
}
