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

* Issue #1: SketchbookView
  * Although I was not the only one contributing to this class, we really let this class get way too big. 
    It violates the single responsibility principle. If I had more
    time, I'd refactor this code so that more classes are utilized, splitting up responsibility
    in a way that makes sense (ie one for Animations, one for Turtle related functions, one for 
    general view functions.)

* Issue #2: Type-casting
  * There were some instances where I type-casted to a certain object type. I should work on not 
    relying on that. However, I think in some cases, this is acceptable, for example when I confirm
    that an object is of that type.


### Abstraction
* I created the Transitions/Paths and Rotations hierarchy. I think this was a good idea, since 
  it made my code much more readable, understandable, and open to extension. Now, multiple paths
  and rotations can be made from the parent classes I wrote.

### Design Challenge: Turtle Instructions

* Alternate design #1: Reflections
  * Use reflections to get the correct function for each turtle command
  * The functions would be located in the same file as TurtleModel

* Alternate design #2: Lambdas
  * Use lambdas and BiFunctions to execute the turtle commands

* Trade-offs
  * Option #1 is much more straightforward and makes more sense conceptually, but it is not 
    good design to keep all of the functions for the turtle in one place.
  * Option #2 is good in terms of design regarding splitting things up into smaller chunks, but it
    brings about other problems like circular dependencies

* Justify your Preferred Solution
  * My preferred solution is still Option 2. Even though it also has its flaws, I think the design
    overall is very good.

* Satisfaction with Chosen Solution
  * I am satisfied with this solution, although I wish we had more time to maybe come up with a 
    better solution that fixed the circular dependency issue.


### Code Design Review

#### Good Example **you** implemented: Turtle Movement

* Methods and Classes used
  * Path
    * MovementPath
    * DoNothingPath
  * Rotation
    * MovementRotate
    * DoNothingRotate
  * TurtleModel
  * TurtleView
  * PathTransition
  * RotateTransition

* GIT commits
  * All commits made by Cynthia France that mention "path" and "rotate" on March 5, 2022
  * ie "Path abstract class", 2985f4249d43f23e84fd89372e80dd734c9e8094

* Design
  * In general, each of the classes that I made for movement (Path, Rotation) are open to extension
    and closed to modification. As demonstrated in my code, many child classes regarding Paths and 
    Rotations can be made.
  * All functions relating to movement and Paths & Rotations (ie make TransitionPath) are encapsulated 
    into its respective class, such as moving forward and backward either a certain number of 
    pixels or to a location as well as rotating any number of degrees. 
  * In terms of assumptions, we assume that 0 degrees (absolute heading) is facing right (similar
    to 0 degrees on a unit circle)

* Evaluation
  * In general, my abstractions follow the single responsibility principle, help to majorly
    reduce the complexity of my code, and also allow for further extension.
  * I think the flow of my code is good and makes good use of the classes and packages I created.
  * Next time, I will focus more on distinguishing between subclasses and parent classes.
    Sometimes it felt as if the lines were slightly blurred


#### Needs Improvement Example **you** implemented: Pen up/down

* Methods and Classes used
  * SketchbookView
  * Pen
  * TurtleView
  * TurtleModel

* GIT commits
  * Commits made by Cynthia regarding the Pen that are made in SketchbookView
  * ie. "draw based on pen status" on Feb 28, 2022
    * commit 531c2e554f40b190b295157e5434f7e4c2d4cb38

* Design
  * Pen is open to extension and closed to modification. The design of the Pen itself allows
    for multiple different types of Pens to exist, such as ones that "draw" in flowers or other 
    shapes instead of a single line
  * Features including Pen size, color, and status are all encapsulated in this feature.
  * We assume that the Pen starts down.

* Evaluation
  * I think that Pen itself was designed well, but the execution/translation from backend to 
    frontend could have been improved on.
  * As of right now, the frontend (SketchbookView) needs to check for the state of the backend
    Pen status and then update TurtleView's Pen object to reflect it. I don't like how SketchbookView
    is the one that has to perform this function. It violates the single responsibility principle
    as Pen isn't directly related to movement & other turtle functions.
  * I'd improve it by separating out the functions, or moving the frontend updating of the Pen to 
    another class, or using reflections/lambda to generalize this process.


#### API ***you*** designed: TurtleView

* What is encapsulated
  * The TurtleView API is encapsulated as the implementation of its functions is hidden from the 
    user/whatever needs to use the API. The main TurtleView API is updating its state, and this
    process is completely hidden from the user. If we were to change the implementation of any 
    specific part of updating the TurtleView, nothing would change on the user end.

* Easy to use and hard to misuse
  * The TurtleView API is both easy to use and hard to misuse. The name "UpdateTurtle" is quite
    obvious as to what the function will do, especially with the required parameters, which are also
    pretty self-explanatory and intuitive. The API doesn't do anything that is unexpected. 

* Provisions for extension
  * TurtleView lays the foundation for the essential functions of a turtle, but allows users
    to create new functionality. For example, one way TurtleView could be updated is InvertedTurtleView,
    where everything is inverted. In this case, only some functions need to be overridden to 
    invert the correct turtle features.


### What make code encapsulated or abstract
* In general, I tried to make my code as encapsulated as possible. I wrote many methods to 
  separate out functions/things that the turtle would need to do, so that even if the implementation
  changes, the client-facing API method would not change whatsoever. This is good for encapsulation,
  as the use of your API does not depend on its implementation.
* I made my code abstract by creating many packages, parent classes, and child classes for each
  element of the Turtle. This way, new features can be easily added by extending from existing 
  classes.

  

## Alternate Designs

### Classes affected by adding multiple turtles
* In the backend, not many classes were affected by multiple turtles. It was just SketchbookView 
  that needed to be altered to accommodate multiple turtles. Of course, the compiler also had to 
  be majorly altered in order to keep track of/accommodate many turtles, each with different IDs.
* Of course, we also created new classes (namely TurtleCollection) which helped to document
  all turtles as well as those that are currently active.
* Compiler was affected because it needed to expand to its knowledge base.
* SketchbookView was affected because it now has to conduct the change on all the active turtles,
  rather than everyone.
* As only two things are affected by this, I don't think there's a way to reduce this number.

### Change features affect on original design
* In general, our original design wasn't too incompatible with the change features. Compared to my 
  project last time, it seems as if this group is much mor proactive". To accommodate new features, 
  we only had to expand/generalize our design to encompass the new features.
* When designing our design, we wanted to make it as general as possible so that change features 
  would be easy to add. As such, we made many abstractions and packages that took car eof 
  very specific elder needs.

### Assumption with significant impact
* When creating the Turtle, we had actually thought about the possiblity of multiple turtles. As such,
  we assumed that each Turtle would have their own command history/movement path, even possibly
  many turtles doing many things at once. 
* This definitely impacted our design, forcing us to abstract our code even further to accommodate
  everything.

### Design Decisions

#### Decision #1: Turtle Commands

* Alternate design #1
  * Use reflections to get the turtle command functions

* Alternate design #2
  * Create classes for each command and create lambdas to implement these classes on the turtle. 

* Trade-offs
  * Design 2 results in way cleaner code and makes more sense design wise, but the way in which we
    called the command classes was very circular and not intuitive.
  * On the other hand, Design 1 would make the code look messier, but reflections are straightforward
    to use, so the design of the connection between turtle, instruction, and command would be better.

* Justify your Preferred Solution
  * Even though Design 2 has some flaws, I still prefer this solution over design 1. I think that 
    this design is very easy to add new features with (ie a new turtle command). This would not be
    the case with Design 1.

* Satisfaction with Chosen Solution
  * I am satisfied with our chosen solution (design 2). If we had more time, I would've liked to 
    expand upon our solution to find one that doesn't require us to call functions in a circle 
    until what we need executed is done so.

#### Decision #2: How to split up the project

* Alternate design #1
  * Console takes in user input
  * Compiler parses through the input and does error checking, puts instructions into a queue
  * TurtleModel pops instructions off and executes them one by one
  * TurtleView updates its state to reflect the new changes
  * Execution of commands and parsing of commands happens simultaneously/independently-- parsing does
    rely on the state of the Turtle whatsoever.

* Alternate design #2
  * Similar to design 1, except compiler just does error checking and the Model parses through 
    input and split up larger, complex functions into smaller ones.

* Trade-offs
  * This is just a question of what part of the project we thought had the responsibility to split
    up instructions. Both ways, assigning this extra responsibility would not only tax/place a lot of 
    emphasis on the part of the project, but also potentially violate single responsibility.

* Justify your Preferred Solution
  * I preferred Design 1, since I thought that the purpose of a compiler was to parse through information
    and convert it into a friendlier format
  * I also thought that since the backend dealt with the Turtle, then it should be using instructions
    from an outside source/API rather than also performing those calculations themselves (use commands
    don't always have to do with the turtle itself)

* Satisfaction with Chosen Solution
  * I am very satisfied with our chosen solution. I think with the way we split up the project (console
    compiler, backend turtle/frontend turtle, pure frontend), the role of parsing instructions
    most properly fits the compiler.


### What Affects your Design Decisions?
* I think the most important thing is the reach of whatever feature you are trying to implement.
  For example, if it is a small feature like updating a state variable, this can easily be 
  encapsulated since it really only lives in a fraction of the project. However, a big feature such
  as a new command touches many aspects of the project, if not all.



## Conclusions

### Coding Habits

* For the better
  * I think my communication for this project has gotten better. I tried to communicate what I was
    doing more often, and my team also made an effort to actually meet and discuss things
    with each other, which also helped a lot with communication.

* Still working on
  * I'm definitely still working on improving design. Especially on this team, I realized that I 
    am not thinking about design enough-- sometimes it has to be the first thing you think about,
    even before brainstorming how to implement a feature


### Removing hardcoded values

* How is it going?
  * It has felt really good to make code data driven instead of hard coding values into the project.
  * Although we should be incorporating data files as we code, I have found that we tend to 
    do so at the end when we confirm that things are working properly with hard-coded values.
  * I think this is also fine, since data files adds another layer of complexity and increases
    the potential for errors, so going step by step is also a good idea.

* Example
  * For example, all of the language property files are data files that are read into the program.
    In these data files, a list of all the possible commands exist, and depend on these files
    for the correct functioning of our code.


### Thinking in terms of APIs

* How is it going?
  * Although I struggled with wrapping my head around APIs at the beginning, I think that thinking
    about public methods as APIs really helped me understand what was going on. It definitely made a 
    positive impact on how I conceptualize code. Since we plan out the APIs from the start, I am 
    conscious/aware of what all needs to be done, making that whatever I implement works with the API.

* Example
  * For example, pre-planning out the API for TurtleModel was very helpful later on when I began to
    build TurtleModel itself. It gives a backbone structure with which to work with.

### Teammate's Code

* What was learned?
  * I have learned that it is best to think about design even before you think about implementation.

* Example
  * We had to go through major refactoring for Turtle Instruction classes since I didn't think enough
    at the beginning, but looking at some of the Compiler stuff and organization, I have learned to 
    think about design first so that refactoring is as painful later on.


### How has your perspective on and practice of coding changed?
* To be quite frank, my team this time was miles better than my team last time. I felt as if
  I was in an environment where collaboration and trust was possible. I've learned so much from this
  team, especially the areas in which I can improve on, as well as my strengths. 
* Because of them, I have a newfound respect for design and will from now on value it much more than
  I did before.

### What specific steps can you take to become a better designer?
* As I highlighted before, to be a better designer, I need to be constantly thinking about design.
  Whenever I go to implement a new feature, I need to first stop and think about the best way
  design-wise to accomplish my task, and then begin coding.
