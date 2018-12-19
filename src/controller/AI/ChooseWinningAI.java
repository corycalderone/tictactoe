package controller.AI;

import java.util.ArrayList;
import java.util.Random;

import model.Piece;
import model.ThreeByThree;
import model.TicTacToe;

public class ChooseWinningAI implements AI {


  @Override
  public void play(TicTacToe model) {
    Piece[][] board = model.boardData();
    ArrayList<Posn> valids = new ArrayList<>();
    boolean moveMade = false;

    // creates copy of board array to be used in mock model
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board.length; c++)
        if (board[r][c].equals(Piece.Blank)) {
          valids.add(new Posn(r, c));
        }
    }

    for (Posn p : valids) {
      Piece[][] boardCopy = new Piece[3][3];
      for (int i = 0; i < board.length; i +=1) {
        for (int j = 0; j < board[0].length; j += 1) {
          boardCopy[i][j] = board[i][j];
        }
      }
      if (!moveMade && makeProposedMove(p, boardCopy)) {
        model.addPiece(p.x, p.y);
        System.out.println("calculated.");
        moveMade = true;
      }
    }

    if (!moveMade) {
      Random rand = new Random(valids.size());
      Posn p = valids.get(rand.nextInt(valids.size()) + 1);
      model.addPiece(p.x, p.y);
      System.out.println("random");
    }
  }

  private boolean makeProposedMove(Posn p, Piece[][] propBoard) {
    TicTacToe prop = new ThreeByThree(propBoard);
    prop.addPiece(p.x, p.y);
    return prop.gameOver();
  }
}