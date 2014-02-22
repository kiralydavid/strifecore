package com.strifecore.core.repository;

import com.strifecore.core.domain.Guide;

public interface GuideRepository {

    public Integer create(Guide guide);

    public Guide read(Integer id);
}
