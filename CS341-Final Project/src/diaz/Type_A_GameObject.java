package diaz;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_A_GameObject extends GameObject {

    public Type_A_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.DOWN);
        defaultDirection = Direction.DOWN;

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
    
    @Override
    public void userMove(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            setY(getY() - getVelocity());
            setDirection(Direction.UP);
            if (getY() < 0) setY(0);
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            setY(getY() + getVelocity());
            setDirection(Direction.DOWN);
            Icon icon = getCurrentImage();
            int canvasHeight = 800;
            if (getY() + icon.getIconHeight() > canvasHeight) {
                setY(canvasHeight - icon.getIconHeight());
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



