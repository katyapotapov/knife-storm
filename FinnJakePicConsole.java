/* Katya Potapov
FinnJakePicConsole.java
January 6, 2014
This thread loads and outputs an image of Finn and Jake to be used in the splash screen.

c                   reference             This variable refers to the Console class.
finnAndJake         image                 This variable stores the loaded image of Finn and Jake.
PICTURE_PATH        string                This variable stores the name of the image in the file, allowing the program to load it.
picWidth            int                   This variable stores the width of the image.
picHeight           int                   This variable stores the height of the image.
*/
import java.awt.*;
import hsa.Console;
import java.applet.*; //access to MediaTracker

public class FinnJakePicConsole extends Thread
{
    static Console c;           // The output console
    static Image finnAndJake;
    private static final String PICTURE_PATH = "FinnRidingJake.png";
    static int picWidth, picHeight;

    /* The loadImage method loads the image of Finn and Jake, checks for image load failure, and initializes the size of the image.
    tracker             reference               This variable refers to the MediaTracker class, which will check for image load failure.

    The try-catch structure catches InterruptedException when the tracker is waiting for the images to load.
    The if structure outputs an error message to the user if the image could not be loaded.
    */
    private void loadImage ()
    {
	finnAndJake = Toolkit.getDefaultToolkit ().getImage (PICTURE_PATH);
	MediaTracker tracker = new MediaTracker (new Frame ());
	tracker.addImage (finnAndJake, 0);
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
	picWidth = finnAndJake.getWidth (null);
	picHeight = finnAndJake.getHeight (null);
    }


    /* The display method runs loadImage and animates the loaded image, moving it across the screen.
    e                   reference               This variable is used to catch InterruptedException.
    The for loop starts at 0 and ends at 1159. It outputs the image of Finn and Jake moving across the whole screen.
    The try-catch structure contains a Thread.sleep command which sets the speed at which the animation moves across the screen.
    */
    public void display ()
    {
	//load image
	loadImage ();

	for (int x = 0 ; x < 1159 ; x += 3)
	{
	    try
	    {
		Thread.sleep (25);
	    }
	    catch (InterruptedException e)
	    {
	    }

	    c.clearRect (x - 60, 514, picWidth, picHeight);
	    c.drawImage (finnAndJake, x - 60, 514, null);
	}
    }

    /* The class constructor creates the object in memory for the code - this allows for the data and methods to be stored.
    con             reference               This parameter pass refers to the Console class.
    */
    public FinnJakePicConsole (Console con)
    {
	c = con;
    }


    //The run method is used by another thread to run display.
    public void run ()
    {
	display ();
    }
}
