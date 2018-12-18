package model;

public interface TicTacToe {
  void addPiece(int row, int col);

  boolean gameOver();

  String render();

  int getRemaining();

  Piece[][] boardData();
}
