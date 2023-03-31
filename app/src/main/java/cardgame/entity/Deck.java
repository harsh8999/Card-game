package cardgame.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.exception.DeckEmptyException;

public class Deck {
    
    private List<Card> cards;

    /**
     * @return the cards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Create Deck of 52 Cards
     */
    public Deck() {
        cards = new ArrayList<Card>();
        // create cards and add it to Deck of Cards
        for(Suit suit: Suit.values()) {
            for(Rank rank: Rank.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public Card draw() {
        if(cards.isEmpty()) {
            throw new DeckEmptyException();
        }

        // remove last card from the deck
        return cards.remove(cards.size() - 1);
    }
}
