package br.com.olx.challenge.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PropertyLoaderException extends RuntimeException {

    @Getter
    private final String errorCode;

}
