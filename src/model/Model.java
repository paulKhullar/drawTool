package model;
import shapes.*;
import java.util.ArrayList;
import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
/*
Model component of the MD architecture, responsible for keeping track of shapes and colours
*/
public class Model {
    private String currentShape = null;
    private Color currentColour= null;
    //treat these arraylists as stacks, lifo
    private ArrayList<Shape> shapes = new ArrayList<>();    
    private ArrayList<Shape> undoneShapes = new ArrayList<>();
    private PropertyChangeSupport changeManager;  
/*
default intitial values when initialised
*/
    public Model(){
        this.changeManager = new PropertyChangeSupport(this); 
        setCurrentShape("Rectangle");
        setCurrentColour(Color.RED);
    }
/*
@param current shape
sets the current shape
*/
    public void setCurrentShape(String currentShape){
       this.currentShape = currentShape;
    }
 /*
 @param current colour
 sets the current colour
*/   
    public void setCurrentColour(Color currentColour){
        this.currentColour = currentColour;
    }
/*
fires a change in the arraylist of shapes to any registered observers as per the observer design pattern
*/
    public void change(ArrayList<Shape> updatedShapes){
        changeManager.firePropertyChange("shapes", shapes, updatedShapes);
        this.shapes = updatedShapes;
    }
/*
registers an observer to the model while remaining decoupled from any observers
*/
    public void addListener(PropertyChangeListener observer){
        changeManager.addPropertyChangeListener(observer);
    }
/*
removes the last shape from the data structure, caches it in case of a redo
*/
    public void undo(){
        ArrayList<Shape> updatedShapes = cloneShapes();
        try{
            undoneShapes.add(updatedShapes.get(updatedShapes.size()-1));
            updatedShapes.remove(updatedShapes.size()-1);
            change(updatedShapes);
        }catch(Exception e){
            return;
        }
    }
/*
removes the last shape from cache and places it back into the data structure
*/
    public void redo(){
        ArrayList<Shape> updatedShapes = cloneShapes();
        try{
            updatedShapes.add(undoneShapes.get(undoneShapes.size()-1));
            undoneShapes.remove(undoneShapes.get(undoneShapes.size()-1));
            change(updatedShapes);
        }catch(Exception e){
            return;
        }
    }
/*
draws the current shape and fires an update to listeners
@param xstart,xfinish,ystart,yfinish
*/
    public void draw(int xStart, int yStart, int xFinish, int yFinish){
        ArrayList<Shape> updatedShapes = cloneShapes();
        switch(currentShape){
            case "Line":
              updatedShapes.add(new DrawLine(xStart, yStart, xFinish, yFinish, currentColour));  
              break;
            case "Ellipse":
              updatedShapes.add(new DrawEllipse(xStart, yStart, xFinish, yFinish, currentColour));
              break;
            case "Rectangle":
              updatedShapes.add(new DrawRectangle(xStart, yStart, xFinish, yFinish, currentColour));
              break;
            case "Cross":
              updatedShapes.add(new DrawCross(xStart, yStart, xFinish, yFinish, currentColour));
              break;
            default:
                return;
        }
        change(updatedShapes);
    }
 /*
 convenience method to clone the datastructure so that it is not changed before a property is fired to observers 
*/   
    public ArrayList<Shape> cloneShapes(){
        return (ArrayList)shapes.clone();
    }
}
