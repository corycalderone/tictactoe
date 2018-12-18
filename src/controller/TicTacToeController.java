package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.AI.AI;
import model.TicTacToe;

public class TicTacToeController {
  private Readable rd; // NTS: Transmits input FROM USER TO PROGRAM
  private Appendable ap;
  private AI ai;

  public TicTacToeController(Readable rd, Appendable ap, AI ai) throws IllegalArgumentException {
    if (rd == null) {
      throw new IllegalArgumentException("Readable cannot be null!");
    }
    if (ap == null) {
      throw new IllegalArgumentException("Appendable cannot be null!");
    }
    this.rd = rd;
    this.ap = ap;
    this.ai = ai;
  }

  private void update(TicTacToe model) {
    transmit(model.render() + "\n");
  }

  private void transmit(String str) {
    try {
      ap.append(str);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  public void playGame(TicTacToe model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }
    update(model);
    Scanner sc = new Scanner(this.rd);
    while (!model.gameOver()) {
      if (!sc.hasNext()) {
        throw new IllegalStateException();
      }
      ArrayList<Integer> coords = new ArrayList<>();

      while (coords.size() < 2) {
        if (!sc.hasNext()) {
          throw new IllegalStateException();
        }
        String s = sc.next();
        if (s.equals("Q") || s.equals("q")) {
          quitGame(model);
          return;
        } else {
          try {
            int n = Integer.parseInt(s); // exception will be caught if not int
            if (n < 0 || n > model.boardData().length) { // check validity
              transmit("Oof. Try again with a different input, preferably an in-range one.\n");
            } else if (n == 0) {
              transmit("Non-zero numbers please.");
            } else {
              coords.add(n);
            }
          } catch (NumberFormatException e) {
            transmit("Oof. Try again, maybe with just numbers this time?\n");
          }
        }
      }
      try {
        model.addPiece(coords.get(0) - 1, coords.get(1) - 1);
        update(model);
        ai.play(model);
        Thread.sleep(1000);
        update(model);
      } catch (IllegalArgumentException | InterruptedException e) {
        transmit("Invalid move. Play again. Reason: " + e.getMessage() + "\n");
      }
    }
    endGame(model);
    return;
  }

  private void endGame(TicTacToe model) {
    transmit("I guess that'll do it!"
            + "\n" + model.render() + "\n");
  }

  /**
   * Provides the end-of-game state if the user's game ends by quitting, either by 'Q' or 'q'.
   *
   * @param model the model of the current Marble Solitaire game state
   */
  private void quitGame(TicTacToe model) {
    transmit("Game quit!" +
            "\n" + "State of game when quit:"
            + "\n" + model.render() + "\n");
  }
}