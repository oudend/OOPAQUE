package se.chalmers.group7.core.tools;

public interface ResizableTool {
  ToolSettings getSettings();

  default void increaseSize(int amount) {
    getSettings().increaseRadius(amount);
  }

  default void decreaseSize(int amount) {
    getSettings().decreaseRadius(amount);
  }
}
