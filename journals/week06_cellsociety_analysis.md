# Journal : Cell Society Project Analysis
### Cynthia France
### 2/11/2022
### TEAM 03


## General Feelings

* Unfortunately, my teammates (and one in particular) were not very proactive in this project.
  I felt a genera air of uncaring for the project as she didn't make any effort to contribute/code/do her part
  until asked. For the majority of the project, she was not even aware of the specifications sheet
  even though I had showed it to her multiple times.
* As a result, she unsurprisingly left a lot of basic features (the majority--none of the buttons 
  even worked/did anything) uncompleted/not started. Thus, I had to spend the majority of
  complete doing her work, which severely impacted my design (or lack thereof), in addition to
  impacting my contribution to the project in terms of other responsibilities/new features.
* I am extremely frustrated and disappointed at this outcome. I feel that if I didn't have to 
  essentially do the work of 2-2.25 members' work, my design would have been infinitely better and I
  would have been MUCH less stressed and pull MUCH less (aka no) all-nighters to make sure our 
  project was even completed on the basic level.
* I basically worked on/touch every aspect of this project and could confidently explain each 
  part of the project, what it does, and how it works with everything else in detail, which 
  shouldn't be the case. Had everyone done their work, I would have only known the details of my
  part of the project, and only generally known the function of my teammates' parts.


## Overall design

### Complete Project
* In general, the subclasses of each major super class had good design. The classes were small and 
  to the point and didn't dabble in things that did not concern itself

Reviewing code that's not mine
* `SimulationView` got very out of hand, long, and had bad design. It could've done with A LOT of 
  refactoring, such as having classes that created each feature/element of the GUI.
* `Cell` can also be refactored a lot. If it were me, I'd create new classes for everything
  that dealt with neighbors and different cell shapes. 
  * Ideally, there would be a class for each shape (rectangle, triangle, hexagon) of cell, and each
    would have extensions that took care of the multiple edge types (finite, toroidal). However
    right now everything is lumped together in the general Cell class, where it does not belong.

#### Adding new Automata
* New Simulations
    * Create as many new extensions of `Cell` as needed for the simulation & code their logic (ie how to
      determine their next state, if they die or live, if they move, etc)
    * In the model, specify how cells are created & generally updated, if there are any specific overall
      logic that needs to take place (ie cells moving, being created, etc.)
      * add all necessary tag additions to `DATA_FIELDS`
    * In the view, specify how a grid must be updated & maintained (if different from default code)
    * In `viewCell`, ensure that each cell can be visually updated to the correct state (ie color)
    * In the .xml files, add in any new tags that are necessary for the new simulation

#### Dependencies

##### Simulation

* Overall dependencies (classes, behavior, exceptions, data, or resources)
  * Simulation (Model) depends on Cell (class), Grid (class) and XML (specifically XMLParser class). 

* Good Example: Cell
  * This dependency is very much warranted. Since the grid is managed by the model, which is made
    up of cells, SimulationModel naturally depends on the Cell class to do its tasks so that it can 
    focus on taking care of the bigger picture. 
    * Cell in responsible for itself (one cell) while SimulationModel is responsible for the entire
      grid of Cells.
  * This dependency is made very clear. Not only is the word "cell" present in some function names,
    pretty much all functions performed by the model deal with Cells exclusively. 
  * Depending on Cell very much helps the code. Not only does it make it much more readable by 
    divvying up tasks for the Cell to do, it takes a lot of burden off of SimulationModel by allowing
    each Cell to do it's own work and manage itself, leaving SimulationModel to only worry about
    the entire Grid.

* Needs Improvement Example: XML
  * This dependency is also very much warranted. Since the XML is responsible for reading and thus
    specifying exactly what the model is supposed to do, SimulationModel would not be able to operate
    with the current structure (reading in from xml files) without it. The information from XMlParser
    specifically dictates the actions of SimulationModel
  * This dependency is not made very clear. In fact, it's more of a 2nd hand dependency.
    SimulationModel uses the values obtained by XMLParser to figure out what it needs to do, but the
    dependency is only known when one goes to the class that creates a SimulationModel instance (Main)
    and sees that an important parameter passed in is from XMLParser.
  * This dependency definitely helps the code, since it defines that the code should be. However,
    sometimes it can be very clunky because there are a lot of variables to `get()`, and you have
    to check for the possibility that something (a tag) doesn't exist (because the current
    simulation doesn't use it, so it's not specified in the xml file, and thus the tag's value that's
    passed in is "")

##### Configuration

* Overall dependencies (classes, behavior, exceptions, data, or resources)
  * Configuraton depends on SimulationModel, Grid, Cell, XML
  * Note: I am assuming configuration refers to the cell configuration/grid of cells

* Good Example: Grid
  * This dependency is warranted. Since configuration is the arrangement of cells in the simulation
    and Grid holds all the cells, Grid is essential to the proper working of Configuration
  * This dependency is very clear. It can be seen through function names, parameters, etc that 
    the configuration of cells is held by the Grid
  * The dependency very much helps the code. Since everything relating to the Grid is now in one place,
    this allows operations involving updating/reading the configuration to be much easier.

* Needs Improvement Example: XML
  * Similar to above, it is warranted because the configuration is defined by the xml file its read
    from, and XMLParser is the one that does the reading
  * Similar to above, it's more of a second-hand dependency and thus is now very clear
  * Similar to above, XMLParser is essential to the configuration (at least, getting the first one)


##### Visualization

* Overall dependencies (classes, behavior, exceptions, data, or resources)
  * Visualization (SimulationView) depends on SimulationModel, ViewCell, and javaFX

* Good Example: javaFX
  * This dependency is warranted. Since the view deals with what the user can see and interact with,
    and out primary way of doing that is through javaFX, the project would not be able to be run
    and be significant to the user without it.
  * The dependency on javaFX is very clear through the function names, parameters, and objects 
    created. 
  * This dependency helps the code a lot. By having javaFX, we have set objects already created for us
    and thus only need to use them to suit our needs instead of creating them from scratch

* Needs Improvement Example: ViewCell
  * This dependency is warranted. Similar to SimulationModel's relationship with Cell, SimulationView
    requires ViewCell to be able to take care of/update itself do decrease its own work load
  * This dependency is not very clear. Only by following a series of function calls would one 
    be able to find that SimulationView relies heavily on ViewCells to do its own thing.


### Design Principle Examples

#### Single Responsibility

* Good Example: ViewCell
  * ViewCell is a good example of Single Responsibility. The only thing it does is change the
    state (aka color/fill) of the cell. In other words, it takes care of the visual aspect of 
    specifically just the cells in the grid.
  * Code-wise, this is clear as the class and its subclasses don't have many functions, indicating
    that it really only has one role to play.

* Needs Improvement Example: SimulationView
  * This class is simply trying to do too much. It is especially clear in the super class. Not only
    does it attempt to keep track of & update the grid of cells, it also houses all the other GUI
    elements such as buttons, dialog boxes, text boxes, etc AS WELL AS each element's functions/specifications
    and what they should do when an event occurs.
  * Instead of having one responsibility, SimulationView has many (probably around 10)


#### Open Closed

* Good Example: SimulationModel
  * As demonstrated very much in this project, SimulationModel is easily extended. Many different
    SimulationModels can be made, adding simulation-specific features and actions.
  * It is also closed as other classes use it.

* Another Good Example: XML Saver
  * Although this class isn't used in this context in the project, it is a good example of Open-Closed.
  * This class is open to extension. For example, someone could extend this class to write to 
    another location, save using different tags, tag hierarchy/structure etc. However, it is closed 
    as it is used by other classes.

* Needs Improvement Example: WaTor Cell/Fish Cell/Shark Cell
  * I felt that it would've been better if more specific WaTor cell types extended off of this cell.
    WaTor cell & its relatd cells (fish and shark) are by far the messiest cells. The whole
    relationship between a WaTor cell becoming a Fish or a Shark cell depending on its state was
    slightly confusing. 
  * It was compounded by the fact that neither Fish nor Shark Cell extended WaTor. Instead, they 
    just extended Cell, where if it did, that could've improved the design by having abstractions.

#### Liskov Substitution

* Good Example: SimulationModel
  * This class perfectly demonstrates Liskov Substitution, as it is used in just that way in
    SimulationView. In many instances, function calls to just the general superclass are being made
    (ie. `model.updateGrid()`) and never in the program is there a need to typecast down to a 
    specific SimulationModel subclass.

* Needs Improvement Example: Cell
  * In most cases, Cell is actually a good example of Liskov Substitution. For the majority of cases,
    calling a function on the general class is perfectly fine and done quite a lot throughout the
    program.
  * However, there are cases where checks for specific Cell subclasses need to be made, which is 
    an area in which this can be improved.



### Code Design Review

#### Made easier to implement by the code's design

* Feature: Cell fill types

* Justification
  * Although we didn't get to this feature, this would've been very easily implemented. Since we 
    had a ViewCell package with subclasses of each type of cell, it would be very easy 
    to associate a certain state with a fill (ie image, color, etc.). There would only need to be
    a few short steps to achieve this:
    * If the fill is an image, add it to the repo
    * Obtain the image from resources to the correct ViewCell subclass
    * Associate the image with its corresponding state


#### Good Example **teammate** implemented: Separate class for cell grid

* Methods and Classes used
  * `List`: `size()`, `add()`, `get()`
  * `Cell`

* GIT commits
  * f4c7e17e, 3e618368, 075fdb17

* Design
  * In general, the Grid class is closed to modifications. It is a comprehensive class that keeps
    track of all Cells in the simulation. An assumption made is that the grid of cells will always
    be 2D.
  * Although it's not extended, I think it'd be on the easier side to extend this class. For example,
    creating a Grid subclass that perhaps updates itself in a different way would be easy (just 
    @Override the updateGrid function). 
  * However, since it's inherently a grid and created off of that assumption, it'd be hard to 
    add features to it that take it out of that box (ie more dimensions)

* Evaluation
  * I really quite like this feature. It's nice to have all the Grid-related functions condensed 
    in one place for easy access, read, and editing. It follows the single responsibility principle
    and would be easy to extend. Should it be extended, I don't think there'd be substitution problems.


#### Needs Improvement Example **teammate** implemented: GUI Design

* Methods and Classes used
  * all things javaFX
    * `BorderPane`: `setBottom`, `setLeft`, `setCenter`, `getChildren`
    * `Button`: 
    * `HBox`/`VBox`: `getChildren`
    * `Dialog`
    * `Slider`
    * `Alert`: `setTitle`, `setHeader`
    * `WebView`: `getEngine`
    * `WebEngine`: `load`

* GIT commits
  * 2405188c, cb0ba393, bd9e2764, e7a4322d, 187e1973, dd37183c

* Design
  * I'm not sure if there is any real design to this feature. In general it is a block of code
    that specifies the values of things. There are some functions that perform specific tasks,
    but none of them were refactored into a separate class and instead exists as many lines of 
    code inside one class. All GUI elements are straight coded into the project.
  * As a result, it is impossible to extend this feature. 

* Evaluation
  * I don't think this is good design at all & thus not flexible whatsoever. If I were to have done
    this, I would have created multiple classes that took care of small chunks of the GUI, such as 
    the Buttons, the separate Panes, the Alerts, etc.
  * All of this is also mixed in with a separate part of the View: the grid. While code responsible
    for the grid is fairly contained, this part of the code has only continued to grow and add on to
    the mess of the `SimulationView` class.
  
* In conclusion, I don't think this is a very flexible nor sustainable design. To add new features,
  one would just have to continually add to a growing list of Buttons and other javaFX objects.
  * Perhaps one could argue that additional features would be easy to add on, as it would consist of
    simply adding an object and its corresponding specifications and functions, but one would only 
    make this argument if they did not care whatsoever about the readability of the code.




## Your Design

### Remaining Design Checklist Issues

* I ran into a lot of issues regarding my teammates' commitment to the project. I had to pick up 
  a lot of their slack and complete their work. As such, my design suffered A LOT.

* I didn't have enough time to refactor my code to a degree that I
  am proud of. I spent the majority of the project not only doing my work (which had increased
  from our plan stage), but also doing the work of other teammates who didn't know what was
  going on and made no effort to complete. These were basic features, so they had to be implemented.
  Had I not had to pick up the slack of my teammates, I would've been able to improve my design
  by a lot. Their lack of effort severely impacted my design.

* Issue #1: Magic values
  * The biggest remaining design problem is to get rid of magic values. Especially on the UI side of
    things, there are magic values that should have been read in from resource files such as the simulation
    xml file, .css files, etc
  * However, I am also confused as to if this was my responsibility in the first place. Originally,
    all front-end related code was to be done by another teammate, but I impelmented this section
    because she had not started on the code and I needed something visual to test my back-end code.
    * Thus, I believe that this failure to replace magic values was her responsibility. However,
      since the rest of the code was written by me, I still feel a sort of attachment and feel as if
      I should have done it anyway, even though it was not assigned to me.
  * However, I am proud of one case of magic value "solving". A team member implemented grid edge
    types, so I added a tag in each xml file for edge type specification, read that in, and always
    passed the edge type parameter as the value from the xml file instead of a magic number.

* Issue #2: Some not dry code
  * Especially in updateGrid() and createGrid(), a lot of simulations' ways to do that are the same.
    Had I had more time, I would have made the possible cell states a tag in each simulation xml file
    so that updateGrid() and createGrid() could just reside as superclass functions that do not 
    need to be overridden. I started this process, but had to stop working on it to take on another
    simulation that a teammate could not figure out.
  * In addition, I had some trouble figuring out a way to deal with potentially empty tags and how to
    assign variables these values should they not be empty. As such, I had a chunk of code that essentially
    said the same thing for each, but replacing with a different xml tag. If I had time, I would've
    refactored it so that the tag is a parameter/variable so that there would only need to be one
    occurrence of the relevant code.

* Issue #3: General Readability
  * In a lot of places such as my part in WaTor and the UI, I was forced to leave my code un-refactored
  * In WaTor, I felt that the general implementation of Cells was quite clunky and not entirely
    intuitive. Had I had the time, I would've liked to create classes that extended WaTor cell, allowing
    similar attributes and methods to be shared. This would have made the overall flow of logic
    much more clear and understandable
  * On the UI side of things, I added buttons and logic for loading new simulations and saving 
    current simulations. Although I am very proud of my implementation of these features, I am far
    from pleased regarding their design. Similar to everything else in the View, the code for 
    these features just sit in the SimulationView class and add to its mess. Given the time, I 
    would have liked to abstract these into separate classes and cleaned up the code.

### Abstraction
* A class hierarchy I am very proud of is ViewCell. I am particularly proud of how clean it is & looks
  and that it truly embodies the single-responsibility principle. I think this set of classes is by 
  far the best out of all the classes in this project in terms of design. Not only is it readable,
  and has only one responsibility, it is also very easily extended and (as mentioned above) I am 
  very confident in the ease of adding any additional features to this part of the project.
* An abstraction I wish was made:
  * Although this is not code that I was responsible for, I wish there were separate classes
    for each shape of cell and each edge type. As of right now, code to find neighbors for all shapes 
    and all edge types sit together in one class (Cell) and is mixed in with things that I think
    are more "Cell"-focused. 

### Design Challenge

#### Decision #1: SchellingGroupCell, PercolatingCell (and WaTor, but I wasn't responsible for starting the cell structure for that one)
* Context: Similar to situations with other simulations, I grappled with how to implement multiple 
  groups of cells + an empty cell.
  * This was the same for Percolation, except it was if blocked cells and empty/filled cells
    should be of the same cell type or not

* Alternate designs
  * Option 1: One cell class for each group of people and another cell class for an empty cell
  * Option 2: One overall cell class, toggling between states
    * For Schelling's: 0= empty, 1= groupA, 2= groupB
    * For Percolation: 0= blocked, 1= empty, 2= filled

* Trade-offs
  * In terms of single purpose, it would be best to go with Option 1, as each type of cell is slightly
    different and would do different things
  * In terms of Liskov substitution, it would be better to go with option 2. Not only is the 
    implementation much easier, it is also easier to create and update the grid (both backend
    and frontend) and no typecasting would be needed to perform the desired functions

* Justify your Preferred Solution
  * I ended up going with Option 2. I chose it mainly for its ease of implementation. Since each 
    type/state of cell wasn't that different from the others characteristic/function-wise, I felt
    it was more important to emphasize the Liskov substitution principle and avoid any typecasting
    rather than abstracting extremely similar and small classes that might not even do best 
    as separate classes.
  * In addition, the fact that I often thought of these as "states" rather than "different cells"
    also indicated to me that they belonged all in once class. Instead of being 3 different "people",
    persay, these states are different "outfits/clothes" that one person can wear. Thus, this 
    showed to me that option 2 was the better option.

* Satisfaction with Chosen Solution
  * I am very satisfied with my chosen solution. I think my solution made it much easier to 
    implement all the features I needed. In fact, I at first tried Percolation the Option 1 way and
    discovered that it was clearly the worse option.


### Code Design Review

#### Good Example **you** implemented: ViewCell

* Methods and Classes used
  * `Color`
  * `Rectangle`: `setFill()`, `setStroke()`
  * `Map`: `get()`

* GIT commits
  * For general structure: 954e11f1
    * debugged gameofLife creation code, separated cell into two parts: a backend and frontend
    * 1/29/2022
  * Further updates were scattered throughout (ie adding new ViewCell subclasses)

* Design
  * I realize that this is not perfect. There are magic values that should've been read from outside
    resource files, but I did not have the time to complete those. 
  * Outside of that, I think this set of classes is by far the best out of all the classes in this
    project. The only thing that can be changed is the state/fill color of the cell. Anything else,
    ie its location, size, etc is not open to modification. It is also heavily extended. It doesn't
    particularly assume anything, which makes it good for future updates and adding additional features.

* Evaluation
  * Reiterating what I said above, it not only embodies the single-responsibility and open-closed
    principles, but is also follows the Liskov substitution principle. It will be very easy to 
    add features later and is clean and easily readable


#### Needs Improvement Example **you** implemented: Save configuration

* Methods and Classes used
  * `Button`
  * `Map`: `put()`
  * `XMLSaver`: `save()`

* GIT commits
  * 2/6/2022
    * 2c3cfb05: added new config button and started xml saver
    * 6251a1a9: possible new button package and XML saver class
    * 9810cd47: XML can now be created from simulation configuration
    * 7d91e182: refactored one part of xml saver
    * d2e832a7: refactored save method in XML saver

* Design
  * When a button (created by SimulationView) is pressed, Main calls XMLParser's save() function,
    allowing a new xml file to be created.

* Evaluation
  * Although I am proud of my work with this feature, I don't think the design is very good.
    It is all over the place in terms of reach and spans multiple files. I think one part of it
    (the XLMSaver class) can be extended in the future, but overall, even I myself am confused 
    by how it works and where to find code relating to saving a new configuration.
  * To improve it, I would abstract everything out into a new class that takes care of everything
    and also links to (calls) XMLParser's save function.

### Specific efforts to make your code as encapsulated or abstract
* Especially in terms of the big superclasses in the beginning, I tried my best to make 
  them as abstract as possible. However, as my teammates started working on the project, these
  classes became less abstract and more messy.
* Even after that, I continued to try to make the code abstract by pulling out finding neighbor, 
  create/update grid functions to the super class so that all subclasses can inherit them.



## Alternate Designs

### Classes affected by changing the Grid's data structure
* SimulationModel
* SimulationView

### Change features affect on original design
* The design didn't handle some aspects of change very well, especially the view. Since everything
  was all lumped together and not abstracted at all, the person in charge of the view had a 
  hard time implementing new features (although a lot of change time was taken up trying to finish
  the basic features-- I basically finished all of her leftover work while she tried to implement
  change). I suggested that it would be best to abstract out, but we ended up focusing on adding
  features instead of abstracting...
* In terms of cell shapes and neighbor types, our design also didn't hold up so well. In our
  discussion on how to both calculate neighbors and the best design for these features, I suggested
  that we abstract and make each shape and edge type their own class, but the teammate implementing
  it did not follow through.
  * (note: during this time, I was trying to finish up the basic UI features that were not
    completed/had not been started)

### Assumption with significant impact
* One assumption from basic that had a significant impact was the cell shape. As can be seen by the
  way we determined neighbors, structured cells in the view, it is clear that we though the only
  cell shape would be rectangular. This assumption permeated every part of our design.

### Design Decisions

#### Decision #1: How to separate the project structure

* Alternate designs
  * Option 1: Similar to breakout, where everything was in one class/file
  * Option 2: Split the project up into Model(simulation) and View, with Cells being a part of the
    backend that would further improve readability

* Trade-offs
  * It was pretty clear that Option 2 was the best option. The only tradeoff would to get into the
    habit of following Option 2's structure.

* Justify your Preferred Solution
  * My preferred solution (Option 2) more closely followed all the design principles talked about 
    in class: single-responsibility, open-close, encapsulation, etc. 
  * Not only would everything be split up so that each class would have clear roles to play, 
    everything would be much more readable and clean, with no wet code.

* Satisfaction with Chosen Solution
  * I am very satisfied with our solution. This was clearly the best way to go, as our code would have
    been extremely messy and unfriendly ig we went with option 1.

#### Decision #2: Cell Shapes & edge types

* Alternate designs
  * Option 1: Have all logic for all shapes and edges in one class (the cell class)
  * Option 2: Abstract it out into multiple smaller classes split up by shape and edge type

* Trade-offs
  * Option 1 would be easier to implement *in the moment* it would be faster to just have everything
    in one place
  * Option 2 would require some more thinking to think up of how to seperate the classes, what would
    inherit from what, etc. However,

* Justify your Preferred Solution
  * I heavily preferred option 2. Even though it would've been more work, I think everything would've
    been a lot cleaner if we made more classes & subclasses. It would more closely follow the single
    responsibility principle.

* Satisfaction with Chosen Solution
  * I am not very satisfied with the chosen solution. It is true that everything works as intended,
    but I do not think that the design is good and that it would be easy to add on additional 
    features relating to these changes.


### What Affects your Design Decisions?
* I think in general, the more isolated the class, the less impact it will have on the overall design
  if one features touches multiple classes, then changing it would result in the needed for changes
  in many more places in the project. Thus, it is best if everythnig is in their own little "bubble"
  so that additional features/changes can happen easily and painlessly



## Conclusions

### Coding Habits

* For the better
  * Although I still need much improvement in this area, I think I committed more often in this 
    project than in my last project. I am improving when it comes to commit smaller chunks of code,
    for example one aspect of on feature, instead of only commiting when the feature is 100% complete
    and debugged.

* Still working on
  * I work really late & require long periods of uninterrupted time to work. This is something that 
    I didn't improve much on from last time.


### Refactoring

* How is it going?
  * This time, I remembered to think more about design as I code & add new features, so refactoring
    was much easier this time around that last time (this is also something I improved on!)
  * Before I add a feature/write a lot of code, I first think about what the impact of said code is,
    and if I think it should be refactored/abstracted, I do so immediately so that I don't have to
    go through the pains of doing so later.
  * I also try to think of solutions to my current problems in terms of ways in which other future
    features could also benefit

* Example
  * my abstracting out Cell and ViewCell, as well as initNeighbors() and updateGrid()
    (or my start at doing so) were all generally made while I was coding instead of afterwards.
  * Solutions for the future: When writing Schelling's, I predicted that it would be important
    to first get the states of all cells before changing them, so that the next cell's next state is not
    impacted by the change the previous cell already made.
  * Thus, I made functions in the SimulationModel and Cell superclasses that would take care of 
    this, by first calculating & obtaining a grid of next states and then actually updating all the 
    cells to those states.


### Teammate's Code

* What was learned?
  * I have learned that we all think in different ways. While I may fix a bug one way, another 
    teammate may find a completely different way to fix it

* Example
  * In the first week (basic), a teammate push a buggy version of Game of Life, so both he and I
    debugged it separately. When we came back together, we had recognized the need for the same 
    solution, but went about arriving at that solution in different ways. I liked to operated more
    in the superclass because I knew this change would be important to later simulations, but he 
    preferred to make changes in the more specific subclasses.


### How has your perspective on and practice of coding changed?
* Although I've still got a long way to go, I've started to think more about design (vs not thinking
  about it at all in Breakout) and trying to predict things/problems that might pop up in the future.
* By doing this, I hope to avoid problems down the line and make it easier on me and my teammates
  to add new features.

### What specific steps can you take to become a better designer?
* I need to think even more! Before coding, it's important to think about the new feature to be 
  added, it's impact on every single part of the project, and if there's ways in which I can
  abstract to make the design better.
