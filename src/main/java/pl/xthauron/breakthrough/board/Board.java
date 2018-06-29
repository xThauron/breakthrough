package pl.xthauron.breakthrough.board;

import pl.xthauron.breakthrough.pawn.Location;
import pl.xthauron.breakthrough.pawn.Pawn;
import pl.xthauron.breakthrough.pawn.PawnColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {


  private final int size;

  private static final String possibleHeader = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";

  private final String header;

  private List<Pawn> pawnList;

  public Board(int size) {
    this.size = size > Location.ALPHABET.length() ? Location.ALPHABET.length() : size;
    this.header = "x " + possibleHeader.substring(0, this.getSize() * 2);
    this.pawnList = new ArrayList<>();

    setup();
  }

  public int getSize() {
    return this.size;
  }

  public List<Pawn> getPawnList() {
    return this.pawnList;
  }

  public Optional<Pawn> getPawnAtLocation(Location location) {
    return this.pawnList.stream()
            .filter(pawn -> Location.compare(location, pawn.getLocation()))
            .findFirst();
  }

  public void display() {
    System.out.println(this.header);
    for (int i = 1; i <= this.getSize(); i++) {
      StringBuilder pawns = new StringBuilder(String.valueOf(i)).append(" ");

      for (int j = 1; j <= this.getSize(); j++) {
        Optional<Pawn> optionalPawn = this.getPawnAtLocation(new Location(this, j, i));

        if (optionalPawn.isPresent()) {
          Pawn pawn = optionalPawn.get();

          if (pawn.getPawnColor() == PawnColor.BLACK) {
            pawns.append("b ");
          } else {
            pawns.append("w ");
          }
        } else {
          pawns.append(". ");
        }
      }

      System.out.println(pawns.toString());
    }
  }

  private void setup() {
    for(int y = 1; y < 3; y++) {
      for(int x = 1; x <= this.size; x++) {
        pawnList.add(new Pawn(PawnColor.BLACK, new Location(this, x, y)));
      }
    }

    for(int y = this.size - 1; y < this.size + 1; y++) {
      for(int x = 1; x <= this.size; x++) {
        pawnList.add(new Pawn(PawnColor.WHITE, new Location(this, x, y)));
      }
    }
  }

}
