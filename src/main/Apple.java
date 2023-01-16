import java.util.Random;
import java.util.TimerTask;

public class Apple extends TimerTask {

    private int position_x;
    private int position_y;
    private Snake snake;

    public int getPosition_x() {
        return position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public Apple(Snake snake){
        this.snake = snake;
    }

    public Apple(){
        this.position_x = 25 * new Random().nextInt(20);
        this.position_y = 25 * new Random().nextInt(20);
    }
    public void run(){
        if(this.snake.getApple() == null){
            this.snake.setApple(new Apple());
        }
    }
}
