/* Katya Potapov
KnifeStorm.java
December 20, 2013
This program outputs a typing game in which random letters fall from the top of the screen with increasing speed, and the user sees and then types a
matching letter which erases to get points.

There are eight screens in this program.
intro screen: introduces the program to the user through graphics;
mainMenu screen: displays a menu of options for the user, allowing them to choose either to play, view instructions, view high scores, or exit;
levelMenu screen: displays a menu of levels for the user, allowing them to choose which level they would like to play at (1, 2, or 3) or to cancel;
display screen:
gameOver screen:
highScores screen:
instructions screen:
goodbye screen:


c                       reference               This variable refers to the Console class.
choice                  string                  This variable stores the menu choice that the user chooses in the main menu.
gameScore               int                     This variable stores the user's score, calculated based on how many keys they correctly input.
gameUsername            string                  This variable stores the username that the user enters to store their score with.
TOTAL                   final int               This variable defines the total amount of columns in the following three arrays.
score                   int[]                   This array stores the
username                string[]
levelStore              string[]
level                   string
skyBlue                 color

ladyRainicorn       image                 This variable stores the loaded image of Finn and Jake.
PICTURE_PATH        string                This variable stores the name of the image in the file, allowing the program to load it.
picWidth            int                   This variable stores the width of the image.
picHeight           int                   This variable stores the height of the image.

finnJake            image                 This variable stores the loaded image of Finn and Jake.
PICTURE_PATH_2      string                This variable stores the name of the image in the file, allowing the program to load it.
picWidth2           int                   This variable stores the width of the image.
picHeight2          int                   This variable stores the height of the image.

*/
import java.awt.*;
import hsa.*;
import java.lang.*;
import java.io.*;
import javax.swing.JOptionPane; //error window


public class KnifeStorm
{
    Console c;
    String choice;
    int gameScore;
    String gameUsername;
    static final int TOTAL = 10;
    int[] score = new int [TOTAL];
    String[] username = new String [TOTAL];
    String[] levelStore = new String [TOTAL];
    String level;
    Color skyBlue = new Color (92, 172, 238);

    //lady rainicorn image stuff
    static Image ladyRainicorn;
    private static final String PICTURE_PATH = "LadyRainicorn.png";
    static int picWidth, picHeight;


    //finn jake image stuff
    static Image finnJake;
    private static final String PICTURE_PATH_2 = "Finn_Jake_Happy.jpg";
    static int picWidth2, picHeight2;


    //The title method outputs the title of the program and clears the screen every time it is called.
    public void title ()
    {
	c.setTextBackgroundColor (skyBlue);
	c.clear ();
	c.print ("", 35);
	c.println ("Knife Storm");
	c.println ();
    }


    //The pauseProgram method pauses the program on a given screen, waiting for user input (any key) to continue.
    private void pauseProgram ()
    {
	c.println ();
	c.print ("Press any key to continue...");
	c.getChar ();
    }


    /* The intro method creates and outputs the splash screen of the program.
    smallTitleFont         font            This variable stores the Andalus font (smaller size) that is used for the program title.
    largeTitleFont         font            This variable stores the Andalus font (larger size) that is used for the program title.
    k                      reference       This variable refers to the Knife thread class, and allows it to run.
    f                      reference       This variable refers to the FinnJakePicConsole thread class, and allows it to run.

    The for loop outputs multiple Knife threads, displaying multiple knives falling from the sky. The start value is 0 and the end value is 960.
    The first try-catch structure contains a join command which makes the program wait for the Knife thread to finish executing before the program continues
    (FinnJakePicConsole has already finished executing by this time, so it does not need to be joined). The structure also contains a Thread.sleep command
    which delays the program for 500 milliseconds before it outputs the title, for aesthetic purposes.
    The second try-catch structure contains a Thread.sleep command which delays the program for 3 seconds so that the user can read the title in the splash
    screen before the program moves on.
    */
    public void intro ()
    {
	Font smallTitleFont = new Font ("Andalus", Font.PLAIN, 120);
	Font largeTitleFont = new Font ("Andalus", Font.PLAIN, 180);
	c.setColor (skyBlue);
	c.fillRect (0, 0, 960, 675);
	Knife k = null;
	FinnJakePicConsole f = new FinnJakePicConsole (c);
	f.start ();
	for (int x = 0 ; x < 960 ; x += 80)
	{
	    k = new Knife (c, x, x * 8);
	    k.start ();
	}
	try
	{
	    k.join ();
	    Thread.sleep (500);
	}
	catch (InterruptedException e)
	{
	}
	c.setFont (smallTitleFont);
	c.setColor (Color.RED);
	c.drawString ("KNIFE", 170, 220);
	c.setFont (largeTitleFont);
	c.drawString ("STORM", 290, 380);
	// for (int x = 0 ; x < 390 ; x++)
	// {
	//     c.setColor (skyBlue);
	//     c.fillRect (-101 + x, 260, 500, 100);
	//     c.setColor (Color.gray);
	//     c.fillRect (-100 + x, 260, 200, 100);
	//     try
	//     {
	//         Thread.sleep (5);
	//     }
	//     catch (InterruptedException e)
	//     {
	//     }
	// }
	try
	{
	    Thread.sleep (3000);
	}
	catch (Exception e)
	{
	}
    }


    /* The mainMenu method displays a menu of options for the user, allowing them to choose what they would like to do next.
    The while loop runs until there is a break command. Every time it is not broken and must run again, it outputs an error message and a blank line where
    the faulty information was.
    The if statement checks if choice contains the correct input (1, 2, 3 or 4). If it does, then the loop is broken and the program continues.
    */
    public void mainMenu ()
    {
	title ();
	c.println ("1. Play");
	c.println ("2. Instructions");
	c.println ("3. High Scores");
	c.println ("4. Exit");
	c.println ();
	while (true)
	{
	    c.setCursor (10, 1);
	    c.print ("Please enter your choice: ");
	    choice = c.readLine ();
	    if (choice.equals ("1") || choice.equals ("2") || choice.equals ("3") || choice.equals ("4"))
		break;
	    JOptionPane.showMessageDialog (null, "Please enter a number between 1 and 4.", "Error", JOptionPane.ERROR_MESSAGE);
	    c.setCursor (10, 1);
	    c.print ("\n");
	}
    }


    /* The levelMenu method displays a menu of levels for the user, allowing them to choose which level they would like to play at or to cancel.
    The while loop runs until there is a break command. Every time it is not broken and must run again, it outputs an error message and a blank line where
    the faulty information was.
    The if statement checks if choice contains the correct input (1, 2, 3 or 4). If it does, then the loop is broken and the program continues.
    */
    public void levelMenu ()
    {
	title ();
	c.println ("Level Select");
	c.println ();
	c.println ("1. Level 1 (Easy)");
	c.println ("2. Level 2 (Medium)");
	c.println ("3. Level 3 (Hard)");
	c.println ("4. Cancel");
	c.println ();
	while (true)
	{
	    c.setCursor (12, 1);
	    c.print ("Please enter your choice: ");
	    level = c.readLine ();
	    if (level.equals ("1") || level.equals ("2") || level.equals ("3") || level.equals ("4"))
		break;
	    JOptionPane.showMessageDialog (null, "Please enter a number between 1 and 4.", "Error", JOptionPane.ERROR_MESSAGE);
	    c.setCursor (12, 1);
	    c.print ("\n");
	}
    }


    /* The loadImage method loads the image of Lady Rainicorn, checks for image load failure, initializes the size of the image, and outputs the image.
	tracker             reference               This variable refers to the MediaTracker class, which will check for image load failure.

	The try-catch structure catches InterruptedException when the tracker is waiting for the images to load.
	The if structure outputs an error message to the user if the image could not be loaded.
	*/
    private void ladyRainicornImage ()
    {
	ladyRainicorn = Toolkit.getDefaultToolkit ().getImage (PICTURE_PATH);
	MediaTracker tracker = new MediaTracker (new Frame ());
	tracker.addImage (ladyRainicorn, 0);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	if (tracker.isErrorAny ())
	{
	    c.println ("Was unable to load " + PICTURE_PATH);
	    return;
	}
	picWidth = ladyRainicorn.getWidth (null);
	picHeight = ladyRainicorn.getHeight (null);
	//draws Lady Rainicorn
	c.drawImage (ladyRainicorn, 304, 564, null); //674 - 110 = 564
	//line that the dagger cannot cross
	c.setColor (Color.red);
	c.drawLine (0, 569, 959, 569); // 564 + 5
	c.setColor (Color.black);
    }


    /* The loadImage method loads the image of Finn and Jake in the goodbye screen, checks for image load failure, initializes the size of the image, and outputs the image.
	tracker             reference               This variable refers to the MediaTracker class, which will check for image load failure.

	The try-catch structure catches InterruptedException when the tracker is waiting for the images to load.
	The if structure outputs an error message to the user if the image could not be loaded.
	*/
    private void finnJakeImage ()
    {
	finnJake = Toolkit.getDefaultToolkit ().getImage (PICTURE_PATH_2);
	MediaTracker tracker = new MediaTracker (new Frame ());
	tracker.addImage (finnJake, 0);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	if (tracker.isErrorAny ())
	{
	    c.println ("Was unable to load " + PICTURE_PATH_2);
	    return;
	}
	picWidth2 = finnJake.getWidth (null);
	picHeight2 = finnJake.getHeight (null);
	//draws Lady Rainicorn
	c.drawImage (finnJake, 204, 64, null); //674 - 110 = 564
    }


    /* The display screen runs the game itself, creating a thread that outputs the characters falling and accepts user input for the characters.
    The thread parameter passes are used to set values within the thread. Variable c defines the Console and variable level defines the level that the
    user has selected.

    b                   reference                   This variable refers to the GameDisplay thread class, which allows this thread to run.
    e                   reference

    The try-catch structure contains a join command that
    */
    public void display ()
    {
	// title ();
	//if background in title isn't changed to this
	//note: skyBlue is declared a lot
	c.setColor (skyBlue);
	c.fillRect (0, 0, 959, 674);
	ladyRainicornImage ();
	GameDisplay b = new GameDisplay (c, level);
	b.start ();
	try
	{
	    b.join ();
	}
	catch (InterruptedException e)
	{
	}
	gameScore = b.getGameScore ();
    }


    /* The highScoreCheck method creates a new file to store high scores in if the previous one is somehow damaged, or if it does not exist.
    output                  reference                   This variable refers to the PrintWriter class and allows the user to write to the file.
    i                       reference                   This variable catches IOException.

    The try-catch structure contains output commands that output the title to a new high score file.
    */
    private void highScoreCheck ()
    {
	PrintWriter output;
	try
	{
	    output = new PrintWriter (new FileWriter ("highscores.txt"));
	    output.println ("KnifeStorm High Score Data");
	    output.close ();
	}
	catch (IOException i)
	{
	}
    }


    /* the gameOver screen outputs a message to the user once the game is over and allows them to input their username for storage in highScores.
    The if structure checks if the username entered is over 12 characters in length. If it is, it creates a substring of the username that is only 12
    characters in length, to keep the high score table intact.
    */
    public void gameOver ()
    {
	title ();
	c.print ("", 34);
	c.println ("GAME OVER! :(");
	c.println ();
	c.println ("Luckily, just as Lady Rainicorn was going to be overcome by knives, she was");
	c.println ("teleported home by Princess Bubblegum's mathematical transporter.");
	c.println ("Thanks for your help!");
	c.println ();
	c.println ("You finished with a score of " + gameScore + ".");
	c.println ();
	c.println ("Please enter your desired username (12 characters max with no blanks): ");
	gameUsername = c.readString ();
	//does a substring if the username is too long
	if (gameUsername.length () > 12)
	    gameUsername = gameUsername.substring (0, 12);
    }


    /* The newHighScore file checks if a new high score has been achieved, and if so, it stores this high score in the file.
    
    output              reference                   refers to PrintWriter
    x                   int                         used to access loop var outside of loop
    
    The first for loop runs from 0 to TOTAL, and inputs the new high scores.
    The if outputs a score to a new spot in the file.
    The next if moves all the other scores down to make room for the if.
    */
    private void newHighScore ()
    {
	PrintWriter output;
	int x = 0;
	for (x = x ; x < TOTAL ; x++)
	{
	    if (score [x] == 0)
	    {
		// c.println ("it's null!");
		score [x] = gameScore;
		username [x] = gameUsername;
		levelStore [x] = level;
		break;
	    }
	    if (gameScore > score [x])
	    {
		for (int y = TOTAL - 1 ; y > x ; y--)
		{
		    score [y] = score [y - 1];
		    username [y] = username [y - 1];
		    levelStore [y] = levelStore [y - 1];
		}
		score [x] = gameScore;
		username [x] = gameUsername;
		levelStore [x] = level;
		break;
	    }
	}
	//note: this try-catch doesn't run if the high score file doesn't need to be modified
	if (x != TOTAL)
	{
	    try
	    {
		output = new PrintWriter (new FileWriter ("highscores.txt"));
		output.println ("KnifeStorm High Score Data");
		for (int y = 0 ; y < TOTAL ; y++)
		{
		    if (score [y] != 0)
		    {
			output.println (score [y]);
			output.println (username [y]);
			output.println (levelStore [y]);
		    }
		    else
			break;
		}

		output.close ();
	    }
	    catch (IOException e)
	    {
	    }
	}

    }


    /* The highScores method checks if the high scores file is valid, and outputs the high scores in a table.
    input               reference               This variable refers to the BufferedReader class, which allows for the reading of the file.
    e                   reference               catches various exceptions (NumberFormatException, IOException)

    The while loop allows break statements to break out of this loop and go to the end.

    */
    public void highScores ()
    {
	BufferedReader input;
	title ();
	c.print ("", 35);
	c.println ("High Scores");
	c.setCursor (5, 18);
	c.print ("Score");
	c.setCursor (5, 38);
	c.println ("User");
	c.setCursor (5, 58);
	c.println ("Level");
	while (true)
	{
	    try
	    {
		input = new BufferedReader (new FileReader ("highscores.txt"));
		//checking if file is valid
		if (!input.readLine ().equals ("KnifeStorm High Score Data"))
		{
		    int errorChoice = JOptionPane.showConfirmDialog (null, "The high score file is invalid. Would you like to create a new high score file?", "Error", JOptionPane.YES_NO_OPTION);
		    if (errorChoice == JOptionPane.YES_OPTION)
		    {
			highScoreCheck ();
		    }
		    break;
		}
		for (int x = 0 ; x < TOTAL ; x++)
		{
		    try
		    {
			score [x] = Integer.parseInt (input.readLine ());
		    }
		    catch (NumberFormatException e)
		    {
		    }
		    username [x] = input.readLine ();
		    levelStore [x] = input.readLine ();
		}


		if (gameScore != 0)
		{
		    newHighScore ();
		}

		for (int x = 0 ; x < TOTAL ; x++)
		{
		    if (score [x] != 0)
		    {
			c.setCursor (x + 7, 3);
			c.print (x + 1 + ".");
			c.setCursor (x + 7, 18);
			c.print (score [x]);
			c.setCursor (x + 7, 38);
			c.print (username [x]);
			c.setCursor (x + 7, 58);
			c.println (levelStore [x]);
		    }
		    else
			break;
		}
	    }
	    catch (IOException e)
	    {
	    }
	    //so that it doesn't calculate every time you see high scores
	    gameScore = 0;
	    break;
	}


	pauseProgram ();
    }


    //The instructions method outputs the instructions for the user on how to play the game.
    public void instructions ()
    {
	title ();
	c.println ("Finn, Jake, and Lady Rainicorn got caught in a knife storm, and it's your job to");
	c.println ("keep Lady Rainicorn safe! Tap the key corresponding to the knife to destroy it");
	c.println ("with your invisible laser vision.");
	c.println ();
	c.println ("Destroy the knife before it goes past the red line,");
	c.println ("or Lady will lose one of her three lives.");
	c.println ();
	c.println ("Destroy the knife after it passes the green bonus line and receive bonus points!");
	pauseProgram ();
    }


    // The goodbye method creates and outputs a goodbye message and closes the output window.
    public void goodbye ()
    {
	title ();
	c.setColor (Color.black);
	finnJakeImage ();
	c.setFont (new Font ("Blackadder ITC", Font.PLAIN, 80));
	c.drawString ("Later, stranger!", 5, 120);
	c.setCursor (22, 1);
	c.print ("Program by Katya Potapov.");
	c.setCursor (23, 1);
	pauseProgram ();
	c.close ();
    }


    // The class constructor creates the object in memory for the code - this allows for the data and methods to be stored.
    public KnifeStorm ()
    {
	c = new Console (20, "KnifeStorm");
	//screen size in pizels: 959 x 674
    }


    /*The main method controls the flow of the program; it makes a new object for the class and executes the methods needed to run the program properly.

    k           reference       This variable refers to the KnifeStorm class.

    The do-while loop reruns the program until the user enters option 4 (exit) in the main menu - when the user does, the program goes to the goodbye screen.
    The first if statement runs levelMenu and another if statement if choice is equal to 1.
    */
    public static void main (String[] args)
    {
	KnifeStorm k = new KnifeStorm ();
	k.intro ();
	do
	{
	    k.mainMenu ();
	    if (k.choice.equals ("1"))
	    {
		k.levelMenu ();
		if (!k.level.equals ("4"))
		{
		    k.display ();
		    k.gameOver ();
		    k.highScores ();
		}
	    }
	    else if (k.choice.equals ("2"))
		k.instructions ();
	    else if (k.choice.equals ("3"))
		k.highScores ();
	    else
		break;
	}


	while (!k.choice.equals ("4"));
	k.goodbye ();
    }
}
