package com.ganesh.productservice;

import com.ganesh.productservice.Exceptions.IDNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IDNotFoundException.class,NumberFormatException.class})
    public ResponseEntity<String> throwException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler()
//    public ResponseEntity<String> handleNumberFormatException(NumberFormatException exception){
//        return new ResponseEntity<>("Invalid id",HttpStatus.OK);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        }
//        System.out.println("method invoked");
//        return errors;
//    }
}
