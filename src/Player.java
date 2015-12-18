import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Zhitnikov.Bronislav on 17.12.2015.
 */
public class Player extends GameObject {
    protected int targetX;
    protected int targetY;
    protected int speed=5;
    protected int fire=0;
    protected boolean isMoving=false;
    public Player(int x,int y,World world){
        super(x,y,world);
        this.targetX=x;
        this.targetY=y;
    }
    public void setTarget(int x,int y){
        targetX=x;
        targetY=y;
    }

    public Image getImage(){
     Image img= new BufferedImage(40,40,BufferedImage.TYPE_INT_ARGB);

        Graphics2D g= (Graphics2D) img.getGraphics();
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, 40, 40);
     return img;
    }
    public void tick(){
        isMoving=false;

        if (targetX!=x||targetY!=y){
            isMoving=true;
            double dx=targetX-x;
            double dy=targetY-y;
            double distance=Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
            double k=distance/speed;
            double mx=dx/k;
            double my=dy/k;
            if (Math.abs(dx)<Math.abs(mx)) mx=dx;
            if (Math.abs(dy)<Math.abs(my)) my=dy;
            x+=mx;
            y+=my;

        }
        for (GameObject o:world.getObjs()){
            int distance= (int) Math.sqrt(Math.pow(o.getX()-x,2)+Math.pow(o.getY()-y,2));
            if (distance<500){
                if (o instanceof Fire && distance<40){
                    fire++;
                    world.removeObject(o);
                }
            }
        }
        world.Redraw();
    }

}
