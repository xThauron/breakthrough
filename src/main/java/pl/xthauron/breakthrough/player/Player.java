package pl.xthauron.breakthrough.player;

import pl.xthauron.breakthrough.pawn.Pawn;
import pl.xthauron.breakthrough.pawn.PawnColor;

import java.util.List;

public interface Player {

  PawnColor getPawnColor();

  List<Pawn> getPawns();

  List<Pawn> getFreePawns();

  void move();

}
