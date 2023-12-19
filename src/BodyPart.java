import java.awt.*;

public class BodyPart {
    protected int x, y,scale = GameScreen.getScale();
    public BodyPart(int x, int y){
        setPosition(x,y);
    }
    public BodyPart(){

    }
    public void render(Graphics g){
        g.fillRect(x*scale,y*scale,scale,scale);
        g.setColor(Color.GREEN);
        g.drawRect(x*scale,y*scale,scale-1,scale-1);
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
