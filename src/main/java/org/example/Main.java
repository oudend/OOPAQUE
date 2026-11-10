package org.example;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import com.raylib.Raylib.Color;
import com.raylib.Raylib.Image;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

public class Main {
    public static void main(String args[]) {
        int width = 160, height = 90;
        int scale = 10;
        InitWindow(width * scale, height * scale, "OOPaque");

        Color[][] canvas = new Color[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                canvas[i][j] = RAYWHITE;

        Color penColor = BLACK;

        ArrayList<Vector2> previewPositions = new ArrayList<>();

        Image img = GenImageColor(width * scale, height * scale, RAYWHITE);
        Texture h = LoadTextureFromImage(img);

        DrawFPS(20, 20);

        SetTargetFPS(60);
        while (!WindowShouldClose()) {
            BeginDrawing();

            ClearBackground(RAYWHITE);

            if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {

                previewPositions.add(GetMousePosition());

                if (previewPositions.size() > 1) {
                    ImageDrawLineEx(img, previewPositions.get(0), previewPositions.get(1), 20, penColor);
                    previewPositions.remove(0);
                }

            }

            if (IsMouseButtonUp(MOUSE_BUTTON_LEFT)) {
                if (previewPositions.size() > 1) {

                    for (int i = 1; i < previewPositions.size(); i++)
                        ImageDrawLineEx(img, previewPositions.get(i - 1), previewPositions.get(i), 20, penColor);
                }
                previewPositions.clear();
            }

            UpdateTexture(h, img.data());
            DrawTexture(h, 0, 0, WHITE);
            EndDrawing();

        }

        CloseWindow();
    }
}
