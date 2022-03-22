package slogo.View.Transitions.Paths;

import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

/**
 * The purpose of this class is to define a path in which the Turtle moves to a different location.
 * In other words, the location of the turtle has changed.
 *
 * Note: Please see full explanation for good design in Path.java
 *
 * This class demonstrates the "open to extension" part of the Single Responsibility Principle. As
 * can be seen, the abstract Path class has been extended so that more specific PathTransitions can
 * be made.
 *
 * GIT Commits:
 * - All commits authored by Cynthia France on March 5, 2022 that have "path" in it:
 *    - refactor pathTransitions
 *    - initial pathtransition refactoring
 *    - more specific pathTransitions
 *    - Path abstract class
 *
 * @author Cynthia France
 * @see PathTransition, Path
 */
public class MovementPath extends Path {
  public static int TURTLE_SPEED = 50; //pixels per second

  /**
   * Creates a PathTranstion object that defines the movement of a TurtleView as a result of a
   * user command that alters the location of the turtle. This PathTransition is one in which
   * the turtle actually and significantly moves from its old location to its new location.
   *
   * @param o             An Optional object that holds either the return value of a turtle command,
   *                      or null
   * @param oldModel      The last state of the TurtleModel, before the new command was run
   * @param myCurrModel   The current state of the TurtleModel, after new command has run
   * @param myView        The TurtleView object on which the PathTransition will be affecting
   * @param root          The root in which TurtleView lives in the window
   */
  public MovementPath(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView, Group root) {
    super(o, oldModel, myCurrModel, myView, root);
  }

  /*
  Creates the PathTransition from old turtle location to new turtle location
   */
  @Override
  protected PathTransition makePathTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView, Group root) {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[X]),
        convertY(oldModel.getNextPos()[Y]));
    javafx.scene.shape.Path path = new javafx.scene.shape.Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myCurrModel.getNextPos()[X]), convertY(myCurrModel.getNextPos()[Y])));
    Duration pathAnimDuration;
    pathAnimDuration = Duration.seconds(Math.abs((double) o.get()) / TURTLE_SPEED);
    PathTransition pathTrans = new PathTransition(pathAnimDuration, path, myView);
    setListener(pathTrans, myCurrModel, myView, root);
    return pathTrans;
  }

  /*
  Defines a listener for the PathTransition so that the Turtle's pen can draw its path as it moves
   */
  private void setListener(PathTransition pathTrans, TurtleModel myCurrModel, TurtleView myView, Group root) {
    pathTrans.currentTimeProperty().addListener( new ChangeListener<>() {

      double[] oldLocation = null;

      /**
       * Draw a line from the old location to the new location
       */
      @Override
      public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

        if (oldValue == Duration.ZERO)
          return;

        // get current location
        double x = myView.getBoundsInParent().getCenterX();
        double y = myView.getBoundsInParent().getCenterY();

        // initialize the location
        if (oldLocation == null) {
          oldLocation = new double[]{x, y};
          return;
        }

        // draw line
        if (myCurrModel.getTurtleRecord().isPenDown()) {
          root.getChildren().add(
              myView.getPen().draw(oldLocation[X], oldLocation[Y], x, y));
        }

        // update old location with current one
        oldLocation[X] = x;
        oldLocation[Y] = y;
      }
    });
  }
}
