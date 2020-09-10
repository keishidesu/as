package me.samoa.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Triangle piece can move any number of steps diagonally. It CANNOT skip
 * over other pieces.The Plus piece moves in a straight line in ANY direction. It CANNOT skip over
 * other pieces. This class implements the combination of both
 */

public class TriPlusPiece extends Piece{

  public TriPlusPiece(Player player, int r, int c, boolean isPlus) {
    super(player, r, c);
    if (isPlus) {
      type = Type.Plus;
    } else {
      type = Type.Triangle;
    }
  }

  @Override
  public void onTurn(int turn) {
    if (turn % 2 == 0) {
      type = (type == Type.Plus) ? Type.Triangle : Type.Plus;
    }
  }

  @Override
  public void onMove(Slot slot) {}

  @Override
  public List<Slot> getAllPlaceableSlot() {
    // vertical down, vertical up, horizontal right, horizontal left :  southeast, northwest, southwest, northeast
    int[][] increments = (type == Type.Plus) ? new int[][]{ { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } } : new int[][]{ { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
    ArrayList<Slot> placeableSlots = new ArrayList<>();

    for (int[] increment : increments) {
      int row = this.getPositionR() + increment[0];
      int col = this.getPositionC() + increment[1];

      while (!(row >= getBoard().getBoardHeight() || col >= getBoard().getBoardWidth() || row < 0 || col < 0)) {
        Slot slot = getBoard().getSlot(row, col);

        if (!slot.isOccupied()) {
          placeableSlots.add(slot);
          row += increment[0];
          col += increment[1];
          continue;
        }
        if (slot.getOccupiedPiece().getPlayer().getOpponentTeam() == getPlayer().getTeam()) {
          placeableSlots.add(slot);
          break;
        }
        break;
      }
    }
    return placeableSlots;
  }
  
}