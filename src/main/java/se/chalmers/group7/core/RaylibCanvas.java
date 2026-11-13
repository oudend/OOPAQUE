package se.chalmers.group7.core;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

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

  @Override
  public void drawCircle(int x, int y, float radius) {
    BeginTextureMode(renderTexture);
    DrawCircleV(new Vector2().x(x).y(y), radius, BLACK);
    EndTextureMode();
  }

  @Override
  public void drawLine(int startX, int startY, int endX, int endY, float diameter) {
    BeginTextureMode(renderTexture);
    DrawLineEx(new Vector2().x(startX).y(startY), new Vector2().x(endX).y(endY), diameter, BLACK);
    EndTextureMode();
  }
}
