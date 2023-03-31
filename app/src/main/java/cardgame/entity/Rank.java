package cardgame.entity;

public enum Rank {
    // ace, king, queen, jack, 10, nine, eight, seven, six, five, four, three, two
    TWO("two"), THREE("three"), FOUR("four"), FIVE("five"), SIX("six"), SEVEN("seven"),
    EIGHT("eight"), NINE("nine"), TEN("ten"), ACE("ace"), KING("king"), QUEEN("queen"), JACK("jack");

    private String rank;

    private Rank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }
}
