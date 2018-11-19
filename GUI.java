package example;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

class Activity implements Comparable<Activity> {
	
	String activityName;
	int duration;
	ArrayList<String> dependencies;

	public Activity(String activityName, int duration, ArrayList<String> dependencies) {
		this.activityName = activityName;
		this.duration = duration;
		this.dependencies = dependencies;
	}

	public Activity() {
		activityName = "";
		duration = 0;
		dependencies = new ArrayList<String>();
	}

	public String getActivityName() {
		return this.activityName;
	}

	public int getDuration() {
		return this.duration;
	}

	public ArrayList<String> getDependencies() {
		return this.dependencies;
	}

	@Override
	public int compareTo(Activity other) {

		return activityName.compareTo(other.activityName);
	}
}

public class GUI {

	// Initializing J variables:
	private JFrame mainFrame;
	private JFrame aboutFrame;
	private JFrame helpFrame;
	
	protected JTextField ActivityField;
	protected JTextField ActivityDuration;
	protected JTextField dependencyField;
	
	//Static Variable -- must be cleared every time
	static ArrayList<LinkedList<Activity>> paths;
	static ArrayList<Activity> activityList;
	static ArrayList<Activity> allActivities;
	
	//Global Variables
	String activityName;
	String activityDuration;
	String dependencyNames;
	String fileName;

	// Launch the application in main:
	public static void main(String[] args) {
		paths = new ArrayList<>();
		activityList = new ArrayList<>();
		allActivities = new ArrayList<>();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creating application:
	public GUI() {
		initialize();
	}

	// Initialize the contents of the frame:
	private void initialize() {
		
		// Application main frame:
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 500, 375);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		
		// Application title of main application window:
		JLabel labelTitle = new JLabel("Network Diagram Analyzer");
		labelTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(149, 26, 200, 15);
		mainFrame.getContentPane().add(labelTitle);
		
		
		// Quit button:
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		quitButton.setBounds(401, 306, 55, 30);
		mainFrame.getContentPane().add(quitButton);
		
			// Quit button action listener. 
			quitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);	// Close program when quit button is pressed.
				}
			});
		
			
		// Input label:
		JLabel labelInput = new JLabel("Input");
		labelInput.setBounds(118, 76, 38, 16);
		mainFrame.getContentPane().add(labelInput);
		
		// Output label:
		JLabel labelOutput = new JLabel("Output");
		labelOutput.setBounds(343, 76, 44, 16);
		mainFrame.getContentPane().add(labelOutput);
		
		
		// Input text area:
		//JTextArea textAreaInput = new JTextArea();
		//textAreaInput.setBounds(45, 104, 180, 190);
		//mainFrame.getContentPane().add(textAreaInput);
		
		ActivityField = new JTextField(20);
		ActivityField.setBounds(125, 104, 90, 30);
		mainFrame.getContentPane().add(ActivityField);
		
		JLabel ActivityLabel = new JLabel("Activity Name:");
		ActivityLabel.setBounds(30, 104, 90, 35);
		mainFrame.getContentPane().add(ActivityLabel);
		
		ActivityDuration = new JTextField(20);
		ActivityDuration.setBounds(125, 154, 90, 30);
		mainFrame.getContentPane().add(ActivityDuration);
		
		JLabel ActivityDurationLabel = new JLabel("Duration:");
		ActivityDurationLabel.setBounds(60, 154, 90, 35);
		mainFrame.getContentPane().add(ActivityDurationLabel);
		//ActivityDuration.addActionListener(this);

		dependencyField = new JTextField(20);
		dependencyField.setBounds(125, 204, 90, 30);
		mainFrame.getContentPane().add(dependencyField);
		
		JLabel DependencyLabel = new JLabel("Dependencies:");	
		DependencyLabel.setBounds(30, 204, 90, 35);
		mainFrame.getContentPane().add(DependencyLabel);
		
		JButton AddActivity = new JButton("Add Activity");
		AddActivity.setBounds(65, 254, 95, 30);
		AddActivity.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		mainFrame.getContentPane().add(AddActivity);
		
		// Process button action listener:
		AddActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				activityName = ActivityField.getText();
				activityDuration = ActivityDuration.getText();
				dependencyNames = dependencyField.getText();
				System.out.println(dependencyNames);
				Activity current = new Activity();
				Activity copy = new Activity();
				
				try
				{
					current.duration = Integer.parseInt(activityDuration);
				} catch (NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null, "Duration must be an integer");
					return;
				}
				current.activityName = activityName;
				StringTokenizer str = new StringTokenizer(dependencyNames, ",");
				while (str.hasMoreTokens())
					current.dependencies.add(str.nextToken());
				
				System.out.println("Activity Name: "+ current.activityName);
				for(int i=0;i<current.dependencies.size();i++)
				{
					System.out.println(current.dependencies.get(i));
				}
				copy.activityName = current.activityName;
				copy.duration = current.duration;
				for(int i=0;i <current.dependencies.size();i++)
					copy.dependencies.add(current.dependencies.get(i));
				allActivities.add(copy);// use this to keep track of every activity entered
				createActivityList(current);
				ActivityField.setText("");
				ActivityDuration.setText("");
				dependencyField.setText("");
				//printActivities();
			}
		});
		
		// Output text area:
		JTextArea textAreaOutput = new JTextArea();
		textAreaOutput.setBounds(274, 104, 180, 190);
		mainFrame.getContentPane().add(textAreaOutput);
		
		
		// About button:
		JButton buttonAbout = new JButton("About");
		buttonAbout.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		buttonAbout.setBounds(171, 44, 75, 29);
		mainFrame.getContentPane().add(buttonAbout);
		
			// About window action listener:
			buttonAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Application about frame:
					aboutFrame = new JFrame();
					aboutFrame.setBounds(100, 100, 400, 265);
					aboutFrame.getContentPane().setLayout(null);
					aboutFrame.setVisible(true);
					
					// Application label (about text):
					JLabel aboutText = new JLabel("<html>This program takes an input of various activities, "
							+ "their duration times, and their preceding activities. Using this input, all "
							+ "possible paths are mapped out using the parameters given. The paths will then "
							+ "be displayed in descending order of duration, listing the longest path to the "
							+ "shortest path. Also, this program will also output the citical path of activities, as well as export the results."
							+ "<br><br>Developed by Team 6 Studios.</html>");
					aboutText.setBounds(25, 15, 350, 180);
					aboutFrame.getContentPane().add(aboutText);
					
					// About close button:
					JButton buttonCloseAbout = new JButton("Close");
					buttonCloseAbout.setBounds(20, 190, 75, 29);
					aboutFrame.getContentPane().add(buttonCloseAbout);
					
					// Close about button action listener. 
					buttonCloseAbout.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							aboutFrame.dispose();	//Close about window when user presses close.
						}
					});
					
				}
			});
		
			
		// Help button:
		JButton buttonHelp = new JButton("Help");
		buttonHelp.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		buttonHelp.setBounds(241, 44, 75, 29);
		mainFrame.getContentPane().add(buttonHelp);
		
			// Help window action listener:
			buttonHelp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Application help frame:
					helpFrame = new JFrame();
					helpFrame.setBounds(100, 100, 800, 650);
					helpFrame.getContentPane().setLayout(null);
					helpFrame.setVisible(true);
					
					// Application label (help text):
					JLabel helpText = new JLabel("<html>To use the program, enter your input in the ‘Input’ dialog box on the left of the interface. Input will be formatted as:<br><br>"
							+ "Activity Name, Duration, List of Dependencies<br><br><br>"
							+ "- The activity name will be the name of the activity, which can be a string containing both letters, numbers, and symbols.<br><br>"
							+ "- The duration will be the length of duration of the previously stated activity, given as an integer. The duration may not contain any letters or symbols.<br><br>"
							+ "- The list of dependencies will be the preceding activities that must come before the given activity. Dependency names must match their corresponding activity name exactly. If there is more than one dependency, separate them in a list with a semicolon.<br><br>"
							+ "- If the activity has no dependencies, in the part of the parameter where the dependency names would go, list the special character ‘X’.<br><br>"
							+ "- The activity name, duration, and list of dependencies must be separated by commas.<br><br>"
							+ "- All input must be given in the order specified above, no rearranging of parameters is allowed.<br><br><br>"
							+ "Once you have typed in all of your input, click the ‘PROCESS’ button on the bottom of the interface to run the program using your parameters.<br><br>"
							+ "The Output of the program using our parameters will be displayed on the right side of the interface. The Output will include a list of all paths possible, using your parameters. The paths will be organized in descending order by duration.<br><br>"
							+ "Also, part of V2, you may press 'Critical Path' to be outputted your network diagram's critical path in the output box, as well as the 'Export Report' button to export the results into a text document.</html>");
					helpText.setBounds(25, 15, 750, 550);
					helpFrame.getContentPane().add(helpText);
					
					// Help close button:
					JButton buttonCloseHelp = new JButton("Close");
					buttonCloseHelp.setBounds(20, 570, 75, 29);
					helpFrame.getContentPane().add(buttonCloseHelp);
					
					// Close help button action listener. 
					buttonCloseHelp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							helpFrame.dispose();	//Close help window when user presses close.
						}
					});
					
				}
			});
		
		
		// Process button:
		JButton buttonProcess = new JButton("Process");
		buttonProcess.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		buttonProcess.setBounds(160, 306, 70, 29);
		mainFrame.getContentPane().add(buttonProcess);
		
			// Process button action listener:
			buttonProcess.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int n = addActivityListToPath();
					if (n == 1)
						JOptionPane.showMessageDialog(null, "Nodes are not connected");
					else if (n == 2)
						JOptionPane.showMessageDialog(null, "Circular Dependency Created");
					else
					{
						String s = displayPathOrders(0);
						textAreaOutput.append(s);
						try
						{
							createFile(fileName);
						} catch (IOException e1)
						{
							e1.printStackTrace();
						}
					}
				}
			});
		
			
		// Reset button:
		JButton buttonReset = new JButton("Reset");
		buttonReset.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		buttonReset.setBounds(265, 307, 55, 29);
		mainFrame.getContentPane().add(buttonReset);
		
			// Reset button action listener:
			buttonReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//textAreaInput.setText(null);
					textAreaOutput.setText(null);
					allActivities.clear();
					paths.clear();
					activityList.clear();
					ActivityField.setText("");
					ActivityDuration.setText("");
					dependencyField.setText("");
				}
			});
		
		JButton btnCriticalOutput = new JButton("Critical Output");
		btnCriticalOutput.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnCriticalOutput.setBounds(66, 306, 100, 29);
		mainFrame.getContentPane().add(btnCriticalOutput);
		
			btnCriticalOutput.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int n = addActivityListToPath();
					if (n == 1)
						JOptionPane.showMessageDialog(null, "Nodes are not connected");
					else if (n == 2)
						JOptionPane.showMessageDialog(null, "A cycle was created");
					String s = displayPathOrders(1);
					textAreaOutput.append(s);
					try
					{
						createFile(fileName);
					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			});
			
		
		JButton btnExportReport = new JButton("Export Report");
		btnExportReport.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnExportReport.setBounds(313, 307, 95, 29);
		mainFrame.getContentPane().add(btnExportReport);
		
			btnExportReport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JTextField filenameField = new JTextField(5);

					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("Please enter a file name:"));
					myPanel.add(filenameField);

					int result = JOptionPane.showConfirmDialog(null, myPanel, "", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION)
					{
						fileName = filenameField.getText();
					}

					int n = addActivityListToPath();
					if (n == 1)
						JOptionPane.showMessageDialog(null, "Nodes are not connected");
					else if (n == 2)
						JOptionPane.showMessageDialog(null, "A cycle was created");
					else
					{
						try 
						{
							createFile(fileName);
						} 
						catch (IOException e1)
						{
							e1.printStackTrace();
						}
					}
				}
			});
		
	}
	
	public void AddToPath(Activity newNode, ArrayList<LinkedList<Activity>> paths)
	{
		if (newNode.dependencies.size() == 0)
		{
			LinkedList<Activity> newPath = new LinkedList<>();
			newPath.add(newNode);
			paths.add(newPath);
		} 
		else
		{
			for (int k = 0; k < newNode.dependencies.size(); k++)
			{
				for (int i = 0; i < paths.size(); i++)
				{
					for (int j = 0; j < paths.get(i).size(); j++)
					{
						if (paths.get(i).getLast().activityName.equalsIgnoreCase(newNode.dependencies.get(k)))
						{
							paths.get(i).add(newNode);
						}
					}
				}
			}
		}
	}
	
	protected void createActivityList(Activity current)
	{
		if (current.dependencies.size() == 0)
		{
			LinkedList<Activity> action = new LinkedList<Activity>();
			action.add(current);
			paths.add(action);
		} else
		{
			activityList.add(current);
		}
	}

	
	protected int addActivityListToPath()
	{
		convertToSingleDependency();
		int pass = checkCircularReference();
		if (pass != 0)
			return pass;
		int errors = 0;
		int i = 0;
		while (activityList.size() > 0)
		{
			int n = addDependencyToList(activityList.get(0).dependencies.get(0), activityList.get(i));
			if (errors > activityList.size())
				return 1;

			if (activityList.size() == 0)
				return 0;

			if (n == 0)
			{
				errors = 0;
				activityList.remove(0);
			} else
			{
				errors++;
				activityList.add(activityList.get(0));
				activityList.remove(0);
			}
			printActivities();
		}
		if (checkAdvancedCircularReference() != 0)
			return 2;
		deleteDuplicates();
		printActivities();
		return 0;
	}
	
	protected int addDependencyToList(String dependency, Activity newNode)
	{
		int added = 0;
		for (int i = 0; i < paths.size(); i++)
		{
			if (i > paths.size())
				break;
			for (int j = 0; j < paths.get(i).size(); j++)
			{
				// check if this is where new dependency needs to be put in
				if (paths.get(i).get(j).activityName.equals(dependency))
				{
					Activity copy = new Activity();
					copy.activityName = newNode.activityName;
					copy.dependencies.add(dependency);
					copy.duration = newNode.duration;
					if (j == paths.get(i).size() - 1)
					{
						paths.get(i).add(copy);
						added++;
					} 
					else
					{
						LinkedList<Activity> newPath = new LinkedList<>();
						for (int k = 0; k <= j; k++)
						{
							Activity newActivity = new Activity();
							newActivity.activityName = paths.get(i).get(k).activityName;
							newActivity.duration = paths.get(i).get(k).duration;
							if (k != 0)
								newActivity.dependencies.add(paths.get(i).get(k).dependencies.get(0));
							newPath.add(newActivity);
						}
						newPath.add(copy);
						paths.add(i + 1, newPath);
						added++;
						i++;
					}
				}
			}
		}
		if (added > 0)
			return 0;
		return -1;
	}
	
	protected String displayPathOrders(int key)
		{
			ArrayList<SortHelper> values = new ArrayList<>();
			for (int i = 0; i < paths.size(); i++)
			{
				SortHelper value = new SortHelper(i, 0);
				for (int j = 0; j < paths.get(i).size(); j++)
				{
					value.duration += paths.get(i).get(j).duration;
				}
				values.add(value);
			}
			int n = values.size();
			for (int i = 0; i < n - 1; i++)
			{
				int max = i;
				for (int j = i + 1; j < n; j++)
				{
					if (values.get(j).duration > values.get(max).duration)
						max = j;
				}
				SortHelper temp = new SortHelper(values.get(max).key, values.get(max).duration);
				values.get(max).key = values.get(i).key;
				values.get(max).duration = values.get(i).duration;
				values.get(i).duration = temp.duration;
				values.get(i).key = temp.key;
			}
			String str = "";
			for (int i = 0; i < values.size(); i++)
			{
				str += "Path \n";
				LinkedList<Activity> list = paths.get(values.get(i).key);
				for (int j = 0; j < list.size(); j++)
				{
					str += list.get(j).activityName + " ";
				}
				str += "\n Duration: " + values.get(i).duration + " ";
				str += "\n";
			}
			if (key == 0){ 
				System.out.println(str);
				return str;
			}
			else
			{
				int critDur = values.get(0).duration;
				int stopIndex = 0;
				str = "Critical Path \n";
				for (int i = 1; i < values.size(); i++)
				{
					if (values.get(i).duration >= critDur)
						stopIndex++;
				}
				for (int i = 0; i <= stopIndex; i++)
				{
					LinkedList<Activity> list = paths.get(values.get(i).key);
					System.out.println("Getting paths["+values.get(i).key+"]");
					for (int j = 0; j < list.size(); j++)
					{
						str += list.get(j).activityName + " ";
					}
				str += "\t Duration: " + values.get(i).duration + " ";
				str += "\n";
			}
			return str;
		}
	}
	
	protected void convertToSingleDependency()
	{
		for (int i = 0; i < activityList.size(); i++)
		{
			if (activityList.get(i).dependencies.size() > 1)
			{
				for (int j = activityList.get(i).dependencies.size() - 1; j >= 1; j--)
				{
					Activity copy = new Activity();
					copy.activityName = activityList.get(i).activityName;
					copy.dependencies.add(activityList.get(i).dependencies.get(j));
					copy.duration = activityList.get(i).duration;
					activityList.get(i).dependencies.remove(j);
					activityList.add(copy);
				}
			}
		}
	}
	
	private void deleteDuplicates()
	{
		boolean flag = false;;
		ArrayList<String> checkDuplicate = new ArrayList<String>();
		for (int j = 0; j < paths.get(0).size(); j++)
		{
			checkDuplicate.add(paths.get(0).get(j).activityName);
		}
		for (int i = 1; i < paths.size(); i++)
		{
			flag = false;
			for(int j=0;j<paths.get(i).size();j++)
			{
				if(checkDuplicate.get(j) != paths.get(i).get(j).activityName)
				{
					for(int k=0;k<paths.get(i).size();k++)
					{
						checkDuplicate.set(k,paths.get(i).get(k).activityName);
					}
					flag = true;
					break;
				}
			}
			if(!flag)
			{
				paths.remove(i);
				i--;
			}
		}
	}
	
	protected int checkCircularReference()
	{
		for (int i = 0; i < activityList.size(); i++)
		{
			for (int j = 0; j < activityList.size(); j++)
			{
				if (activityList.get(i).dependencies.get(0).equals(activityList.get(j).activityName)
						&& activityList.get(j).dependencies.get(0).equals(activityList.get(i).activityName))
					return 2;
			}
		}
		return 0;
	}
	
	private int checkAdvancedCircularReference()
	{
		ArrayList<String> checkDuplicate;
		for (int i = 0; i < paths.size(); i++)
		{
			checkDuplicate = new ArrayList<String>();
			for (int j = 0; j < paths.get(i).size(); j++)
			{
				if (checkDuplicate.size() == 0)
					checkDuplicate.add(paths.get(i).get(j).activityName);
				else
				{
					if (checkDuplicate.contains(paths.get(i).get(j).activityName))
						return 1;
					else
						checkDuplicate.add(paths.get(i).get(j).activityName);
				}
			}
		}
		return 0;
	}

	public void printActivities()
	{
		for (int i = 0; i < paths.size(); i++)
		{
			for (int j = 0; j < paths.get(i).size(); j++)
			{
				System.out.print(paths.get(i).get(j).activityName + " ");
			}
			System.out.println();
		}
		System.out.println("Activity List");
		for (int i = 0; i < activityList.size(); i++)
		{
			System.out.print(activityList.get(i).activityName + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	public void createFile(String FileName) throws IOException
	{
		String myFileName = FileName + ".txt";
		try
		{
			File file = new File(myFileName);
			FileWriter fileWriter = new FileWriter(file, false);
			PrintWriter writer = new PrintWriter(fileWriter);
			writer.println(myFileName);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			writer.println(dtf.format(now));
			writer.println("Activity List: ");
			for (int i = 0; i < allActivities.size(); i++)
			{
				writer.print(allActivities.get(i).activityName + " ");
				writer.println(allActivities.get(i).duration);
				writer.println();
			}
			writer.println();
			writer.println("Paths: ");
			int duration;
			for (int i = 0; i < paths.size(); i++)
			{
				duration = 0;
				for (int j = 0; j < paths.get(i).size(); j++)
				{
					writer.print(paths.get(i).get(j).activityName + " ");
					duration += paths.get(i).get(j).duration;
				}
				writer.print(duration);
				writer.println();
			}
			writer.println();
			writer.close();
		} 
		catch (UnsupportedEncodingException e){}
	}
}

class SortHelper {
	int key;
	int duration;

	public SortHelper()
	{
		duration = 0;
		key = 0;
	}

	public SortHelper(int key, int duration)
	{
		this.key = key;
		this.duration = duration;
	}
}