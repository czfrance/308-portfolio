package slogo.View.Transitions.Paths;

import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

/**
 * The purpose of this class is to define a path in which the Turtle does not move to a different location.
 * In other words, the location of the turtle has stayed the same. This may be as a result of a
 * command that changes another property of the turtle, or because no command has been run, so the
 * turtle must stay still.
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
public class DoNothingPath extends Path {
  public static final double NO_MOVEMENT_TIME = 0.01; //pixels per second
  public static final double NO_MOVEMENT_DISTANCE = 0.01; //pixels per second

  /**
   * Creates a PathTranstion object that defines the movement of a TurtleView as a result of a
   * user command that alters the location of the turtle. This PathTransition is one in which
   * the turtle stays in place and does not move. This usually happens when the turtle command is to
   * change heading or other action that would not require a turtle's position to change.
   *
   * @param o             An Optional object that holds either the return value of a turtle command,
   *                      or null
   * @param oldModel      The last state of the TurtleModel, before the new command was run
   * @param myCurrModel   The current state of the TurtleModel, after new command has run
   * @param myView        The TurtleView object on which the PathTransition will be affecting
   * @param root          The root in which TurtleView lives in the window
   */
  public DoNothingPath(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView, Group root) {
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
        new LineTo(convertX(myCurrModel.getNextPos()[X]), convertY(myCurrModel.getNextPos()[Y] - NO_MOVEMENT_DISTANCE)),
        new LineTo(convertX(oldModel.getNextPos()[X]), convertY(oldModel.getNextPos()[Y])));

    return new PathTransition(Duration.seconds(NO_MOVEMENT_TIME), path, myView);
  }
}
