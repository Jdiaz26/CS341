package diaz;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_C_GameObject extends GameObject {

    public Type_C_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.RIGHT);
        defaultDirection = Direction.RIGHT;

        imageList = new LinkedList<Icon>();
        imageList.add(new ImageIcon("images/Type_C_Left.png"));
        imageList.add(new ImageIcon("images/Type_C_Right.png"));
    }

    public void move(Canvas c) {
        Icon icon = getCurrentImage();
        int iconWidth = icon.getIconWidth();
        int canvasWidth = (int) c.getSize().getWidth();

        if (getDirection() == Direction.RIGHT) {
            setX(getX() + getVelocity());
            if (getX() + iconWidth > canvasWidth) setDirection(Direction.LEFT);
        } else {
            setX(getX() - getVelocity());
            if (getX() < 0) setDirection(Direction.RIGHT);
        }
    }
    
    @Override
    public void userMove(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            setDirection(Direction.LEFT);
            setX(getX() - getVelocity());
            if (getX() < 0) setX(0);
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            setDirection(Direction.RIGHT);
            setX(getX() + getVelocity());
        }
    }



    public void setImage() {
        if (getDirection() == Direction.LEFT) currentImage = 0;
        else currentImage = 1;
    }
}




