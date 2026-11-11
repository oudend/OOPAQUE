package se.chalmers.group7.core;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import se.chalmers.group7.core.tools.Brush;
import se.chalmers.group7.core.tools.Tool;

public class App {

  private int width;
  private int height;

  private Tool selectedTool;

  private RaylibCanvas selectedLayer;

  private boolean mouseDown = false;

  private Vector2 previousMousePosition = Vector2Zero();

  public App(int width, int height) {
    this.width = width;
    this.height = height;

    InitWindow(this.width, this.height, "Demo");
    SetTargetFPS(60);

    selectedLayer = new RaylibCanvas(this.width, this.height);

    selectedTool = new Brush(selectedLayer, 20);
  }

  public void run() {
    while (!WindowShouldClose()) {
      BeginDrawing();
      ClearBackground(RAYWHITE);

      // renders the RaylibCanvas
      Rectangle rect =
          new Rectangle()
              .x(0.0f)
              .y(0.0f)
              .width((float) selectedLayer.getWidth())
              .height((float) -selectedLayer.getHeight());
      DrawTextureRec(selectedLayer.getTexture(), rect, Vector2Zero(), WHITE);

      Vector2 mousePosition = GetMousePosition();

      if (IsMouseButtonDown(MOUSE_BUTTON_LEFT) && !mouseDown) {
        selectedTool.onPointerDown((int) mousePosition.x(), (int) mousePosition.y());
        mouseDown = true;
      }

      if (IsMouseButtonUp(MOUSE_BUTTON_LEFT) && mouseDown) {
        selectedTool.onPointerUp();
        mouseDown = false;
      }

      if (Vector2Distance(previousMousePosition, mousePosition) > 1) {
        previousMousePosition = mousePosition;
        selectedTool.onPointerMove((int) mousePosition.x(), (int) mousePosition.y());
      }

      EndDrawing();
    }
  }
}
