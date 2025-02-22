package com.lian.marketing.lianstockmicroservice.configuration;

import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.usecase.SubcategoryUseCase;
import com.lian.marketing.lianstockmicroservice.domain.spi.ISubcategoryPersistencePort;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter.SubcategoryAdapter;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper.ISubcategoryEntityMapper;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository.ISubcategoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SubcategoryBeanConfiguration {
    private final ISubcategoryEntityMapper subcategoryEntityMapper;
    private final ISubcategoryRespository subcategoryRespository;

    @Bean
    public ISubcategoryPersistencePort subcategoryPersistencePort() {
        return new SubcategoryAdapter(subcategoryRespository, subcategoryEntityMapper);
    }

    @Bean
    public ISubcategoryServicePort subcategoryServicePort() {
        return new SubcategoryUseCase(subcategoryPersistencePort());
    }
}
