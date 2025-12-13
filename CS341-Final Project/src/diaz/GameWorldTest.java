package diaz;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.Dimension;

public class GameWorldTest {

    @Test
    public void testFactoryCreatesCorrectTypes() {
        assertTrue(GameObjectFactory.createGameObject("A", 0, 0) instanceof Type_A_GameObject);
        assertTrue(GameObjectFactory.createGameObject("B", 0, 0) instanceof Type_B_GameObject);
        assertTrue(GameObjectFactory.createGameObject("C", 0, 0) instanceof Type_C_GameObject);
        assertTrue(GameObjectFactory.createGameObject("D", 0, 0) instanceof Type_D_GameObject);
    }

    @Test
    public void testTypeAMovesDownThenUp() {
        Canvas c = new Canvas();
        c.setSize(new Dimension(200, 200));

        Type_A_GameObject a = new Type_A_GameObject(50, 0);
        a.setVelocity(10);
        a.setDirection(Direction.DOWN);

        a.move(c);
        assertEquals(10, a.getY());

        // Move to near bottom and verify it switches to UP
        a.setY(195);
        a.move(c);
        assertEquals(Direction.UP, a.getDirection());
    }

    @Test
    public void testTypeCMovesLeftAndRight() {
        Canvas c = new Canvas();
        c.setSize(new Dimension(200, 200));

        Type_C_GameObject obj = new Type_C_GameObject(190, 50);
        obj.setVelocity(10);
        obj.setDirection(Direction.RIGHT);

        obj.move(c);
        assertEquals(Direction.LEFT, obj.getDirection());
    }

    @Test
    public void testTypeDMovementAndBounds() {
        Canvas c = new Canvas();
        c.setSize(new Dimension(200, 200));

        Type_D_GameObject d = new Type_D_GameObject(100, 100);
        d.setVelocity(10);

        // Move UP
        d.setDirection(Direction.UP);
        d.move(c);
        assertEquals(90, d.getY());

        // Move LEFT
        d.setDirection(Direction.LEFT);
        d.move(c);
        assertEquals(90, d.getX());
    }
}

