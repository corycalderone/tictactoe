import java.io.InputStreamReader;

import controller.AI.AI;
import controller.AI.ChooseWinningAI;
import controller.TicTacToeController;
import model.ThreeByThree;
import model.TicTacToe;

public class Launcher {
  public static void main(String[] args) {
    TicTacToe t = new ThreeByThree();
    AI ai = new ChooseWinningAI();
    TicTacToeController c = new TicTacToeController(new InputStreamReader(System.in), System.out, ai);
    c.playGame(t);
  }
}
