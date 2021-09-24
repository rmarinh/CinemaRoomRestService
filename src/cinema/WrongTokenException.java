package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongTokenException extends RuntimeException {

    //final static String message = "Wrong token!";
    public WrongTokenException(String message){

        super(message);
    }


}