package delegate;

import javax.swing.JPanel;
import shapes.Shape;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
/*
class to draw each of the shapes on an observer update
*/
public class DrawableCanvas extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();
    public DrawableCanvas(){
        setBackground(Color.white);
    } 
    public void setShapes(ArrayList<Shape> shapes){
        this.shapes = shapes;
    }
/*
draws the given list of shapes according to their respective implementations
*/
    public void paint( Graphics graphics){
        super.paintComponent(graphics);//clears canvas of previous shapes
        for(Shape shape : shapes){
            shape.drawShape(graphics);//polymorphism!
        }
    }
}
