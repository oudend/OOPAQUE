package se.chalmers.group7.core.tools;

import se.chalmers.group7.core.Canvas;

public class Line implements Tool {
  private Canvas canvas;
  private int radius;
  private MouseTracker mouseTracker = new MouseTracker();

  private int endX;
  private int endY;

  public Line(Canvas canvas, int radius) {
    this.canvas = canvas;
    this.radius = radius;
  }

  @Override
  public void onPointerDown(int x, int y) {
    mouseTracker.start(x, y);
    endX = x;
    endY = y;
  }

  @Override
  public void onPointerMove(int x, int y) {
    if (!mouseTracker.isPointerDown()) return;
    endX = x;
    endY = y;
  }

  @Override
  public void onPointerUp() {
    if (!mouseTracker.isPointerDown()) return;

    int startX = mouseTracker.getPreviousX();
    int startY = mouseTracker.getPreviousY();

    canvas.drawCircle(startX, startY, radius);
    canvas.drawLine(startX, startY, endX, endY, radius * 2.0f);
    canvas.drawCircle(endX, endY, radius);

    mouseTracker.end();
  }

  @Override
  public void deactivate() {}
}
