package se.chalmers.group7.core.tools;

import se.chalmers.group7.core.Canvas;

public class Brush implements Tool {

  private Canvas canvas;
  private int radius;
  private MouseTracker mouseTracker = new MouseTracker();

  public Brush(Canvas canvas, int radius) {
    this.canvas = canvas;
    this.radius = radius;
  }

  @Override
  public void onPointerDown(int x, int y) {
    mouseTracker.start(x, y);
  }

  @Override
  public void onPointerMove(int x, int y) {
    if (!mouseTracker.isPointerDown()) return;

    canvas.drawCircle(x, y, radius);
    canvas.drawLine(mouseTracker.getPreviousX(), mouseTracker.getPreviousY(), x, y, radius * 2.0f);
    canvas.drawCircle(mouseTracker.getPreviousX(), mouseTracker.getPreviousY(), radius);

    mouseTracker.move(x, y);
  }

  @Override
  public void onPointerUp() {
    mouseTracker.end();
  }

  @Override
  public void deactivate() {
    // not really handle-able right now
  }
}
