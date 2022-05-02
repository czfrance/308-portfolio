# Journal : OOGASalad Project Analysis
#### Cynthia France
#### oogabooga
#### 5/1/2022


## Design Review

### Description of the Overall Design
#### High-Level Design

The implementation of this project was split into two sub-projects, the Engine (game player) and the
Builder (authoring environment). The goal was to have these two programs function independently, 
with the only link between the two being the JSON configuration format for defining a game and a 
single splash screen that would allow users to navigate to the Builder or Engine accordingly. This
separation meant that we could parallelize the work on the player and game-authoring environment.

##### Engine
The engine implements an MVC architecture. The view waits for user input, and the controller starts the 
appropriate action in the model. After completing the action, the model implements changes in the view. 
through the use of callbacks passed to it through the controller. 

###### Model
The model consists of four major classes that controls the game flow. These include the: Engine, Player,
Oracle, and Game. 

The `Engine` class is the main contact with the controller and also controls the flow of the game. 
It pings each player that it is their turn to make a move and awaits for the choice before validating
and sending the updated board to the game.

The `Player` API represents the players for the game. This API can be extended to make multiple types 
of players. Currently, there is a human player and a player Artificial Intelligence player. The player 
api defines the ability to choose a move and each player implements them separately.

The `Oracle` class is used as a service to determine if a selected moves are valid moves. It does 
this by checking the list of valid Rules and returning which moves are valid.

The `Game` class keeps track of the current state of the board and is observable so that when the board 
changes for whatever reason the model and the view are both alerted.

###### View
The View is divided into multiple sections and is designed to be completely closed. When new games are 
created they are immediately added to the dashboard and can be played immediately. The main elements 
are the dashboard and the boardview.

The `Dashboard` consists of a Game Selection window that displays multiple `GameIcons`. These game icons
are a template class that are created based on the game folders in a specified directory. Each icon 
selects an image based on the game folders and auto-populates the info panel when selected.


##### Builder
The builder implements an MVC architecture. The view dispatches callbacks to the controller, which
calls the model to update the game configuration accordingly.

###### Model

The model is composed of a few major APIs that dictate how information is stored in the back-end of
the builder. The `BuilderModel` is a single API that provides access methods for modifying all data in
the model (so that the controller has a single external API to call). 

The `Property` API describes how properties are stored in the builder. A Property is just some 
information about a game element (such as the name of a piece or rule). Properties are immutable, 
but were opted to be a class instead of a record because we felt that an `AbstractProperty` class 
would reduce code repetition.

The `Element` API represents a single game element in the configuration (i.e. an entire piece or 
rule). Elements are essentially collections of properties. The `ElementFactory` API has methods for 
creating elements, including retrieving the required properties for certain types of elements. The 
`FactoryProvider` implements the [Abstract Factory Pattern](https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm), 
providing methods for creating elements without the need for concrete declarations of ElementFactories.

The `Board` API stores information about the board. This includes size, shape, piece configuration,
and cell colors. While the cell colors are only needed in the view for display purposes, they must
be stored in the model in order to be serialized to JSON.

The `JSONParseable` and `JSONSerializable` interfaces each provide a single method that determines how
a class is parsed or serialized (reading or loading from JSON). Using this interface standardized 
the process of parsing and serialization, and meant that there did not need to be a single 
Parser/Serializer that held all information about saving and loading a game configuration from JSON.
We felt that this design better adhered to the Single Responsibility Principle and active classes.

###### View

The view is composed of several basic JavaFX view classes as well as several abstractions. The view
is split into Tabs, which are each responsible for the creation of a single game element. These tabs
all implement the `BasicTab`, which is the internal API for creating a tab in the builder.

Each tab has a `GameElementList` (lists existing game elements) and PropertySelectors.
`PropertySelector` is an API that defines general methods for allowing users to set the values of 
certain properties. For instance, an `IntegerSelector` prompts users to select integers and a 
`BooleanSelector` prompts users to select a boolean value.

The view communicates with the controller by dispatching `Callback`s, which are handled by the 
controller using a Listener based system. The view obtains information about the existing game
configuration from the model using these Callbacks. The view does not store any information about 
the game elements other than their names, and retrieves the necessary information if users wish
to edit existing elements.


### Change Scenarios

#### Adding new game variation
##### through the builder
* To add a new game variation, it is recommended that the game is created in the builder. Thus, all 
  that is needed is to go into the builder and follow the steps there to create a game. Outside of 
  knowing the actions, conditions, rules, and win conditions
  for the game and for each piece, you must have icons/images for the different pieces that are used in the game.
* Once you are in the builder, the first step is to upload piece images and associate them with 
  players and ids. Then, create the board, specifying its colors and the starting configuration of 
  peices. Then, we move onto create actions for pieces, conditions for pieces, rules for actions, 
  and win conditions for the game.
* After everything is completed, click the save button and create a new folder to save the game in.
* In order for the game to show up on the dashboard, this folder must be saved under `data/games`.
* If you'd like, you can add a game icon (will show up in the dashboard). This icon should be 
  located in the main game folde (same hierarchy as `config.json`)

##### through writing json
* If you were to choose to code the json for the game, follow the json format for the game, and 
  code in all the icons, rules, conditions, actions, win conditions, board colors and configuration, etc.
* All piece images must be saved in a folder called `resources`
* save the json in a file called `config.json`, located in the top directory of the game
* save the game icon also in the top directory of the game

##### Design
* The ease in which a user can create a game shows that it is encapsulated and abstract. In the 
  builder, all one needs to do is specify the rules, conditions, and actions for a piece, without 
  having to know how the json is built specifically, or any syntax. Everything specific to creating 
  the game is encapsulated inside of it, so that it is all in one place.

#### Changing data file format
* If the file format were to change, a number of different files would have to change as well. 
    * On the builder side, the following would change:
        * In builder model, create a `Serializable` class for the file format
        * create a `parseable` class for the file format
    * On the engine side, the following would change:
        * `engine/parser/AbstractParser` must be changed so that it will parse the new file format instead of JSON. 
        * This would require finding a different method of parsing files.

* All in all, there are only 3 files in the entire project that would be need to be changed for 
  the application to support a different file format. This demonstrates that the project, especially 
  the view parser is both abstract and inheritance. The `AbstractParser` class is an abstract parent 
  class, from which many classes, such as Cell, WinCondition, Action, etc. inherit. 
* The parent class is abstracted so that only the simplest interface is shown to the user, and the 
  use of inheritance allows us to embody the open-closed Principle. The `AbstractParser` class is 
  open to extension (ie parsing for colors, a rule, etc), but closed to modification.
* The fact that only one file needs to be modified in the view demonstracts that it is following 
  the Liskov Substitution Principle. Although many classes and functions are affected by the change 
  in files, general parent class terms have been used, which puts us at a disadvantade.

#### Add new Player view
* In order to add a new view to the player, one only needs to take a few steps:
    * code the new view, including all of its elements and functions
    * Depending on what the view does, this class should be located in either the `game` or `setup` 
      directory/hierarchy. Place the class(es) that you wrote in the property file hierarchy.
    * In `ViewManager`, create a function that creates the new class, and reconnect the buttons in 
      a way so that the flow of the view is directed through the new view in the proper order.
    * Test out the application to make sure that there are no bugs
* Our use of the ViewManager encapsulates the code so that everything regarding switching and 
  managing views is located there. This makes adding a new view relatively easy and painless, as 
  we only need to tell the ViewManager know that this view exists, and hook it up accordingly.

#### Extension Feature
* Extension Features: Player Profiles
  * Creating player profiles should be relatively easy.
  * First, create a profile package. Then, in the view, create a simple "create account" login page,
    which takes in name, username, password, and any other information you'd like to collect.
  * After obtaining this information, add the user to a user config file (could be in JSON) that 
    contains the user's username, and password. The password should be protected with password 
    hashing.
  * In another user file, store the user's username, name, and any game-relevant information
  * When the user logs in, compare the username and password to the one on file, and if there is a 
    match, associate all further game play with this player's information.
  * To make it easier, we can create a Player class that stores all information, which can be parsed
    from the JSON file, and can also serialize its information to JSON when the user logs off.
  * Any relevant user actions taken during the game will automatically update Player, which updates
    the JSON file upon closing/logoff

### Design Changes Made

#### Significant Change

* Discussion
  * A significant change made to the project occurred on the engine model side. Originally, the 
    backend `Board`, which holds all information regarding the configuration, movement, and 
    addition/deletion of pieces was mutable.
  * Discussion around this issue revolved around the accessibility of `Board`. Since `Board` was 
    mutable, this made it possible for other classes to forcefully change the configuration of 
    pieces through `public` methods.
  * Obviously, this is not ideal and must change.

* Decision
  * The decision was made to make `ImmutableBoard`, which, as the name suggests, is a Board that is
    not mutable, and thus safe to pass around without fear/possibility of another part of the program
    altering the state of the board.
  
* Communication
  * Our team had a Slack channel, and discussion revolving this change involved people in the Engine.
    Thus, to communicate this change to the rest of the team (Builder), we sent a message in the 
    `#general` channel. 
  * Technically, it was not necessary for Builder members to know this change as it does not affect
    them at all, but communication is important and this was a big change, so we informed of the 
    change.


#### Implementation Change

* Discussion
  * An implementation change was the splitting of the JSON parser/serializer into two parts: one that
    creates JSON from user interactions and one that reads/parses JSON from a file.

* Decision
  * The decision to make this change arose from the need to split up the original `JSONSerializable`
    class to fit the single-responsibility principle
  * Now, there are two classes:
    * `JSONSerializable`, which adapts backend objects to their JSON format
    * `JSONParseable`, which converts a JSON string to an object

* Communication
  * This change was similarly communicated to the entire team through the oogabooga slack channel.
    As both the builder and engine uses the JSON parser/serializer, this was a necessary communication
    so that everyone would know what was going on.


### API Examples

#### Stable
The `Property` class remained relatively stable throughout the entire project.

* Describe as Service
  * The `Property` class has a name, value, default value, and "form" (which indicates how the 
    property is displayed)
  * It serves as an API for the builder frontend, providing the properties for each aspect of game
    making, for example the properties that are involved with specific actions, conditions, rules,
    etc.

* Easy to use and hard to misuse
  * The `Property` class is hard to misuse, as it is immutable. Methods that return information
    regarding some aspect of a `Property` create copies of the `Property` in question to protect
    the actual `Property` data.
  * It is easy to use as it is clear what elements each `Property` has.
  * The data of `Property` are stored as objects (easy to use and interact with), but can be turned
    into Strings for ease of parsing and whatnot.

* Encapsulation and extension
  * The `Property` class is encapsulated. It contains the name, value, default value, and "form"
    of the instance as well as functions pertaining to the operation of a `Property`. Alterations 
    to a `Property` can only be done through methods.
  * The `Property` class is extended numerous times, with different subclasses of `Property` having 
    different types for the value using generics


#### Changed
The Engine `Controller` class was a class whose API went through some changes as the project
developed.

* Describe as Service
  * The `Controller` class API connects the backend model and frontend view of the engine,
    serving as the communication point between the two sides of the application. 
  * It performs a variety of functions such as reporting the Board, getting the Engine, undoing 
    a move, getting the Game, resetting, etc.

* Easy to use and hard to misuse
  * The `Controller` class is easy to use because its methods uphold the single responsibility 
    principle: they only do one thing, which is what the method is named after. The combination
    of proper method naming and single responsibility make this class easy to use.
  * By nature, this class works very closely with the backend `Board` class. As stated previously, 
    the `Board` is immutable, which makes the `Controller` class hard to misuse, as everything that
    is passed through is immutable and thus is not open for potentially malicious people/methods
    to take advantage of.

* Encapsulation and extension
  * The `Controller` is encapsulated because all of its functions and operations are wrapped up 
    nicely in the `Controller` class. It is not difficulty to understand this class and very easy to 
    use.
  * There are no current extensions of the `Controller` class, but extensions can be made. It would
    be easy to extend off of this class to make a more specific program controller for more specific 
    purposes.


### Feature Design Examples

#### Good Example **teammate** implemented
Good Design: Thivya's configure in builder view
* Design
  * In the configure package, there are three classes:
    * `FormatDropDown`: Extends ComboBox. Getting the items in the dropdown as well as setting the
      format of the dropdown are all encapsulated within the class.
    * `SettingsView`: Interface that defines a popup. Makes a stage and sets up the stage
    * `SettingsWindow`: Implements `SettingsView` interface. Creates the Settings popup as well as
      all the elements in the settings tab. All the methods for creating the settings popup, the 
      stage, settings the theme and font are all encapsulated in this class.
      * We assume that the theme and font exist.

* Evaluation
  * I think the design of this feature is good. The elements that make up each aspect of the Settings
    window is broken up into manageable, single-responsibility elements, including the combo box. 
  * I appreciated the SettingsView interface, as it allows for further abstractions and extensions 
    to be implemented further on, for example if we wanted to make a different type of pop up, it 
    would be very easy to implement the SettingsView interface and abstract.


#### Needs Improvement Example **teammate** implemented
Needs Improvement Design: The design that needs improvement is Game class
* Design
  * The Game class extends the Observable class. It is used by the Controller.

* Evaluation
  * I don't think the design is bad per say, but I don't think the class is properly 
    named. The name `Game` class implies that it should encapsulate more than just resetting the
    the game and going forward and backward in the game.
  

## Your Design

### Assumption or choice that had a global or significant impact on your design
One assumption about the assignment is that all changes made in a certain window should be reflected
in the entire application. I made this assumption because in a game, these controls (ie theme, 
mouse sounds) do not control a certain game, but the entire application. This impacted the way in
which I created, changed and updated the aspects of the game, especially the theme.

For the theme, I had to keep track of all the scenes and stages of the application and go back 
to update the theme in each of the scenes. This impacted the place in which the function was located.

### Design Challenges

#### Design #1
First design consideration: location of theme change

* Trade-offs
  * As currently implemented, the theme is changed in the viewManager. This is mainly due to an 
    assumption that I made that I talked about earlier: assumption about the assignment is that all changes made in a certain window should be reflected
    in the entire application. I made this assumption because in a game, these controls (ie theme,
    mouse sounds) do not control a certain game, but the entire application. This impacted the way in
    which I created, changed and updated the aspects of the game, especially the theme.
  * Thus, to change the theme of each individual window of the application (necessary in order
    to play multiple games at the same time), the theme change function had to be located in the 
    ViewManager, as that is where all the scenes and stages of the game are created and kept 
    track of.
  * This allows for the assumption to be properly taken care of. However, I dislike how a button 
    who is in such a branched stage/part of the hierarchy is being executed in essentially what is the
    root of the entire application. It feels wrong to have to pass either this function or the button
    through so many levels of the program.

* Alternate designs
  * Alternatively, I could ignore my assumption and only change the theme of the window that the 
    game is currently on. This means that only the window in which the player selected the theme
    change would experience the theme change.
  * This would fit better with the rule that higher classes should not depend on lower
    classes. However, this does not make sense game-design-wise. I feel that if a 
    user were to indicate a theme change, they would expect it to happen throughout
    all of the windows.

* Solution
  * As a solution, I went along with my assumption and had the function be executed
    in the ViewManager. To try to have better design, I opted to pass the function
    through to the place where the button was created rather than having several
    getters throughout several classes in order to pass the theme change button
    all the way back to the ViewManager.

* Satisfaction (Justification or Suggestion)
  * Although I am not completely satisfied with this decision, I do think it was 
    the best decision and cannot do any better, Any other solution would have 
    resulted in losses in design in other ways.

#### Design #2
Second design consideration: Another design consideration that I had to wrestle with
was regarding popup menu. 

* Trade-offs
  * In order for the full effect of the popup to take place, there are two things 
    that need to happen. First, a Gaussian Blur has to put on the current stage. This
    renders the stage uneditable. The second step is to create the popup menu, which 
    is a transparent stage with a box displaying some sort of message.
  * The tradeoff/deliberation comes from where to implement the Gaussian Blur in 
    relation to where the popup stage is created.
  * Currently, the Gaussian Blur is applied in the `GameView`, the same place where
    the popup view is created.

* Alternate designs
  * Alternatively, the stage that the Blur should evoked on could be passed into the
    popup view and the Blur applied to the stage when the popup is created, in the
    popup view constructor. However, this would entail passing the stage into the
    popup view, so there is a chance that it can be used maliciously for other 
    purposes

* Solution
  * The solution I went with was the design currently in place. The Blur is applied 
    in the GameView, and then the popup view constructor is called.

* Satisfaction (Justification or Suggestion)
  * I am satisfied with this decision. I think that this is the best way to keep
    the design encapsulated while having the best design possible.



### Design Pattern

* Design problem applied to
  * I used the `SelectorFactory` and `AIPLayerFactory` class in my program.

* Classes and methods that implement it
  * The `AIPLayerFactory` class implements the `SelectorFactory`, and the `PlayerFactory`
    implements `AIPLayerFactory`. 

* How it helped your design
  * This helped my design by making it more streamlined and easy to use. My part of 
    the code used the AIPlayerFactory in order to implement one player games (playing)
    against an AI in a 2+ player game. This helped my design be much more simplistic
    and easy to read.



### Abstraction Examples

#### Example #1
The first example is a series of abstractions: `Popup`/`SettingsView`/`SettingsEntry`

* Classes/Interfaces included in abstraction
  * The classes that are included in this abstraction include:
    * `PopupView`
    * `MessageView`
    * `SettingsView`
    * `SettingsHeader`
    * `SettingsEntry`
    * `EntryText`
    * `MouseSoundEntry`
    * `ThemeSoundEntry`

* Design goal
  * The goal of my design was to make it as abstract as possible, making sure that 
    it is easy to extend the abstract classes in order to ensure that any further extensions
    are easy to implement and easy to incorportate into the program.
  * It also follows the Liskov substitution principle. Subclasses that extend the parent
    class are able to be substituted for the parent class.

* How it helps
  * This helps my design by making it very streamlined and easy to read. It follows the open-closed
    principle in that the parent class is open to extension but closed to modification, as seen
    in the many examples that I demonstrated in the program. 
  * In addition, my code follows the Liskov substitution principle, so it will make it easier
    in the future to change the code.
  * Finally, abstracting these classes makes my code flow much better and look a lot more clear.


#### Example #2
The second example is my refactored code in my analysis, the `ControlPanel` and `ControlButton` 
packages

* Classes/Interfaces included in abstraction
  * The classes that are included in this abstraction include:
    * `ControlPanel`
      * `GameControlPanel`
      * `SettingsControlPanel`
    * `ControlButton`
      * `HomeButton`
      * `InfoButton`
      * `PauseButton`
      * `RestartButton`
      * `SaveButton`
      * `SettingsButton`
      * `UndoButton`

* Design goal
  * The goal of my design was to make it as abstract as possible, making sure that
    it is easy to extend the abstract classes in order to ensure that any further extensions
    are easy to implement and easy to incorportate into the program.
  * It also follows the Liskov substitution principle. Subclasses that extend the parent
    class are able to be substituted for the parent class.

* How it helps
  * This helps my design by making it very streamlined and easy to read. It follows the open-closed
    principle in that the parent class is open to extension but closed to modification, as seen
    in the many examples that I demonstrated in the program.
  * In addition, my code follows the Liskov substitution principle, so it will make it easier
    in the future to change the code.
  * Finally, abstracting these classes makes my code flow much better and look a lot more clear.
  * In addition, my code incorporated the use of lamdas, so everything is closed: nothing (ie
    buttons, Text, etc) is shared with the rest of the program. Instead, functions in the form of
    Runnables, Suppliers, and Consumers are shared.
    
  


### Feature Design Examples
my refactored code in my analysis, the `ControlPanel` and `ControlButton` packages

#### Good Example **you** implemented

* Design
  * The design of the feature, the control panel, is split up into two abstract classes: the 
    actual control panel class and the control button class, which are the buttons that make up 
    each control panel class.
  * The subclasses that extend off of the control panel are the actual panels, while the 
    button subclasses that extend off of ControlButton are the actual buttons in the actual 
    ControlPanels. 
  * In order to set the actions of the buttons, runnables, suppliers, and consumers are used to 
    to pass the functions along.

* Evaluation
  * I think the design of this feature is good. The goal of my design was to make it as abstract as possible, making sure that
    it is easy to extend the abstract classes in order to ensure that any further extensions
    are easy to implement and easy to incorportate into the program.
  * It also follows the Liskov substitution principle. Subclasses that extend the parent
    class are able to be substituted for the parent class.
  * This helps my design by making it very streamlined and easy to read. It follows the open-closed
      principle in that the parent class is open to extension but closed to modification, as seen
      in the many examples that I demonstrated in the program.
  * In addition, my code follows the Liskov substitution principle, so it will make it easier
    in the future to change the code.
  * Finally, abstracting these classes makes my code flow much better and look a lot more clear.
  * In addition, my code incorporated the use of lamdas, so everything is closed: nothing (ie
    buttons, Text, etc) is shared with the rest of the program. Instead, functions in the form of
    Runnables, Suppliers, and Consumers are shared.


#### Needs Improvement Example **you** implemented
The `BoardView` class is a class that needs a lot of improvement

* Design
  * There really isn't too much design in this class. It ended being a dumping ground for the 
    code that generally related to the movement of pieces, but we didn't know what to do with 
    it/where to put it. The BoardView is created in the GameView class, but operates essentially
    operates seperate of the GameView class.

* Evaluation
  * The design of this class is really bad. As I stated before, it is a dumping ground of code,
    so it breaks the single responsibility principle on all levels. In addition, there isn't
    any abstraction, although it could benefit from a lot of abstraction and use of lambdas.
  * In general, the class is hard to read and ugly, and any extensions or additions to the 
    project/application would either result in making the class even longer and thus more 
    confusing and hard to read, or require lots of changes.



## Conclusions

#### Thing #1 you have done to improve as a coder/designer
* Over the semester, something that I've done to improve as a coder/designer is plan out what 
  I'm going to code. It started only when I was forced to make the plans in the CS 308 projects.
  Before this, I would go in and start coding with an idea in my head and see what would 
  result. Usually this worked, but it would result in very messy code that wasn't very coherent 
  at all. In addition, this system wouldn't work for working in a team, as there needs to be a plan 
  put in place for the team in order to get started and use the APIs put in place.

#### Thing #2 you have done to improve as a coder/designer
* Another thing that I've done to improve as a coder/designer is to communicate more with my
  group. I think it is a necessary thing to do in order to work in a group. If there was no 
  communication or no proper communication between the team, then the workflow would break 
  down and the entire project would fall apart. This happened in my first group project, 
  and I was stuck doing all of the work because my teammates were not working and were not
  communicating with me properly.

#### Thing #1 you have done to improve your teamwork or to be a better teammate
* One thing that I've done to improve my teamwork is to communicate more with my
  group. I think it is a necessary thing to do in order to work in a group. If there was no
  communication or no proper communication between the team, then the workflow would break
  down and the entire project would fall apart. This happened in my first group project,
  and I was stuck doing all of the work because my teammates were not working and were not
  communicating with me properly.

#### Thing #2 you have done to improve your teamwork or to be a better teammate
* Another thing that I've done to improve my teamwork is to be participate more in group 
  discussions. At the beginning of the semester, it was less of not participating and more 
  of the discussions not existing. As the semester went on, our teams got better at communication
  and thus having discussions. As the number of dicussions increased, of course I participated
  more.

#### Thing #1 you have done this semester to improve how you manage "large", ambiguous, open ended projects
* One thing that I've done to improve how I manage large projects is to communicate more with my
  group. I think it is a necessary thing to do in order to work in a group. If there was no
  communication or no proper communication between the team, then the workflow would break
  down and the entire project would fall apart. This happened in my first group project,
  and I was stuck doing all of the work because my teammates were not working and were not
  communicating with me properly.

#### Thing #2 you have done this semester to improve how you manage "large", ambiguous, open ended projects
* Another thing that I've done to improve how I manage large projects is to be participate more in group
  discussions. At the beginning of the semester, it was less of not participating and more
  of the discussions not existing. As the semester went on, our teams got better at communication
  and thus having discussions. As the number of dicussions increased, of course I participated
  more.

#### Biggest strength #1 as a coder/designer?
* One of my biggest strengths as a coder/designer is my work ethic and stubbornness. I don't like to leave things
  unfinished, so I will often work until the goal that I set out for myself I reached. I am
  very passionate about what I do, and when that is the case, I will dedicate as much time
  into the project as needed until the result is something I am satisfied and can be proud
  of.

#### Biggest strength #2 as a coder/designer?
* Another of my biggest strengths as a coder/designer is my ability to understand code quickly. I 
  think this is something very important as you are not the only person working on the project,
  so it is important to be able to read and understand other teammates' code quickly and efficiently,
  and then build upon it and use it in your own code.

#### Favorite part of working on "large" team software project
* My favorite part of working on large team software projects is that you are able to depend on
  other people. This way, you don't have to worry about every single aspect of the project
  and you don't have to think about and fully comprehend how every part works. You can choose to
  place your trust in your teammates and know that they will eventually get the work done.