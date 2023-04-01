package cardgame.entity;

public enum Suit {
    SPADE("Spade"), CLUBS("Club"), HEART("Heart"), DIAMONS("Diamond");

    private String suit;

    private Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
