package com.strifecore.core.service;

import com.strifecore.core.domain.Hero;

import java.util.List;

public interface HeroService {

    public Hero getById(Integer id);

    public List<Hero> getAll();

}
