package com.lian.marketing.lianstockmicroservice.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.lian.marketing.lianstockmicroservice.domain.api.IS3ServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.usecase.S3UseCase;
import com.lian.marketing.lianstockmicroservice.domain.spi.IS3PersistencePort;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter.S3Adapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguration {
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3 s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    @Bean
    public IS3PersistencePort s3PersistencePort() {
        return new S3Adapter(s3Client());
    }

    @Bean
    public IS3ServicePort s3ServicePort() {
        return new S3UseCase(s3PersistencePort());
    }
}
