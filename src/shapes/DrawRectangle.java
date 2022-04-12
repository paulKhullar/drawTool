package shapes;

import java.lang.Math;
import java.awt.Graphics;
import java.awt.Graphics2D;  
import java.awt.Color;
import java.awt.Rectangle;

public class DrawRectangle extends Shape {
    public DrawRectangle(int xStart, int yStart, int xFinish, int yFinish, Color colour){
       super(xStart,yStart,xFinish,yFinish,colour);
   }
/*
draws a graphics 2d rectangle
*/
   public void drawShape(Graphics graphics){
     Graphics2D graphics2D = (Graphics2D)graphics;
     graphics2D.setColor(colour);
     Rectangle rectangle = new Rectangle(xFinish,yFinish,Math.abs(xFinish-xStart),Math.abs(yFinish-yStart));
     graphics2D.draw(rectangle);
   }
}
