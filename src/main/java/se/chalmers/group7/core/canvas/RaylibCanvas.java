package se.chalmers.group7.core.canvas;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import static se.chalmers.group7.core.canvas.BlendingMode.NORMAL;

public class RaylibCanvas implements Canvas {
  RenderTexture renderTexture;

  private final int width;
  private final int height;

  public RaylibCanvas(int width, int height) {
    this.width = width;
    this.height = height;

    renderTexture = LoadRenderTexture(this.width, this.height);
  }

  public Texture getTexture() {
    return renderTexture.texture();
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  private static void applyBlendMode(BlendingMode blendingMode) {
    switch (blendingMode) {
      case NORMAL:
        BeginBlendMode(BLEND_ALPHA);
        break;
      case LINEAR_SUBTRACTIVE_ERASE:
        rlSetBlendFactorsSeparate(
            RL_ZERO, RL_ONE, // RGB: keep destination RGB unchanged
            RL_ONE, RL_ONE, // Alpha: 1 * dstAlpha - 1 * srcAlpha
            RL_FUNC_ADD, RL_FUNC_REVERSE_SUBTRACT);
        BeginBlendMode(BLEND_CUSTOM_SEPARATE);
        break;
    }
  }

  @Override
  public void drawCircle(int x, int y, float radius, Color color, BlendingMode blendingMode) {
    BeginTextureMode(renderTexture);
    applyBlendMode(blendingMode);
    DrawCircleV(new Vector2().x(x).y(y), radius, color);
    EndBlendMode();
    EndTextureMode();
  }

  @Override
  public void drawLine(
      int startX,
      int startY,
      int endX,
      int endY,
      float diameter,
      Color color,
      BlendingMode blendingMode) {
    BeginTextureMode(renderTexture);
    applyBlendMode(blendingMode);
    DrawLineEx(new Vector2().x(startX).y(startY), new Vector2().x(endX).y(endY), diameter, color);
    EndBlendMode();
    EndTextureMode();
  }
}
