package diaz;

import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_A_GameObject extends GameObject {

    public Type_A_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.DOWN);

        imageList = new LinkedList<Icon>();
        imageList.add(new ImageIcon("images/Type_A_Up.png"));
        imageList.add(new ImageIcon("images/Type_A_Down.png"));
    }

    public void move(Canvas c) {
        Icon icon = getCurrentImage();
        int iconHeight = icon.getIconHeight();
        int canvasHeight = (int) c.getSize().getHeight();

        if (getDirection() == Direction.DOWN) {
            setY(getY() + getVelocity());

            if (getY() + iconHeight > canvasHeight) {
                setDirection(Direction.UP);
            }
        } else {
            setY(getY() - getVelocity());
            if (getY() < 0) {
                setDirection(Direction.DOWN);
            }
        }
    }

    public void setImage() {
        if (getDirection() == Direction.UP) {
            currentImage = 0;
        } else {
            currentImage = 1;
        }
    }
}



