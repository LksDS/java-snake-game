import java.awt.*;

public class SnakeHead extends BodyPart{
    private Snake snake;
    public SnakeHead(){
        super();
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x*scale,y*scale,scale,scale);
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
    }
    public void tick(){
        move();
        verifyFoodColliding();
        verifyLostConditions();
    }
    private void move(){
        x+= snake.getXUpdate();
        y+= snake.getYUpdate();
        snake.setLastDirection(snake.getMoveDirection());
    }
    public void verifyLostConditions(){
        verifyCollideSelf();
        verifyOutOfBounds();
    }
    public void verifyOutOfBounds(){
        if(x < 0 || x > GameScreen.getWidthTiles()||y<0||y>GameScreen.getHeightTiles()){
            snake.gameOver = true;
        }
    }
    public void verifyCollideSelf(){
            Vector headPosition = new Vector(x,y);
        for(BodyPart part: snake.body){
            Vector bodyPosition = new Vector(part.x,part.y);
            if (headPosition.isColliding(bodyPosition)) {
                snake.gameOver = true;
            }
        }
    }
    private void verifyFoodColliding(){
        Rectangle food = new Rectangle(Game.food.getX(),Game.food.getY(),1,1);
        Rectangle head = new Rectangle(snake.head.x,snake.head.y,1,1);
        if (head.intersects(food)){
            eatFood();
            Game.food.reallocateFood();
        }
    }
    private void eatFood(){
        Game.food.reallocateFood();
        snake.hasEaten();
    }
    public void setSnake(Snake snake){
        this.snake = snake;
    }
}
