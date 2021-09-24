package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = { TheaterController.class })
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ResponseError apiError, HttpStatus status) {
        return new ResponseEntity<>(apiError, status);
    }
    @ControllerAdvice
    public class ApplicationExceptionHandler {


        @ExceptionHandler(WrongTokenException.class)
        protected ResponseEntity <Object> handleWrongTokenException(WrongTokenException e) {
            ResponseError responseError = new ResponseError("Wrong token!");
            return   buildResponseEntity(responseError, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(SeatAlreadyPurchasedException.class)
        protected ResponseEntity <Object> handleSeatAlreadyPurchasedException(SeatAlreadyPurchasedException e) {
            ResponseError responseError = new ResponseError("The ticket has been already purchased!" );
            return   buildResponseEntity(responseError, HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(SeatOutOfBoundsException.class)
        protected ResponseEntity <Object> handleSeatOutOfBoundsException(SeatOutOfBoundsException e) {
            ResponseError responseError = new ResponseError("The number of a row or a column is out of bounds!" );
            return   buildResponseEntity(responseError, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(WrongPasswordException.class)
        protected ResponseEntity <Object> handleWrongPasswordException(WrongPasswordException e) {
            ResponseError responseError = new ResponseError("The password is wrong!" );
            return   buildResponseEntity(responseError, HttpStatus.UNAUTHORIZED);
        }

    }


}
