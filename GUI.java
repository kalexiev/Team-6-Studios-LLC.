import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.ActionEvent;

class Activity {
    
    public String activityName;
    public int duration;
    public String predecessors;

    Activity(String name, int dur, String pred)
    {
        activityName = name;
        duration = dur;
        predecessors = pred;
        
    }
    
    public static Comparator<Activity> ActComparator = new Comparator<Activity>() {

		public int compare(Activity a1, Activity a2) {
		   int ActDur1 = a1.duration;
		   int ActDur2 = a2.duration;
		   
		   //descending order
		   return ActDur2 - ActDur1;
	    }};
}

public class GUI {

	// Initializing J variables:
	private JFrame mainFrame;
	private JFrame aboutFrame;
	private JFrame helpFrame;

	// Launch the application in main:
	public static void main(String[] args) {
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
		mainFrame.setBounds(100, 100, 500, 385);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		
		// Application title of main application window:
		JLabel labelTitle = new JLabel("Network Diagram Analyzer");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(160, 25, 170, 15);
		mainFrame.getContentPane().add(labelTitle);
		
		
		// Quit button:
		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(380, 310, 80, 30);
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
		JTextArea textAreaInput = new JTextArea();
		textAreaInput.setBounds(45, 104, 180, 190);
		mainFrame.getContentPane().add(textAreaInput);
		
		// Output text area:
		JTextArea textAreaOutput = new JTextArea();
		textAreaOutput.setBounds(274, 104, 180, 190);
		mainFrame.getContentPane().add(textAreaOutput);
		
		
		// About button:
		JButton buttonAbout = new JButton("About");
		buttonAbout.setBounds(171, 44, 75, 29);
		mainFrame.getContentPane().add(buttonAbout);
		
			// About window action listener:
			buttonAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Application about frame:
					aboutFrame = new JFrame();
					aboutFrame.setBounds(100, 100, 400, 215);
					aboutFrame.getContentPane().setLayout(null);
					aboutFrame.setVisible(true);
					
					// Application label (about text):
					JLabel aboutText = new JLabel("<html>This program takes an input of various activities, "
							+ "their duration times, and their preceding activities. Using this input, all "
							+ "possible paths are mapped out using the parameters given. The paths will then "
							+ "be displayed in descending order of duration, listing the longest path to the "
							+ "shortest path.<br><br>Developed by Team 6 Studios.</html>");
					aboutText.setBounds(25, 0, 350, 150);
					aboutFrame.getContentPane().add(aboutText);
					
					// About close button:
					JButton buttonCloseAbout = new JButton("Close");
					buttonCloseAbout.setBounds(20, 150, 75, 29);
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
		buttonHelp.setBounds(241, 44, 75, 29);
		mainFrame.getContentPane().add(buttonHelp);
		
			// Help window action listener:
			buttonHelp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Application help frame:
					helpFrame = new JFrame();
					helpFrame.setBounds(100, 100, 800, 600);
					helpFrame.getContentPane().setLayout(null);
					helpFrame.setVisible(true);
					
					// Application label (help text):
					JLabel helpText = new JLabel("<html>To use the program, enter your input in the Input dialog box on the left of the interface. Input will be formatted as:<br><br>"
							+ "Activity Name,Duration,List of Dependencies <br><br>with no spaces!<br><br> "
							+ "- The activity name will be the name of the activity, which can be a string containing both letters, numbers, and symbols.<br><br>"
							+ "- The duration will be the length of duration of the previously stated activity, given as an integer. The duration may not contain any letters or symbols.<br><br>"
							+ "- The list of dependencies will be the preceding activities that must come before the given activity. Dependency names must match their corresponding activity name exactly. If there is more than one dependency, separate them in a list with a semicolon.<br><br>"
							+ "- If the activity has no dependencies, in the part of the parameter where the dependency names would go, list the special character @ (at-symbol).<br><br>"
							+ "- The activity name, duration, and list of dependencies must be separated by commas.<br><br>"
							+ "- All input must be given in the order specified above, no rearranging of parameters is allowed.<br><br><br>"
							+ "Once you have typed in all of your input, click the PROCESS button on the bottom of the interface to run the program using your parameters.<br><br>"
							+ "The Output of the program using our parameters will be displayed on the right side of the interface. The Output will include a list of all paths possible, using your parameters. The paths will be organized in descending order by duration.</html>");
					helpText.setBounds(25, 0, 750, 550);
					helpFrame.getContentPane().add(helpText);
					
					// Help close button:
					JButton buttonCloseHelp = new JButton("Close");
					buttonCloseHelp.setBounds(20, 520, 75, 29);
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
		buttonProcess.setBounds(125, 310, 100, 29);
		mainFrame.getContentPane().add(buttonProcess);
		
			// Process button action listener:
			buttonProcess.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						String userInput[] = textAreaInput.getText().split("\\n");
						ArrayList<String>inputList = new ArrayList<>(Arrays.asList(userInput));
						ArrayList<Activity>activityList = new ArrayList<>();
						
						for (int i = 0; i < inputList.size(); i++) {
							String currAct = inputList.get(i);
							String[] temp;
							temp = currAct.split(",");
							//***if temp[1] is not an int, throw error***
							try
						    {
								Activity tempAct = new Activity(temp[0], Integer.parseInt(temp[1]), temp[2]);   
								activityList.add(tempAct);	
						    }
						    catch (NumberFormatException nfe)
						    {
						    	JOptionPane.showMessageDialog(null, "Input error - check activity type input", "Error", JOptionPane.ERROR_MESSAGE);
						    }
							
						}
						
						//Sort the list by duration
						//Collections.sort(activityList, Activity.ActComparator);
						//Find paths
						
						//Write Paths to an output list
						ArrayList<String>outputList = new ArrayList<>();
						int paths = 0;
						for (int i = 0; i < activityList.size() - 1; i++) {
							Activity currAct1 = activityList.get(i);
							String temp1 = (currAct1.predecessors);
							for (int j = 1; j < activityList.size(); j++) {
								Activity currAct2 = activityList.get(j);
								String temp2 = (currAct2.predecessors);
								if (temp1 == temp2) {
									paths = paths + 1;
								}
							}
						}
						textAreaOutput.append("**There are " + paths+ " paths**" + "\n\n");
						int j = 1;
						for (int i = 0; i <= paths; i++); {
							textAreaOutput.append("Path: " + j + '\n');
							
							j++;
							int duration = 0;
							String temp2 = "meh";
							
							for (int i = 0; i < activityList.size(); i++) {
								Activity currAct = activityList.get(i);
								if (i > 0) {
									Activity lastAct = activityList.get(i - 1);
									temp2 = (lastAct.activityName);
								}
								String temp1 = (currAct.activityName);
								String temp3 = (currAct.predecessors);
								if (i == 0) {
									outputList.add(temp1);
									duration = currAct.duration + duration;
								}
								if (i > 0) {
									if (temp3.indexOf(temp2) >= 0) {
										outputList.add(temp1);
										duration = currAct.duration + duration;
									}
								}
							}
							
							for (int i = 0; i < outputList.size(); i++) {
								textAreaOutput.append(outputList.get(i) + '\n');	
							}
							
							textAreaOutput.append("Duration: " + duration + '\n');
					}
					} 
					catch (ArrayIndexOutOfBoundsException exception) {
						 JOptionPane.showMessageDialog(null, "Input error...", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
			});
		
			
		// Reset button:
		JButton buttonReset = new JButton("Reset");
		buttonReset.setBounds(267, 310, 80, 29);
		mainFrame.getContentPane().add(buttonReset);
		
			// Reset button action listener:
			buttonReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					textAreaInput.setText(null);
					textAreaOutput.setText(null);
				}
			});
		
	}
}