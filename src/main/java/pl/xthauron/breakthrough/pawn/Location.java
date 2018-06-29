package pl.xthauron.breakthrough.pawn;

import pl.xthauron.breakthrough.board.Board;

import java.util.Optional;

public class Location {

  public final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private final Board board;

  private int horizontally;
  private int vertically;

  public Location(Board board, int horizontally, int vertically) {
    this.board = board;

    this.horizontally = horizontally;
    this.vertically = vertically;
  }

  public Board getBoard() {
    return this.board;
  }

  public int getVertically() {
    return this.vertically;
  }

  public int getHorizontally() {
    return horizontally;
  }

  public String getFieldName() {
    StringBuilder fieldName = new StringBuilder();

    fieldName.append(horizontally > board.getSize() ? Location.ALPHABET.charAt(board.getSize() - 1) : Location.ALPHABET.charAt(horizontally - 1));
    fieldName.append(vertically > board.getSize() ? board.getSize() : vertically);

    return fieldName.toString();
  }

  public boolean checkLocation() {
    return this.vertically >= 1 && this.vertically <= this.getBoard().getSize() && this.horizontally >= 1 && this.horizontally <= this.getBoard().getSize();
  }

  public Optional<Pawn> getPawn() {
    return this.getBoard().getPawnList().stream()
            .filter(pawn -> Location.compare(this, pawn.getLocation()))
            .findFirst();
  }

  public static boolean compare(Location location1, Location location2) {
    return location1.getHorizontally() == location2.getHorizontally() &&
            location1.getVertically() == location2.getVertically() &&
            location1.getBoard().equals(location2.getBoard());
  }

  public static Location getLocationByString(String location, Board board) {
    if(location.length() < 2) return null;
    String firstLetter = location.substring(0, 1);
    String secondNumber = location.substring(1, 2);
    int horizontally = ALPHABET.indexOf(firstLetter) + 1;
    int vertically;

    try {
      vertically = Integer.parseInt(secondNumber);
    } catch(NumberFormatException e) {
      return null;
    }

    if(horizontally == -1) return null;

    return new Location(board, horizontally, vertically);
  }

}
