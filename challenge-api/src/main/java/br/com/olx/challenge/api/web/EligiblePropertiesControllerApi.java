package br.com.olx.challenge.api.web;

import br.com.olx.challenge.common.payload.EligiblePropertiesResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface EligiblePropertiesControllerApi {

    @ApiOperation(value = "Obtem os imóveis elegíveis para o portal solicitado", response = EligiblePropertiesResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso", response = EligiblePropertiesResponse.class),
        @ApiResponse(code = 400, message = "Requisição inválida")})
    ResponseEntity<EligiblePropertiesResponse>  getEligibleProperties(@RequestHeader("portal") String portal,
        @RequestHeader(value = "page", defaultValue = "0") Integer page,
        @RequestHeader(value = "size", defaultValue = "30") Integer size);

}
