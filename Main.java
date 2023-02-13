import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math;
public class Main{
    public static void main(String[] args)
    {  
        Frame frame = new Frame();
        frame.setSize(500,320);
        Player player = new Player();
        MyJPanel panel = new MyJPanel(player);
        frame.addKeyListener(player.getControlls());
        JButton button = new JButton("add ball");
        button.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            panel.addBall();
          }
        });
        button.setBounds(50,0,150,15);
        panel.add(button);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(500,320));
        ImageIcon icon = new ImageIcon("pick.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        JLabel pick = new JLabel(icon);
        pick.setBounds(0,0,50,50);
        panel.add(pick);
        frame.add(panel);
        frame.setVisible(true);
    }
}