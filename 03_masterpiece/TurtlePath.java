package slogo.View.Transitions.Paths;

import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

/**
 * The purpose of this class is to specify the exact movement of a Turtle as a result of a command
 * being run. TurtlePath utilizes the Path object to create said PathTransitions.
 *
 * Note: Please see full explanation for good design in Path.java
 *
 * This class and the refactor in general embodies the Single Responsibility Principle. The purpose
 * of this class is to quite literally figure out which type of PathTransition is needed. The responsibilties
 * that originally all belonged to another class (SketchbookView) has noe been sepatatd into single
 * responsibilties that make sense.
 *
 * GIT Commits:
 * - All commits authored by Cynthia France on March 5, 2022 that have "path" in it:
 *    - refactor pathTransitions
 *    - initial pathtransition refactoring
 *    - more specific pathTransitions
 *    - Path abstract class
 *
 * @author Cynthia France
 * @see PathTransition, Path, MovementPath, DoNothingPath
 */
public class TurtlePath {
  public static int X = 0;
  public static int Y = 1;

  PathTransition turtlePath;

  /**
   * Creates the proper PathTransition object for a TurtleView to follow based on the results of the
   * most recent command run. If no command was run, or the turtle didn't move, then the PathTransition
   * is nothing. Otherwise, TurtlePath defines the correct movement path for the Turtle.
   *
   * @param o             An Optional object that holds either the return value of a turtle command,
   *                      or null
   * @param oldModel      The last state of the TurtleModel, before the new command was run
   * @param myCurrModel   The current state of the TurtleModel, after new command has run
   * @param myView        The TurtleView object on which the PathTransition will be affecting
   * @param root          The root in which TurtleView lives in the window
   */
  public TurtlePath(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView, Group root) {
    turtlePath = makePathTransition(o, oldModel, myCurrModel, myView, root);
  }

  /**
   * Gives the user the PathTransition that the TurtleView will follow
   *
   * @return    The PathTransition of this TurtlePath object, the way in which the Turtle will move
   */
  public PathTransition getPathTransition() {
    return turtlePath;
  }

  /*
  gets the correct PathTransition based on if a command was run and the turtle's location changed (Movement)
  or the Turtle's location should not change (NoMovement)
   */
  private PathTransition makePathTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView, Group root) {

    if (o.isPresent() && moved(oldModel.getNextPos(), myCurrModel)) {
      return new MovementPath(o, oldModel, myCurrModel, myView, root).getPath();
    } else {
      return new DoNothingPath(o, oldModel, myCurrModel, myView, root).getPath();
    }
  }

  //checks if the turtle's location has changed as a result of a command
  private boolean moved(double[] nextPos, TurtleModel myCurrModel) {
    return (nextPos[X] != myCurrModel.getNextPos()[X] || nextPos[Y] != myCurrModel.getNextPos()[Y]);
  }
}
