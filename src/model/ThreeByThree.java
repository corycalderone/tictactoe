package model;

import java.util.ArrayList;

public class ThreeByThree implements TicTacToe {
  private Piece[][] board;
  private int pieceCount;
  Piece whoseTurn;

  public ThreeByThree() {
    this.board = new Piece[3][3];
    initBoard();
    this.whoseTurn = Piece.X;
    this.pieceCount = 0;
  }

  public ThreeByThree(Piece[][] board) {
    if (board.length == 3 && board[0].length == 3) {
      this.board = board;
    } else {
      throw new IllegalArgumentException("Invalid board size for this model. Expected 3, got " +
              board.length);
    }
    this.whoseTurn = Piece.O;
    this.pieceCount = 0;
  }

  private void initBoard() {
    for (int r = 0; r < this.board.length; r++) {
      for (int c = 0; c < this.board[0].length; c++) {
        this.board[r][c] = Piece.Blank;
      }
    }
  }

  @Override
  public void addPiece(int row, int col) {
    Piece p;
    if (this.board[row][col].equals(Piece.Blank)) {
      switch (whoseTurn) {
        case X:
          p = Piece.X;
          break;
        case O:
          p = Piece.O;
          break;
        default:
          throw new IllegalStateException("...what?");
      }
      this.board[row][col] = p;
      if (whoseTurn.equals(Piece.X)) {
        whoseTurn = Piece.O;
      } else {
        whoseTurn = Piece.X;
      }
    } else {
      throw new IllegalArgumentException("Space is taken.");
    }
  }

  public int getRemaining() {
    return this.board.length - this.pieceCount;
  }

  public boolean gameOver() {
    ArrayList<ArrayList<Piece>> rows = new ArrayList<>();

    // let's get all the horizontal rows...
    for (int r = 0; r < board.length; r++) {
      ArrayList<Piece> arr = new ArrayList<>();
      for (int c = 0; c < board.length; c++) {
        arr.add(board[r][c]);
      }
      rows.add(arr);
    }

    // ...and now the verticals...
    for (int c = 0; c < board[0].length; c++) {
      ArrayList<Piece> arr = new ArrayList<>();
      for (int r = 0; r < board.length; r++) {
        arr.add(board[r][c]);
      }
      rows.add(arr);
    }

    //...and the diagonals. *BOTH OF THEM*

    ArrayList<Piece> arr1 = new ArrayList<>();
    ArrayList<Piece> arr2 = new ArrayList<>();

    for (int i = 0; i < board[0].length; i++) {
      arr1.add(board[i][i]);
    }
    rows.add(arr1);

    for (int i = board[0].length - 1; i >= 0; i--) {
      arr2.add(board[i][i]);
    }

    rows.add(arr2);


    for (int i = 0; i < rows.size(); i++) {
      if (allEqual(rows.get(i))) {
        return true;
      }
    }
    return false;
  }

  private boolean allEqual(ArrayList<Piece> arr) {
    for (int i = 1; i < arr.size(); i++) {
      if (!arr.get(i).equals(arr.get(0))) {
        return false;
      }
    }
    return !arr.get(0).equals(Piece.Blank);
  }

  @Override
  public Piece[][] boardData() {
    return board;
  }

  @Override
  public String render() {
    StringBuilder display = new StringBuilder();
    for (int r = 0; r < this.board.length; r++) {
      for (int c = 0; c < this.board[0].length; c += 1) {
        switch (this.board[r][c]) {
          case X:
            display.append("X");
            if (c < board.length - 1) {
              display.append(" ");
            }
            break;
          case O:
            display.append("O");
            if (c < board.length - 1) {
              display.append(" ");
            }
            break;
          case Blank:
            display.append("_");
            if (c < board.length - 1) {
              display.append(" ");
            }
            break;
          default:
            throw new IllegalStateException("How did you get here?!");
        }
      }
      display.append("\n");
    }
    return display.toString();
  }
}