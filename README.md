# SDDAssignment
term 2 SDD Assignment

This assignment involved me creating a text typing game that challenged players to try and type words correctly
and gain points based on the word.

In my original implementation of this, I attempted to allow the player to score points for partial
correctness in that if you type half a word correctly, it would apply half the score of that word.
This proved to be challenging however, so I changed the implementation to instead by limited to
only providing points when the entire word was correctly spelt.

The scoring system I developed is simple, and scores points for the player by the length of the word,
an example being if you typed "Home" correctly you would score 4 points.

For the GUI interface I used a tool imported into Eclipse (The IDE used to develop this project) to create the GUI
visually first, then allowing the tool to create the code through that, tweaking the code here and there
to tune things after the fact.

I originally wanted to make the game use text arrays held in the program to supply the "questions" but instead
opted for the more interesting challenge of implementing external text files to draw from. This provided ample challenge as I
had to wrestle with Java file management and navigation as I found that the program could not always find the text files when called.
Eventually I opted to use a "Resources" folder internally within Eclipse's project heirarchy which then held the separate text
files.
