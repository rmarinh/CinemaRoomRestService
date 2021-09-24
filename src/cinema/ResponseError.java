package cinema;


import org.springframework.http.HttpStatus;


public class ResponseError {
    private  String error;


    public ResponseError() {
    }

    public ResponseError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
