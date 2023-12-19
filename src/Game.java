import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    Thread gameThread;
    GameScreen screen;
    private final Snake snake;
    static Food food;
    public Game(){
        screen = new GameScreen();
        screen.setGame(this);
        snake = new Snake();
        food = new Food();
        food.setGame(this);
        food.reallocateFood();
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ticksPerSecond = 5.0;
        double ns = 1000000000 / ticksPerSecond;
        double delta = 0;
        double timer = System.currentTimeMillis();
        screen.requestFocus();
        while(gameThread!=null) {
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                tick();
                render();
                delta--;

            }
            if(System.currentTimeMillis() - timer >= 1000) {
                timer+=1000;
            }
        }
    }

    public void tick(){
        snake.tick();
        food.tick();
    }

    public void render(){
        if(screen.getBufferStrategy() == null)
            screen.createBufferStrategy(3);
        BufferStrategy bs = screen.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0, screen.getWidth(),screen.getHeight());
        snake.render(g);
        food.render(g);
        g.dispose();
        bs.show();
    }

    public static void gameReset(){
        food.reallocateFood();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public Snake getSnake(){
        return this.snake;
    }
}
