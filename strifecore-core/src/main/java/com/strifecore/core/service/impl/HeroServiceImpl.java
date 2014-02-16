package com.strifecore.core.service.impl;

import com.strifecore.core.domain.Hero;
import com.strifecore.core.repository.HeroRepository;
import com.strifecore.core.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Override
    @Transactional
    public Hero getById(Integer id) {
        return heroRepository.read(id);
    }
}
