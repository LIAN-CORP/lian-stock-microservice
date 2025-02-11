package com.lian.marketing.lianstockmicroservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.datasource.url=",
        "spring.datasource.username=",
        "spring.datasource.password="
})
class LianStockMicroserviceApplicationTests {

    @Test
    void contextLoads() {
    }

}
