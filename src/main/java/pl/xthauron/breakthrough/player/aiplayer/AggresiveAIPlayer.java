package pl.xthauron.breakthrough.player.aiplayer;

import pl.xthauron.breakthrough.board.Board;
import pl.xthauron.breakthrough.pawn.Location;
import pl.xthauron.breakthrough.pawn.Pawn;
import pl.xthauron.breakthrough.pawn.PawnColor;
import pl.xthauron.breakthrough.player.Player;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AggresiveAIPlayer implements Player {

  private final Board board;

  private final PawnColor pawnColor;

  public AggresiveAIPlayer(Board board, PawnColor pawnColor) {
    this.board = board;

    this.pawnColor = pawnColor;
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

  @Override
  public void move() {
    List<Pawn> pawnsAbleToAttack = this.getFreePawns().stream()
            .filter(pawn -> pawn.getAvailableMoves().stream()
                    .filter(location -> location.getPawn().isPresent() && location.getPawn().get().getPawnColor() != this.pawnColor)
                    .count() > 0)
            .collect(Collectors.toList());

    Random rand = new Random();

    Pawn choosenPawn;
    if(!pawnsAbleToAttack.isEmpty())
      choosenPawn = pawnsAbleToAttack.get(rand.nextInt(pawnsAbleToAttack.size()));
    else
      choosenPawn = this.getFreePawns().get(rand.nextInt(this.getFreePawns().size()));

    List<Location> locationsAbleToMove = choosenPawn.getAvailableMoves();
    List<Location> locationsAbleToBeAttacked = locationsAbleToMove.stream()
            .filter(location -> location.getPawn().isPresent() && location.getPawn().get().getPawnColor() != this.pawnColor)
            .collect(Collectors.toList());

    Location choosenLocation = null;
    if(!locationsAbleToBeAttacked.isEmpty())
      choosenLocation = locationsAbleToBeAttacked.get(rand.nextInt(locationsAbleToBeAttacked.size()));
    else
      choosenLocation = locationsAbleToMove.get(rand.nextInt(locationsAbleToMove.size()));

    choosenPawn.move(choosenLocation);
  }


}
