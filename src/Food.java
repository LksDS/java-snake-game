import java.awt.*;
import java.util.Random;

public class Food {
    private int x;
    private int y;
    private final int scale = GameScreen.getScale();
    private Game game;

    public Food(){
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void reallocateFood(){
        boolean isFree = false;
        Random rand = new Random();
        while(!isFree){
            this.x = rand.nextInt(GameScreen.getWidthTiles());
            this.y = rand.nextInt(GameScreen.getHeightTiles());
            isFree = isFreePosition();
        }
        setPosition(x,y);
    }

    public boolean isFreePosition(){
        Vector food = new Vector(x,y);
        Snake snake = game.getSnake();
        for(BodyPart part: snake.body){
            Vector partPosition = new Vector(part.x,part.y);
            if (food.isColliding(partPosition)){
                return false;
            }
        }
        Vector headPosition = new Vector(snake.head.x,snake.head.y);
        return !food.isColliding(headPosition);
    }
    public void setGame(Game game){
        this.game = game;
    }
    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x*scale,y*scale,scale,scale);
    }
    public void tick(){
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}
