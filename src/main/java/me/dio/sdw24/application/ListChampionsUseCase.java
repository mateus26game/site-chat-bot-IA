package me.dio.sdw24.application;

import me.dio.sdw24.domain.model.Champions;
import me.dio.sdw24.domain.ports.ChampionsRepositoty;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepositoty repositoty) {


    public List<Champions> findAll() {
        return  repositoty.findAll();
    }
}
