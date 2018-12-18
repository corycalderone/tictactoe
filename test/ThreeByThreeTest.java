import org.junit.Test;

import model.ThreeByThree;
import model.TicTacToe;

import static org.junit.Assert.assertEquals;

public class ThreeByThreeTest {
  TicTacToe t = new ThreeByThree();

  @Test
  public void testSetup() {
    assertEquals("", t.render());
  }
}
