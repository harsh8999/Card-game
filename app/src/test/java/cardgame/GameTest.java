package cardgame;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cardgame.entity.Player;
import cardgame.exception.PlayerLimitException;

@DisplayName("Game Test")
public class GameTest {

    private static Game game;

    @BeforeAll
    public static void setup() {
        game = new Game();
    }

    @Test
    @DisplayName("Adding more than 4 players to Game throws ")
    void addingMoreThanFourPlayersInGame() {
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            players.add(new Player(i, "i"));
        }

        for(int i = 0; i< 4; i++) {
            game.addPlayer(players.get(i));
        }
        assertThrows(PlayerLimitException.class, () -> game.addPlayer(players.get(4)));

    }
    
}
