package tests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/*
main method which runs the junit tests
*/
public class TestMain {
         public static void main(String[] args){
            Result result = JUnitCore.runClasses(TestModel.class);
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
            System.out.println(result.wasSuccessful());
         }
}
