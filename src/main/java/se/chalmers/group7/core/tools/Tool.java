package se.chalmers.group7.core.tools;

public interface Tool {
  void onPointerDown(int x, int y);

  void onPointerMove(int x, int y);

  void onPointerUp();

  void deactivate();
}
