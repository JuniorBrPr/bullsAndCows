# Bulls and Cows game
This is an interpretation of the bulls and cows game written in Java. Bulls and Cows is a code-breaking game in which 
one player thinks of a secret code, and the other player tries to guess the code. The code consists of a series of 
symbols (typically digits or letters) and is a certain length. The player who is trying to guess the code can make as 
many guesses as they want, but they only have a limited number of turns to do so.

## Running the game
To play the Bulls and Cows game, create an instance of the BullsCows class. The game will start automatically and prompt
the player to enter their guesses. The player can enter their guess as a string of characters. The `game()` method will 
then check the guess and give the player feedback on the number of bulls and cows. The player wins if they guess the 
secret code correctly before running out of turns.

### BullsCows class properties
* `turn`: an integer that keeps track of the current turn of the game. It is initialized to `0` in the constructor.

* `code`: a character array that stores the secret code that the player needs to guess. It is initialized in the setCode
method.

* `set`: a Set of characters that stores the symbols that the secret code contains. It is initialized in the 
setCode method. Used to keep track of the symbols that the secret code contains while generating the secret code.

### BullsCows class methods
* `BullsCows()`
The BullsCows class has a single constructor that takes no arguments. It initializes the turn property to 0, calls the 
setCode method to set the code and set properties, and then calls the game method to start the game.

* `game()`
The game method is a loop that runs until the player guesses the secret code. It prompts the player to enter their guess
and checks if it matches the secret code. If the guess is correct, the player wins and the loop ends. If the guess is 
incorrect, the game method gives the player feedback on the number of bulls (correct symbols in the correct position) 
and cows (correct symbols in the wrong position). The game method also increments the turn property after each guess.

* `setCode()`
The setCode method is called by the constructor to set the code and set properties. It prompts the player to enter the
desired length of the secret code and the number of possible symbols that the code can contain. It then generates a 
random secret code using the specified length and symbols and stores it in the code property. It also stores the set of 
possible symbols in the set property.

