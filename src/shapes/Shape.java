package shapes;
import java.awt.Color;
import java.awt.Graphics;
/*
shape abstract class with some of the baseline shape behaviour
*/
public abstract class Shape {
  int xStart;
  int yStart;
  int xFinish;
  int yFinish; 
  Color colour;
/*
constructor to initialise coordinates and colour
*/
  public Shape(int xStart, int yStart, int xFinish, int yFinish, Color colour){
    this.xStart = xStart;
    this.yStart = yStart;
    this.xFinish = xFinish;
    this.yFinish = yFinish;
    this.colour = colour;
  }
/*
abstract draw method that all shapes must implement respectively
*/
   public abstract void drawShape(Graphics graphics);
}
