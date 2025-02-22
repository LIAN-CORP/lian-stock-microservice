package com.lian.marketing.lianstockmicroservice.configuration;

import com.lian.marketing.lianstockmicroservice.domain.api.IProductServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.usecase.ProductUseCase;
import com.lian.marketing.lianstockmicroservice.domain.spi.IProductPersistencePort;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter.ProductAdapter;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper.IProductEntityMapper;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductBeanConfiguration {

    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final SubcategoryBeanConfiguration subcategoryBeanConfiguration;

    @Bean
    public IProductPersistencePort productPersistencePort() {
        return new ProductAdapter(productRepository, productEntityMapper);
    }

    @Bean
    public IProductServicePort productServicePort() {
        return new ProductUseCase(productPersistencePort(), subcategoryBeanConfiguration.subcategoryServicePort());
    }

}
