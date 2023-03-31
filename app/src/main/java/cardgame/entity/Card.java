package cardgame.entity;

public class Card {
    private Rank rank;
    private Suit suit;

    /**
     * @param rank
     * @param suit
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * @return the rank
     */
    public Rank getRank() {
        return rank;
    }
    
    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card [rank=" + rank + ", suit=" + suit + "]";
    }
}
