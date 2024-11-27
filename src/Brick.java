import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

class Brick {
    private RectangleShape shape;

    public Brick(float x, float y) {
        shape = new RectangleShape(new Vector2f(75, 20));
        shape.setPosition(x, y);
        shape.setFillColor(Color.RED);
    }

    public void draw(RenderWindow window) {
        window.draw(shape);
    }

    public FloatRect getBounds() {
        return shape.getGlobalBounds();
    }
}
