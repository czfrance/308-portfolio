package oogasalad.engine.view.ControlPanel.ControlButton;

/**
 * The purpose of this class is to create and define the characteristics of the save button, which
 * saves a game state to a user-chosen directory. It inherits from ControlButton
 *
 * Note: Please see full explanation for good design in ControlButton.java
 *
 * This class demonstrates the "open to extension" part of the Single Responsibility Principle. As
 * can be seen, the abstract ControlPanel class has been extended so that more specific panels can
 * be made.
 *
 * GIT Commits:
 * - All commits authored by Cynthia France that have "controlbutton" or "control button" in it:
 *    - make ControlButton super class with subclasses for all Control Panel Buttons
 *
 * @author Cynthia France
 * @see ControlButton, HomeButton, InfoButton, PauseButton, RestartButton, SettingsButton, UndoButton
 */
public class SaveButton extends ControlButton {

  /**
   *
   * creates and defines the characteristics of the save button, which saves a game state to a user-chosen directory.
   *
   * @param language user-specified language in which the UI is displayed in
   * @param setSave Runnable that defines how the save button behaves when clicked
   */
  public SaveButton(String language, Runnable setSave) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Save");
    createButton();
    setAction(setSave);
  }
}