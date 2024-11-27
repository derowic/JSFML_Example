import org.jsfml.graphics.*;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

import java.util.List;

class Ball {
    private CircleShape shape;
    private Vector2f velocity = new Vector2f(4, -4);

    public Ball(float x, float y) {
        shape = new CircleShape(10);
        shape.setPosition(x, y);
        shape.setFillColor(Color.WHITE);
    }

    public void update(RenderWindow window, Paddle paddle, List<Brick> bricks) {
        // Ruch piłki
        shape.move(velocity);

        // Odbicie od krawędzi okna
        if (shape.getPosition().x <= 0 || shape.getPosition().x + shape.getRadius() * 2 >= 800) {
            velocity = new Vector2f(-velocity.x, velocity.y);
        }
        if (shape.getPosition().y <= 0) {
            velocity = new Vector2f(velocity.x, -velocity.y);
        }

        // Odbicie od paletki
        if (shape.getGlobalBounds().intersection(paddle.getBounds()) != null) {
            velocity = new Vector2f(velocity.x, -velocity.y);
        }

        // Odbicie od cegiełek
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (shape.getGlobalBounds().intersection(brick.getBounds()) != null) {
                bricks.remove(i);
                velocity = new Vector2f(velocity.x, -velocity.y);
                break;
            }
        }
    }

    public void draw(RenderWindow window) {
        window.draw(shape);
    }
}
