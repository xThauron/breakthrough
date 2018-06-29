package pl.xthauron.breakthrough;

import pl.xthauron.breakthrough.board.Board;
import pl.xthauron.breakthrough.pawn.Pawn;
import pl.xthauron.breakthrough.player.Player;

import java.util.List;


public class Breakthrough {

  private Board board;

  private List<Player> players;

  private Player winner = null;

  private boolean gameLasts;

  private Player currentPlayerRound;

  public Breakthrough(Board board, List<Player> players) {
    this.board = board;
    this.gameLasts = true;
    this.players = players;

    startGame();
  }

  private void startGame() {
    while(this.gameLasts) {
      for(Player player : players) {
        this.currentPlayerRound = player;
        display();
        this.currentPlayerRound.move();
        checkWinConditions();

        if(!gameLasts) break;
        // Jak będzie Pan chciał pooglądać grę kompów, to polecam dać tego sleepa :) Polecam, miły seans
        /*try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }*/
      }
    }

    this.board.display();

    System.out.println("Gra zakończona!");
    if(winner != null) {
      System.out.println("Wygrał gracz " + winner.getPawnColor());
    }
    else {
      System.out.println("Zaden z pionkow nie moze sie ruszyc. REMIS!");
    }
    System.out.println("Gratulujemy!");
  }

  public List<Player> getPlayers() {
    return this.players;
  }

  public void checkWinConditions() {
    int playersAlive = players.size();
    int playersAbleToMove = players.size();
    Player randomAlivePlayer = null;

    for(Player player : players) {
      if(player.getPawns().isEmpty()) {
        playersAlive--;
      }
      if(player.getFreePawns().isEmpty()) {
        playersAbleToMove--;
      }
      else {
        if(player.getPawns().stream().filter(Pawn::checkIfWins).count() >= 1) {
          this.gameLasts = false;
          this.winner = player;
          break;
        }
        randomAlivePlayer = player;
      }
    }

    if(this.gameLasts) {
      if(playersAlive == 1) {
        this.gameLasts = false;
        this.winner = randomAlivePlayer;
      }
      else if(playersAbleToMove == 0) {
        this.gameLasts = false;
        this.winner = null;
      }
    }

  }

  public void display() {
    System.out.println("\nKOLEJ GRACZA: " + this.currentPlayerRound.getPawnColor().getTextColor().toUpperCase() + "\n");

    this.board.display();
  }
}
