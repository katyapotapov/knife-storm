/* Katya Potapov
Ms Dyke
December 21, 2013
This class creates the display for the game. In this display, knives with letters on them fall, and the user must enter the correct letter to erase the knife.

c                       reference               This variable refers to the Console class.
randomGenerator         reference               This variable refers to the Random class, and allows for the creation of a random integer later in the code.
levelChoice             String                  This variable stores which level the user chooses to play at.
a                       reference               This variable refers to the GameInput thread class and allows for this thread to be run within the program.
gameScore               int                     This variable stores the total game score accumulated by the destroying of the daggers.
*/
import java.awt.*;
import hsa.Console;
import java.lang.*;
import java.util.Random;

public class GameDisplay extends Thread
{
    private Console c;           // The output console
    Random randomGenerator = new Random ();
    String levelChoice;
    private GameInput a;
    int gameScore;

    /* The display method outputs the falling knives with letters on them that the user must destroy by entering the correct letter.
    letterFont              font                    This variable stores the font for the falling letters.
    sleepTime               int                     This variable stores the amount of time that it takes for the knife to fall based on the selected level.
    missedKnives            int                     This variable stores how many knives the user has missed. If they have missed three, the game ends.
    totalKnives             int                     This variable stores the total amount of knives that have fallen. This is used to calculate the increasing speed of the game.
    randChar                char                    This variable stores the random character generated every time a new knife falls.
    scoreIncrement          int                     This variable stores the amount by which the score should increase per knife destroyed based on the level.
    x                       int                     This variable is the loop variable for the knives falling, declared locally to allow for access of the variable outside of the loop.
    gameOver                boolean                 This variable stores whether or not the game is over.
    randString              string                  This variable stores the string equivalent of randChar for outputting on the screen.
    randInt                 int                     This variable stores the random integer that determines where a knife falls, generated every time a new knife falls.
    xPolygon                int[]                   This array stores the x-coordinates for the triangle outputted as part of a falling knife.
    yPolygon                int[]                   This array stores the y-coordinates for the triangle outputted as part of a falling knife.
    e                       reference               This variable catches InterruptedException.

    The first if, else if, and else set different values for sleepTime (the speed at which the daggers fall) and scoreIncrement (essentially a level
    multiplier - gives more points per dagger in hard, and less in easy) based on which level the user has selected.
    The while loop runs the main part of the game while gameOver is false (while the game is not over). gameOver becomes true when the user has missed three knives.
    The for loop makes the knives fall until they are at the red line or until the user enters the correct input. It starts at 0 and ends at 568.
    The try-catch structure contains a Thread.sleep command, which sets the amount of time that it takes the animation to run.
    The next if statement breaks out of the loop if the user's entry is equal to the current falling character.
    The next if statement increases the falling knife's speed if a multiple of five knives have fallen (ie. every five knives the falling speeds up).
    The next if statement checks if x is less than 568. If so, this means that the user destroyed the dagger before it fell all the way to the red line,
    and they should get points for this.
    The if and else statement within this previous statement checks if x is greater than 400. If so, the dagger was within the green line and the user receives
    bonus points for this. If not, it simply adds the normal amount of points.
    The else statement after these statements (referring to the if statement checking if x is less than 568) increase missedKnives, because the knife has
    hit the red line.
    The if statement within this else statement checks if the user has missed three knives. If they have, then the game is now over


    */
    public void display ()
    {
	Font letterFont = new Font ("Sans Serif", Font.PLAIN, 30);
	int sleepTime;
	int missedKnives = 0;
	int totalKnives = 0;
	char randChar;
	int scoreIncrement;
	int x;
	boolean gameOver = false;
	String randString;
	int randInt;

	if (levelChoice.equals ("1"))
	{
	    sleepTime = 20;
	    scoreIncrement = 8;
	}
	else if (levelChoice.equals ("2"))
	{
	    sleepTime = 15;
	    scoreIncrement = 12;
	}
	else
	{
	    sleepTime = 10;
	    scoreIncrement = 18;
	}
	//get ready for game - add later
	while (!gameOver)
	{
	    //randomize randChar
	    randChar = (char) (randomGenerator.nextInt (25) + 97);
	    //make randChar string
	    randString = String.valueOf (randChar);
	    //set randInt - where the char will enter the screen
	    randInt = randomGenerator.nextInt (934); //959 - 25 = 934
	    Color skyBlue = new Color (92, 172, 238);
	    for (x = 0 ; x < 568 ; x += 2)
	    {
		int[] xPolygon = {randInt, randInt + 10, randInt + 20};
		int[] yPolygon = { - 55 + x, x, -55 + x};
		c.setColor (Color.green);
		c.drawLine (0, 400, 959, 400);
		c.setColor (skyBlue);
		c.fillRect (randInt - 20, -122 + x, 60, 60);
		c.setColor (new Color (255, 215, 0));
		c.fillOval (randInt, -120 + x, 20, 20);
		c.setColor (new Color (238, 207, 161));
		c.fillRect (randInt - 2, -100 + x, 24, 40);
		c.setColor (new Color (238, 201, 0));
		c.fillRect (randInt - 20, -60 + x, 60, 5);
		c.setColor (new Color (205, 200, 177));
		c.fillPolygon (xPolygon, yPolygon, 3);
		c.setColor (Color.black);
		c.setFont (letterFont);
		c.drawString (randString, randInt + 1, -70 + x);
		try
		{
		    Thread.sleep (sleepTime);
		}
		catch (InterruptedException e)
		{
		}
		if (a.userKey == randChar)
		{
		    break;
		}
	    } //for x
	    //erases dagger when done
	    c.setColor (skyBlue);
	    c.fillRect (randInt - 20, -122 + x, 60, 122);
	    //speed increase
	    totalKnives++;
	    //if totalKnives is a multiple of 5
	    if (totalKnives % 5 == 0)
		sleepTime--;

	    if (x < 568)
	    {
		if (x > 400)
		    gameScore += (scoreIncrement + (scoreIncrement / 2));
		else
		    gameScore += scoreIncrement;
	    }
	    else
	    {
		missedKnives++;
		if (missedKnives == 3)
		{
		    a.setGameOver (true);
		    gameOver = true;
		    c.setColor (Color.black);
		    c.setFont (new Font ("Sans Serif", Font.PLAIN, 16));
		    c.drawString ("Press any key to continue...", 5, 645);
		}
	    }
	    c.setCursor (23, 1);
	    c.print ("Score: " + gameScore + " Lives: " + (3 - missedKnives));
	    c.setColor (Color.black);
	    c.drawRect (0, 590, 230, 60);
	}
    }


    public int getGameScore ()
    {
	return (gameScore);
    }

    /* The class constructor creates the object in memory for the code - this allows for the data and methods to be stored.
    con               reference               This parameter pass refers to the Console class.
    newLevelChoice    string                  This parameter pass is used by another thread to set an initial value for levelChoice. 
    */
    public GameDisplay (Console con, String newLevelChoice)
    {
	c = con;
	levelChoice = newLevelChoice;
    }

    /* The run method is used by another thread to run this thread. The GameInput thread is started here, and the display method is run. 
    
    e               reference               This variable catches InterruptedException.
    
    The try-catch contains a join command, so that the next method does not start if GameInput is not over.
    */
    public void run ()
    {
	a = new GameInput (c, false); 
	a.start ();
	display ();
	try
	{
	    a.join ();
	}
	catch (InterruptedException e)
	{
	}
    }
} // GameDisplay class


