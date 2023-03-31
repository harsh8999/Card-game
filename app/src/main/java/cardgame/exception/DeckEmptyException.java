package cardgame.exception;

public class DeckEmptyException extends RuntimeException {

    /**
     * 
     */
    public DeckEmptyException() {
    }

    /**
     * @param message
     */
    public DeckEmptyException(String message) {
        super(message);
    }
    
    
}
