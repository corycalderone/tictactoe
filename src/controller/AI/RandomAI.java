package controller.AI;

import java.util.ArrayList;
import java.util.Random;

import model.Piece;
import model.TicTacToe;

public class RandomAI implements AI {

  public RandomAI() {}

  @Override
  public void play(TicTacToe model) {
    Piece[][] board = model.boardData();
    ArrayList<Posn> valids = new ArrayList<>();

    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board.length; c++)
      if (board[r][c].equals(Piece.Blank)) {
        valids.add(new Posn(r, c));
      }
    }
    Random rand = new Random(valids.size());
    Posn p = valids.get(rand.nextInt(valids.size()) + 1);

    model.addPiece(p.x, p.y);
  }
}
