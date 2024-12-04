package se.ericnorrwing.weatherboy.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import se.ericnorrwing.weatherboy.handler.exception.ResponseException;
import se.ericnorrwing.weatherboy.handler.exception.RoleNotFoundException;
import se.ericnorrwing.weatherboy.handler.exception.UserAlreadyExistsException;
import se.ericnorrwing.weatherboy.handler.exception.UserNotFoundException;

import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    //TODO below method

    @ExceptionHandler(ResponseException.class)
    protected ProblemDetail handleConflict(ResponseException e, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());

        String userInput = request.getParameter("userInputPersonalNumber");
        if (userInput != null) {
            problemDetail.setProperty("Invalid personal number", userInput);
        }

        problemDetail.setProperty("Detail", e.getDetail());

        return problemDetail;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ProblemDetail handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ProblemDetail handleUserNotFound(UserNotFoundException e, WebRequest request) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}


