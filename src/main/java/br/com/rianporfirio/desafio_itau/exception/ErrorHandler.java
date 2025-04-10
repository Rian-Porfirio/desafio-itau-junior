package br.com.rianporfirio.desafio_itau.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<MethodArgumentNotValidException> handlerError422() {
    return ResponseEntity.unprocessableEntity().build();
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<HttpMessageNotReadableException> handleError400() {
    return ResponseEntity.badRequest().build();
  }
}
