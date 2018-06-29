package pl.xthauron.breakthrough.player.aiplayer;

import pl.xthauron.breakthrough.board.Board;
import pl.xthauron.breakthrough.pawn.Location;
import pl.xthauron.breakthrough.pawn.Pawn;
import pl.xthauron.breakthrough.pawn.PawnColor;
import pl.xthauron.breakthrough.player.Player;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomAIPlayer implements Player {

  private final Board board;

  private final PawnColor pawnColor;

  public RandomAIPlayer(Board board, PawnColor pawnColor) {
    this.board = board;

    this.pawnColor = pawnColor;
  }

  @Override
  public void move() {

    Random rand = new Random();

    List<Pawn> freePawnsAbleToMove = this.getFreePawns().stream()
            .filter(pawn -> !pawn.getAvailableMoves()
                    .isEmpty())
            .collect(Collectors.toList());

    Pawn choosenPawn = freePawnsAbleToMove.get(rand.nextInt(freePawnsAbleToMove.size()));

    Location choosenLocation = choosenPawn.getAvailableMoves().get(rand.nextInt(choosenPawn.getAvailableMoves().size()));

    choosenPawn.move(choosenLocation);
  }

  @Override
  public PawnColor getPawnColor() {
    return this.pawnColor;
  }

  @Override
  public List<Pawn> getPawns() {
    return this.board.getPawnList().stream()
            .filter(pawn -> pawn.getPawnColor() == this.pawnColor)
            .collect(Collectors.toList());
  }

  @Override
  public List<Pawn> getFreePawns() {
    return this.getPawns().stream()
            .filter(pawn -> !pawn.getAvailableMoves()
                    .isEmpty())
            .collect(Collectors.toList());
  }

}
