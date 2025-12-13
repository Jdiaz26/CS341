package diaz;

import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_B_GameObject extends GameObject {

    public Type_B_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.DOWN);

        imageList = new LinkedList<Icon>();
        imageList.add(new ImageIcon("images/Type_B_Up.png"));
        imageList.add(new ImageIcon("images/Type_B_Down.png"));
        imageList.add(new ImageIcon("images/Type_B_Left.png"));
        imageList.add(new ImageIcon("images/Type_B_Right.png"));
    }

    public void move(Canvas c) {
        Icon icon = getCurrentImage();
        int iconHeight = icon.getIconHeight();
        int iconWidth = icon.getIconWidth();
        int canvasHeight = (int) c.getSize().getHeight();
        int canvasWidth = (int) c.getSize().getWidth();

        if (getDirection() == Direction.DOWN) {
            setY(getY() + getVelocity());
            if (getY() + iconHeight >= canvasHeight) {
                setY(canvasHeight - iconHeight);
                setDirection(Direction.RIGHT);
            }
        }
        else if (getDirection() == Direction.RIGHT) {
            setX(getX() + getVelocity());
            if (getX() + iconWidth >= canvasWidth) {
                setX(canvasWidth - iconWidth);
                setDirection(Direction.UP);
            }
        }
        else if (getDirection() == Direction.UP) {
            setY(getY() - getVelocity());
            if (getY() <= 0) {
                setY(0);
                setDirection(Direction.LEFT);
            }
        }
        else if (getDirection() == Direction.LEFT) {
            setX(getX() - getVelocity());
            if (getX() <= 0) {
                setX(0);
                setDirection(Direction.DOWN);
            }
        }
    }

    public void setImage() {
        if (getDirection() == Direction.UP) currentImage = 0;
        else if (getDirection() == Direction.DOWN) currentImage = 1;
        else if (getDirection() == Direction.LEFT) currentImage = 2;
        else if (getDirection() == Direction.RIGHT) currentImage = 3;
    }
}




