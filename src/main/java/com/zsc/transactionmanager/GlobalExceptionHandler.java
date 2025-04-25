package com.zsc.transactionmanager;
import com.zsc.transactionmanager.exception.TransactonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 处理所有异常
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception e) {
        // 可以记录日志等操作
        System.err.println(e.getMessage());
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Object> handleInvalidParameterExceptions(InvalidParameterException e) {
        // 可以记录日志等操作
        System.err.println(e.getMessage());
        return new ResponseEntity<>("invalid parameter occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 针对特定异常的处理
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<>("A null pointer exception occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = TransactonException.class)
    public ResponseEntity<Object> handleTransactionException(TransactonException e) {
        System.err.println(e.getMessage());
        return new ResponseEntity<>("A transaction exception occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}