package se.chalmers.group7.core.tools;

public class MouseTracker {
  private boolean pointerDown = false;
  private int previousX = 0;
  private int previousY = 0;

  public void start(int x, int y) {
    pointerDown = true;
    previousX = x;
    previousY = y;
  }

  public void move(int x, int y) {
    if (pointerDown) {
      previousX = x;
      previousY = y;
    }
  }

  public void end() {
    pointerDown = false;
  }

  public boolean isPointerDown() {
    return pointerDown;
  }

  public int getPreviousX() {
    return previousX;
  }

  public int getPreviousY() {
    return previousY;
  }
}
