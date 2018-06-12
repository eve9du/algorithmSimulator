import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Arrays;
import java.util.Random;



//Source: Big Java 22.6
public class SortViewer
{
	public static int[] values;
	public static final int size =50; 
	
	
	public SortViewer() {
		values = randomArray(size);
	}
	

	public static int[] randomArray(int length) {
		int[] finalArray = new int[length];
		Random rn = new Random();
		int counter=0;
		for(int i = 1; i <= length; i++ ) {
			//System.out.println(i);
			finalArray [counter] = i;
			counter++;
			//System.out.println(i);
		}
		for(int i=0;i<length;i++) {
			int temp =finalArray [i];
			int index = rn.nextInt(size);
			finalArray [i] = finalArray [index];
			finalArray [index]=temp;
			
		}
		return finalArray ;
	}
	
	public static int[] deepCopy(int[] vals) {
		//int n= size;
		int [] list = new int[size];
		for (int i =0;i<size;i++) {
			list[i]=vals[i];
		}
		return list;
	}
	
	
	public static void main(String[] args)
	{
		
		
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		final int FRAME_WIDTH = 600;
		final int FRAME_HEIGHT = 800;

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel("Selection Sort");
		label2 = new JLabel("Insertion Sort");
		label3 = new JLabel("Bubble Sort");
		label.setSize(FRAME_WIDTH/30, 2);
		
		
		int[] arr = new int[size];
		arr = randomArray(size);
		int [] deepCopy1 = deepCopy(arr);		
		
		
		
		final SelectionSortComponent component = new SelectionSortComponent(deepCopy(arr));
		final InsertionSortComponent component2 = new InsertionSortComponent(deepCopy(arr));
		final BubbleSortComponent component3 = new BubbleSortComponent(deepCopy(arr));
		panel = new JPanel(new GridLayout(3, 2));
		panel.add(label);
		panel.add(component);
		panel.add(label2);
		panel.add(component2);
		panel.add(label3);
		panel.add(component3);

		component.setLocation(0,0);
		frame.add(panel);


		frame.setVisible(true);
		component.startAnimation();
		component2.startAnimation();

		component3.startAnimation();


	}
}