package br.com.olx.challenge.api.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface LoadPropertiesControllerApi {

    @ApiOperation(value = "Carrega os imóveis do arquivo para a memória")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida")})
    void loadProperties();

}
