package tests;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import model.Model;
import shapes.*;
import java.awt.Color;
public class TestModel {
    Model model;
    @Before void initialiseModel(){
        model = new Model();
    }
    
    @Test public void testShape(){
       model.setCurrentShape("Rectangle"); 
       assert(true);
    }
    @Test public void testInvalidShape(){
       try{
            model.setCurrentShape("banana"); 
            model.draw(10, 10, 10, 10);
            assert(true);
        }catch(Exception e){
            assert(false);//do not want to crash at runtime
        }
    }
    @Test public void testColour(){
       model.setCurrentColour(Color.BLACK);
       assert(true); 
    }
    @Test public void testInvalidColour(){
       model.setCurrentColour(null);
       assert(false); 
    }
    @Test public void testTooManyUndos(){
        try{
                model.setCurrentShape("Line");
            for(int i = 0 ; i < 3 ; i ++){
                model.draw(i, 0, i+10, 5);
            }
            for(int i = 0; i < 4 ; i ++){
                model.undo();
            }
            assert(true);
        } catch (Exception e){
            assert(false);
        }
    }
    @Test public void testTooManyRedos(){
        try{
            model.setCurrentShape("Line");
            for(int i = 0 ; i < 3 ; i ++){
                model.draw(i, 0, i+10, 5);
            }
            for(int i = 0; i < 3 ; i ++){
                model.undo();
            }
            for(int i = 0; i < 4 ; i ++){
                model.redo();
            }
            assert(true);
        } catch (Exception e){
            assert(false);
        }      
}
}
