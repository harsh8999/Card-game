package cardgame.entity;

public enum Rank {
    // ace, king, queen, jack, 10, nine, eight, seven, six, five, four, three, two
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"), TEN("10"), ACE("A"), KING("K"), QUEEN("Q"), JACK("J");

    private String rank;

    private Rank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }
}
