public class Vector {
    int x, y;
    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }
    protected boolean isColliding(Vector v1){
        return v1.x == this.x && v1.y == this.y;
    }
}
