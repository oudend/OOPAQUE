package se.chalmers.group7.core.tools;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.ColorAlpha;

import com.raylib.Raylib;
import se.chalmers.group7.core.canvas.BlendingMode;
import se.chalmers.group7.core.canvas.Canvas;

public class Eraser implements Tool {
  private Canvas canvas;
  private int radius;

  private Integer previousX;
  private Integer previousY;

  private boolean pointerDown = false;

  private float opacity = 1.0f;

  public Eraser(Canvas canvas, int radius) {
    this.canvas = canvas;
    this.radius = radius;
  }

  public Raylib.Color getColor() {
    return ColorAlpha(WHITE, opacity);
  }

  @Override
  public void onPointerDown(int x, int y) {
    pointerDown = true;

    canvas.drawCircle(x, y, radius, getColor(), BlendingMode.LINEAR_SUBTRACTIVE_ERASE);

    previousX = x;
    previousY = y;
  }

  @Override
  public void onPointerMove(int x, int y) {
    if (!pointerDown) return;

    canvas.drawCircle(x, y, radius, getColor(), BlendingMode.LINEAR_SUBTRACTIVE_ERASE);
    canvas.drawLine(
        previousX,
        previousY,
        x,
        y,
        radius * 2.0f,
        getColor(),
        BlendingMode.LINEAR_SUBTRACTIVE_ERASE);
    canvas.drawCircle(
        previousX, previousY, radius, getColor(), BlendingMode.LINEAR_SUBTRACTIVE_ERASE);

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
