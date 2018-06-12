import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;
/**
 *  A component that displays the current state of the selection sort algorithm.
 */
public class BubbleSortComponent extends JComponent
{
	private BubbleSorter sorter;
	public static int[] values;
	/**
 Constructs the component.
	 */
	public BubbleSortComponent(int[] array)
	{
		this.values=array;

		sorter = new BubbleSorter(values, this);
	

	}




	public void paintComponent(Graphics g)
	{
		sorter.draw(g);
		
	}

	/**
 Starts a new animation thread.
	 */
	public void startAnimation()
	{
		class AnimationRunnable implements Runnable
		{
			public void run()
			{
				try
				{
					sorter.sort();
					
				}
				catch (InterruptedException exception)
				{
				}
			}
		}

		Runnable r = new AnimationRunnable();
		Thread t = new Thread(r);
		t.start();
	}
}
