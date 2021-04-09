package br.com.olx.challenge.api.web;

import br.com.olx.challenge.common.payload.EligiblePropertiesResponse;
import br.com.olx.challenge.common.service.PortalPropertyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/properties")
@Api(value = "Imóveis do Portal")
public class EligiblePropertiesController implements EligiblePropertiesControllerApi {

    private final PortalPropertyService portalPropertyService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EligiblePropertiesResponse> getEligibleProperties(
        @RequestHeader("portal") String portal,
        @RequestHeader(value = "page", defaultValue = "0") Integer page,
        @RequestHeader(value = "size", defaultValue = "30") Integer size) {
        return ResponseEntity.ok().body(portalPropertyService.getPropertiesFromPortal(portal, page, size));
    }

}
