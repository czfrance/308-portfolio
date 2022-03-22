package slogo.View.Transitions.Paths;

import java.awt.Dimension;
import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

/**
 * The purpose of this class is to serve as a parent Path class, which is what a turtle follows
 * when moving in the window. Children of Path will define more specific ways in which a Turtle
 * can move, including staying still
 *
 * I think this is well designed as it abstracts PathTransitions into chunks that make sense for
 * the purpose of the project. By doing this, the Single Responsibility, Open-Closed, and
 * Dependency Inversion Principles are all met.
 *
 * The sole purpose of this class is to take care of turtle movement. This not only makes the purpose
 * of this class clearer, but also cleans up code nicely in SketchbookView, which is where this class
 * is being used. Path is a parent class that is closed to modification, but can be extended a number of
 * different ways, as I've demonstrated with the child classes. In this hierarchy, lower level
 * modules do not depend on higher level ones (only higher -> lower).
 *
 * GIT Commits:
 * - All commits authored by Cynthia France on March 5, 2022 that have "path" in it:
 *    - refactor pathTransitions
 *    - initial pathtransition refactoring
 *    - more specific pathTransitions
 *    - Path abstract class
 *
 * @author Cynthia France
 * @see PathTransition, MovementPath, DoNothingPath
 */
public abstract class Path {
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static int X = 0;
  public static int Y = 1;

  PathTransition path;

  /**
   * Creates a PathTranstion object that defines the movement of a TurtleView as a result of a
   * user command that alters the location of the turtle.
   *
   * @param o             An Optional object that holds either the return value of a turtle command,
   *                      or null
   * @param oldModel      The last state of the TurtleModel, before the new command was run
   * @param myCurrModel   The current state of the TurtleModel, after new command has run
   * @param myView        The TurtleView object on which the PathTransition will be affecting
   * @param root          The root in which TurtleView lives in the window
   */
  public Path(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView, Group root) {
    path = makePathTransition(o, oldModel, myCurrModel, myView, root);
  }

  /**
   * Gives the user the PathTransition that the Path object follows
   *
   * @return    The PathTransition of this Path object
   */
  public PathTransition getPath() {
    return path;
  }

  //Makes the PathTransition from old turtle location to new turtle location
  protected abstract PathTransition makePathTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView, Group root);

  /*
  converts the X coordinate from the project defined coordinates ( (0,0) in center) to javafx
  recognized coordinates ( (0,0) in top left corner)
   */
  protected double convertX(double modelX) {
    return modelX + (DEFAULT_SIZE.width / 2);
  }

  /*
  converts the X coordinate from the project defined coordinates ( (0,0) in center) to javafx
  recognized coordinates ( (0,0) in top left corner)
   */
  protected double convertY(double modelY) {
    return (DEFAULT_SIZE.height / 2) - modelY;
  }
}
