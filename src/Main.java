import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Main {
    public static void main(String[] args) {
        // Ustawienia okna gry
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(800, 600), "Arkanoid");
        window.setFramerateLimit(60);



        // Paletka
        RectangleShape paddle = new RectangleShape(new Vector2f(100, 20));
        paddle.setFillColor(Color.BLUE);
        paddle.setPosition(350, 550);

        // Piłka
        CircleShape ball = new CircleShape(10);
        ball.setFillColor(Color.RED);
        ball.setPosition(395, 300);
        float ballSpeedX = 4f, ballSpeedY = 4f;

        // Cegiełki
        RectangleShape[][] bricks = new RectangleShape[5][10];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                bricks[row][col] = new RectangleShape(new Vector2f(60, 20));
                bricks[row][col].setFillColor(Color.GREEN);
                bricks[row][col].setPosition(50 + col * 70, 50 + row * 30);
            }
        }

        // Pętla gry
        while (window.isOpen()) {
            // Obsługa zdarzeń
            for (Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }
            }

            // Ruch paletki
            if (Keyboard.isKeyPressed(Keyboard.Key.LEFT) && paddle.getPosition().x > 0) {
                paddle.move(-6, 0);
            }
            if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT) && paddle.getPosition().x < 700) {
                paddle.move(6, 0);
            }

            // Ruch piłki
            ball.move(ballSpeedX, ballSpeedY);

            // Odbicia piłki od ścian
            if (ball.getPosition().x <= 0 || ball.getPosition().x >= 780) {
                ballSpeedX = -ballSpeedX;
            }
            if (ball.getPosition().y <= 0) {
                ballSpeedY = -ballSpeedY;
            }

            // Odbicie piłki od paletki
            if (ball.getGlobalBounds().intersection(paddle.getGlobalBounds()) != null) {
                ballSpeedY = -ballSpeedY;
            }

            // Sprawdzenie kolizji z cegiełkami
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 10; col++) {
                    if (bricks[row][col] != null &&
                            ball.getGlobalBounds().intersection(bricks[row][col].getGlobalBounds()) != null) {
                        bricks[row][col] = null; // Usunięcie cegiełki
                        ballSpeedY = -ballSpeedY;
                        break;
                    }
                }
            }

            // Sprawdzenie przegranej
            if (ball.getPosition().y > 600) {
                System.out.println("Game Over!");
                window.close();
            }

            // Rysowanie
            window.clear(Color.BLACK);
            window.draw(paddle);
            window.draw(ball);
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 10; col++) {
                    if (bricks[row][col] != null) {
                        window.draw(bricks[row][col]);
                    }
                }
            }
            window.display();
        }
    }
}
