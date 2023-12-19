import java.awt.*;
import java.util.ArrayList;

public class Snake {
    ArrayList<BodyPart> body = new ArrayList<>();
    SnakeHead head = new SnakeHead();
    private Direction moveDirection = Direction.RIGHT;
    private Direction lastDirection = Direction.RIGHT;
    private int yUpdate = 0, xUpdate = 1;
    boolean hasEaten = false;
    boolean gameOver = false;

    public Snake() {
        resetBodySize();
        resetPosition();
        head.setSnake(this);
    }


    public void render(Graphics g) {
        int i = 0;
        for (BodyPart part : body) {
            setBodyColorFade(i,g);
            part.render(g);
            i++;
        }
        head.render(g);
    }

    public void tick() {
        if (!gameOver) {
            if (!hasEaten) {
                body.removeFirst();
            } else {
                hasEaten = false;
            }
            body.add(new BodyPart(head.x, head.y));
            head.tick();
        }else{
            restartGame();
            Game.gameReset();
        }
    }
    public void restartGame(){
        resetBodySize();
        resetPosition();
        gameOver = false;
    }
    public void setBodyColorFade(int i,Graphics g){
        int size = body.size();
        int colorLimit = 255;
        int colorMin = 120;
        int greenAmount = colorMin+i*(colorLimit-colorMin)/size;
        g.setColor(new Color(0,greenAmount,0));
    }
    public void changeDirection(Direction dir){
        switch (dir){
            case Direction.UP:
                moveUp();
                break;
            case Direction.LEFT:
                moveLeft();
                break;
            case Direction.RIGHT:
                moveRight();
                break;
            case Direction.DOWN:
                moveDown();
                break;
        }
    }
    public void resetBodySize() {
        body.clear();
        for (int i = 0; i < 4; i++) {
            body.add(new BodyPart());
        }
    }

    public void resetPosition() {
        lastDirection = Direction.RIGHT;
        moveRight();
        for (int i = 0; i < 4; i++) {
            body.get(i).setPosition(i+1, 3);
        }
        head.setPosition(5, 3);
    }



    private void moveUp(){

        if(lastDirection != Direction.DOWN) {
            moveDirection = Direction.UP;
            xUpdate = 0;
            yUpdate = -1;
        }
    }
    private void moveDown(){

        if(lastDirection != Direction.UP) {
            moveDirection = Direction.DOWN;
            xUpdate = 0;
            yUpdate = 1;
        }
    }
    private void moveLeft(){
        if(lastDirection != Direction.RIGHT) {
            moveDirection = Direction.LEFT;
            xUpdate = -1;
            yUpdate = 0;
        }
    }
    private void moveRight(){

        if(lastDirection != Direction.LEFT) {
            moveDirection = Direction.RIGHT;
            xUpdate = 1;
            yUpdate = 0;
        }
    }
    public Direction getMoveDirection(){
        return this.moveDirection;
    }
    public int getYUpdate(){
        return this.yUpdate;
    }
    public int getXUpdate(){
        return this.xUpdate;
    }
    public void hasEaten(){
        hasEaten = true;
    }
    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }
}
