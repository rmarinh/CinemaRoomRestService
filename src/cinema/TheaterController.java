package cinema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@RestController
public class TheaterController {

    Theater theater = new Theater(9, 9);

// Add your code below this line and do not change the code above the line.

    @GetMapping("/seats")
    public Theater getSeats() {
        return theater;
    }
    final Scanner scanner = new Scanner(System.in);
    @PostMapping(path = "/return", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String returnTicket(@RequestBody Token token) throws JsonProcessingException {
            if(theater.isTicketPurchaseTokenValid(token.getToken())){
                Ticket ticket = theater.returnTicket(token.getToken());
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer().withRootName("returned_ticket");
                String json = writer.writeValueAsString(ticket);
                return json;

            }else
                throw new WrongTokenException("Wrong token!");

    }
    @PostMapping(path = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TicketPurchase purchaseSeat(@RequestBody Ticket requestTicket) throws Exception {
       return  theater.purchaseSeat(requestTicket);
     }

    @PostMapping(path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Statistics theaterStatistics(@RequestParam("password") Optional<String> password)  {
        if(!password.isPresent() )
            throw new WrongPasswordException("wrong password");

        String pass= password.get();
        if (!pass.equals("super_secret") || pass.isEmpty() || null == pass)
            throw new WrongPasswordException("wrong password");

        return  theater.getTheaterStatistics();
    }

}