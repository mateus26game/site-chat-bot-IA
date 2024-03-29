package me.dio.sdw24.adapters.in;

import jdk.jfr.StackTrace;
import me.dio.sdw24.domain.exception.ChampionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChampionNotFoundException.class)
    public ResponseEntity<ApiError> handleDomainException(ChampionNotFoundException domainError){
        return ResponseEntity
                .unprocessableEntity()
                .body( new ApiError(domainError.getMessage()));

    }



    public record ApiError(String message){}

}
