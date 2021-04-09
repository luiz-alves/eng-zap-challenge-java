package br.com.olx.challenge.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UnloadedDatabaseException extends RuntimeException {

    @Getter
    private final String errorCode;

}
