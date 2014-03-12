package com.strifecore.core.repository;

import com.strifecore.core.domain.Hero;

import java.util.List;

public interface HeroRepository {

    public Integer create(Hero hero);

    public Hero read(Integer id);

    public List<Hero> readAll();
}
