import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Zhitnikov.Bronislav on 17.12.2015.
 */
public class World {
    private ArrayList<GameObject> objs;
    public ArrayList<GameObject> getObjs(){ return objs;}
    private ArrayList<GameObject> objToRemove;
    private ArrayList<GameObject> objToAdd;
    private int width;
    private int height;
    private int tick=0;
    private Player player;
    private boolean needRedraw=true;
    public World(int width,int height){
        objs=new ArrayList<>();
        objToRemove=new ArrayList<>();
        objToAdd=new ArrayList<>();
        this.width=width;
        this.height=height;
        player = new Player(width/2,height/2,this);
        objs.add(player);
        getImage();
        objs.add(new FireProducer(500,750,this));
    }
    public void removeObject(GameObject obj){
        obj.setActive(false);
        objToRemove.add(obj);
    }
    public void addObject(GameObject obj){
        objToAdd.add(obj);
    }
    public void tick(){
        objs.removeAll(objToRemove);
        objToRemove.clear();
        objs.addAll(objToAdd);
        objToAdd.clear();
        tick++;

        for (GameObject o:objs){
            o.tick();
        }
    }
    private Image result;
    public Image getImage(){
        if (needRedraw || result==null) {
            result = new BufferedImage(500, 600, BufferedImage.TYPE_INT_ARGB);
            Graphics g = result.getGraphics();
            Image layerMob = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
            Image layerStruct = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
            Image layerEffect = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
            Graphics gMob = layerMob.getGraphics();
            Graphics gStruct = layerStruct.getGraphics();
            Graphics gEffect = layerEffect.getGraphics();
            int stX = player.getX() - 250;
            int stY = player.getY() - 250;
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 500, 500);
            for (GameObject o : objs) {
                Image objImg = o.getImage();
                if (o.getX() > stX && o.getX() < stX + 500 && o.getY() > stY && o.getY() < stY + 500)
                    if (o instanceof Player || o instanceof GameMob) {

                        gMob.drawImage(objImg, o.getX() - objImg.getWidth(null) / 2 - stX, o.getY() - objImg.getHeight(null) / 2 - stY, null);
                    } else if (o instanceof GameStructure) {
                        gStruct.drawImage(objImg, o.getX() - objImg.getWidth(null) / 2 - stX, o.getY() - objImg.getHeight(null) / 2 - stY, null);
                    } else if (o instanceof GameEffect) {
                        gEffect.drawImage(objImg, o.getX() - objImg.getWidth(null) / 2 - stX, o.getY() - objImg.getHeight(null) / 2 - stY, null);
                    } else {
                        g.drawImage(objImg, o.getX() - objImg.getWidth(null) / 2 - stX, o.getY() - objImg.getHeight(null) / 2 - stY, null);
                    }
            }
            g.drawImage(layerStruct, 0, 0, null);
            g.drawImage(layerMob, 0, 0, null);
            g.drawImage(layerEffect, 0, 0, null);
            g.drawImage(getButtonImage(), 0, 500, null);
            needRedraw=false;
        }
        return  result;
    };
    public void Redraw(){
        needRedraw=true;
    }
    public Image getButtonImage(){
        Image result=new BufferedImage(500,100,BufferedImage.TYPE_INT_ARGB);
        Graphics g=result.getGraphics();
        g.setColor(Color.RED);
        g.drawRect(0, 0, 100, 100);
        g.setColor(Color.CYAN);
        g.drawRect(101, 0, 200, 100);
        g.setColor(Color.BLUE);
        g.drawRect(201, 0, 300, 100);
        g.setColor(Color.GREEN);
        g.drawRect(301,0,400,100);
        g.setColor(Color.GRAY);
        g.drawRect(401,0,500,100);
        return result;
    }
    public void setMove(int x,int y){
        int targX=player.getX()-250+x;
        int targY=player.getY()-250+y;
        player.setTarget(targX, targY);

    }
    public void setClick(int x,int y){
        if (y<500) setMove(x,y);
        else if (x<100) {} //Установить Огонь
        else if (x<200) {} //Установить Воздух
        else if (x<300) {} //Установить Вода
        else if (x<400) {} //Установить Земля
        else {} //Смешать


    }

}
