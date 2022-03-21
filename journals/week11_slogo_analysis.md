# Journal : SLogo Project Analysis
### Cynthia France
### 3/19/2022
### Team 01


## Overall design

### Complete Project

#### Adding new language Command
* To add a new command to the language, you must first write the code for backend execution of the 
  command as a Class in the InstructionClasses folder.
* Add the command and its corresponding user input to the languages files in slogo.languages
* If the command changes an aspect of the turtle in the front end beyond what is already taken account, 
  ensure that those changes are reflected in SketchbookView

#### Adding new front end component
* If it is a front end component relating to the turtle
  * Add this feature into the TurtleView class
  * Ensure that it can be updated, and that it is done so both correcly and in the updateTurtle
    function
* Otherwise,
  * Add this new component to SimulationDisplay
  * Update its function 

#### Implementing a new feature
* To update a turtle's pen color, shape, and pen size
  * create a new package in InstructionClasses, TurtleViewCommands
  * create a TurtleViewRecord class, similar to the existing TurtleRecord class, except with
    front end features
  * write new Classes for each of these commands that reflects the current lambda structure using 
    TurtleViewRecords instead of TurtleRecords, which update the necessary front end/view component
  
#### Dependencies

##### Model

* Overall dependencies (classes, behavior, exceptions, data, or resources)
  * In general the Model doesn't depend on the View whatsoever. All changes originate from the 
    backend/model and are reflected in the frontend.
    * If we were to implement buttons that changed turtle state, then the Model would depend on that
      part of the View. As of now, though, there are no dependencies
    * The Model (specifically the compiler) depends on language files in the resources package to
      identify commands in their respective languages
  * We don't specifically have a Controller, although the Compiler could be thought of as the 
    Controller, communicating between the front end Console and backend Turtle by error checking and
    translating functions
* Good Example
  * Model: languages
    * This is a good dependency as every line of the resource files are necessary for the proper
      functioning of the program
    * This dependency is easy to find. It is very clearly indicated in the program
    * This dependency definitely helps the code that uses it, making it much more neat, readable, 
      and closes the code.

* Needs Improvement Example
  * Model: Instruction
    * *Note: Instruction is also a part of the backend
    * The dependency is warranted. TurtleMode's dependency on Instruction uses the entirety of the 
      Instruction class/packages
    * Although the dependency is in a public function, it is still not clear at first glance the 
      purpose of the dependency/that it exists
    * The dependency significantly helps the code by making it more readable and single responsibility



##### View

* Overall dependencies (classes, behavior, exceptions, data, or resources)
  * The View depends on TurtleModel and TurtleInsnModel.
    * TurtleModel: depends on the values in its TurtleRecord object
    * TurtleInsnModel: depends on the return values of getCreatedTurtleMap() and getActiveIDList(),
      which returns a map of Turtles and their IDs and the list of active turtles, identified by ID,
      respectively
* Good Example
  * View: TurtleInsnModel
    * The dependency is warranted. TurtleInsnModel stores all of the relevant information about
      all Turtles ever created, which is an integral part and plays an important role/reaches a
      significant amount of the backend code.
    * The dependency is easy to find as it is clearly passed in a public function.

* Needs Improvement Example
  * View: TurtleModel
    * The dependency is warranted. TurtleModel's TurtleRecord quite literally reaches every aspect
      of the backend turtle, as it stores all of the Turtle's information about its location,
      heading, state, etc.
    * The dependency is slightly hidden, as we get the TurtleModels from the return of a function
      in TurtleInsnModel.
    * The dependency helps the code that uses it by making it more readable and allowing the code
      to be single responsibility



### Design Principle Examples

#### Open Closed

* Good Example: Instruction
  * the Instruction class is a good example of the open-closed principle. There are many ways one
    can extend the Instruction class, as can be seen in the project. There are already multiple 
    extensions/inheritance hierarchies for this class, such as TurtleCommand, MathOperation, 
    ListCommand, etc, yet it is closed to modification.

* Needs Improvement Example: OpeningWindow
  * I think this class could have been abstracted better, for example extending off of a 
    broader Window class. This would make the code more open to extension, rather than having it
    as is, specific to OpeningWindow function/information.


#### Interface Segregation

* Good Example: DashboardView
  * This is a good example because the interface is specific enough so that any classes that would
    like to implement it do not have to deal with issues regarding unused methods.

* Needs Improvement Example
  * We didn't use any other interfaces in our project.


#### Dependency Inversion

* Good Example: Paths
  * TurtlePath depends exclusively on lower level modules such as MovementPath and DoNothingPath and
    does not depend on any specific details.

* Needs Improvement Example: TurtleModel-BiFunction/Instruction dependency
  * This dependency essentially drives everything in a circle. TurtleModel depends on Instruction,
    which in turn depends on TurtleModel. Although one module is not exactly a "lower-level" than
    the other, this dependency clearly does not embody the spirit of the Dependency Inversion Principle



### Code Design Review

#### Good Example **teammate** implemented

* Methods and Classes used
  * Instruction
  * Instruction methods
  * Instruction's child classes

* GIT commits
  * Commits relating to Instruction/InstructionClass, generally authored by Brandon Bae
  * ie Commit 23477c5f2cf9c683bb47f3e81d016f009119de96 on Feb 25 by Brandon Bae
    * "organized InstructionClasses into instruction type packages and created..."

* Design
  * The entire class is open to extension, but closed to modification.
  * An assumption made is that Instructions will have a return value that can be calculated 
    before running the program
  * All functions relating to Instructions (ie isDone, getParameters, etc) are encapsulated into
    this class.

* Evaluation
  *  The Instruction class is good design as it embodies the open-closed principle. There are many ways one
     can extend the Instruction class, as can be seen in the project. There are already multiple
     extensions/inheritance hierarchies for this class, such as TurtleCommand, MathOperation,
     ListCommand, etc, yet it is closed to modification.


#### Needs Improvement Example **teammate** implemented

* Methods and Classes used
  * Compiler
  * Instruction
  * Constant
  * InsnList
  * TurtleCollection
  * PatternParser

* GIT commits
  * Commits relating to Compiler, authored by Brandon Bae
  * ie Commit 3b2dc089efdec693686939caa3094575cebeec14
    * "Compiler parsing strings into cmd and val stack functionality added"

* Design
  * In general, I felt that there is not much in the way of design for this class. There was a lot of 
    code of different functions all in the same place.

* Evaluation
  * I thought that this class could be refactored a lot to fit the Single Responsibility Principle.
    There was a lot of code, all doing different things in the same class, which made it hard to 
    read and understand.


#### API ***teammate*** designed: TurtleInsnModel

* What is encapsulated
  * The implementation be easily changed without affecting the rest of the program. For example,
    no matter what type of data structure is used for keeping track of the next instructions, 
    this will not affect the rest of the program in any way when running runNextInsn(), since
    everything related to the instructions are encapsulated within the class.

* Easy to use and hard to misuse
  * In general, it is easy to use. All of the functions do exactly what you expect them to.
  * My only notes are:
    * Some of the functions return modifiable objects, which can can be misused
    * Some people might not expect getCreatedTurtleMap to return a map with key ID and value
      TurtleModel.

* Provisions for extension
  * I'm not sure how extendable this class is. I suppose it can be extended to hold different 
    information, but in that case the class name would not make sense. It also doesn't exactly 
    encourage users to create new functionality


### What makes an API stable and also flexible
* An API is stable if the implementation of a certain feature changes, but the way in which 
  the API is presented/used can remain the same. In other words, implementation of a feature 
  does not affect the use of the API.
* An API is flexible when users have multiple ways of interacting with the API, for example 
  having different input and output formats (JSON, XML, etc)



## Your Design

### Remaining Design Checklist Issues

* Issue #1

* Issue #2

* Issue #3


### Abstraction


### Design Challenge

* Alternate design #1

* Alternate design #2

* Trade-offs

* Justify your Preferred Solution

* Satisfaction with Chosen Solution


### Code Design Review

#### Good Example **you** implemented

* Methods and Classes used

* GIT commits

* Design

* Evaluation


#### Needs Improvement Example **you** implemented

* Methods and Classes used

* GIT commits

* Design

* Evaluation


#### API ***you*** designed

* What is encapsulated

* Easy to use and hard to misuse

* Provisions for extension


### What make code encapsulated or abstract





## Alternate Designs

### Classes affected by adding multiple turtles


### Change features affect on original design


### Assumption with significant impact


### Design Decisions

#### Decision #1

* Alternate design #1

* Alternate design #2

* Trade-offs

* Justify your Preferred Solution

* Satisfaction with Chosen Solution


#### Decision #2

* Alternate design #1

* Alternate design #2

* Trade-offs

* Justify your Preferred Solution

* Satisfaction with Chosen Solution


### What Affects your Design Decisions?




## Conclusions

### Coding Habits

* For the better

* Still working on


### Removing hardcoded values

* How is it going?

* Example


### Thinking in terms of APIs

* How is it going?

* Example


### Teammate's Code

* What was learned?

* Example


### How has your perspective on and practice of coding changed?


### What specific steps can you take to become a better designer?
