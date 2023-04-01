package cardgame.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cardgame.exception.DeckEmptyException;

@DisplayName("Deck Test")
public class DeckTest {

    @Test
    @DisplayName("draw function should throw DeckEmptyException")
    public void drawCardException() {
        Deck deck = new Deck();
        
        for(int i = 0; i < 52; i++) {
            deck.draw();
        }

        Assertions.assertThrows(DeckEmptyException.class, () -> deck.draw());
    }
    
}
