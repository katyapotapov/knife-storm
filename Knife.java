/* Katya Potapov
Knife.java
January 6, 2014
This thread outputs an animation of a knife falling used in the splash screen.

c                   reference             This variable refers to the Console class.
location            int                   This variable is used to store the y-coordinate of the dagger.
wait                int                   This variable is used to store the amount of time from execution to the time that the knife falls.
*/
import java.awt.*;
import hsa.Console;
import java.lang.*;

public class Knife extends Thread
{
    static Console c;           // The output console
    int location;
    int wait;


    /* The knife method outputs the falling knife.
    xPolygon                int[]          stores the x-coordinates of the polygon
    yPolygon                int[]          stores the y-coordinates of the polygon
    e                       reference      catches InterruptedException

    The first try-catch sets the amount of time from execution to the time that the knife falls.
    The second try-catch sets the amount of time it takes for the knife to fall.
    */
    public void knife ()
    {
	try
	{
	    Thread.sleep (wait);
	}
	catch (InterruptedException e)
	{
	}

	Color skyBlue = new Color (92, 172, 238);
	for (int x = 0 ; x < 674 ; x++)
	{
	    int[] xPolygon = {20 + location, 30 + location, 40 + location};
	    int[] yPolygon = { - 55 + x, x, -55 + x};
	    c.setColor (skyBlue);
	    c.fillRect (location, -101 + x, 60, 100);
	    c.setColor (new Color (255, 215, 0));
	    c.fillOval (20 + location, -100 + x, 20, 20);
	    c.setColor (new Color (139, 90, 0));
	    c.fillRect (25 + location, -82 + x, 10, 22);
	    c.setColor (new Color (238, 201, 0));
	    c.fillRect (location, -60 + x, 60, 5);
	    c.setColor (new Color (205, 200, 177));
	    c.fillPolygon (xPolygon, yPolygon, 3);
	    try
	    {
		Thread.sleep (2);
	    }
	    catch (InterruptedException e)
	    {
	    }
	}
    }


    /* The class constructor creates the object in memory for the code - this allows for the data and methods to be stored.
	con               reference               This parameter pass refers to the Console class.
	d                 int                     This parameter pass is used by another thread to set an initial value for location.
	w                 int                     This parameter pass is used by another thread to set an initial value for wait. */
    public Knife (Console con, int d, int w)
    {
	c = con;
	location = d;
	wait = w;
    }


    //The run method is used by another thread to run knife.
    public void run ()
    {
	knife ();
    }
} // Knife class
