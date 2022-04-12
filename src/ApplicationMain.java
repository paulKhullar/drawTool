import model.Model;
import delegate.Delegate;

public class ApplicationMain {
     public static void main(String[] args){
         Model m = new Model();
         Delegate d = new Delegate(m);
     }    
}
