# Journal : Improvement Analysis
#### NAME
#### DATE


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

* Very Readable method
    * Method's purpose

    * What makes this method easy to read and understand

    * Why do you feel this method does (or not) embody the Single Responsibility Principle

    * How much did this method benefit (or not) from refactoring and how many "drafts" did it go through?


* Not very Readable method
    * Method's purpose

    * What could make this method easier to read and understand

    * Why do you feel this method does (or not) embody the Single Responsibility Principle

    * How much did this method benefit (or not) from refactoring and how many "drafts" did it go through?



## Design

* How to add a new level variation to the game

* Where is your code not SHY (i.e., what dependencies exist between classes or methods)?


### Trade-offs

* Design issue that worked out
    * Describe the issue and the trade-offs you considered

    * What alternatives did you consider?

    * How did you eventually handle it?

    * Are you satisfied with the final solution? Why or why not?


* Design issue that could still be improved on
    * Describe the issue and the trade-offs you considered

    * What alternatives did you consider?

    * How did you eventually handle it?

    * Are you satisfied with the final solution? Why or why not?


### Features

* Feature that is well designed
    * Classes, methods, or variables are required in the implementation this feature

    * Did the design of this feature change at all during its own implementation or with the implementation of a latter feature? Did its implementation change any earlier features?

    * Why are you satisfied with your design?

    * Design details
        * What were your goals did you have (beyond simply making it work)?

        * What assumptions did you make (to make it easier, etc.) and did they affect any other parts of the program?

        * Did it make implementing any future features easier (or was it made easier by an earlier feature)?



* Feature that could be improved
    * Classes, methods, or variables are required in the implementation this feature

    * Did the design of this feature change at all during its own implementation or with the implementation of a latter feature? Did its implementation change any earlier features?

    * Why are you unsatisfied with your design?

    * Design details
        * What were your goals did you have (beyond simply making it work)?

        * What assumptions did you make (to make it easier, etc.) and did they affect any other parts of the program?

        * Did it make implementing any future features easier (or was it made easier by an earlier feature)?



## Conclusion

* What kind of coding did you spend the most time on (e.g., debugging, writing features, refactoring, learning new tools/frameworks, or even just thinking)?

* What coding habits did this project make you want to change and/or convince you to keep?

* How could you improve your overall coding process to make it more reliable, efficient, enjoyable, etc. in the future?