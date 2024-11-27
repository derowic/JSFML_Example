import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

import java.util.ArrayList;
import java.util.List;

public class Arkanoid {
    public static void main(String[] args) {
        // Utwórz okno
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(800, 600), "Arkanoid - JSFML");

        // Ustaw ograniczenie FPS
        window.setFramerateLimit(60);

        // Obiekty gry
        Paddle paddle = new Paddle(400, 550);
        Ball ball = new Ball(400, 300);
        List<Brick> bricks = createBricks();

        while (window.isOpen()) {
            // Obsługa zdarzeń
            for (Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }
            }

            // Ruch paletki
            paddle.update(window);

            // Ruch piłki
            ball.update(window, paddle, bricks);

            // Rysowanie
            window.clear(Color.BLACK);
            paddle.draw(window);
            ball.draw(window);
            for (Brick brick : bricks) {
                brick.draw(window);
            }
            window.display();
        }
    }

    private static List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                bricks.add(new Brick(80 * col + 5, 30 * row + 5));
            }
        }
        return bricks;
    }
}
