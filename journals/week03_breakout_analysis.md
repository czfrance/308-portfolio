# Journal : Improvement Analysis
#### Cynthia France
#### 1/20/2022


## Planning

* Did you over- or underestimate the size of this project?
  * I definitely underestimated the size of this project. While planning/brainstorming, I found an
    idea that I liked and quickly became too ambitious.

* How did thinking about the entire project before coding help or hurt your efforts?
  * I think it helped much more than it hurt. For one, I was able to just sit down and think
    about what I wanted my project to be like. Had I gone straight to programming, I would not
    have been nearly as satisfied with my work not would I have had nearly as good of a game (it'd
    be a lot more plain)
  * However, thinking about the entire project also resulted in my ideas and excitement getting
    the best of me, so I came up with too many features and ideas.

* How can you plan better in the future?
  * Next time, I need to begin planning with a clear idea of the scope of the project, how much time
    everything would take, and my abilities, so I don't over-plan
  * I think it would also be best to start earlier.


## Project Management

* How did you start this project (from basically nothing) and how were you able to make progress?
  * I started this project from the prompt/assignment that was given to me. I did use the Bouncer
    example from class/lab as a guide for how to begin my project.
  * I made progress by first building the foundations of the game, then slowly adding more and more
    things to it.
  
* How did you choose or prioritize different parts?
    * Early: Building the foundations: firstly, I had just a random paddle, block, and ball that 
        would interact with each other. Then, I found the images that I wanted to use in my game, 
        implemented reading from a file, and made sure that all logic (ie # of block hits, how the
        ball moves, blocks being deleted, #lives & blocks hit, etc.) made sense & worked.

    * Middle: Once I had a working basic breakout game, I started implementing the special features
        that I had planned for the blocks, paddle, ball, etc. Then, I worked on the cheat keys.

    * Late: Once a full game could be played in a single window, I moved on to figure out & implement
        the splash & results screen as well as how/when each would show up (along with the main
        game screen). Since some cheat keys depend on that, I finished cheat key implementation after
        I got the screens working. Then, I focused on making everything look good visually/on the
        screen. Finally, I refactored & documented.

* How can you better manage (your part of) a project in the future?
  * I should definitely start earlier and get used to working on code in smaller chunks of time.
    through this project, I discovered that I dislike working on code if I know I won't be able to
    do it for an extended amount of time/might get interrupted, so I always waited until VERY late
    at night to work on the project. This was not ideal at all. I need to learn to work in smaller
    and shorter chunks of time. 
  * To do so, I must be able to better divvy up my tasks & set smaller goals that lead up to bigger
    ones.


## GIT

* Do you feel you committed often enough that others could have reviewed, understood, and integrated
  your commits in a timely manner in a team setting? Why or why not?
  * I definitely did not. As I previously mentioned, I like to work in very large chunks of time, and
    would often forget to commit until I finished my "session". This would involve me making many
    leaps and bounds of improvements and changes between commits, which is not ideal.
  * I got better by the end, though. I think by the end (last day) of working on the project, my
    frequency of commits was approaching acceptable.
  * I also noticed that my commits tended to include pretty much every single file in my project,
    which would not be easy at all to review.

* Do you feel your commit messages accurately represent your project's "story"? Why or why not 
  (provide specific examples)?
  * I think it gives a very birds-eye view of my project's story, perhaps something you'd find 
    as a synopsis and less of a chapter summary. Since I didn't commit often enough, I know for a 
    fact some of my commits were "got A, B, C, D, E, and F done and working", or "finished coding 
    the entire game."


### GIT Best Practices.

* Commit #1
    * 1/15/2022: implemented slippery paddle, adjusted requirements of slippery & salted paddle
      * Files changed: 
        * ``src/main/java/breakout/Ball.java``
        * ``src/main/java/breakout/Block.java``
        * ``src/main/java/breakout/Breakout.java``
        * ``src/main/java/breakout/Paddle.java``

    * What was the purpose(s) of this commit and what kind of coding did it reflect (i.e., new code,
      refactoring, debugging, etc.)?
      * The purpose of this commit was to add a new feature into my game and adjust the requirements
        of this and a related feature so that it is more reasonable (found this issue as I was 
        implementing the new feature)
      * This represents new code.

    * Why did you choose to package the changes in this commit as you did?
      * I chose to package the changes like this because it just involves a single aspect/feature
        of my project. (a paddle feature)

    * What made this commit important to the overall project?
      * This commit was important because it basically set the foundation for my game paddle, which
        is an integral part of the game.

    * What about this commit could be improved to better follow a best practice?
      * It would be better to split this commit up into 2 commits: one implementing the new
        feature, and then a follow-up that adjusted the requirements/specifications


* Commit #2
    * 1/17/2022: scene logic is fully working, all cheat keys work correctly, bugs exist
      * Files changed:
        * ``src/main/java/breakout/Breakout.java``
        * ``src/main/java/breakout/Main.java``
        * ``src/main/java/breakout/SplashScreen.java``
        * ``src/main/java/breakout/SceneController.java`` --> deleted

    * What was the purpose(s) of this commit and what kind of coding did it reflect (i.e., new code,
      refactoring, debugging, etc.)?
      * The purpose of this commit was to push code that correctly takes a player through the game.
      * This represents new code.
      * As the nature of cheat keys and scene flow logic are intertwined (especially when skipping
        or fast-forwarding levels), this commit also indicates that all cheat keys work, but that
        more bugs exist & need fixing.

    * Why did you choose to package the changes in this commit as you did?
      * packaged the changes this way because it only involves a single aspect/feature
        of my project. (Screen flow/logic)

    * What made this commit important to the overall project?
      * This commit was expriement important to the overall project. Without it, breakout.java would
      * just be a dumb class that does stuff.

    * What about this commit could be improved to better follow a best practice?
      * I could have refactored much more. I got more into detail in the DESIGN.md readme, but
        I would have created 1 or 2 more classes out of methods that could've been grouped together
        and serve a coherent purpose (ie. Effects + subclasses PowerUps and Disadvantages)


## Coding

* Which kinds of tasks were easiest for you and which were hardest?
  * The easiest tasks were creating the objects, drawing them, and getting inter-game features & logic
    to work as I'd like it to.
  
* Where is your code not DRY (i.e., where does duplication exist within your code)?
  * When creating images/text, when checking if the game is over/has been won or lost

* How could your code do better Telling the Other Guy (i.e., empower a class to do something itself
  rather than doing it yourself in the "primary" class)?
  * As I'd said above, I'd create at least 2 new classes, a BreakoutMap and an Effects class. Effects
    would have 2 subclasses PowerUps and Disadvantages.


### Clean Code

* Very Readable method: moveBlocks()
    * Method's purpose
      * To update the positions of all blocks in the game
    * What makes this method easy to read and understand
      * This is the only thing that the function does. It's passed in a List of List of Blocks,
        loops through the blocks and calls block.move() on all of them (given the block isn't null)
    * Why do you feel this method does (or not) embody the Single Responsibility Principle
      * It embodies the Single Responsibility Principle because its only job is to move the Blocks.
        Other than that, it doesn't do anything else.
    * How much did this method benefit (or not) from refactoring and how many "drafts" did it go through?
      * It benefitted a bit from refactoring. Since the function itself is pretty simple, the only
        real change I made was delegating the actual block moving to the Block class. When I first 
        wrote the function, it was the function itself that called block.setX() and block.setY() 
        instead of the block calling it on itself. 

* Not very Readable method: destroyBlock
    * Method's purpose
      * The method's purpose is to remove the block that was destroyed from root and set its value
        to null. 
    * What could make this method easier to read and understand
      * The first thing I should've done (not just in this function) was to not make the ``blocks``
        an List of List of Blocks. I've just now realized that this was unnecessary, as I initially
        drew the blocks from the file, and anything subsequent I would reloop through the list, thus
        removing the need for a rigid grid-like structure of blocks (especially since the blocks move
        around anyway, so even figuring out blocks that need to be effected for AOE power-ups don't
        rely on this structure). That way, I could just pass in the block itself instead of its 
        location in the List. I also wouldn't have to set the Block to null, and could instead just
        remove it from the List.
      * Another separate thing I would do is make an abstract method in the Block class that 
        does whatever a block needs to do once it is destroyed (ie a special effect). That way, I
        can just call block.doEffect (or something along those lines) instead of first checking if 
        the block is one of the blocks with special effects and then calling the specific block 
        function it's supposed to do if it is that type of Block. This would avoid the potential for
        very long if statements that was mentioned in class.
    * Why do you feel this method does (or not) embody the Single Responsibility Principle
      * It does not embody the Single Responsibility Principle. It does several things:
        1. Set the block to null in the blocks List
        2. Remove the block from root
        3. Check if the block is of type A or B and if it is, then perform A's or B's special
           function
           * even within this check, one type of block could return 2 different things, which also
             needs to be checked...
    * How much did this method benefit (or not) from refactoring and how many "drafts" did it go through?
      * It benefitted a bit from refactoring. For instance, I delegated specific block tasks to 
        that specific block, but now realize that there could've been more of that.


## Design

### How to add a new level variation to the game
Create ``.txt`` block map with the following format:
* First line: 2 numbers, separated by space, of the size of the map: ``numCols`` ``numRows``
* The next ``numRows`` rows: the actual block map, with the characters:
  * ``0``: no block
  * ``1``: snow block
  * ``2``: wood block
  * ``3``: brick block
  * ``4``: concrete block
  * ``5``: steel block
  * ``A``: snow angel block
  * ``B``: black ice block
* blocks should be separated by a **space**
* a newline character (just hit enter) should follow the last block in a row
  * NO SPACES AFTER THE LAST BLOCK
* Place file in the ``resources`` folder (``src/main/resources``)
* Create a new constant with its file path (``src/main/resources/lvlName.txt``)
* Update ``Main``'s ``NUM_LVLS`` CONSTANT
* Add the new level to the ``getMap()`` function in ``Breakout.java``
* Document this feature in the ``README`` markdown file

### Where is your code not SHY (i.e., what dependencies exist between classes or methods)?
* I'm not sure if this is an example, but in my SnowAngelBlock class, one of the functions is 
  an effect that the block can have on other blocks. However instead of just figuring out which
  blocks to effect, this block goes ahead and performs the effect as well, even though the list of 
  block was passed into it.


### Trade-offs

* Design issue that worked out
    * Describe the issue and the trade-offs you considered
      * Issue: too many types of blocks and powers, my Block class was getting very messy
      * I could either have very long methods that took care of every possibility, or many many
        short methods.
    * What alternatives did you consider?
      * Create a new class for each subtype of Block (with a power)
    * How did you eventually handle it?
      * I created the sub classes
    * Are you satisfied with the final solution? Why or why not?
      * Mostly! I think the subclasses were great, but I could've definitely made some Block 
        methods abstract and thus streamline my main Breakout code.


* Design issue that could still be improved on
    * Describe the issue and the trade-offs you considered
      * Issue: wayyy too many images needed to be loaded in
      * This involved many lines of setting image file paths, and then an equal obscene number of 
        creating the Image objects. However, I needed these, otherwise I wouldn't be able to have
        all the necessary images
    * What alternatives did you consider?
      1. Create a class that takes care of all the images, loads, them, stores them, organizes them
      2. dictate the file path and load the image/create the Image all in one go
    * How did you eventually handle it?
      * I chose to create a new class.
    * Are you satisfied with the final solution? Why or why not?
      * I'm not completely satisfied. Although now my code is organized better, I still think that 
        new class is slightly dumb and should do more than just create images and return them on
        request.

### Features

* Feature that is well-designed: Moving blocks
    * Classes, methods, or variables that are required in the implementation of this feature
      * ``Block``
        * ``speed``: the base max speed of the block, a ``final``
        * ``currSpeed``: the current base speed of the block (either ``speed`` or ``0``)
        * ``speedFraction``: the fraction of ``currSpeed`` the block is actually travelling at
        * ``angle``: the angle at which the block travels
        * ``move()``: sets the new location of the block
        * ``freeze()``: sets ``currSpeed = 0``
        * ``unfreeze()``: sets ``currSpeed = speed``
      * ``Breakout``
        * ``BASE_BLOCK_SPEED``: dictates the base block speed of a block, is multiplied by the 
          game level to set the base block speed for each level
        * ``step()``: updates locations of all game elements, including the blocks
    * Did the design of this feature change at all during its own implementation or with the 
      implementation of a latter feature? Did its implementation change any earlier features?
        * Yes. I had somewhat of a hard time deciding which classes should keep track of which parts
          of the block information. For example, I initially had all things related to block speed 
          in the ``Breakout`` class, but realized that it should probably belong to the ``Block`` 
          class instead.
    * Why are you satisfied with your design?
        * I am very happy with this design because I am confident I can change things about this 
          feature/add new features that involve this feature without too much of a hassle. For 
          example, the power-up winter freeze freezes all blocks in place for a certain amount
          of time, and I was able to implement this without much difficulty.
    * Design details
        * What were your goals did you have (beyond simply making it work)?
          * Originally, my goal was just to make the blocks move at a certain speed depending on the
            level. However, as I began coding, I decided to make it fancier by starting the blocks
            off at a slower speed and then gradually increasing their speed up to a certain maximum,
            hence the ``speedFraction`` variable and its relation to ``currSpeed``.
        * What assumptions did you make (to make it easier, etc.) and did they affect any other 
          parts of the program?
          * I didn't make any assumptions about this-- maybe that the speed would depend on the level?
        * Did it make implementing any future features easier (or was it made easier by an earlier 
          feature)?
          * Yes. As I talked about in "why am I satisfied", it made implementing one of my power-ups 
            very easy, as everything relating to this feature was all consolidated in one place and
            easy to keep track of.



* Feature that could be improved: Slippery Paddle
    * Classes, methods, or variables that are required in the implementation of this feature
      * ``Ball``
        * ``SLIP_ANGLE``: the additional angle that's added (from vertical) that the ball will 
          travel
        * ``slip()``: calculates & sets the new angle of the ball
      * ``Paddle``
        * ``slippery``: ``boolean``, whether paddle is slippery
        * ``makeSlippery()``: set ``slippery`` to true
        * ``isSlippery()``: returns ``slippery``
      * ``Breakout``
        * ``DISADVGS``: list of disadvantages, includes this feature
        * ``slipperyPaddle()``: calls ``paddle.makeSlippery()``
        * ``doDisAdv()``: calls ``slipperyPaddle(true)``
        * ``stopDisAdv()``: calls ``slipperyPaddle(false)``
    * Did the design of this feature change at all during its own implementation or with the 
      implementation of a latter feature? Did its implementation change any earlier features?
      * Yes. I changed the parameters of the feature after implementing another paddle feature.
    * Why are you unsatisfied with your design?
      * I think that it is present in too many part of my code. It's not consolidated at all and
        a potential might have to result in changes in all of these parts, which is not ideal and
        I may miss a part.
    * Design details
        * What your goals did you have (beyond simply making it work)?
          * my goal was for the feature's affect to look convincing and make sense in relation
            to my theme (winter).
        * What assumptions did you make (to make it easier, etc.) and did they affect any other 
          parts of the program?
          * I randomly dictated the degree to which my feature would affect the game (in this case,
            it was the additional angle at which the ball would travel in)
        * Did it make implementing any future features easier (or was it made easier by an earlier 
          feature)?
          * Although I did not base any additional features on this feature, my guess is that 
            it would've been pretty complicated to add an additional feature relating to this one,
            since my code for its implementation is so spread out.



## Conclusion

* What kind of coding did you spend the most time on (e.g., debugging, writing features, 
  refactoring, learning new tools/frameworks, or even just thinking)?
  * I spent the most time on writing new features and refactoring the code.
  * An Honorable Mention: figuring out how to switch between different screens
  
* What coding habits did this project make you want to change and/or convince you to keep?
  * I need to learn how to work in shorter periods of time and not be too picky on whether
    I would be interrupted. I only wanted to work on the project when I had many hours consecutively,
    so I would not start working until ~midnight and then stop at like 6am... oops...
  * I also discovered that making a plan/brainstorming (aka part 1 of the project) is very helpful
    and curbed some of my spontaneous tendencies
  * I also need to commit more often when I finish smaller tasks/chunks of code
  
* How could you improve your overall coding process to make it more reliable, efficient, enjoyable, 
  etc. in the future?
  * I basically need to do everything that I highlighted above. That would greatly reduce the 
    stress placed upon me. 