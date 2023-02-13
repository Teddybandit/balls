import java.awt.event.*;
import java.awt.*;
public class Player {
    private int y = 100;
    private int x = 100;
    private boolean aPress,sPress,dPress,wPress;
    KeyAdapter list;
    public Player(){
        list = new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                //System.out.println(e.getKeyCode());
                switch(e.getKeyCode()){
                    case 65:
                        aPress = true;
                        //System.out.println("a pressed");
                        break;
                    case 83:
                        sPress = true;
                        //System.out.println("s pressed");
                        break;
                    case 68:
                        dPress = true;
                        //System.out.println("d pressed");
                        break;
                    case 87:
                        //System.out.println("w pressed");
                        wPress = true;
                        break;

                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                switch(e.getKeyCode()){
                    case 65:
                        //System.out.println("a released");
                        aPress = false;
                        break;
                    case 83:
                        //System.out.println("s released");
                        sPress = false;
                        break;
                    case 68:
                        //System.out.println("d released");
                        dPress = false;
                        break;
                    case 87:
                        //System.out.println("w released");
                        wPress = false;
                        break;
                }
            }
        };
    }
    public KeyListener getControlls(){
        return list;
    }
    public double getx(){
        return x;
    }
    public double gety(){return y;}
    public void move(){
        if(y>280)
            sPress=false;
        if(y<10)
            wPress=false;
        if(x>510)
            x=-10;
        else if(x<-10)
            x=510;
        if(aPress){
            x-=3;
        }
        if(sPress){
            y+=3;
        }
        if(dPress){
            x+=3;
        }
        if(wPress){
            y-=3;
        }
    }
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x-10,y-10,20,20);
    }

}
