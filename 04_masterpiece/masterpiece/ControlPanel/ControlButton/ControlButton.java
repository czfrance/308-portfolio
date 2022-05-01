package oogasalad.engine.view.ControlPanel.ControlButton;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oogasalad.engine.view.ControlPanel.GameControlPanel;

/**
 * Abstract class for control buttons that control specific game and application function (ie pause, save, etc)
 *
 * The purpose of this class is to serve as a parent ControlPanel class, which control specific game and application function
 * such as pause, save, etc. Children of ControlButton will contain more specific functions to control the game.
 *
 * I think this is well designed as it abstracts ControlButton into chunks that make sense for
 * the purpose of the project. By doing this, the Single Responsibility, Open-Closed, and
 * Dependency Inversion Principles are all met.
 *
 * The sole purpose of this class is to define general characteristics about a button in the control panel.
 * This not only makes the purpose of this class clearer, but also cleans up code nicely in ControlPanel,
 * which is where this class is being used. ControlButton is a parent class that is closed to modification,
 * but can be extended a number of different ways, as I've demonstrated with the child classes. In
 * this hierarchy, lower level modules do not depend on higher level ones (only higher -> lower).
 *
 * GIT Commits:
 * - All commits authored by Cynthia France that have "controlbutton" or "control button" in it:
 *    - make ControlButton super class with subclasses for all Control Panel Buttons
 *
 * @author Cynthia France
 * @see HomeButton, InfoButton, PauseButton, RestartButton, SaveButton, SettingsButton, UndoButton
 */
public abstract class ControlButton extends Button {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/resource-names/";
  public static final String DEFAULT_LANGUAGE_RESOURCE_PACKAGE = "/engine-view/languages/";
  public static ResourceBundle imBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Image");
  public static String IMAGES_FOLDER = "/engine-view/images/";
  public static int ICON_SIZE = 25;

  protected ResourceBundle myResources;
  protected String imagePath;

  /**
   *
   * creates the control button specified that will control an aspect of the game
   *
   * @param language user-specified language in which the UI is displayed in
   */
  public ControlButton(String language) {
    super();
    myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language);
  }

  //obtains the image and defines aspects fo the button
  protected void createButton() {
    ImageView buttonImage = getButtonImage(imagePath);
    this.setGraphic(buttonImage);
    this.setId("cp-button");
  }

  //obtains the image for the button
  private ImageView getButtonImage(String path) {
    ImageView buttonImage = new ImageView(new Image(path));
    buttonImage.setId("cp-button-image");
    buttonImage.setFitWidth(ICON_SIZE);
    buttonImage.setFitHeight(ICON_SIZE);
    return buttonImage;
  }

  //sets the behavior of the button
  protected void setAction(Runnable setButton) {
    this.setOnAction(e -> setButton.run());
  }
}