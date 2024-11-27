import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

class Paddle {
    private RectangleShape shape;

    public Paddle(float x, float y) {
        shape = new RectangleShape(new Vector2f(100, 20));
        shape.setPosition(x, y);
        shape.setFillColor(Color.WHITE);
    }

    public void update(RenderWindow window) {
        if (Keyboard.isKeyPressed(Keyboard.Key.LEFT) && shape.getPosition().x > 0) {
            shape.move(-6, 0);
        }
        if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT) && shape.getPosition().x + shape.getSize().x < 800) {
            shape.move(6, 0);
        }
    }

    public void draw(RenderWindow window) {
        window.draw(shape);
    }

    public FloatRect getBounds() {
        return shape.getGlobalBounds();
    }
}
