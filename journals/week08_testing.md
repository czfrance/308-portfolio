# Journal : Reflection
### Cynthia France
### 02/24/2022


## Testing: Before Supplemental Materials

### What has been your experience with testing your code prior to this course?
Prior to this course, I did a lot of manual testing (aka trying different edge cases and checking
that they were correct by myself). In high school, I had a bit of exposure to JUnit tests, but
that only consisted of the acknowledgement that they exist. We never wrote any oursleves.

### What has been your experience with testing your code during this course?
Similar to what I said above, I mainly tested my code by trying things out myself. This would 
essentially be me manually doing what a JUnit test would do: I'd perform the actions and check to
make sure the result is what it's supposed to be. I'd use a lot of print statements to check my
code and make sure that each step is what it should be.

### How confident are you that the code you write works? Why or why not?
After writing the first draft of my code, I am not at all confident that my code works. I know that
there's always something that I miss, an edge case I didn't consider, etc. In general for all
programmers, it is very rare that code works perfectly on the first go. Even if it does, there's
always ways to make it better as well.

### What are common causes of the bugs/errors you make in your code?
The majority of bugs/errors I make in my code are just a result of me not thinking of all the 
possibilities, or thinking that something I wrote would work one way when in acutality it doesn't.
There are also cases when I mis-count something, resulting in null pointer exceptions, etc.

### What are your strategies for creating happy paths tests?
The best way to create happy paths tests is to just pick out each complete component of each feature
and test to make sure that it works the way you'd like it to. For example, if you're trying to 
calculate what coordinate to move to given an angle and distance, you'd want to check to see if 
you have the correct x and y distances, then check to see if the coordinate matches what you'd
expect for a certain case.

### What are your strategies for creating sad paths tests?
Since you already have exceptions and errors in place, just create the necessary conditions so that
these exceptions are thrown. For example, if you're trying to calculate the angle from position a 
to position b using tan(dx/dy), but dy = 0, a divide by zero exception would be thrown. To check
to see if this works, write a test where dy = 0 and make sure the correct exception is thrown.

### What are your strategies for testing your code regularly?
The best way is to test extensively after each feature is implemented! This way, you don't have to
go back and debug something from a week ago that you thought was correct, but turns out it was all
wrong.


## Testing: Supplemental Materials

### Did either of these change how you think about testing?
I never thought about testing something when I hadn't finished implementing it. In the TDD example,
they created tests and tested the code as they made more and more progress. This was something
that I hadn't thought of previously. 

### What other kinds of errors can you think of beyond basic functionality bugs?
Beyond basic functionality bugs, other kinds of errors could be ones that your specific program would
like to not accept. For example, having negative coordinates is perfectly fine, but perhaps your 
specific program only wants the user to use positive coordinates. The same goes with unicode symbols/languages.

### What ideas do you have about testing beyond Unit testing (hand writing specific inputs for individual methods)?
Beyond unit testing, there's also integration testing, where you test if the
different components are working together correctly. In Slogo, unit tests could be thought of as 
testing for just the model, just the view, just the compiler, etc, which integration testing would be
testing to see if these things work together correctly. of course, this is also a very small scale
example. A bigger better example would probably be something like a company product, where aspect one
of the company (say land surveying) must work together with another different part (ie creating
3D representation of the land). These two are both big tasks that have many smaller components, for
which unit testing would be ideal. Then to test the both, we'd use integration testing.


## Reflection: Interesting Notes

### Thing #1
The biggest reflection point for me would definitely be my first team in CS 308. It was... stressful
to say the least. Especially now that I see how much better is it when I'm on a good team, I can
safely say that my first project was not a great experience. I was left to do the majority of the
work, and I was always stressed about even getting stuff done, knowing that if I didn't get started,
noone would do any work whatsoever. Nevertheless, it was all the same an important experience for
me. I learned a lot from it, such as how to deal with uncooperative teammates and now I have a
unique experience under my belt to draw from in future situations.

### Thing #2
On the other hand, I'd like to gush about my new team. I think we really click together and have a 
nice synergy. Everyone is eager to contribute, pull their weight, and provide feedback. I've learned
sooo much over the past week or so and am very happy with how things are going. If anything, I feel
slightly out of my comfort zone because my teammates are so knowledgeable. I am both comfortable
voicing my ideas, but also feel the need to constantly improve so that I am contributing to the 
team. I find myself in a very very different position from last time, but I am very satisfied with
how everything's going.

### Thing #3
I'd never given feedback/coded with anyone until my current group worked on the labs together. I find
it very fun when one person connects their computer to a TV and we all get to work on the lab 
together (it's so much better than pair programming!). During that first session, though, I 
definitely was out of my comfort zone giving my teammates suggestions on what to code/how to solve
the problem, or catching small mistakes in their code. However, it was very fun because the reception
of my feedback was great and everyone did the same thing!

### Thing #4
In regard to trying new things, I've gotten much better at being okay with just coding something
and seeing if it works. Before, I'd always worry about if something works completely right on the
first try, but now, I realize that it's important to just give new things a try. For example, in
lab I just decided to give reflections a go and see what all broke, and that was not only much 
more fun (because my teammates were also there thinking about it with me), but also took less time
to get something down.


## Summaries

### Thing #1
Reflections-- It was hard to wrap my head around this at first, but I just have to remember that
all classes inherit from a Class Class and Methods from a Method Class. We can obtain classes and 
their methods the same way we obtain objects and their attributes. To get the method of a class, 
you must match up the method name (passed as a String) as well as the method's required parameter types.

### Thing #2
We haven't 100% decided to go with this idea, but we're thinking of using Consumer to allow our
Turtle to perform its necessary functions. How it'd work is that each function would be its own 
class with a run() method. This method would call Turtle's "run"/"doStuff" function and pass in
the increment as well as a Consumer, which is a lambda with the instructions that we'd like the
turtle to do. The turtle would call Consumer.invoke() for everything to be executed.


## Learning from Feedback

### Thing #1
I've still got a long way to go in terms of good design. There are a lot of things that I'm not used
to design-wise and I am still very much functionality-focused/centric when it comes to programming. 
However, I am very thankful that my teammates this time are good at design and remind me to think of
ways to improve my design.

### Thing #2
An area I can improve in is committing more often. I think I've been steadily improving in this 
area. I basically did none in Breakout, then some in Cell Society. Now, I think I more consistently 
commit and very rarely forget to. However, I can always do better. Something else I should improve on
is not taking the lazy way initially. A lot of the times I do something just so that I can get it 
down, but forget to change it to the better/preferred way later, so it just stays bad (ie hard-coded
values).