package slogo.example;

import java.util.InputMismatchException;
import java.lang.IllegalArgumentException;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import util.DukeApplicationTest;


/**
 * Show TestFX driving GUI for testing.
 *
 * @author Robert C. Duvall
 */
public class AnimatedShapeTest extends DukeApplicationTest {
    public static final double MATCH_TOLERANCE = 0.01;

    // keep in case need to call application methods in tests
    private AnimatedShape myApp;
    // keep GUI components used in multiple tests
    private Rectangle myActor;


    // this method is run BEFORE EACH test to set up application in a fresh state
    @Override
    public void start (Stage stage) {
        // create app and add scene for testing to given stage
        myApp = new AnimatedShape();
        Scene scene = myApp.makeScene(400, 100);
        stage.setScene(scene);
        stage.show();

        // components that will be reused in different tests
        myActor = lookup("#actor").query();
    }


    @Test
    void testAnimation () {
        Assertions.assertEquals(100, myActor.getX(), MATCH_TOLERANCE);
        Assertions.assertEquals(50, myActor.getY(), MATCH_TOLERANCE);

        Animation animation = myApp.makeAnimation(myActor, 350, 50, 90);
        animation.play();
        sleep(4, TimeUnit.SECONDS);    // PAUSE: not typically recommended in tests

        Assertions.assertEquals(325, myActor.getX() + myActor.getTranslateX(), MATCH_TOLERANCE);
        Assertions.assertEquals(50, myActor.getY(), MATCH_TOLERANCE);
        Assertions.assertEquals(90, myActor.rotateProperty().get(), MATCH_TOLERANCE);
    }

    @Test
    void testSetResources () {
        Assertions.assertThrows(IllegalArgumentException.class, () -> myApp.setResources(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> myApp.setResources("  "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> myApp.setResources("DoesNotExist"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> myApp.setResources(AnimatedShape.CONFIGURATION_RESOURCE_PATH));
        Assertions.assertThrows(IllegalArgumentException.class, () -> myApp.setResources(AnimatedShape.CONFIGURATION_RESOURCE_PATH + "DoesNotExist"));
    }

    @Test
    void testResourceNumbers () {
        myApp.setResources(AnimatedShape.CONFIGURATION_RESOURCE_PATH + "Numbers");

        assertEquals(13, myApp.getResourceNumber("OK"));
        assertEquals(13, myApp.getResourceNumber("OKwithSpaces"));

        Assertions.assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Negative"));
        Assertions.assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Real"));
        Assertions.assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Word"));
        Assertions.assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Pre"));
        Assertions.assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Post"));
        Assertions.assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Mixed"));
    }

    @Test
    void testResourceColors () {
        myApp.setResources(AnimatedShape.CONFIGURATION_RESOURCE_PATH + "Colors");

        assertEquals(Color.GREEN, myApp.getResourceColor("OK"));
        assertEquals(Color.RED, myApp.getResourceColor("OKwithSpaces"));

        Assertions.assertThrows(InputMismatchException.class, () -> myApp.getResourceColor("DoesNotExist"));
        Exception e = Assertions.assertThrows(InputMismatchException.class, () -> myApp.getResourceColor("BadCase"));
        Assertions.assertEquals("Property BadCase is not a color: Green", e.getMessage());
    }
}
