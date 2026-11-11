package se.chalmers.group7.core;

public interface Canvas {
  int getWidth();

  int getHeight();

  void drawCircle(int x, int y, float radius);

  void drawLine(int startX, int startY, int endX, int endY, float diameter);
  //    void drawImage();
  //    void drawTexture();
}
