package diaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Canvas extends JComponent implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private Timer gameLoopTimer;
    private List<GameObject> gameObjectList;
    private int highlighted = 0;

    public Canvas() {
        gameObjectList = new LinkedList<GameObject>();

        frame = new JFrame("Animation Canvas");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        // GAME LOOP
        gameLoopTimer = new Timer(25, this);
        gameLoopTimer.start();

        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        // ADD ALL GAME OBJECTS USING THE FACTORY
        addDefaultGameObjects();

        frame.setVisible(true);
        
        // First object starts under user control
        gameObjectList.get(0).setUnderUserControl(true);

    }

    /**
     * Add characters to the world using your Factory
     */
    private void addDefaultGameObjects() {
        GameObject a = GameObjectFactory.createGameObject("A", 100, 100);
        GameObject b = GameObjectFactory.createGameObject("B", 300, 200);
        GameObject c = GameObjectFactory.createGameObject("C", 100, 400);
        GameObject d = GameObjectFactory.createGameObject("D", 400, 400);

        // Set default velocity for auto-moving objects
        a.setVelocity(5);
        b.setVelocity(5);
        c.setVelocity(5);
        d.setVelocity(5);

        addGameObject(a);
        addGameObject(b);
        addGameObject(c);
        addGameObject(d);
    }

    public synchronized void addGameObject(GameObject sprite) {
        gameObjectList.add(sprite);
    }

    /**
     * Drawing everything
     */
    public synchronized void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject obj = gameObjectList.get(i);
            obj.draw(this, g);

            if (i == highlighted) {
                drawHighlight(g, obj);
            }
        }
    }

    /**
     * Draw a red box around selected object
     */
    private void drawHighlight(Graphics g, GameObject obj) {
        int x = obj.getX();
        int y = obj.getY();
        int w = obj.getCurrentImage().getIconWidth();
        int h = obj.getCurrentImage().getIconHeight();

        g.setColor(Color.RED);
        g.drawRect(x - 2, y - 2, w + 4, h + 4);
    }

    /**
     * Game Loop
     */
    public synchronized void actionPerformed(ActionEvent e) {
        for (GameObject gameObject : gameObjectList) {
            gameObject.move(this);
            gameObject.setImage();
        }
        repaint();
    }

    // --------------------------------------------------------
    // KEY LISTENER
    // --------------------------------------------------------

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
    	GameObject selected = gameObjectList.get(highlighted);

    	if (selected.isUnderUserControl() && selected instanceof KeyListener) {
    	    ((KeyListener) selected).keyPressed(e);
    	}



    }

    public void keyReleased(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_TAB) {

    	    // Turn OFF user control for old highlighted object
    	    gameObjectList.get(highlighted).setUnderUserControl(false);

    	    // Move highlight
    	    highlighted++;
    	    if (highlighted >= gameObjectList.size()) {
    	        highlighted = 0;
    	    }

    	    // Turn ON user control for new highlighted object
    	    gameObjectList.get(highlighted).setUnderUserControl(true);
    	}


    	GameObject selected = gameObjectList.get(highlighted);

    	if (selected.isUnderUserControl() && selected instanceof KeyListener) {
    	    ((KeyListener) selected).keyReleased(e);
    	}
    }
}
