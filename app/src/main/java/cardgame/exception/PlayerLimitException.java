package cardgame.exception;

public class PlayerLimitException extends RuntimeException {

    /**
     * 
     */
    public PlayerLimitException() {
    }

    /**
     * @param message
     */
    public PlayerLimitException(String message) {
        super(message);
    }
    
}
