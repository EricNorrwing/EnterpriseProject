package se.ericnorrwing.weatherboy.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    //TODO
    @ExceptionHandler(MyException.class)
    protected ProblemDetail handleConflict (MyException e, WebRequest request ) {
        ProblemDetail problemDetail
                = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        //If you need a multitude of extra info, otherwise use property singular
        //problemDetail.setProperties(Map.of("Error", "Customer did not read instructions"));
        problemDetail.setProperty("Invalid personal number", Objects.requireNonNull(request.getParameter("userInputPersonalNumber")));
        return problemDetail;
    }

    //TODO
    @ExceptionHandler(UserCouldntReadException.class)
    protected ProblemDetail handleMyException (UserCouldntReadException e) {
        ProblemDetail problemDetail
                = ProblemDetail.forStatusAndDetail(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        problemDetail.setProperties(Map.of("Error", "Form was not filled correctly"));
        //Implement BusinessService and let the service handle logging and other business logic
        return problemDetail;
    }
}
