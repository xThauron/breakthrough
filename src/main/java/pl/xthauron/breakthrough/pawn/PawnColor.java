package pl.xthauron.breakthrough.pawn;

public enum PawnColor {

  WHITE("Bialy"),
  BLACK("Czarny");

  private final String textColor;

  PawnColor(String textColor) {
    this.textColor = textColor;
  }

  public String getTextColor() {
    return textColor;
  }

}
