import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static Rectangle.rec_height;
import static Rectangle.rec_width;

public class Snake extends JPanel {

    private static final Color c = new Color(23,195,34);
    private static final  int start = 250;
    private static final int speed = 25;

    private ArrayList<Rectangle> body;
    private String direction;
    private Apple apple;

    private Main window;

    public Snake(Main window) {
        this.window = window;
        this.body = new ArrayList<>();
        body.add(new Rectangle(start, start));
        Rectangle last = this.body.get(0);
        body.add(new Rectangle(last.getPosition_x() - rec_width, last.getPosition_y()));
        Rectangle last_2 = this.body.get(1);
        body.add(new Rectangle(last_2.getPosition_x() - rec_width, last_2.getPosition_y()));

        this.direction = "right";
    }

        public void setDirection(String direction){
            this.direction = direction;
        }
        public String getDirection(){
            return this.direction;
        }
        public void addPart(){
            Rectangle last = this.body.get(this.body.size()-1);
            switch (this.direction){
                case "right" -> this.body.add(new Rectangle(last.getPosition_x() - rec_width, last.getPosition_y()));
                case "left" -> this.body.add(new Rectangle(last.getPosition_x() + rec_width, last.getPosition_y()));
                case "up" -> this.body.add(new Rectangle(last.getPosition_x(), last.getPosition_y() + rec_width));
                case "down" -> this.body.add(new Rectangle(last.getPosition_x(), last.getPosition_y() - rec_width));
            }

        }

        public void checkCollision() {
            Rectangle r3 = this.body.get(0);
            for (int i = 1; i < this.body.size(); i++) {
                Rectangle r2 = this.body.get(i);

                if (r3.intersects(r2)) {
                    System.out.println("You lose!");
                    this.window.setVisible(false);
                    JFrame parent = new JFrame("Game over!");
                    JOptionPane.showMessageDialog(parent, "YOur score: " + this.body.size());

                    this.window.dispatchEvent(new WindowEvent(this.window, WindowEvent.WINDOW_CLOSED));
                    System.exit(0);
                }
            }
            if (this.apple != null) {
                if (r3.intersects(new Rectangle(this.apple.getPosition_x(), this.apple.getPosition_y()))) {
                    this.apple = null;
                    this.addPart();
                }

            }
        }
            public void moveSnake() {
                ArrayList<Rectangle> newLst = new ArrayList<>();

                Rectangle first = this.body.get(0);
                Rectangle head = new Rectangle(first.getPosition_x(), first.getPosition_y());

                switch (this.direction){
                    case "right" -> head.setPosition_x(speed);
                    case "left" -> head.setPosition_x(-speed);
                    case "up" -> head.setPosition_y(-speed);
                    case "down" -> head.setPosition_y(speed);
                }

                newLst.add(head);

                for (int i=1; i < this.body.size(); i++){
                    Rectangle previous = this.body.get(i-1);
                    Rectangle newRec = new Rectangle(previous.getPosition_x(), previous.getPosition_y());
                    newLst.add(newRec);
                }

                this.body = newLst;
                checkCollision();
            }

            private void drawSnake(Graphics g){
                moveSnake();

                Graphic2D g2d = (Graphic2D) g;

                if (this.apple != null) {
                    g2d.setPaint(Color.red);
                    g2d.drawRect(this.apple.getPosition_x(), this.apple.getPosition_y(), rec_width, rec_height);
                    g2d.fillRect(this.apple.getPosition_x(), this.apple.getPosition_y(), rec_width, rec_height);
                }
                g2d.setPaint(Color.blue);

                for (Rectangle rec : this.body) {
                    g2d.drawRect(rec.getPosition_x(), rec.getPosition_y(), rec_width, rec_height);
                    g2d.fillRect(rec.getPosition_x(), rec.getPosition_y(), rec_width, rec_height);
                }
            }
            public void setApple(Apple apple){
                this.apple = apple;
            }
            public Apple getApple() {
                return this.apple;
            }

                @Override
                public void paintComponent(Graphics g){
                    super.paintComponent(g);
                    setBackground(c);
                    drawSnake(g);
                }
            }
        }
