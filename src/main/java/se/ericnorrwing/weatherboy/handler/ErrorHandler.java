package se.ericnorrwing.weatherboy.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import se.ericnorrwing.weatherboy.handler.exception.*;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ProblemDetail handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setProperties(Map.of("Error", "User already exists"));
        return problemDetail;
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ProblemDetail handleUserNotFound(UserNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperties(Map.of("Error", "User not found"));
        return problemDetail;
    }

    @ExceptionHandler(RoleNotFoundException.class)
    protected ProblemDetail handleRoleNotFoundException(RoleNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperties(Map.of("Error", "Role not found"));
        return problemDetail;
    }

    @ExceptionHandler(LocationNotFoundException.class)
    protected ProblemDetail handleLocationNotFoundException(LocationNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperties(Map.of("Error", "Location not found"));
        return problemDetail;
    }
}
