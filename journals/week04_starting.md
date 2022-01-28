# Journal : Starting
#### Cynthia France
#### 1/28/2022


## Starting a Big Project

### Initial impression of team
I thought my team was very nice! Jose and Diane are both cool people seem willing to learn and put
effort into their projects. However, I also got the feeling that they wouldn't be upset at leaving
something half finished or a more basic version, which I'm slightly worried about.

### First team meeting experience
Since the beginning, we've had trouble scheduling time to meet as a team. First we arranged for one
time, but then had to change due to someone's activities. As such, we weren't able to meet for a 
very long time as a group of 3. Since then, we haven't met up in person or even on zoom except for
in class. Regarding this, I am quite frustrated as this meant we were behind on the project.

### First design challenge
For me personally, my first design challenge was whether to use a controller, and if so, 
if we were supposed to use one or multiple (per simulation). In the end, I decided to go with no
controller as it wouldn't do much. Another challenge I ran into was Cell subclasses. Although my
teammates (and me too, initially) think that there should be a Cell subclass for each state (ie 
burning, alive, empty, filled), I now think that there should be a subclass or two per simulation
(ie GameOfLifeCell, GroupCell, FireCell, and then 2 types of cells for WaTor: FishCell and SharkCell).
We still need to discuss this.

As for what we decided to tackle first, we agreed that the XML parser would have to be completed/working
first before we can really start on the project, so I started with that.

### First piece of code
My first piece of code was the XML parser. I took inspiration from the example given in class and was
able to adapt it for use in our project. I also created an example XML file with a basic grid 
configuration. It took sort of a long time, but in the end it was successful, so I am happy. After that,
I worked on creating a model and view and basic logic to pass the XML file information between 
everything and draw cells in the view.

### Hardest part or biggest obstacle
By far the hardest part came when we first started the project. Jose and I were both working on the
project at the same time and since the project was just starting off, we both made many changes to many 
of the files, so I had to spend around 3 hours resolving conflicts :(:(

### First coding session insights
We haven't had an official coding session yet, but in lab we got started on the project. I don't
want to look negatively upon my team member, but I felt that it was mainly Jose and I doing the work
for both the lab and the project. So far, Diane has not made a single contribution to the labs and 
projects that we have worked on together code-wise. We wrote code together for around 1 hour, and then
Jose and I went back and worked on it for many hours (at least 3 for Jose, 6 for me) that night. 
Tiredness and merge conflicts were the main things that got in my way as I was programming.

### Successful outcome
The most successful outcome would of course be meeting all the requirements of the project as well
as having well designed code. This would mean abstract classes, many classes, and clean, documented code.

On the teammate side, the best outcome would be that we all equally contribute to the project

### Worst possible outcome
The work possible outcome would be that we do not implement all the simulations and our code is
both messy and buggy. 

On the teammate side, the worst outcome would be that only one or two people do the work and this
work results in lots of conflicts that take up even more time to fix. Along with communication 
problems and different ideas for how to do things (that don't get resolved/talked about)


## Reflection


## Summaries (readings, code examples, design discussions, etc.)

### CSS Stylesheets and Languages
You can drastically change the look and feel of a window by keeping all button/object configurations
in a stylesheets file and editing that. You can also create multiple sheets and use different styles
for different purposes/occasions (ie different simulations). Similarly, you can easily toggle between
languages by associating variables/labels(?) with text that you'd like to use. Then in separate language
files, you can change the text to said language, and based on which one is used at runtime, the language
displayed will be different.

### MVC Structure
The MVC (Model, View Controller) is an easy and good way to separate the back-end from the front-end 
in a project. The purpose of this is so that the back-end (model) is completely unreliant on javafx and is
just purely code, while the front-end (view) is purely display logic. They don't have to worry much
about the specifics of things, which is what the controller (the in-between) takes care of. The 
controller also communicates between the model and the view to pass along information & perform
logic and checks.


## Feedback (mentoring, feedback, office hours, etc.)

### How to divvy up team member responsibilities
This is actually where I first learned that it'd be best to split up member responsibilities by
MVC/XML structure. Kathleen went through how her team delegated responsibility taught me the best/suggested 
way to do things. 

### breakout + the rest of the semester
Kathleen also spent so much time with me going over how to improve my breakout project and how to 
succeed in the rest of the semester. For Breakout, she told me about how later in the class, we'll 
learn about different ways to store data and pull it in instead of keeping it all in the file.