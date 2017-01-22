/* Katya Potapov
GameInput.java
January 4, 2014
This thread allows the program to accept user input during gameplay for the falling characters.

c               reference               This variable refers to the Console class.
userKey         char                    This variable stores the user input, a single character at a time.
gameOver        boolean                 This variable stores whether or not the game is over.

*/
import hsa.Console;
import java.lang.*;

public class GameInput extends Thread
{
    private Console c;
    char userKey;
    boolean gameOver;

    /* The userInput method uses a while loop to get user input until the game is over.
    The while loop runs if gameOver is false, and allows the user to enter input (a single character at a time). It ends when gameOver is true. */
    public void userInput ()
    {
	while (!gameOver)
	{
	    userKey = c.getChar ();
	}
    }


    /* The setGameOver method allows another thread to set the value of gameOver.
    ifGameOver            boolean             This parameter pass is used by another thread to set a new value for gameOver. */
    public void setGameOver (boolean newGameOver)
    {
	gameOver = newGameOver;
    }


    /* The class constructor creates the object in memory for the code - this allows for the data and methods to be stored.
    con               reference               This parameter pass refers to the Console class.
    newGameOver       boolean                 This parameter pass is used by another thread to set an initial value for gameOver. */
    public GameInput (Console con, boolean newGameOver)
    {
	c = con;
	gameOver = newGameOver;
    }


    //The run method is used by another thread to run userInput.
    public void run ()
    {
	userInput ();
    }
}
