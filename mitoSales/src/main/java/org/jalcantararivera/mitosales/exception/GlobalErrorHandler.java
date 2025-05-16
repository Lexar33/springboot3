package org.jalcantararivera.mitosales.exception;

import org.jalcantararivera.mitosales.dto.GenericResponse;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleAllExceptions(Exception ex, WebRequest req) {
        CustomErrorResponse errorResponse=new CustomErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(500,"BACKEND-ERROR",Arrays.asList(errorResponse)),HttpStatus.NOT_FOUND);
    };
/*
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(404,"NOT-FOUND", Arrays.asList(errorResponse)),HttpStatus.NOT_FOUND);
    };

 */
    //DESDE SPRING BOOT 3
    @ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
        pd.setTitle("Model not found exception");
        pd.setType(URI.create(req.getContextPath()));
        pd.setProperty("code","404");
        pd.setProperty("message","not-found");
        return pd;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        CustomErrorResponse errorResponse=new CustomErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(400,"BAD REQUEST",Arrays.asList(errorResponse)),HttpStatus.NOT_FOUND);
    }
}
