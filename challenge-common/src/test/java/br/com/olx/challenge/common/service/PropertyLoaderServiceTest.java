package br.com.olx.challenge.common.service;

import br.com.olx.challenge.common.ChallengeCommonApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {ChallengeCommonApplicationTest.class})
@ExtendWith(SpringExtension.class)
class PropertyLoaderServiceTest {

    @Autowired
    private PropertyLoaderService propertyLoaderService;

    @Test
    void should_load_properties() {
        Assertions.assertDoesNotThrow(() -> propertyLoaderService.loadProperties());
    }



}
