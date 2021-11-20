package com.digitalinovatione.heroesapi.livecoding.config.repository;

import com.digitalinovatione.heroesapi.livecoding.config.document.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes,String> {
}
