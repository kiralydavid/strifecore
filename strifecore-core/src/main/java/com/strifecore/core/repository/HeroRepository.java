package com.strifecore.core.repository;

import com.strifecore.core.domain.Hero;

public interface HeroRepository {

    public Integer create(Hero hero);

    public Hero read(Integer id);
}
