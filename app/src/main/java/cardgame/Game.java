package cardgame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

import cardgame.entity.Card;
import cardgame.entity.Deck;
import cardgame.entity.Player;
import cardgame.exception.GameDrawException;

public class Game {

    private final int MAX_PLAYERS = 4;
    private final int INITIAL_CARDS_IN_HAND = 5;
    private final String FORWARD = "forward";
    private final String BACKWARD = "backward";
    private final Scanner scanner;

    private Deck deck;
    private List<Player> players;

    // Stack of cards to be drawn
    private Deque<Card> drawPile;
    private Deque<Card> discardPile;

    private boolean gameEnded;
    private boolean hasValidMove;

    private String direction;

    private int currentPlayerIndex;
    private int nextPlayerIndex;

    /**
     * 
     */
    public Game() {
        deck = new Deck();
        players = new ArrayList<Player>();
        discardPile = new ArrayDeque<Card>();
        gameEnded = false;
        currentPlayerIndex = 0;
        nextPlayerIndex = currentPlayerIndex + 1;
        direction = FORWARD;
        scanner = new Scanner(System.in);
    }

     /**
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    
    /**
     * @return the drawPile
     */
    public Deque<Card> getDrawPile() {
        return drawPile;
    }

    /**
     * @return the discardPile
     */
    public Deque<Card> getDiscardPile() {
        return discardPile;
    }

    public void addPlayer(Player player) {
        if(players.size() > MAX_PLAYERS) {
            throw new RuntimeException("Maximum number of players reached");
        }
        players.add(player);
    }

    public void distributeCards() {
        // shuffle the deck of cards
        deck.shuffle();

        // distribute the cards to the players
        for(int i = 0; i < INITIAL_CARDS_IN_HAND; i++) {
            for(Player player: players) {
                player.addCardInHand(deck.draw());
            }
        }
        
        setupDrawPile();
    }

    private void setupDrawPile() {
        drawPile = new ArrayDeque<Card>(deck.getCards());
    }

    public Card drawCard() {
        if(drawPile.isEmpty()) {
            throw new GameDrawException("Game Draw !!!");
        }
        return drawPile.pop();
    }

    // at this point 
    // every player has 5 cards in hand
    // draw pile is full and shuffled
    // discard pile is empty
    public void startGame() {

        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("Game Starts... ");
        
        while(!gameEnded) {
            // if card drawn than no need to check for top of the drawn dekh for special characters
            boolean cardDrawn = false;
            // Player plays
            System.out.println("Current Player "+currentPlayerIndex);
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n");
            System.out.println(currentPlayer.getName() + "'s turn..");
            int ind = 1;
            for(Card handCard: currentPlayer.getCards()) {
                System.out.println(ind + ". " + handCard);
                ind++;
            }

            int cardIndex;
            hasValidMove = false;

            // first move
            if(discardPile.isEmpty()) {
                System.out.println("Enter a card index (1, 2, 3...) : ");
                cardIndex = scanner.nextInt() - 1; 
                // Invalid 
                while(cardIndex < 0 || cardIndex > currentPlayer.numberOfCardsInHand()) {
                    System.out.println("Please enter valid Index Number!!!");
                    System.out.print("Enter a card index (1, 2, 3...) : ");
                    cardIndex = scanner.nextInt() - 1;
                }
                
                hasValidMove = true;
                Card playedCard = currentPlayer.playCard(cardIndex);
                discardPile.push(playedCard);
            }
            
            while(!hasValidMove) {
                System.out.println();
                System.out.println("Discard Pile's top card : " + discardPile.peek());
                System.out.println();
                System.out.println("Enter a card index (1, 2, 3...) ");
                System.out.print("If NO valid card present enter " + (currentPlayer.numberOfCardsInHand() + 1) + " to draw a card from Draw Pile. : " );
                cardIndex = scanner.nextInt() - 1; 

                // Invalid 
                if(cardIndex > currentPlayer.numberOfCardsInHand()) {
                    System.out.println("Please enter valid Index Number!!!");
                }

                // draw 1 card from draw pile
                if(cardIndex == currentPlayer.numberOfCardsInHand()) {
                    
                    hasValidMove = true;
                    try {
                        cardDrawn = true;
                        currentPlayer.addCardInHand(drawCard());
                    } catch (GameDrawException ex) {
                        gameEnded = true;
                    }

                } else if(currentPlayer.getCards().get(cardIndex).getSuit() == discardPile.peek().getSuit() || 
                        currentPlayer.getCards().get(cardIndex).getRank() == discardPile.peek().getRank()) {
                    
                    hasValidMove = true;
                    Card playedCard = currentPlayer.playCard(cardIndex);
                    discardPile.push(playedCard);

                } else if(currentPlayer.getCards().get(cardIndex).getSuit() != discardPile.peek().getSuit() || 
                        currentPlayer.getCards().get(cardIndex).getRank() != discardPile.peek().getRank()) {
                    
                    System.out.println("This Card cannot be played you need to choose a card either "+ discardPile.peek().getSuit() + " or "+discardPile.peek().getRank()+" or any other face Card...");

                }
            }

            // The game ends when one player runs out of cards
            if(currentPlayer.numberOfCardsInHand() == 0) {
                System.out.println(currentPlayer.getName() +" wins...");
                gameEnded = true;
                break;
            }

            // moveNextPlayerIndex();
            if(direction.equals(FORWARD)) {
                nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
            } else {
                nextPlayerIndex = (currentPlayerIndex - 1);
                if(nextPlayerIndex < 0) {
                    nextPlayerIndex = players.size() - 1;
                }
            }

            // if card is not drawn than check for special case
            if(!cardDrawn) {
                // actionCards();
                // System.out.println("Action Card on top....");
                // System.out.println(discardPile.peek().getRank());
                switch(discardPile.peek().getRank()) {
                    // Ace(A): Skip the next player in turn
                    case ACE:
                        // next player
                        // moveNextPlayerIndex();
                        System.out.println("Skip\n");
                        if(direction.equals(FORWARD)) {
                            nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
                        } else {
                            nextPlayerIndex = (currentPlayerIndex - 1);
                            if(nextPlayerIndex < 0) {
                                nextPlayerIndex = players.size() - 1;
                            }
                        }
                        break;

                    // Kings(K): Reverse the sequence of who plays next
                    case KING:
                        // reverse();
                        System.out.println("Reverse\n");
                        if(direction.equals(BACKWARD)) {
                            direction = FORWARD;
                        } else {
                            direction = BACKWARD;
                        }
                        break;
                        
                    // Queens(Q): +2
                    case QUEEN:
                        // draw 2 cards from drawDeck
                        // drawCardPalenty(players.get(nextPlayerIndex), 2);
                        System.out.println("Draw 2\n");
                        try {
                            for(int i = 0; i < 2; i++) {
                                players.get(nextPlayerIndex).addCardInHand(drawCard());
                            }
                        } catch (GameDrawException ex) {
                            gameEnded = true;
                        }
                        break;
                    
                    // Jacks(J): +4
                    case JACK:
                        // draw 4 cards from drawDeck
                        // drawCardPalenty(players.get(nextPlayerIndex), 4);
                        System.out.println("Draw 4\n");
                        try {
                            for(int i = 0; i < 4; i++) {
                                players.get(nextPlayerIndex).addCardInHand(drawCard());
                            }
                        } catch (GameDrawException ex) {
                            gameEnded = true;
                        }
                        break;
                    default:
                        break;
                }
            }

            
            // set current player to next player for next round
            currentPlayerIndex = nextPlayerIndex;
        }
    }

    // private void actionCards() {
    //     System.out.println("Action Card on top....");
    //     System.out.println(discardPile.peek().getRank());
    //     switch(discardPile.peek().getRank()) {
    //         // Ace(A): Skip the next player in turn
    //         case ACE:
    //             // next player
    //             moveNextPlayerIndex();
    //             break;

    //         // Kings(K): Reverse the sequence of who plays next
    //         case KING:
    //             reverse();
    //             break;
                
    //         // Queens(Q): +2
    //         case QUEEN:
    //             // draw 2 cards from drawDeck
    //             drawCardPalenty(players.get(nextPlayerIndex), 2);
    //             break;
            
    //         // Jacks(J): +4
    //         case JACK:
    //             // draw 4 cards from drawDeck
    //             drawCardPalenty(players.get(nextPlayerIndex), 4);
    //             break;
    //         default:
    //             break;
    //     }
    // }

    // private void reverse() {
    //     if(direction.equals(BACKWARD)) {
    //         direction = FORWARD;
    //     } else {
    //         direction = BACKWARD;
    //     }
    // }

    // private void moveNextPlayerIndex() {
    //     // next player
    //     if(direction.equals(FORWARD)) {
            //     nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
            // } else {
            //     nextPlayerIndex = (currentPlayerIndex - 1);
            //     if(nextPlayerIndex < 0) {
            //         nextPlayerIndex = players.size() - 1;
            //     }
            // }
    // }

    // private void drawCardPalenty(Player player, int numberOfCards) {
    //     try {
    //         for(int i = 0; i < numberOfCards; i++) {
    //             player.addCardInHand(drawCard());
    //         }
    //     } catch (GameDrawException ex) {
    //         gameEnded = true;
    //     }
    // }
}
