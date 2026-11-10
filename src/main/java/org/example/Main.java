package org.example;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import com.raylib.Raylib;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Image;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

public class Main {

  public static void main(String args[]) {
    int screenWidth = 1600, screenHeight = 900;
//    int scale = 10;
    InitWindow(screenWidth, screenHeight, "OOPaque");

    int drawRadius = 10;

    Color selectedColor = BLACK;

    Color eraseColor = RAYWHITE; // ColorAlpha(BLACK, 0.0f);

    Color currentColor = selectedColor;

    Vector2 previousPosition = null;

    RenderTexture canvas = LoadRenderTexture(screenWidth, screenHeight);
//    SetTextureFilter(canvas, TEXTURE_FILTER_POINT);

    //    SetTargetFPS(60);
    while (!WindowShouldClose()) {
      if (IsKeyDown(KEY_LEFT_SHIFT)) {
        currentColor = eraseColor;
      } else {
        currentColor = selectedColor;
      }

      if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
        Vector2 currentPosition = GetMousePosition();

        if (previousPosition == null) previousPosition = currentPosition;

        if (Vector2Distance(currentPosition, previousPosition) < drawRadius / 2.0f) {
          BeginTextureMode(canvas);
            DrawCircleV(currentPosition, drawRadius, currentColor);
          EndTextureMode();
        } else {
          BeginTextureMode(canvas);
            DrawCircleV(previousPosition, drawRadius, currentColor);
            DrawCircleV(GetMousePosition(), drawRadius, currentColor);
            DrawLineEx(previousPosition, currentPosition, drawRadius * 2.0f, currentColor);
          EndTextureMode();
        }

        previousPosition = currentPosition;
      }

      if(IsMouseButtonUp(MOUSE_BUTTON_LEFT)) previousPosition = null;

      BeginDrawing();
      ClearBackground(RAYWHITE);
      Rectangle rect = new Rectangle().x(0.0f).y(0.0f).width((float)canvas.texture().width()).height((float)-canvas.texture().height());
      DrawTextureRec(canvas.texture(), rect, Vector2Zero(), WHITE);
//      DrawTexture(canvas.texture(), 0, 0, WHITE);
      DrawFPS(20, 20);
      EndDrawing();
    }

    UnloadRenderTexture(canvas);
    CloseWindow();
  }
}
