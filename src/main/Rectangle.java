public class Rectangle {

    private int position_x;
    private int position_y;

    public static final int rec_width = 25;
    public static final int rec_height = 25;

    public Rectangle(int position_x, int position_y){
        this.position_x = position_x;
        this.position_y = position_y;
    }

    public boolean intersects(Rectangle r2){
        return this.position_x == r2.getPosition_x() && this.position_y == r2.getPosition_y();

    }
    public int getPosition_x() {
        return this.position_x; }

    public int getPosition_y() {
        return this.position_y;
    }
    public void setPosition_x(int increment){
        this.position_x = this.position_x + increment;
    }
    public void setPosition_y(int increment){
        this.position_y = this.position_y + increment;
    }
}
