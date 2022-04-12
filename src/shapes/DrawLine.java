package shapes; 

import java.awt.Graphics;
import java.awt.Color;

public class DrawLine extends Shape{
   public DrawLine(int xStart, int yStart, int xFinish, int yFinish, Color colour){
       super(xStart,yStart,xFinish,yFinish,colour);
   }
/*
draws a line
*/
   public void drawShape(Graphics graphics){
     graphics.setColor(colour);
     graphics.drawLine(xStart, yStart, xFinish, yFinish);
   } 
}
