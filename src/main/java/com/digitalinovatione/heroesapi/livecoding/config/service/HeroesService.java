package com.digitalinovatione.heroesapi.livecoding.config.service;

import com.digitalinovatione.heroesapi.livecoding.config.document.Heroes;
import com.digitalinovatione.heroesapi.livecoding.config.repository.HeroesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class HeroesService {

    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }
    public Flux<Heroes> findAll(){
        return Flux.fromIterable(this.heroesRepository.findAll());
    }
    public Mono<Heroes> findById(String id){
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }
    public Mono <Heroes> save(Heroes hero){
        return Mono.justOrEmpty(this.heroesRepository.save(hero));
    }
    public Mono<Boolean> deleteByIdHero(String id){
         heroesRepository.deleteById(id);
         return Mono.just(true);
    }
}
