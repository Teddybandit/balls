import java.lang.Math;
import java.awt.*;
public class Ball{
    private double x,y,xSpeed,ySpeed;
    private static int nextID;//gives each ball a unique ID
    private Color color;
    private int ID;
    private int lastBounce = -1;//The ID of the last ball it hit
    //set to -1 when it hits a wall
    //cant bounce off the ball until it bounces of something else
    private int size;
    //constructors
    public Ball(double x,double y,double xSpeed,double ySpeed){
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        ID=nextID;
        nextID++;
        color = randomColor();
        size=(int)(Math.random()*11+10);//random size from 10-20
    }
    public Ball(){
        x=(Math.random()*480+10);
        y=(Math.random()*280+10);
        xSpeed=Math.random()*2-1;
        ySpeed=Math.random()*2-1;
        ID=nextID;
        nextID++;
        color = randomColor();
        size=(int)(Math.random()*11+10);
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
        ySpeed+=.01;//if you want to add gravity
    }
    static void collide(Ball b1,Ball b2){//has 2 balls bounce off eachother if they are touching
        double xdis = b1.x-b2.x;
        double ydis = b1.y-b2.y;
        int sizeTotal = b1.size+b2.size;
        //first two checks are redundant but conserve processing power
        if(Math.abs(xdis)<sizeTotal
                &&Math.abs(ydis)<sizeTotal
                &&((b2.lastBounce!=b1.ID)||(b1.lastBounce!=b2.ID))
                &&Math.pow(xdis,2)+Math.pow(ydis,2)<Math.pow(sizeTotal,2)
                ){
            double xvel = b1.xSpeed-b2.xSpeed;//if you want an explanation take physics
            double yvel = b1.ySpeed-b2.ySpeed;
            double velocity = Math.sqrt(Math.pow(xvel,2)+Math.pow(yvel,2));
            double touch = Math.atan2(ydis , xdis);
            double bounce = Math.cos(touch-Math.PI-(Math.atan2(yvel,xvel)));
            double distence = Math.sqrt(Math.pow(xdis,2)+Math.pow(ydis,2));
            b1.xSpeed+=bounce*velocity*xdis/distence;
            b2.xSpeed-=bounce*velocity*xdis/distence;
            b1.ySpeed+=bounce*velocity*ydis/distence;
            b2.ySpeed-=bounce*velocity*ydis/distence;
            b1.lastBounce=b2.ID;//ensures they dont bounce off eachother again
            b2.lastBounce=b1.ID;
            //debug messages
            /*System.out.println("xvel "+xvel+"\nyvel "+yvel+"\ntouch"+touch+"\nbounce"+bounce);
            System.out.println("bounce degrees "+Math.atan2(yvel,xvel));
            System.out.println("ybounce - "+(bounce*velocity*ydis/distence));
            System.out.println("xbounce - "+(bounce*velocity*xdis/distence));*/

        }

    }
    /*using absolute value instead of *=-1 ensures they dont get trapped outside the boundaries.
    lastBounce =-1 lets them bounce off anything
     */
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
    //generates a random color with one value above 192 to make it more vibrant
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
        g.fillOval((int)x-size,(int)y-size,2*size,2*size);
    }
}