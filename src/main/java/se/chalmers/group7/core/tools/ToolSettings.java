package se.chalmers.group7.core.tools;

/**
 * Class for remembering tool-values across tools when switching. For example keeping the smae
 * radius when switching from brush to eraser.
 */
public class ToolSettings {
  private int radius = 15;

  public int getRadius() {
    return radius;
  }

  public void increaseRadius(int amount) {
    setRadius(this.radius + amount);
  }

  public void decreaseRadius(int amount) {
    setRadius(this.radius - amount);
  }

  private void setRadius(int radius) {
    this.radius = Math.max(1, radius);
  }
}
