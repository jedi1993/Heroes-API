package com.digitalinovatione.heroesapi.livecoding.config.controller;
import com.amazonaws.services.dynamodbv2.xspec.M;
import com.digitalinovatione.heroesapi.livecoding.config.document.Heroes;
import com.digitalinovatione.heroesapi.livecoding.config.repository.HeroesRepository;
import com.digitalinovatione.heroesapi.livecoding.config.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.digitalinovatione.heroesapi.livecoding.config.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroesController {

    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public HeroesController (HeroesService heroesService, HeroesRepository heroesRepository){
        this.heroesRepository=heroesRepository;
        this.heroesService=heroesService;
    }
@GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems(){
        log.info("requesting the list off all heroes");
        return heroesService.findAll();
}
@GetMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id){
        log.info("requesting the hero with id {}",id);
        return heroesService.findById(id)
                .map((item) -> new ResponseEntity<>(item,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>((HttpStatus.NOT_FOUND)));
}
@PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes hero){
        log.info("a new hero was created");
        return heroesService.save(hero);
}
    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deletebyIDHero(@PathVariable String id) {
        heroesService.deleteByIdHero(id);
        log.info("Deleting the hero with id {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}
