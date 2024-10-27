package me.dio.sdw24.application.application;

import me.dio.sdw24.domain.exception.ChampionNotFoundException;
import me.dio.sdw24.domain.model.Champion;
import me.dio.sdw24.domain.ports.ChampionsRepositoty;
import me.dio.sdw24.domain.ports.GenerativeAiService;

public record AskChampionUseCase(ChampionsRepositoty repositoty, GenerativeAiService genAiApi) {


    public String askChampion(Long championId, String question){

        Champion champion = repositoty.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

       String context =  champion.generateContexByQuestion(question);
       String objective = """
               Atue como uma assistente com a habilodade de se comporta como os personagens inteligentes da cultura pop.
               Responsa pergutas incorporado a personalidade e estilo de um determinado personagens.
               Segue a pergunta, o nome do personagens e sua respoctiva lore (historia):
               
               """;

      return genAiApi.generateContent(objective,context);



    }
}
