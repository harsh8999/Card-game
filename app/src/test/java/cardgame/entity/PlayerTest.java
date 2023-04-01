package cardgame.entity;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Player Test")
public class PlayerTest {
    
    @Test
    @DisplayName("Player playCard should throw IndexOutOfBoundsException")
    void playersCard() {
        Player player = new Player(1, "Harsh");
        Deck deck = new Deck();
        deck.shuffle();
        List<Card> cards = deck.getCards();
        
        for(int i = 0; i < 5; i++) {
            player.addCardInHand(cards.get(i));
        }

        // player plays all 5 card
        for(int i = 0; i < 5; i++) {
            player.playCard(0);
        }
        
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> player.playCard(0));
    }
}
