package shapes;

import java.lang.Math;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;  
import java.awt.Color;

public class DrawEllipse extends Shape{
   public DrawEllipse(int xStart, int yStart, int xFinish, int yFinish, Color colour){
       super(xStart,yStart,xFinish,yFinish,colour);
   }
/*
draws a grahpics 2d ellipse
*/
   public void drawShape(Graphics graphics){
     Graphics2D graphics2D = (Graphics2D)graphics;
     graphics2D.setColor(colour);
     Ellipse2D ellipse = new Ellipse2D.Double(xFinish,yFinish,Math.abs(xFinish-xStart),Math.abs(yFinish-yStart));
     graphics2D.draw(ellipse);
   }
} 