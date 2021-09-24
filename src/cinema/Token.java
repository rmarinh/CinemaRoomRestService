package cinema;

import java.util.Objects;
import java.util.UUID;

public class Token {
    private UUID token;
    public Token() {
        this.token = UUID.randomUUID();
    }

    public Token(UUID token) {
        this.token = token;

    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
