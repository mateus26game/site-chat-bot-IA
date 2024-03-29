package me.dio.sdw24.application.application;

import me.dio.sdw24.domain.model.Champion;
import me.dio.sdw24.domain.ports.ChampionsRepositoty;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepositoty repositoty) {


    public List<Champion> findAll() {
        return  repositoty.findAll();
    }
}
