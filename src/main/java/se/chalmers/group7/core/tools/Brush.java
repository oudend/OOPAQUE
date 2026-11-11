package se.chalmers.group7.core.tools;

import se.chalmers.group7.core.Canvas;

public class Brush implements Tool {

  private Canvas canvas;
  private int radius;

  private Integer previousX;
  private Integer previousY;

  private boolean pointerDown = false;

  public Brush(Canvas canvas, int radius) {
    this.canvas = canvas;
    this.radius = radius;
  }

  @Override
  public void onPointerDown(int x, int y) {
    pointerDown = true;

    previousX = x;
    previousY = y;
  }

  @Override
  public void onPointerMove(int x, int y) {
    if (!pointerDown) return;

    canvas.drawCircle(x, y, radius);
    canvas.drawLine(previousX, previousY, x, y, radius * 2.0f);
    canvas.drawCircle(previousX, previousY, radius);

    previousX = x;
    previousY = y;
  }

  @Override
  public void onPointerUp() {
    pointerDown = false;

    previousX = null;
    previousY = null;
  }

  @Override
  public void deactivate() {
    // not really handle-able right now
  }
}
