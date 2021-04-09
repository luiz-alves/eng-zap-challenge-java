package br.com.olx.challenge.api.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

}
