package se.chalmers.group7.core.canvas;

import com.raylib.Raylib;

public interface Canvas {
  int getWidth();

  int getHeight();

  void drawCircle(int x, int y, float radius, Raylib.Color color, BlendingMode blendingMode);

  void drawLine(
      int startX,
      int startY,
      int endX,
      int endY,
      float diameter,
      Raylib.Color color,
      BlendingMode blendingMode);
  //    void drawImage();
  //    void drawTexture();
}
