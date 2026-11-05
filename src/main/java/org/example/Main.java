package org.example;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class Main {
    public static void main(String args[]) {
        int width = 80, height = 45;
        int scale = 10;
        InitWindow(width * scale, height * scale, "OOPaque");

        Color[][] canvas = new Color[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                canvas[i][j] = RAYWHITE;

        Color penColor = BLACK;

        SetTargetFPS(60);
        while (!WindowShouldClose()) {
            BeginDrawing();
            ClearBackground(RAYWHITE);
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++) {
                    DrawRectangle(i * scale, j * scale, scale, scale, canvas[i][j]);
                    System.out.println(canvas[i][j]);
                }

            if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
                int xPos = Math.max(Math.min(GetMouseX() / scale, width), 0);
                int yPos = Math.max(Math.min(GetMouseY() / scale, height), 0);
                canvas[xPos][yPos] = penColor;
            }

            if (IsKeyDown(KEY_SPACE))
                for (int i = 0; i < width; i++)
                    for (int j = 0; j < height; j++)
                        canvas[i][j] = RAYWHITE;
            DrawFPS(20, 20);
            EndDrawing();
        }
        CloseWindow();
    }
}