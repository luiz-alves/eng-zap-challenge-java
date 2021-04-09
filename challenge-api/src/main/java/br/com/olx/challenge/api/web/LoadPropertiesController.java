package br.com.olx.challenge.api.web;

import br.com.olx.challenge.common.service.PropertyLoaderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Api(value = "Imóveis do Portal")
public class LoadPropertiesController implements LoadPropertiesControllerApi {

    private final PropertyLoaderService propertyLoaderService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void loadProperties() {
        propertyLoaderService.loadProperties();
    }


}
