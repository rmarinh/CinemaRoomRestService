package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongPasswordException extends RuntimeException {

    //final static String message = "The password is wrong!";
    public WrongPasswordException(String message){

        super(message);
    }


}