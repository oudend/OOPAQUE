package se.chalmers.group7.core.tools;

import se.chalmers.group7.core.Canvas;

public class Brush implements Tool, ResizableTool {

  private Canvas canvas;
  private ToolSettings settings;
  private MouseTracker mouseTracker = new MouseTracker();

  public Brush(Canvas canvas, ToolSettings settings) {
    this.canvas = canvas;
    this.settings = settings;
  }

  @Override
  public void onPointerDown(int x, int y) {
    mouseTracker.start(x, y);
  }

  @Override
  public void onPointerMove(int x, int y) {
    if (!mouseTracker.isPointerDown()) return;

    canvas.drawCircle(x, y, settings.getRadius());
    canvas.drawLine(mouseTracker.getPreviousX(), mouseTracker.getPreviousY(), x, y, settings.getRadius() * 2.0f);
    canvas.drawCircle(mouseTracker.getPreviousX(), mouseTracker.getPreviousY(), settings.getRadius());

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

  @Override
  public ToolSettings getSettings() {
    return settings;
  }
}
