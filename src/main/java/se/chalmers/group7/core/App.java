package se.chalmers.group7.core;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import se.chalmers.group7.core.canvas.RaylibCanvas;
import se.chalmers.group7.core.tools.Brush;
import se.chalmers.group7.core.tools.Eraser;
import se.chalmers.group7.core.tools.Tool;

public class App {

  private int width;
  private int height;

  private Tool selectedTool;

  private RaylibCanvas selectedLayer;

  private boolean mouseDown = false;

  private Vector2 previousMousePosition = Vector2Zero();

  private Texture transparencyPatternTexture;

  private final Tool[] tools;

  public App(int width, int height) {
    this.width = width;
    this.height = height;

    InitWindow(this.width, this.height, "Demo");
    SetTargetFPS(60);

    selectedLayer = new RaylibCanvas(this.width, this.height);

    tools = new Tool[] {new Brush(selectedLayer, 20), new Eraser(selectedLayer, 20)};

    selectedTool = tools[0];

    RenderTexture transparencyPattern = LoadRenderTexture(20, 20);

    BeginTextureMode(transparencyPattern);
    DrawRectangle(0, 0, 10, 10, GRAY);
    DrawRectangle(10, 10, 10, 10, GRAY);
    EndTextureMode();

    transparencyPatternTexture = transparencyPattern.texture();

    SetTextureWrap(transparencyPatternTexture, TEXTURE_WRAP_REPEAT);
  }

  public void run() {
    while (!WindowShouldClose()) {
      BeginDrawing();
      ClearBackground(RAYWHITE);

      DrawTexturePro(
          transparencyPatternTexture,
          new Rectangle().x(0).y(0).width(width / 20.0f * 15.0f).height(height / 20.0f * 15.0f),
          new Rectangle().x(0).y(0).width(width).height(height),
          new Vector2().x(0).y(0),
          0,
          WHITE);

      // renders the RaylibCanvas
      Rectangle rect =
          new Rectangle()
              .x(0.0f)
              .y(0.0f)
              .width((float) selectedLayer.getWidth())
              .height((float) -selectedLayer.getHeight());
      DrawTextureRec(selectedLayer.getTexture(), rect, Vector2Zero(), WHITE);

      Vector2 mousePosition = GetMousePosition();

      if (IsKeyDown(KEY_W)) {
        selectedTool = tools[1];
      } else if (IsKeyUp(KEY_W)) {
        selectedTool = tools[0];
      }

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
