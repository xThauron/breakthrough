package pl.xthauron.breakthrough.player;

import pl.xthauron.breakthrough.board.Board;
import pl.xthauron.breakthrough.pawn.Location;
import pl.xthauron.breakthrough.pawn.Pawn;
import pl.xthauron.breakthrough.pawn.PawnColor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HumanPlayer implements Player {

  private final Board board;

  private final PawnColor pawnColor;

  public HumanPlayer(Board board, PawnColor pawnColor) {
    this.board = board;
    this.pawnColor = pawnColor;
  }

  @Override
  public void move() {
    System.out.println("WYBIERZ PIONEK!");

    Optional<Pawn> pawn = this.board.getPawnAtLocation(askForLocation());

    while((!pawn.isPresent()) || (pawn.get().getPawnColor() != pawnColor)) {
      System.out.println("BLEDNA LOKALIZACJA! SPROBUJ JESZCZE RAZ!");
      pawn = this.board.getPawnAtLocation(askForLocation());
    }

    System.out.println("WYBIESZ CEL!");
    Location destinationLocation = null;
    boolean goodLocation = false;
    while(!goodLocation) {
      destinationLocation = askForLocation();
      for(Location location : pawn.get().getAvailableMoves()) {
        if(Location.compare(location, destinationLocation)) {
          goodLocation = true;
          break;
        }
      }
      if(!goodLocation)
        System.out.println("NIE MOZESZ WYKONAC TAKIEGO RUCHU! PODAJ INNY CEL");
    }

    pawn.get().move(destinationLocation);
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

  private Location askForLocation() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("LOKACJA: ");

    Location location = Location.getLocationByString(scanner.nextLine(), this.board);

    while(location == null) {
      System.out.println("Blednie podana lokacja! Musi byc format literka + cyfra (np. A5)");
      System.out.println("Sproboj jeszcze raz!");
      System.out.println("LOKACJA: ");
      location = location.getLocationByString(scanner.nextLine(), this.board);
    }

    return location;
  }

}
