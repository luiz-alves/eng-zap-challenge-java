package br.com.olx.challenge.api.web;

import br.com.olx.challenge.common.service.PortalPropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class EligiblePropertiesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortalPropertyService portalPropertyService;

    @Test
    void should_return_zap_portal_properties() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/properties")
            .header("portal", "zap"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void should_return_vivareal_portal_properties() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/properties")
            .header("portal", "vivareal"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void should_return_error_when_portal_not_exists() throws Exception {
        Mockito.when(portalPropertyService.getPropertiesFromPortal("dasdasdas",0,30)).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(MockMvcRequestBuilders
            .get("/properties")
            .header("portal", "dasdasdas"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

}
