import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class MyJPanel extends JPanel{
    ArrayList<Ball> balls = new ArrayList<Ball>();
    int ballNum = 20;
    JLabel text = new JLabel(ballNum+" balls");
    JTextField fpsSetter = new JTextField("set fps to()",10);
    private double fps = 20;
    Player player;
    public MyJPanel(Player p){
        player = p;
        text.setBounds(200,0,100,15);
        add(text);
        fpsSetter.setBounds(300,0,100,20);
        add(fpsSetter);
        for(int i=0;i<20;i++){
            balls.add(i,new Ball());
        }
        setBackground(Color.GRAY);
        fpsSetter.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              String s = fpsSetter.getText();
              try {
                  fps = Integer.valueOf(s.substring(s.indexOf('(') + 1,s.length()-1));
                fpsSetter.setBackground(Color.WHITE);
                fpsSetter.setText("set fps to()");
              }catch(Exception ex){
                fpsSetter.setBackground(Color.RED);
                fpsSetter.setText("set fps to()");
              }
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
        player.move();
        player.draw(g);
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