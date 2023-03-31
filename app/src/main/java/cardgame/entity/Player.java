package cardgame.entity;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private int id;
    private String name;
    private List<Card> cards;

    /**
     * @param id
     * @param name
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.cards = new ArrayList<Card>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cards in Hand
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * @param card
     * add card in hand
     */
    public void addCardInHand(Card card) {
        cards.add(card);
    }
    
    /**
     * @return the number of cards in Hand
     */
    public int numberOfCardsInHand() {
        return cards.size();
    }


    public Card playCard(int index) {
        
        if(index >= cards.size()) {
            throw new IndexOutOfBoundsException("Doesn't contain card !!!");
        }

        return cards.remove(index);
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", cards=" + cards + "]";
    }


}
