import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math;
public class Main{
    public static void main(String[] args)
    {  
        Frame frame = new Frame();
        frame.setSize(500,320);
        MyJPanel panel = new MyJPanel();
        JButton button = new JButton("add ball");
        button.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            panel.addBall();
          }
        });
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
    }
}