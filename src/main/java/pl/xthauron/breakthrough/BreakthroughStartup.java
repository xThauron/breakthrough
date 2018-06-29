package pl.xthauron.breakthrough;

import pl.xthauron.breakthrough.board.Board;
import pl.xthauron.breakthrough.pawn.PawnColor;
import pl.xthauron.breakthrough.player.HumanPlayer;
import pl.xthauron.breakthrough.player.Player;
import pl.xthauron.breakthrough.player.aiplayer.AggresiveAIPlayer;
import pl.xthauron.breakthrough.player.aiplayer.RandomAIPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BreakthroughStartup {

  public static void main(String[] args) {
    int option = 0;
    Board board = new Board(8);
    Player playerOne;
    Player playerTwo;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Witaj w grze Breakthrough!");
    System.out.println("Ustawienia gracza pierwszego");
    System.out.println("1. Gracz");
    System.out.println("2. Komputer");
    System.out.println("Wybieram opcje nr: ");
    option = scanner.nextInt();

    Random random = new Random();
    int color = random.nextInt(1);

    PawnColor playerOnePawnColor = color == 0 ? PawnColor.WHITE : PawnColor.BLACK;

    playerOne = getPlayer(option, board, scanner, playerOnePawnColor);

    System.out.println("Ustawienia gracza drugiego");
    System.out.println("1. Gracz");
    System.out.println("2. Komputer");
    System.out.println("Wybieram opcje nr: ");
    option = scanner.nextInt();

    PawnColor playerTwoPawnColor = color == 0 ? PawnColor.BLACK : PawnColor.WHITE;

    playerTwo = getPlayer(option, board, scanner, playerTwoPawnColor);

    List<Player> players = new ArrayList<>();
    if(playerOne.getPawnColor() == PawnColor.WHITE) {
      players.add(playerOne);
      players.add(playerTwo);
    }
    else {
      players.add(playerTwo);
      players.add(playerOne);
    }

    new Breakthrough(board, players);
  }

  private static Player getPlayer(int option, Board board, Scanner scanner, PawnColor pawnColor) {
    Player player;
    if(option == 1) player = new HumanPlayer(board, pawnColor);
    else {
      System.out.println("Zdefiniuj rodzaj komputera");
      System.out.println("1. Totalny random");
      System.out.println("2. Agresywny ziomek");
      System.out.println("Wybieram opcje nr: ");
      int optionAI = scanner.nextInt();
      if(optionAI == 1) player = new RandomAIPlayer(board, pawnColor);
      else player = new AggresiveAIPlayer(board, pawnColor);
    }
    return player;
  }

}
