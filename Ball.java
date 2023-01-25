import java.lang.Math;
import java.awt.*;
public class Ball{
    private double x,y,xSpeed,ySpeed;
    private static int nextID;
    private Color color;
    private int ID;
    private int lastBounce = -1;
    public Ball(double x,double y,double xSpeed,double ySpeed){
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        ID=nextID;
        nextID++;
        color = randomColor();
    }
    public Ball(){
        x=(Math.random()*480+10);
        y=(Math.random()*280+10);
        xSpeed=Math.random()*2-1;
        ySpeed=Math.random()*2-1;
        ID=nextID;
        nextID++;
        color = randomColor();
    }
    public int getX(){
        return (int) x;
    }
    public int getY(){
        return (int) y;
    }
    public void move(){
        x+=xSpeed;
        y+=ySpeed;
        ySpeed+=.00;
    }
    static void collide(Ball b1,Ball b2){//has 2 balls bounce off eachother if they are touching
        double xdis = b1.x-b2.x;
        double ydis = b1.y-b2.y;
        if(Math.abs(xdis)<20
                &&Math.abs(ydis)<20
                &&((b2.lastBounce!=b1.ID)||(b1.lastBounce!=b2.ID))
                &&Math.pow(xdis,2)+Math.pow(ydis,2)<400
                ){
            double xvel = b1.xSpeed-b2.xSpeed;
            double yvel = b1.ySpeed-b2.ySpeed;
            double velocity = Math.sqrt(Math.pow(xvel,2)+Math.pow(yvel,2));
            double touch = Math.atan2(ydis , xdis);
            double bounce = Math.cos(touch-Math.PI-(Math.atan2(yvel,xvel)));
            double distence = Math.sqrt(Math.pow(xdis,2)+Math.pow(ydis,2));
            b1.xSpeed+=bounce*velocity*xdis/distence;
            b2.xSpeed-=bounce*velocity*xdis/distence;
            b1.ySpeed+=bounce*velocity*ydis/distence;
            b2.ySpeed-=bounce*velocity*ydis/distence;
            b1.lastBounce=b2.ID;
            b2.lastBounce=b1.ID;
            //debug messages
            /*System.out.println("xvel "+xvel+"\nyvel "+yvel+"\ntouch"+touch+"\nbounce"+bounce);
            System.out.println("bounce degrees "+Math.atan2(yvel,xvel));
            System.out.println("ybounce - "+(bounce*velocity*ydis/distence));
            System.out.println("xbounce - "+(bounce*velocity*xdis/distence));*/

        }

    }
    public void wallBounce(){
        if (x<10){
            xSpeed=Math.abs(xSpeed);
            lastBounce=-1;
        }else if(x>490){
            xSpeed=Math.abs(xSpeed)*-1;
            lastBounce=-1;
        }
        if(y<10){
            ySpeed=Math.abs(ySpeed);
            lastBounce=-1;
        }else if(y>290){
            ySpeed=Math.abs(ySpeed)*-1;
            lastBounce=-1;
        }
    }
    private Color randomColor(){
        int high = (int)(Math.random()*3);
        int[] RGB = new int[3];
        RGB[high] = (int)(Math.random()*64+192);
        RGB[(high+1)%3] = (int)(Math.random()*256);
        RGB[(high+2)%3] = (int)(Math.random()*256);
        return new Color(RGB[0],RGB[1],RGB[2]);
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval((int)x-10,(int)y-10,20,20);
    }
}