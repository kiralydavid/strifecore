package com.strifecore.api.controller;

import com.strifecore.api.exception.ErrorJson;
import com.strifecore.api.exception.HeroNotFoundException;
import com.strifecore.core.domain.Hero;
import com.strifecore.core.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HeroNotFoundException.class)
    public ErrorJson exceptionHandler() {
        return new ErrorJson(1001, "Hero not found.");
    }


    @RequestMapping(value = "/{heroId}", method = RequestMethod.GET)
    public Hero getHero(@PathVariable Integer heroId) {

        Hero hero = heroService.getById(heroId);

        if(hero == null) {
            throw new HeroNotFoundException();
        }

        return hero;
    }

}
