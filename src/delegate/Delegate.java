package delegate;
import model.Model;
import shapes.Shape;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
delegate is responsible for the user interface and making relevant calls to the model
*/

public class Delegate implements ActionListener, PropertyChangeListener {
    private JFrame jframe;
    private DrawableCanvas canvas;
    private JMenuBar buttonPanel;
    private JButton crossButton, ellipseButton, lineButton, rectangleButton, undoButton, redoButton;
    private JColorChooser colorChooser;
    private static final int DEFAULT_FRAME_WIDTH = 500;
    private static final int DEFAULT_FRAME_HEIGHT = 500;
    private Model model;
    private int xStart = -1;
    private int yStart = -1;
/*
initialise our UI with relevant decomposed method calls
*/
    public Delegate(Model model){
        jframe = new JFrame("Vector Drawing");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT); // set frame size
        jframe.setVisible(true); // display frame
        
        this.model = model;
        initialiseButtons();
        initialiaseColourChooser();
        initialiseCanvas();
        model.addListener(this);//register observer
        addCanvasActionListener(this);
        addButtonActionListener(this);
        addColourChooserActionListener(this);

    }
/*
adds our canvas to the main JFrame
*/
    private void initialiseCanvas(){
        this.canvas = new DrawableCanvas();
        jframe.getContentPane().add(this.canvas, BorderLayout.CENTER);
    }
/*
adds our buttons to a menu bar
*/
    private void initialiseButtons(){
       crossButton = new JButton("Cross");
       lineButton= new JButton("Line");
       rectangleButton = new JButton("Rectangle");
       ellipseButton = new JButton("Ellipse");
       undoButton = new JButton("Undo");
       redoButton = new JButton("Redo");

       buttonPanel = new JMenuBar();
       buttonPanel.add(crossButton);
       buttonPanel.add(lineButton);
       buttonPanel.add(ellipseButton);
       buttonPanel.add(rectangleButton);
       buttonPanel.add(undoButton);
       buttonPanel.add(redoButton);
       jframe.setJMenuBar(buttonPanel);
      
    }
/*
initialise our colour selector
*/
    private void initialiaseColourChooser(){
        colorChooser = new JColorChooser();
        colorChooser.setSize(DEFAULT_FRAME_WIDTH/10, DEFAULT_FRAME_HEIGHT/5);
        jframe.getContentPane().add(this.colorChooser, BorderLayout.WEST);
    }
/*
@param action listener
registers an actionlistener to the color selecter and calls to the model on colour changes
*/
    private void addColourChooserActionListener(ActionListener a){
    colorChooser.getSelectionModel().addChangeListener((ChangeListener) new ChangeListener() {
       @Override
       public void stateChanged(ChangeEvent e) {

          Color colour = colorChooser.getColor(); // get colour
          model.setCurrentColour(colour); // Set model queue
       }
    });
    }
/*
@param actionlistener
registers action listeners to our buttons
*/
    private void addButtonActionListener(ActionListener a){
        crossButton.addActionListener(a);
        lineButton.addActionListener(a);
        rectangleButton.addActionListener(a);
        ellipseButton.addActionListener(a);
        undoButton.addActionListener(a);
        redoButton.addActionListener(a);

    }
/*
registers an action listener to our canvas, sets the initial coordinates to a -1 flag after every shape is drawn so that they do not affect each other
draws the relevant shape based on the coordinates given by the mouse event
*/
    private void addCanvasActionListener(ActionListener a){

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e){
                if(xStart == -1 || yStart == -1){
                    xStart = e.getX();
                    yStart = e.getY();
                }               
            }
        });
        canvas.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                model.draw(xStart, yStart, e.getX(), e.getY());
                xStart = -1;
                yStart = -1;
            }
        });
    }
/*
every time the model fires a property change to observers, redraw the canvas to account for that update
*/
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        canvas.setShapes((ArrayList<Shape>) evt.getNewValue());
        canvas.repaint();
    }
/*
actionlistener behaviour for buttons, makes the relevant function call to the model for each button
*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == undoButton){
            model.undo();
        }
         else if(e.getSource() == redoButton){
            model.redo();
        }
        else if(e.getSource() == crossButton){
            model.setCurrentShape("Cross");
        }
        else if(e.getSource() == lineButton){
            model.setCurrentShape("Line");
        }
        else if(e.getSource() == rectangleButton){
            model.setCurrentShape("Rectangle");
        }
        else if(e.getSource() == ellipseButton){
            model.setCurrentShape("Ellipse");
        }
    } 
}
