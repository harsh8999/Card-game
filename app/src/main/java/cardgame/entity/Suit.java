package cardgame.entity;

public enum Suit {
    SPADE("spade"), CLUBS("club"), HEART("heart"), DIAMONS("diamond");

    private String suit;

    private Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
