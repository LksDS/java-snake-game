import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JFrame implements KeyListener {
    private static final int widthTiles = 16, heightTiles = 12, scale = 50;
    Game game;
    private static int width,height;
    public void setGame(Game game){
        this.game = game;
    }
    public GameScreen(){
        width = widthTiles *scale;
        height = heightTiles *scale;
        initScreen();
    }
    public static int getScale(){
        return scale;
    }
    public static int getWidthTiles(){
        return widthTiles;
    }
    public static int getHeightTiles(){
        return heightTiles;
    }
    public void initScreen(){
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("SnakeGame");
        getContentPane().setBackground(Color.black);
        setUndecorated(true);
        setVisible(true);
        addKeyListener(this);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                game.getSnake().changeDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_W:
                game.getSnake().changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_S:
                game.getSnake().changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_D:
                game.getSnake().changeDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
