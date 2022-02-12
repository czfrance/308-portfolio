package cellsociety.xml;

import cellsociety.models.Grid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

/**
 * XMLSaver is the class that saves a new .xml file to data/ with certain set tags as well as
 * those passed in.
 * <p>
 * All information that the user does not specify is pulled from the preexisting simulation
 * information to ensure that the new configuration will work.
 *
 * I think this is good design because the XMl saver is it's own class. Bad design would've been to
 * have all xml-related responsibilties all in one file/class, but here, I separated out the XML
 * related responsibilities so that roles are more clear.
 *
 * It follows the Single Responsibility Principle as it only has one job-- to save the current
 * simulation configuration as a new file. It doesn't do anything else, such as loading a new file
 * or parsing another file's information.
 *
 * It also follows the Open-Closed Principle. This class is open to extension. For example, someone
 * could extend this class to write to another location, save using different tags, tag
 * hierarchy/structure etc. However, it is closed as it is used by other classes
 *
 * @author Cynthia France
 */
public class XMLSaver {

  /**
   * creates and saves a new .xml file with the specified tags and tag info based on the information
   * passed in
   *
   * There are certain required tags and a set structure that our xml files must be saved in.
   * For example, the root must be "data" with attribute "simulation"
   * Apart from that, the tags inside the root must have at lease the following:
   * - simulationType: the type of simulation being run
   * - title: the title of the simulation
   * - author: the author/created of this specific cell configuration
   * - description: a description of what the simulation does
   * - width: the width of our grid of cells
   * - height: the height of the grid of cells
   * - config: the cell grid configuration
   * - speed: the speed of animation/how fast a new generation is displayed
   * - neighborType: the type of neighbors this grid should have (ie finite, toroidal, etc)
   *
   * Any tags past that are simulation specific.
   *
   * If new tag content is not specified, the old simulation's configuration will be saved
   *
   * @param dataValues tag values from the original simulation this new configuration will be drawn from
   * @param dataFields the list of all xml tags used for simulations
   * @param grid       the grid of cells at their current state (state to be saved) of the simulation
   * @param saveInfo   information the user has entered via dialog boxes
   * @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
   * @throws TransformerException if an unrecoverable error occurs during the course of the transformation.
   */
  public void save(Map<String, String> dataValues, List<String> dataFields, Grid grid,
      Map<String, Optional> saveInfo) throws ParserConfigurationException, TransformerException {

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    /*
    ASSUMPTION: Dr. Duvall has stated in class that he accepts "magic values" that are only used once.
    In the following code, all values (strings) were only used once.
     */
    Document doc = docBuilder.newDocument();
    //add the root tag & set its attribute
    Element root = doc.createElement("data");
    doc.appendChild(root);
    root.setAttribute("simulation", dataValues.get("simulationType"));

    //create all tags & their content
    setTagData(dataValues, dataFields, grid, saveInfo, root, doc);
    //create & save the xml file
    writeToDocument(doc, saveInfo.get("filename").get().toString());
  }

  //creates all the tags for the xml file and assigns each tag its content
  private void setTagData(Map<String, String> dataValues, List<String> dataFields, Grid grid,
      Map<String, Optional> saveInfo, Element root, Document doc) {
    //go through all the existing tags
    for (String field : dataFields) {
      //create the tag
      Element tag = doc.createElement(field);

      if (field.equals("config")) {
        //if the tag is config, get the string of cell configurations
        String gridConfig = getGridInfo(grid);
        tag.setTextContent(gridConfig);
      } else if (dataValues.get(field).equals("")) {
        //if there is no content for a field, do not add a tag for it
        continue;
      } else if (saveInfo.containsKey(field) && (saveInfo.get(field).isPresent())) {
        //check if the tag's content was specified by the user
        tag.setTextContent(saveInfo.get(field).get().toString());
      } else {
        //if the tag's content was not specified from the user, draw it from the original simualation
        tag.setTextContent(dataValues.get(field));
      }
      //add the tag to root
      root.appendChild(tag);
    }
  }

  //writes the xml information compiled earilier to the desired location (data/) with the desired
  //file name (title) and correct extension (.xml) and
  //catches the TransformerException should it be thrown
  private void writeToDocument(Document doc, String title) {
    try (FileOutputStream output =
        //the new file's location
        new FileOutputStream("data/" + title + ".xml")) {
      //write the xml
      writeXml(doc, output);
    } catch (IOException | TransformerException e) {
      e.printStackTrace();
    }
  }

  //creates the grid of cell configurations to be added to the xml file from the Grid passed in
  private String getGridInfo(Grid grid) {
    String gridConfig = "";
    //loop through all cells in the simulation's cell grid
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.getRow(i).size(); j++) {
        //get the state of the cell
        int state = grid.getRow(i).get(j).getMyCurrentState();
        //turn the state (an int) into a String and write it to gridConfig
        gridConfig += Integer.toString(state);
      }
      //when a row has been written, end the line in a . and start a new line (special formatting)
      gridConfig += ".\n";
    }
    //return the string of cell states
    return gridConfig;
  }

  //writes the xml information compiled earlier to an xml file located at the specified location
  //also sets it to be written formatted (with indents)
  private static void writeXml(Document doc, OutputStream output)
      throws TransformerException {

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(output);

    transformer.transform(source, result);
  }
}
