package shapes;

import java.lang.Math;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;  
import java.awt.Color;
import java.awt.geom.GeneralPath;
/*
class to draw diagonal cross, some of the behaviour isn't entirely correct
*/
public class DrawCross extends Shape {

   public DrawCross(int xStart, int yStart, int xFinish, int yFinish, Color colour){
       super(xStart,yStart,xFinish,yFinish,colour);
   }
/*
draws a diagonal cross by creating a path
*/
   public void drawShape(Graphics graphics){
       //adapted from: http://www.java2s.com/Code/Java/2D-Graphics-GUI/Createsadiagonalcrossshape.html
    Graphics2D g2d = (Graphics2D)graphics;
    final GeneralPath p0 = new GeneralPath();
    int widthx = Math.abs(xStart - xFinish);
    int widthy = Math.abs(yStart - yFinish);
    int l = widthy/5;
    int t = widthx/5;
    float SQRT2 = (float) Math.pow(2.0, 0.5);
    p0.moveTo(widthx-l - t, widthy-l + t);
    p0.lineTo(widthx-l + t,widthy -l - t);
    p0.lineTo(widthx,widthy -t * SQRT2);
    p0.lineTo(widthx + l - t,widthy -l - t);
    p0.lineTo(widthx +l + t,widthy -l + t);
    p0.lineTo(widthx + t * SQRT2, widthy);
    p0.lineTo(widthx + l + t,widthy + l - t);
    p0.lineTo(widthx + l - t, widthy+ l + t);
    p0.lineTo(widthx,widthy + t * SQRT2);
    p0.lineTo(widthx -l + t, widthy+ l + t);
    p0.lineTo(widthx -l - t, widthy + l - t);
    p0.lineTo(widthx-t * SQRT2, widthy);
    p0.closePath();
    g2d.setColor(colour);
    g2d.draw(p0);
   } 
}
