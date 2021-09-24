package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatAlreadyPurchasedException extends RuntimeException {

    //inal static String message = "The ticket has been already purchased!";
  /*  public SeatAlreadyPurchasedException(){
        super(message);
    }

   */
    public SeatAlreadyPurchasedException(String message){
        super(message);
    }

}
