package pl.xthauron.breakthrough.pawn;

import java.util.ArrayList;
import java.util.List;

public class Pawn {

  private final PawnColor pawnColor;

  private Location location;

  public Pawn(PawnColor pawnColor, Location location) {
    this.pawnColor = pawnColor;
    this.location = location;
  }

  public PawnColor getPawnColor() {
    return pawnColor;
  }

  public Location getLocation() {
    return location;
  }

  private int getDestinationRow() {
    return this.pawnColor == PawnColor.BLACK ? location.getBoard().getSize() : 1;
  }

  public boolean checkIfWins() {
    return this.location.getVertically() == getDestinationRow();
  }

  public List<Location> getAvailableMoves() {
    List<Location> availableMoves = new ArrayList<>();
    Location location;

    int changeY = this.getPawnColor() == PawnColor.BLACK ? 1 : -1;

    location = new Location(this.location.getBoard(), this.location.getHorizontally()-1, this.location.getVertically()+changeY);
    if(location.checkLocation() && (!location.getPawn().isPresent() || location.getPawn().get().getPawnColor() != this.getPawnColor()))  availableMoves.add(location);
    location = new Location(this.location.getBoard(), this.location.getHorizontally(), this.location.getVertically()+changeY);
    if(location.checkLocation() && !location.getPawn().isPresent())  availableMoves.add(location);
    location = new Location(this.location.getBoard(), this.location.getHorizontally()+1, this.location.getVertically()+changeY);
    if(location.checkLocation() && (!location.getPawn().isPresent() || location.getPawn().get().getPawnColor() != this.getPawnColor()))  availableMoves.add(location);

    return availableMoves;
  }

  public void move(Location location) {
      System.out.println(this.location.getFieldName() + " >> " + location.getFieldName());
      if(location.getPawn().isPresent()) {
        this.location.getBoard().getPawnList().remove(location.getPawn().get());
      }

      this.location = location;
  }

}
