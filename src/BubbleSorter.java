import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JComponent;

/**
 This class sorts an array, using the selection sort algorithm.
 */
public class BubbleSorter
{
	// This array is being sorted
	private int[] a;
	// These instance variables are needed for drawing
	private int markedPosition = -1;
	private int alreadySorted = -1;

	private Lock sortStateLock;

	// The component is repainted when the animation is paused
	private JComponent component3;

	private static final int DELAY = 100;

	/**
 Constructs a selection sorter.
 @param anArray the array to sort
 @param aComponent the component to be repainted when the animation
 pauses
	 */

	public BubbleSorter(int[] anArray, JComponent aComponent)
	{
		a = anArray;
		sortStateLock = new ReentrantLock();
		component3 = aComponent;
	}

	/**
 Sorts the array managed by this selection sorter.
	 */
	public void sort()
			throws InterruptedException
	{
		int counter = 0;
		int pat =a.length;
		for (int q=0;q<a.length;q++) {
			counter+=1;
			alreadySorted = pat -counter;
			for (int i = 1; i <a.length ; i++)
			{
				try {
					sortStateLock.lock();

					markedPosition =i;
					if(a[i-1]>a[i]) {
						ArrayUtil.swap(a, i-1, i);
					}
				}
				finally {
					sortStateLock.unlock();
				}

				pause(2);
			}
		}
	}

	/**
 Draws the current state of the sorting algorithm.
 @param g the graphics context
	 */
	public void draw(Graphics g)
	{
		sortStateLock.lock();
		try
		{
			int deltaX = component3.getWidth() / a.length ;
			for (int i = 0; i < a.length; i++)
			{
				if (i == markedPosition)
				{
					g.setColor(Color.RED);
					g.fillRect(i * deltaX,2,5, 2*a[i]);
				}
				else if (i <= alreadySorted)
				{
					g.setColor(Color.BLACK);
					
				}
				else
				{
					g.setColor(Color.BLUE);
					g.fillRect(i * deltaX,2,5, 2*a[i]);
				}
				g.drawRect(i * deltaX,2,5, 2*a[i]);
			}
		}
		finally
		{
			sortStateLock.unlock();
		}
	}


	public static boolean isSorted(int[] a) 
	{

		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false; // It is proven that the array is not sorted.
			}
		}

		return true; // If this part has been reached, the array must be sorted.
	}

	/**
 Pauses the animation.
 @param steps the number of steps to pause
	 */
	public void pause(int steps)
			throws InterruptedException
	{
		component3.repaint();
		Thread.sleep(steps * DELAY);
	}
}
