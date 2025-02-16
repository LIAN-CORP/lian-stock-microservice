package com.lian.marketing.lianstockmicroservice.configuration;

import com.lian.marketing.lianstockmicroservice.domain.api.ICategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.usecase.CategoryUseCase;
import com.lian.marketing.lianstockmicroservice.domain.spi.ICategoryPersistencePort;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter.CategoryAdapter;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper.ICategoryEntityMapper;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class CategoryBeanConfiguration {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    @Bean
    private ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryEntityMapper, categoryRepository);
    }

    @Bean
    private ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

}
