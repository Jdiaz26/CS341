package diaz;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_D_GameObject extends GameObject {

  public Type_D_GameObject(int x, int y) {
    super(x, y);
    setDirection(Direction.NONE);

    imageList = new LinkedList<Icon>();
    imageList.add(new ImageIcon("images/Type_D_Up.png"));
    imageList.add(new ImageIcon("images/Type_D_Down.png"));
    imageList.add(new ImageIcon("images/Type_D_Left.png"));
    imageList.add(new ImageIcon("images/Type_D_Right.png"));
  }

  // MOVE METHOD
  public void move(Canvas c) {
    Icon icon = getCurrentImage();

    int iconHeight = icon.getIconHeight();
    int iconWidth = icon.getIconWidth();
    int canvasHeight = (int) c.getSize().getHeight();
    int canvasWidth = (int) c.getSize().getWidth();

    switch (getDirection()) {
      case Direction.UP:
        setY(getY() - getVelocity());
        if (getY() < 0) setY(0);
        break;

      case Direction.DOWN:
        setY(getY() + getVelocity());
        if (getY() + iconHeight > canvasHeight)
          setY(canvasHeight - iconHeight);
        break;

      case Direction.LEFT:
        setX(getX() - getVelocity());
        if (getX() < 0) setX(0);
        break;

      case Direction.RIGHT:
        setX(getX() + getVelocity());
        if (getX() + iconWidth > canvasWidth)
          setX(canvasWidth - iconWidth);
        break;

      default:
        break;
    }
  }

  // THIS REPLACES keyPressed()
  @Override
  public void userMove(int keyCode) {
    if (keyCode == KeyEvent.VK_UP) {
      setDirection(Direction.UP);
    }
    else if (keyCode == KeyEvent.VK_DOWN) {
      setDirection(Direction.DOWN);
    }
    else if (keyCode == KeyEvent.VK_LEFT) {
      setDirection(Direction.LEFT);
    }
    else if (keyCode == KeyEvent.VK_RIGHT) {
      setDirection(Direction.RIGHT);
    }
  }

  //IMAGE LOGIC
  public void setImage() {
    switch (getDirection()) {
      case Direction.UP:    currentImage = 0; break;
      case Direction.DOWN:  currentImage = 1; break;
      case Direction.LEFT:  currentImage = 3; break;
      case Direction.RIGHT: currentImage = 2; break;
      default: break;
    }
  }
}


