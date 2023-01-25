import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class MyJPanel extends JPanel{
    ArrayList<Ball> balls = new ArrayList<Ball>();
    int ballNum = 20;
    JLabel text = new JLabel(ballNum+" balls");
    JTextField fpsSetter = new JTextField(3);
    private double fps = 20;
    public MyJPanel(){
        text.setBackground(Color.WHITE);
        add(text);
        add(fpsSetter);
        for(int i=0;i<20;i++){
            balls.add(i,new Ball());
        }
        setBackground(Color.GRAY);
        fpsSetter.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            fps=Integer.valueOf(fpsSetter.getText());
          }
        });
    }
    public void addBall(){
      balls.add(new Ball(-10,-10,1,1));
      ballNum++;
      text.setText(ballNum+" balls");
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<balls.size();i++){
            balls.get(i).wallBounce();
            for(int i2=i+1;i2<balls.size();i2++){
                if(i2!=i){
                    Ball.collide(balls.get(i),balls.get(i2));
                }
            }
        }
        for(Ball ball:balls){
            ball.move();
            ball.draw(g);
        }
        try{
            Thread.sleep((int)(1000/fps));
        }catch(Exception e){}
        repaint();
    }
}