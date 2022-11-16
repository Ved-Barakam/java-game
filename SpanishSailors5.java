// Ved Barakam
// 5-17-21
// SpanishSailors5.java

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.KeyListener;		
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class SpanishSailors5
{
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Spanish Sailors");
		CardForSpanishSailors5 panel = new CardForSpanishSailors5();
		frame.add(panel);
		frame.setSize(1000, 700);
		frame.setLocation(100, 150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class CardForSpanishSailors5 extends JPanel
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	
	// Creates all of the cards and adds them to the instance of the CardLayout.
	// Each card has a name so it can be shown if needed. The object 'this' is
	// the primary panel, which is the parent panel.
	public CardForSpanishSailors5 ( )
	{		
		listOfCards = new CardLayout();
		setLayout(listOfCards); 
		
		HomePanel first = new HomePanel(listOfCards, this);
		add(first, "1");
		
		InstructionsPanel second = new InstructionsPanel(listOfCards, this);
		add(second, "2");
		
		TopScoresPanel third = new TopScoresPanel(listOfCards, this);
		add(third, "3");
		
		PlayOptionsPanel fourth = new PlayOptionsPanel(listOfCards, this);
		add(fourth, "4");
		
		LearnPresentTenseV fifth = new LearnPresentTenseV(listOfCards, this);
		add(fifth, "5");
		
		LearnPreteriteTenseV sixth = new LearnPreteriteTenseV(listOfCards, this);
		add(sixth, "6");
		
		LearnPresentProgressiveTenseV seventh = new LearnPresentProgressiveTenseV(listOfCards, this);
		add(seventh, "7");
		
		GamePanel eighth = new GamePanel(listOfCards, this, fourth);
		add(eighth, "8");
		
		IncorrectPanel ninth = new IncorrectPanel(listOfCards, this, eighth);
		add(ninth, "9");
		
		GameOverPanel tenth = new GameOverPanel(listOfCards, this, eighth);
		add(tenth, "10"); 
	}
}

class HomePanel extends JPanel implements ActionListener
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	private String[] names; // Array that holds the image names	
	private Image[] images; // Array that holds the image objects

	// The field variables names & images and initialized to their needed values.
	// Several methods are then called to create the components.
	public HomePanel (CardLayout c, CardForSpanishSailors5 p)
	{
		listOfCards = c;
		primaryPanel = p;
			
		names = new String[] {"BackgroundHomeP.png", "SSailorsLogo.png"};
		images = new Image[names.length];
		
		setLayout(null);
			
		loadMyImage();
		createDirectionsJB();
		createPlayJB();
		createLeaderboardJB();
		createExitJB();
	} 
		
	// Creates the left directions JButton with grey background & white text
	public void createDirectionsJB()
	{
		JButton directionsB = new JButton ("Instructions");
		directionsB.setFont(new Font("Tahoma", Font.BOLD, 32));
		directionsB.setBounds(40, 410, 240, 140);
		directionsB.addActionListener(this); // One handler method for all events
		directionsB.setForeground(new Color(242, 237, 237));
		directionsB.setBackground(new Color(128, 128, 128)); 
		directionsB.setOpaque(true); // Needed for background of JButton
		directionsB.setBorderPainted(false); 
		add(directionsB);	
	}
		
	// Creates the middle play JButton with grey background & white text
	public void createPlayJB()
	{
		JButton playB = new JButton ("Play");
		playB.setFont(new Font("Tahoma", Font.BOLD, 80));
		playB.setBounds(380, 410, 240, 140);
		playB.addActionListener(this);
		playB.setForeground(new Color(242, 237, 237));		
		playB.setBackground(new Color(128, 128, 128)); 
		playB.setOpaque(true); 
		playB.setBorderPainted(false); 
		add(playB);
	}
		
	// Creates the right Leaderboard JButton with grey background & white text
	public void createLeaderboardJB()
	{
		JButton topScoresB = new JButton ("Leaderboard");
		topScoresB.setFont(new Font("Tahoma", Font.BOLD, 30));
		topScoresB.setBounds(720, 410, 240, 140);
		topScoresB.addActionListener(this);
		topScoresB.setForeground(new Color(242, 237, 237));	
		topScoresB.setBackground(new Color(128, 128, 128));
		topScoresB.setOpaque(true); 
		topScoresB.setBorderPainted(false); 
		add(topScoresB);
	}
		
	// Creates the bottom right exit JButton with grey bacground & red text
	public void createExitJB()
	{
		JButton exitB = new JButton ("Exit");
		exitB.setFont(new Font("Tahoma", Font.BOLD, 28));
		exitB.setBounds(890, 600, 100, 70);
		exitB.addActionListener(this); // When this JButton is pressed, the program will terminate
		exitB.setForeground(Color.RED);	
		exitB.setBackground(new Color(128, 128, 128)); 
		exitB.setOpaque(true); 
		exitB.setBorderPainted(false); 
		add(exitB);
	}
		
	// Loads all of the pictures. Calls getMyImage to do this.
	public void loadMyImage()
	{
		for (int i = 0; i < names.length; i++)
		{
			images[i] = getMyImage(names[i]);		
		}
	}
		
	// Uses a try-catch block to get both of the images and save it to the image object.
	// If the image can't be found, an error message will be displayed.
	// The image/picture is then returned from where it was called.
	public Image getMyImage(String pictName) 
	{
		Image picture = null;
		File pictFile = new File (pictName);
		try
		{
			picture = ImageIO.read(pictFile);
		}
		catch(IOException e)
		{
			System.err.printf("\n\n\nERROR: Cannot find/open file %s", pictName);
			System.exit(0);
		}
				
		return picture;
	}
	
	// drawImage is used to draw both of the images at the desired size and coordinates.
	public void paintComponent(Graphics g) 
	{
		g.drawImage(images[0], 0, 0, 1000, 700, this);
		g.drawImage(images[1], 100, 5, this); 
	}
	
	// This handler method is used for all the events in this class.
	// Depending on which JButton the user pressed, the specific card will be 
	// shown to the screen. For example, if the uses presses 'Play', the fourth
	// card will be shown.
	public void actionPerformed(ActionEvent evt) 
	{
		String command = evt.getActionCommand();
				
		if (command.equals("Instructions"))
		{
			listOfCards.next(primaryPanel);
		}
		else if (command.equals("Play")) 
		{
			listOfCards.show(primaryPanel, "4");
		}
		else if (command.equals("Leaderboard"))
		{
			listOfCards.show(primaryPanel, "3");
		}
		else 
		{
			System.exit(1); 
		}
	}
}

class InstructionsPanel extends JPanel implements ActionListener
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	private String pictName; // Name of picture being loaded.
	private Image picture; // Object the image is saved to.
	
	// The field variables are initialized to their needed values. The layout is
	// then set to a BorderLayout with a 10 pixel gap. Several methods are then called
	// for creating the components.
	public InstructionsPanel (CardLayout c, CardForSpanishSailors5 p)
	{
		listOfCards = c;
		primaryPanel = p;
		
		pictName = new String ("SSailorsInstructionsImg.png");
		picture = null;
	
		setLayout(new BorderLayout(10, 10)); // 10 pixel horizontal and vertical gap for BL.
		setBackground(new Color(0, 133, 255));
		
		getMyImage(); 
		createInstructionsJL();
		createImagesJP();
		createInstructionsJTA();
		createHomeJB();
	}
		
	// The instructions JLabel is added to another panel simply so it can be 
	// centered. The blank JPanel is then added to BL.NORTH.
	public void createInstructionsJL()
	{
		JLabel instructionsJL = new JLabel("Instructions");
		instructionsJL.setFont(new Font("Tahoma", Font.BOLD, 55));
		instructionsJL.setForeground(Color.WHITE);
		JPanel instructionsJP = new JPanel();
		add(instructionsJP, BorderLayout.NORTH);
		instructionsJP.setLayout(new FlowLayout(FlowLayout.CENTER));
		instructionsJP.add(instructionsJL);
		instructionsJP.setBackground(new Color(0, 133, 255));
	}
		
	// An ImageIcon & JLabel is used to put an image in BL.SOUTH since an 
	// image isn't a component and you can't add it to layouts.
	public void createImagesJP()
	{
		JLabel imagesJP = new JLabel(new ImageIcon("SSailorsInstructionsImg.png"));
		imagesJP.setPreferredSize(new Dimension(1000, 150));
		imagesJP.setBackground(new Color(0, 133, 255));
		add(imagesJP, BorderLayout.SOUTH);
	}
		
	// This JTextArea will display all of the instructions to the user. Insets 
	// are used so the formatting of the text looks better. The text for the 
	// instructions is then appended to the JTextArea. A JScrollPane is also used 
	// so the user can scroll down.
	public void createInstructionsJTA()
	{
		JTextArea instructionsJTA = new JTextArea();
		instructionsJTA.setFont(new Font("Tahoma", Font.PLAIN, 30));
		instructionsJTA.setMargin(new Insets(10, 10, 10, 10));
		instructionsJTA.setLineWrap(true);  
		instructionsJTA.setWrapStyleWord(true); 
		instructionsJTA.append("Welcome to SpanishSailors! The objective of this game is "
			+ "to get as many points as possible! You get points everytime you eliminate a pirate. "
			+ "If you have the most points, you will be on the leaderboard. There are up to 5 waves and your goal "
			+ "is to survive all of them! You get 1 point for every right answer. You can control the ship by press the up and arrow keys. "
			+ "You must get rid of the pirate before you run out of lives. You can always customize the number of lives "
			+ "you have. When entering your answers, make sure you don't include any spaces. It's highly recommended to practice "
			+ "the learn mode before you play the game. You must be in the same row as the pirate you want to eliminate. "
			+ "Good luck and have fun! Also please remember to put accents where needed!");
		JScrollPane scroller = new JScrollPane(instructionsJTA);	
		add(scroller, BorderLayout.CENTER);
	}
		
	// Home JButton if user wants to go back to first panel/card.
	public void createHomeJB()
	{		
		JButton homeJB = new JButton("Home");
		homeJB.setFont(new Font("Tahoma", Font.BOLD, 40));
		homeJB.setBackground(Color.WHITE);
		homeJB.addActionListener(this);
		add(homeJB, BorderLayout.WEST);
	}
	
	// A try-catch block is used to get the image, which is then saved to picture.
	public void getMyImage() 
	{
		File pictFile = new File(pictName);
		try
		{
			picture = ImageIO.read(pictFile);
		}
		catch (IOException e)
		{
			System.err.println("\n\n " + pictName + " can't be found.\n\n");
			e.printStackTrace();
		}
	}
	
	// The background is set to a specific color. super.paintComponent is then called 
	// to paint this background.
	public void paintComponent(Graphics g) 
	{	
		setBackground(new Color(0, 133, 255));
		super.paintComponent(g);
	}
	
	// If the user presses the Home JButton, the first panel (home panel) will be shown.
	public void actionPerformed(ActionEvent evt) 
	{
		String command = evt.getActionCommand();
				
		if (command.equals("Home"))
		{
			listOfCards.show(primaryPanel, "1");
		}
	}
}

class TopScoresPanel extends JPanel implements ActionListener
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	private Scanner input; // object to read input file
	private String inFileName; // name of input file to read from
	private JTextArea leaderboardJTA; // JTextArea for the top scores
	
	// The field variables are initialized and the BorderLayout has a 10 pixel
	// vertical & horizontal gap. Methods are then called to create the components.
	public TopScoresPanel (CardLayout c, CardForSpanishSailors5 p)
	{
		listOfCards = c;
		primaryPanel = p;
		
		input = null;
		inFileName = new String ("HighScores.txt");
		
		setLayout(new BorderLayout()); // 10 pixel horizontal & vertical gap for BL
		setBackground(new Color(0, 133, 255));
		
		openFile();
		createLeaderboardJL();
		createLeaderboardHomeJB();
		createLeaderboardJTA();
	}
	
	// This paintComponent method is created so after the user enters their name for 
	// the leaderboard, the leaderboard JTextArea will updated immediately.
	// setText is used to 'append' to the JTextArea. 
	public void paintComponent(Graphics g) 
	{
		String [] topScores = topScores();
		String text = new String();
		for (int i = 0; i < topScores.length; i++) 
		{
			if (topScores[i] != null) 
			{
				text += (i + 1) + ". " + topScores[i] + "\n\n\n";
			}
		}
		
		leaderboardJTA.setText(text);
	}
			
	// The leaderboard JLabel is added to another panel simply so that it can
	// be centered. The panel is then added to BL.NORTH.
	public void createLeaderboardJL()
	{
		JLabel leaderboardJL = new JLabel("Leaderboard");
		leaderboardJL.setFont(new Font("Tahoma", Font.BOLD, 55));
		leaderboardJL.setForeground(Color.WHITE);
		JPanel leaderboardJP = new JPanel();
		add(leaderboardJP, BorderLayout.NORTH);
		leaderboardJP.setLayout(new FlowLayout(FlowLayout.CENTER));
		leaderboardJP.add(leaderboardJL);
		leaderboardJP.setBackground(new Color(0, 133, 255));
	}
		
	// The leaderboard JButton is added to another panel simply so that it can
	// be centered. The panel is then added to BL.SOUTH.
	public void createLeaderboardHomeJB()
	{
		JButton leaderboardHomeJB = new JButton ("Home");
		leaderboardHomeJB.setFont(new Font("Tahoma", Font.BOLD, 50));
		leaderboardHomeJB.setBackground(Color.WHITE);
		JPanel leaderboardHomeJP = new JPanel();
		add(leaderboardHomeJP, BorderLayout.SOUTH);
		leaderboardHomeJB.addActionListener(this);
		leaderboardHomeJB.setPreferredSize(new Dimension(200, 75));
		leaderboardHomeJP.add(leaderboardHomeJB);
		leaderboardHomeJP.setBackground(new Color(0, 133, 255));
	}
		
	// This JTextArea will display all of the high scores from the HighScoresTxt.file
	// in the future. The text will be formatted and be organized neatly. 
	// Insets are used to create a margin so that the formatting looks better.
	// A scroll bar is also added so the user can scroll down.
	// Also, topScores is called. Then, in the for loop, the JTextArea is simply
	// appended to. The user name is wrote with a hyphen between it and the score.
	// topScores[i] != null is used so that if there is only one score, the other
	// scores won't appear as null.
	public void createLeaderboardJTA()
	{
		String text = new String();
		String [] topScores = topScores();
		
		for (int i = 0; i < topScores.length; i++) 
		{
			if (topScores[i] != null) 
			{
				text += (i + 1) + ". " + topScores[i] + "\n\n\n";
			}
		}
			
		leaderboardJTA = new JTextArea(text);
		
		leaderboardJTA.setFont(new Font("Tahoma", Font.PLAIN, 40));
		leaderboardJTA.setMargin(new Insets(10, 10, 10, 10));
		leaderboardJTA.setLineWrap(true);  
		leaderboardJTA.setWrapStyleWord(true); 
		JScrollPane scroller = new JScrollPane(leaderboardJTA);	
		add(scroller, BorderLayout.CENTER);
	}
	
	// This method reads from the HighScores.txt file and sorts the numbers from
	// greatest to least. First, Scanner and .hasNextLine is used to see how many 
	// total scores are in the HighScores.txt file. Then, all of the lines/scores 
	// are saved to the scores array. The file is then closed. From there, two for-loops
	// and a temp variable are used to organize all of the scores from greatest to least.
	// From there, only the top 3 scores in the array will be taken. topScores
	// is then returned from where it was called. 
	public String[] topScores() 
	{
        String [] topScores = new String[3];
		int count = 0;
		openFile();
		while (input.hasNextLine()) 
		{
			input.nextLine();
			count++;
		}

		String[] scores = new String[count];
		openFile();
		count = 0;
			
		while (input.hasNextLine()) 
		{
			String line = input.nextLine();
			scores[count++] = line;
		}
		input.close();
			
		for (int i = 0; i < count; i++) 
		{
			for (int j = i + 1; j < count; j++) 
			{
				String tempVar = "";
					
				if (Integer.parseInt(scores[i].split(" - ")[1]) < Integer.parseInt(scores[j].split(" - ")[1])) 
				{
					tempVar = scores[i];
					scores[i] = scores[j];
					scores[j] = tempVar;
				}
			}
		}
			
		for (int n = 0; n < Math.min(3, scores.length); n++) 
		{
			topScores[n] = scores[n];
		}
		
        return topScores; 
	}
	
	// This method uses a try-catch block to open the input file
	public void openFile()
	{
		File inFile = new File(inFileName);
		try
		{
			input = new Scanner (inFile);
		}
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot find/open file %s.\n\n\n", inFileName);
			System.exit(2);
		}
	}
	
	// If the user presses the home JButton, the home panel will be shown.
	public void actionPerformed(ActionEvent evt) 
	{
		String command = evt.getActionCommand();
				
		if (command.equals("Home"))
		{
			listOfCards.show(primaryPanel, "1");
		}
	}
}

class PlayOptionsPanel extends JPanel implements ActionListener, ChangeListener
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	private ButtonGroup bg;	// to select the color so only one is selected
	private int speedSVal; // value from the speed slider 
	private int livesSVal; // value from the number of lives slider
	private JSlider speedSlider; // slider for the speed of pirates
	private JSlider numOfLivesSlider; // slider for number of lives
	private String fileOpenName; // file of which questions will be chosen from
	
	// The field variables are initialized and a BorderLayout is used. Two methods
	// are called to create the components.
	public PlayOptionsPanel (CardLayout c, CardForSpanishSailors5 p)
	{
		listOfCards = c;
		primaryPanel = p;
		
		speedSVal = 50;
		livesSVal = 13;
		
		fileOpenName = new String ("RegularQuestions.txt");
		
		setLayout(new BorderLayout());
		setBackground(new Color(128, 128, 128));
		
		createBlankNWSEPanels();
		createCenterPanel();
	}
		
	// A blank cyan JPanel is added to BL.NORTH, WEST, SOUTH, & EAST.
	public void createBlankNWSEPanels()
	{
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(1000, 100));
		add(northPanel, BorderLayout.NORTH);
		northPanel.setBackground(new Color(0, 227, 255));
			
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(1000, 100));
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setBackground(new Color(0, 227, 255));
			
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(100, 700));
		add(eastPanel, BorderLayout.EAST);
		eastPanel.setBackground(new Color(0, 227, 255));
			
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(100, 700));
		add(westPanel, BorderLayout.WEST);
		westPanel.setBackground(new Color(0, 227, 255));
	}
			
	// The center panel is grey and this is where the user can customize all the settings
	// and difficulty. 
	public void createCenterPanel()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(500, 800));
		add(centerPanel, BorderLayout.CENTER);
			
		centerPanel.setLayout(new GridLayout(2,1));
		JPanel sliderHolderP = new JPanel();
		JPanel conjugationModeHolderP = new JPanel();
		centerPanel.add(sliderHolderP);
		centerPanel.add(conjugationModeHolderP);
		
		createSliders(sliderHolderP);
		createConjugationMode(conjugationModeHolderP);
	}
		
	// This panel used a GL(2,1) layout to add the two sliders respectively. The 
	// slider on the top is used to control the dificulty or the speed of the pirates.
	// The slider on the bottom is used to control the number of lives that the use has.
	// Both slides have action listeners to get their values.
	public void createSliders(JPanel sliderHolderPIn)
	{
		sliderHolderPIn.setLayout(new GridLayout(2,1));
		JPanel speedOfPirates = new JPanel();
		JPanel numOfLives = new JPanel();
		sliderHolderPIn.add(speedOfPirates);
		sliderHolderPIn.add(numOfLives);
			
		speedOfPirates.setLayout(new BorderLayout());
		numOfLives.setLayout(new BorderLayout());
		speedOfPirates.setBackground(new Color (100, 100, 100));
		numOfLives.setBackground(new Color (100, 100, 100));
		
		JLabel speedPiratesJL = new JLabel("Choose The Speed Of The Pirates");
		JLabel numHeartsJL = new JLabel("Choose The Number Of Lives");
		
		speedPiratesJL.setFont(new Font("Tahoma", Font.BOLD, 20));
		numHeartsJL.setFont(new Font("Tahoma", Font.BOLD, 20));
		speedPiratesJL.setHorizontalAlignment(JLabel.CENTER);
		numHeartsJL.setHorizontalAlignment(JLabel.CENTER);
		speedPiratesJL.setForeground(Color.WHITE);
		numHeartsJL.setForeground(Color.WHITE);
		
		speedOfPirates.add(speedPiratesJL, BorderLayout.NORTH);
		numOfLives.add(numHeartsJL, BorderLayout.NORTH);
		
		speedSlider = new JSlider(0, 100, 50); 
		speedSlider.setMajorTickSpacing(10); 
		speedSlider.setPaintTicks(true);
		speedSlider.setLabelTable(speedSlider.createStandardLabels(10)); 
		speedSlider.setPaintLabels(true);
		speedSlider.setOrientation(JSlider.HORIZONTAL);
		speedSlider.addChangeListener(this);
		speedOfPirates.add(speedSlider, BorderLayout.CENTER);
		
		numOfLivesSlider = new JSlider(0, 25, 13); 
		numOfLivesSlider.setMajorTickSpacing(5); 
		numOfLivesSlider.setPaintTicks(true);
		numOfLivesSlider.setLabelTable(numOfLivesSlider.createStandardLabels(5)); 
		numOfLivesSlider.setPaintLabels(true);
		numOfLivesSlider.setOrientation(JSlider.HORIZONTAL);
		numOfLivesSlider.addChangeListener(this);
		numOfLives.add(numOfLivesSlider, BorderLayout.CENTER);
	}
		
	// Creates a GL(2,1) and creates two panels for JRadioButton and JButtons.
	// The JRadioButtons go on the left and JButton go on the right. Inside both of 
	// the panels, a BorderLayout is then used. In BL.NORTH a JLabel is added to both
	// panels. Then the JButtons and JRadioButtons are then added to BL.CENTER.
	public void createConjugationMode(JPanel conjugationModeHolderPIn)
	{
		conjugationModeHolderPIn.setLayout(new GridLayout(1,2));
		JPanel conjugationMode = new JPanel();
		JPanel chooseMode = new JPanel();
		conjugationModeHolderPIn.add(conjugationMode);
		conjugationModeHolderPIn.add(chooseMode);
			
		conjugationMode.setLayout(new BorderLayout());
		chooseMode.setLayout(new BorderLayout());
		conjugationMode.setBackground(new Color (100, 100, 100));
		chooseMode.setBackground(new Color (100, 100, 100));
		
		JLabel conjugationModeJL = new JLabel("Choose Type Of Conjugation");
		JLabel chooseModeJL = new JLabel("Choose Mode");
		
		conjugationModeJL.setFont(new Font("Tahoma", Font.BOLD, 20));
		chooseModeJL.setFont(new Font("Tahoma", Font.BOLD, 20));
		conjugationModeJL.setHorizontalAlignment(JLabel.CENTER);
		chooseModeJL.setHorizontalAlignment(JLabel.CENTER);
		conjugationModeJL.setForeground(Color.WHITE);
		chooseModeJL.setForeground(Color.WHITE);
		
		conjugationMode.add(conjugationModeJL, BorderLayout.NORTH);
		chooseMode.add(chooseModeJL, BorderLayout.NORTH);
	
		ButtonGroup bg = new ButtonGroup();
		JPanel JRadioBHolder = new JPanel();
		conjugationMode.add(JRadioBHolder, BorderLayout.CENTER);
		JRadioBHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 25));
		JRadioBHolder.setBackground(new Color (100, 100, 100));
		
		JRadioButton regularJRB = new JRadioButton ("Regular");
		regularJRB.setFont(new Font("Tahoma", Font.PLAIN, 25));
		regularJRB.setForeground(Color.WHITE);
		regularJRB.setSelected(true);
		bg.add(regularJRB);
		regularJRB.addActionListener(this);
		JRadioBHolder.add(regularJRB);
		
		JRadioButton preteriteJRB = new JRadioButton ("Preterite");
		preteriteJRB.setFont(new Font("Tahoma", Font.PLAIN, 25));
		preteriteJRB.setForeground(Color.WHITE);
		bg.add(preteriteJRB);
		preteriteJRB.addActionListener(this);
		JRadioBHolder.add(preteriteJRB);
		
		JRadioButton presentProgJRB = new JRadioButton ("Present Progressive");
		presentProgJRB.setFont(new Font("Tahoma", Font.PLAIN, 25));
		presentProgJRB.setForeground(Color.WHITE);
		bg.add(presentProgJRB);
		presentProgJRB.addActionListener(this);
		JRadioBHolder.add(presentProgJRB);
		
		// This chunk of the following codes creates the JButton for choosing the mode.
		JPanel JButtonsHolder = new JPanel();
		chooseMode.add(JButtonsHolder, BorderLayout.CENTER);
		JButtonsHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 20));
		JButtonsHolder.setBackground(new Color (100, 100, 100));
		
		JButton learnModeJB = new JButton ("Learn Mode");
		learnModeJB.setFont(new Font("Tahoma", Font.BOLD, 30));
		learnModeJB.setForeground(new Color(120, 120, 120));	
		learnModeJB.setBackground(Color.WHITE);
		learnModeJB.addActionListener(this);
		JButtonsHolder.add(learnModeJB);
			
		JButton playModeJB = new JButton ("Play Mode");
		playModeJB.setFont(new Font("Tahoma", Font.BOLD, 30));
		playModeJB.setForeground(new Color(120, 120, 120));	
		playModeJB.setBackground(Color.WHITE);
		playModeJB.addActionListener(this);
		JButtonsHolder.add(playModeJB);
			
		JButton optionsHomeJB = new JButton ("Home");
		optionsHomeJB.setFont(new Font("Tahoma", Font.BOLD, 30));
		optionsHomeJB.setForeground(new Color(120, 120, 120));	
		optionsHomeJB.setBackground(Color.WHITE);
		optionsHomeJB.addActionListener(this);
		JButtonsHolder.add(optionsHomeJB);
	}
	
	// This handler method shows all of the necessary cards when the user presses 
	// the corresponding JButton. Also, when the user presses a JRadioButton, 
	// the certain problems will then be used.
	public void actionPerformed(ActionEvent evt) 
	{
		String command = evt.getActionCommand();
				
		if (command.equals("Learn Mode"))
		{
			listOfCards.show(primaryPanel, "5");
		}
		else if (command.equals("Play Mode"))
		{
			listOfCards.show(primaryPanel, "8");
		}
		else if (command.equals("Home")) 
		{
			listOfCards.show(primaryPanel, "1");
		}
		else if (command.equals("Regular")) 
		{
			fileOpenName = new String ("RegularQuestions.txt");
		}
		else if (command.equals("Preterite")) 
		{
			fileOpenName = new String ("PreteriteQuestions.txt");
		}
		else if (command.equals("Present Progressive")) 
		{
			fileOpenName = new String ("PresentPQuestions.txt");
		}
	}
	
	// This method handler is for the JSlider. The value from the slider will be 
	// used to change the difficulty or speed.      
	public void stateChanged (ChangeEvent evt) 
	{
		speedSVal = speedSlider.getValue();
		livesSVal = numOfLivesSlider.getValue();
	}
	
	// Getter method for speedSVal
	public int speedSVal() 
	{
		return speedSVal;
	}

	// Getter method for the number of lives
	public int getNumOflives() 
	{
		return livesSVal;
	}
	
	public void setNumOflives(int livesSVal) 
	{
		this.livesSVal = livesSVal;
	}
	
	
	// Getter method to get the file name, since there are different verbs
	public String getFileOpenName() 
	{
		return fileOpenName;
	}
}

class LearnPresentTenseV extends JPanel 
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	
	// The field variables are initialized. The layout is set to a GL(2,1).
	// Two panels/classes are created and added to the layout.
	public LearnPresentTenseV (CardLayout c, CardForSpanishSailors5 p)
	{
		listOfCards = c;
		primaryPanel = p;
		
		setLayout(new GridLayout(2, 1));
		setBackground(Color.WHITE);
		
		PresentImage pi = new PresentImage();
		JTAreaAndJBP1 jtaajbp1 = new JTAreaAndJBP1();
		
		add(pi);
		add(jtaajbp1);
	}
	
	public class PresentImage extends JPanel
	{
		private String pictName; // Name of picture being loaded.
		private Image picture; // Object the image is saved to.
		
		// The name of the picture is initialized and getMyImage is called.
		public PresentImage()
		{
			pictName = new String ("RegularConjugation.png");
			picture = null;
			
			getMyImage();
		}
		
		// This method gets the image needed by using a try catch block. 
		public void getMyImage() 
		{
			File pictFile = new File(pictName);
			try
			{
				picture = ImageIO.read(pictFile);
			}
			catch (IOException e)
			{
				System.err.println("\n\n " + pictName + " can't be found.\n\n");
				e.printStackTrace();
			}
		}
		
		// paintComponent draws the image to the panel with the specified coordinates
		public void paintComponent(Graphics g) 
		{	
			g.drawImage(picture, 225, 0, this);
		}
	}
	
	public class JTAreaAndJBP1 extends JPanel implements ActionListener
	{
		// The layout is set to a BL and createJB & createJTA are called.
		public JTAreaAndJBP1()
		{
			setLayout(new BorderLayout());
			
			createJB();
			createJTA();
		}
		
		// This method creates a blank panel and adds it to BL.SOUTH so that the
		// JButtons can be centered. The two JButtons added are Back and Bext.
		public void createJB()
		{
			JPanel JBHolderP = new JPanel();
			add(JBHolderP, BorderLayout.SOUTH);
			JBHolderP.setLayout(new FlowLayout());
			
			JButton backJB = new JButton("Back");
			backJB.setFont(new Font("Tahoma", Font.BOLD, 20));
			backJB.setForeground(new Color(242, 237, 237));		
			backJB.setBackground(new Color(128, 128, 128)); 
			backJB.setOpaque(true); 
			backJB.setBorderPainted(false); 
			backJB.addActionListener(this);
			backJB.setPreferredSize(new Dimension(200, 45));
			JBHolderP.add(backJB);	
			
			JButton nextJB = new JButton("Next");
			nextJB.setFont(new Font("Tahoma", Font.BOLD, 20));
			nextJB.setForeground(new Color(242, 237, 237));		
			nextJB.setBackground(new Color(128, 128, 128)); 
			nextJB.setOpaque(true); 
			nextJB.setBorderPainted(false);
			nextJB.addActionListener(this);
			nextJB.setPreferredSize(new Dimension(200, 45));
			JBHolderP.add(nextJB);
		}
		
		// This method adds the JTextArea with insets and also a JScrollPane to
		// BL.CENTER. The concept will be further explained in the JTA.
		public void createJTA()
		{
			JTextArea regularJTA = new JTextArea("To conjugate verbs in the present tense, simply see if it is an"
				+ " -ar verb, -er verb, or an -ir verb. If it's an ar-verb, simply remove the -ar part for the first" 
				+ " step in conjugating it. Then, if you want to conjugate it in in the yo form, simply add an 'o' to the"
				+ " end of the sentence. For example hablar in the yo form would be hablo. Then for conjugating something in the tú form," 
				+ " simply remove the -ar from the sentence, and add 'as' to the end of the sentence. For example, hablar in the tú form" 
				+ " would be hablas. This is the same idea for conjugating ar verbs in the several forms. Please look at the chart above for"
				+ " further information. Conjugating -ir and -er verbs are the exact same. You simply remove the -ir or -er depending on the"
				+ " verb you would like to conjugate. To conjugate comer or vivir, you simply remove the -ir and -er, then add 'o' for the yo"
				+ " form. If you would want to conjugate it in the tú form, you would simply add 'es' to the end of sentence. Look at the chart" 
				+ " for more information.");
			
			regularJTA.setFont(new Font("Tahoma", Font.PLAIN, 25));
			regularJTA.setMargin(new Insets(20, 20, 20, 20));
			regularJTA.setLineWrap(true);  
			regularJTA.setWrapStyleWord(true); 
			regularJTA.setBackground(new Color(128, 128, 128)); 
			JScrollPane scroller = new JScrollPane(regularJTA);	
			add(scroller, BorderLayout.CENTER);
		}
	
		// This handler method allows the user to go back or next in the card layout.
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
					
			if (command.equals("Back"))
			{
				listOfCards.show(primaryPanel, "4");
			}
			else if (command.equals("Next"))
			{
				listOfCards.show(primaryPanel, "6");
			}
		}
	}
}

class LearnPreteriteTenseV extends JPanel
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	
	// The field variables are initialized. The layout is set to a GL(2,1).
	// Two panels/classes are created and added to the layout.
	public LearnPreteriteTenseV (CardLayout c, CardForSpanishSailors5 p)
	{
		listOfCards = c;
		primaryPanel = p;
		
		setLayout(new GridLayout(2, 1));
		setBackground(Color.WHITE);
		
		PreteriteImage pri = new PreteriteImage();
		JTAreaAndJBP2 jtaajbp2 = new JTAreaAndJBP2();
		
		add(pri);
		add(jtaajbp2);
	}
	
	public class PreteriteImage extends JPanel
	{
		private String pictName; // Name of picture being loaded.
		private Image picture; // Object the image is saved to.
		
		// The name of the picture is initialized and getMyImage is called.
		public PreteriteImage()
		{
			pictName = new String ("PreteriteConjugation.png");
			picture = null;
			
			getMyImage();
		}
		
		// This method gets the image needed by using a try catch block. 
		public void getMyImage() 
		{
			File pictFile = new File(pictName);
			try
			{
				picture = ImageIO.read(pictFile);
			}
			catch (IOException e)
			{
				System.err.println("\n\n " + pictName + " can't be found.\n\n");
				e.printStackTrace();
			}
		}
		
		// paintComponent draws the image to the panel with the specified coordinates
		public void paintComponent(Graphics g) 
		{	
			g.drawImage(picture, 175, 0, this);
		}
	}
	
	public class JTAreaAndJBP2 extends JPanel implements ActionListener
	{
		// The layout is set to a BL and createJB & createJTA are called.
		public JTAreaAndJBP2()
		{
			setLayout(new BorderLayout());
			
			createJB();
			createJTA();
		}
		
		// This method creates a blank panel and adds it to BL.SOUTH so that the
		// JButtons can be centered. The two JButtons added are Back and Bext.
		public void createJB()
		{
			JPanel JBHolderP = new JPanel();
			add(JBHolderP, BorderLayout.SOUTH);
			JBHolderP.setLayout(new FlowLayout());
			
			JButton backJB = new JButton("Back");
			backJB.setFont(new Font("Tahoma", Font.BOLD, 20));
			backJB.setForeground(new Color(242, 237, 237));		
			backJB.setBackground(new Color(128, 128, 128)); 
			backJB.setOpaque(true); 
			backJB.setBorderPainted(false); 
			backJB.addActionListener(this);
			backJB.setPreferredSize(new Dimension(200, 45));
			JBHolderP.add(backJB);	
			
			JButton nextJB = new JButton("Next");
			nextJB.setFont(new Font("Tahoma", Font.BOLD, 20));
			nextJB.setForeground(new Color(242, 237, 237));		
			nextJB.setBackground(new Color(128, 128, 128)); 
			nextJB.setOpaque(true); 
			nextJB.setBorderPainted(false);
			nextJB.addActionListener(this);
			nextJB.setPreferredSize(new Dimension(200, 45));
			JBHolderP.add(nextJB);
		}
		
		// This method adds the JTextArea with insets and also a JScrollPane to
		// BL.CENTER. The concept will be further explained in the JTA.
		public void createJTA()
		{
			JTextArea preteriteJTA = new JTextArea("To conjugate verbs in the preterite tense, simply see if it is an"
				+ " -ar verb, -er verb, or an -ir verb. If it's an ar-verb, simply remove the -ar part for the first" 
				+ " step in conjugating it. Then, if you want to conjugate it in in the yo form, simply add an 'é' to the"
				+ " end of the sentence. For example hablar in the yo form would be hablé. Then for conjugating something in the tú form," 
				+ " simply remove the -ar from the sentence, and add 'aste' to the end of the sentence. For example, hablar in the tú form" 
				+ " would be hablaste. This is the same idea for conjugating ar verbs in the several forms. Please look at the chart above for"
				+ " further information. Conjugating -ir and -er verbs are the exact same. You simply remove the -ir or -er depending on the"
				+ " verb you would like to conjugate. To conjugate comer or vivir, you simply remove the -ir and -er, then add 'í' for the yo"
				+ " form. If you would want to conjugate it in the tú form, you would simply add 'iste' to the end of sentence. Look at the chart" 
				+ " for more information. If you can memorize the endings of each verbs, you can conjugate any word easily.");
			
			preteriteJTA.setFont(new Font("Tahoma", Font.PLAIN, 25));
			preteriteJTA.setMargin(new Insets(20, 20, 20, 20));
			preteriteJTA.setLineWrap(true);  
			preteriteJTA.setWrapStyleWord(true); 
			preteriteJTA.setBackground(new Color(128, 128, 128)); 
			JScrollPane scroller = new JScrollPane(preteriteJTA);	
			add(scroller, BorderLayout.CENTER);
		}
	
		// This handler method allows the user to go back or next in the card layout.
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
					
			if (command.equals("Back"))
			{
				listOfCards.show(primaryPanel, "5");
			}
			else if (command.equals("Next"))
			{
				listOfCards.show(primaryPanel, "7");
			}
		}
	}
}

class LearnPresentProgressiveTenseV extends JPanel //implements ActionListener
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	
	// The field variables are initialized. The layout is set to a GL(2,1).
	// Two panels/classes are created and added to the layout.
	public LearnPresentProgressiveTenseV (CardLayout c, CardForSpanishSailors5 p)
	{
		listOfCards = c;
		primaryPanel = p;
		
		setLayout(new GridLayout(2, 1));
		setBackground(Color.WHITE);
		
		PresentProgressiveImage ppi = new PresentProgressiveImage();
		JTAreaAndJBP3 jtaajbp3 = new JTAreaAndJBP3();
		
		add(ppi);
		add(jtaajbp3);
	}
	
	// The name of the picture is initialized and getMyImage is called.
	public class PresentProgressiveImage extends JPanel
	{
		private String pictName; // Name of picture being loaded.
		private Image picture; // Object the image is saved to.
		
		public PresentProgressiveImage()
		{
			pictName = new String ("Present Progressive Conjugation.png");
			picture = null;
			
			getMyImage();
		}
		
		// This method gets the image needed by using a try catch block. 
		public void getMyImage() 
		{
			File pictFile = new File(pictName);
			try
			{
				picture = ImageIO.read(pictFile);
			}
			catch (IOException e)
			{
				System.err.println("\n\n " + pictName + " can't be found.\n\n");
				e.printStackTrace();
			}
		}
		
		// paintComponent draws the image to the panel with the specified coordinates
		public void paintComponent(Graphics g) 
		{	
			g.drawImage(picture, 150, 0, this);
		}
	}
	
	public class JTAreaAndJBP3 extends JPanel implements ActionListener
	{
		// The layout is set to a BL and createJB & createJTA are called.
		public JTAreaAndJBP3()
		{
			setLayout(new BorderLayout());
			
			createJB();
			createJTA();
		}
		
		// This method creates a blank panel and adds it to BL.SOUTH so that the
		// JButtons can be centered. The two JButtons added are Back and Bext.
		public void createJB()
		{
			JPanel JBHolderP = new JPanel();
			add(JBHolderP, BorderLayout.SOUTH);
			JBHolderP.setLayout(new FlowLayout());
			
			JButton backJB = new JButton("Back");
			backJB.setFont(new Font("Tahoma", Font.BOLD, 20));
			backJB.setForeground(new Color(242, 237, 237));		
			backJB.setBackground(new Color(128, 128, 128)); 
			backJB.setOpaque(true); 
			backJB.setBorderPainted(false); 
			backJB.addActionListener(this);
			backJB.setPreferredSize(new Dimension(200, 45));
			JBHolderP.add(backJB);	
			
			JButton nextJB = new JButton("Next");
			nextJB.setFont(new Font("Tahoma", Font.BOLD, 20));
			nextJB.setForeground(new Color(242, 237, 237));		
			nextJB.setBackground(new Color(128, 128, 128)); 
			nextJB.setOpaque(true); 
			nextJB.setBorderPainted(false);
			nextJB.addActionListener(this);
			nextJB.setPreferredSize(new Dimension(200, 45));
			JBHolderP.add(nextJB);
		}
		
		// This method adds the JTextArea with insets and also a JScrollPane to
		// BL.CENTER. The concept can be further explained in the JTA.
		public void createJTA()
		{
			JTextArea presentPJTA = new JTextArea("To conjugate verbs in the present progessive tense, simply see if it is an"
				+ " -ar verb, -er verb, or an -ir verb. If it's an ar-verb, simply remove the -ar part for the first" 
				+ " step in conjugating it. Then, if you want to conjugate it in in the yo form, simply add an 'ando' to the"
				+ " end of the sentence. For example hablar in the yo form would be hablando. Then for conjugating something in the tú form," 
				+ " simply remove the -ar from the sentence, and once again add 'ando' to the end of the sentence. For example, hablar in the tú form" 
				+ " would be hablando. This is the same idea for conjugating ar verbs in the several forms. Please look at the chart above for"
				+ " further information. Conjugating -ir and -er verbs are the exact same. You simply remove the -ir or -er depending on the"
				+ " verb you would like to conjugate. To conjugate comer or vivir, you simply remove the -ir and -er, then add 'iendo' for the yo"
				+ " form. If you would want to conjugate it in the tú form, you would simply add 'iendo' to the end of sentence. Look at the chart" 
				+ " for more information. Important things to know: if the base verb has an 'e', it will then become an 'i'. For example, decir would"
				+ " become diciendo. If the base verb also has an ir/er, it will become a 'y'. For example leer would become leyendo. The last scenario"
				+ " is that a 'o' becomes an 'u'. For example dormir would become durmiendo.");
			
			presentPJTA.setFont(new Font("Tahoma", Font.PLAIN, 25));
			presentPJTA.setMargin(new Insets(20, 20, 20, 20));
			presentPJTA.setLineWrap(true);  
			presentPJTA.setWrapStyleWord(true); 
			presentPJTA.setBackground(new Color(128, 128, 128)); 
			JScrollPane scroller = new JScrollPane(presentPJTA);	
			add(scroller, BorderLayout.CENTER);
		}
	
		// This handler method allows the user to go back or next in the card layout.
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
					
			if (command.equals("Back"))
			{
				listOfCards.show(primaryPanel, "6");
			}
			else if (command.equals("Next"))
			{
				listOfCards.show(primaryPanel, "4");
			}
		}
	}
}

class GamePanel extends JPanel 
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	private PlayOptionsPanel playOptionsPanel; // object of PlayOptionsPanel
	
	private String[] names;	// the names of the pictures
	private Image[] images;	// array of images to be drawn

	private JLabel scoreJL; // JLabel for the score
	private JLabel waveJL; // JLabel for the wave
	private JLabel livesJL; // JLabel for the number of lives
	
	private int randNum1; // random number for the first pirate
	private int randNum2; // random number for the second pirate
	private int randNum3; // random number for the third pirate
	private int randNum4; // random number for the fourth pirate
	private String [] questions; // array that holds all the questions
	private String [] answers; // array that holds all the answers
	private int [] oldQuestions; // array that holds old question number
	private int oldQuestionsIndex; // old questions index 
	private String userAnswer; // this string holds the user's answer
	
	private int xPosPirate1; // x-coordinate of pirate in first row
	private int xPosPirate2; // x-coordinate of pirate in second row
	private int xPosPirate3; // x-coordinate of pirate in third row
	private int xPosPirate4; // x-coordinate of pirate in fourth row
	
	private int scoreNum; // total number of points user has
	private int yCordShip; // y-coordinates of the ship
	
	private int speedPirates; // speed of pirates (used in timer)
	private String inFileName; // name of input file to read from
	private int numberOfQuestions; // number of questions that are in the file
	private int numOfQuestionsWrong; // number of questions wrong that user has got

	private Timer timer; // timer object used for the animation of the pirates
	private ShipWest sw; // this FV is used so that the shipWest panel has focus after the user answers
	
	// All of the field variables are initialized and then a BorderLayout
	// is used. Then, instances of the BorderLayout.SOUTH, WEST, NORTH, and
	// CENTER are created to make nested class/ seperate panels for each 
	// BorderLayout part. Although nested classes are disadvised, they are 
	// allowed in this case since all of the nested classes are for one page.
	public GamePanel (CardLayout c, CardForSpanishSailors5 p, PlayOptionsPanel pop)
	{
		listOfCards = c;
		primaryPanel = p;
		playOptionsPanel = pop;
		
		names = new String[] {"SSPirate.png", "SSShip.gif"};
		images = new Image[names.length];

		oldQuestionsIndex = -1;
		userAnswer = new String ("");
		
		xPosPirate1 = 960;
		xPosPirate2 = 700;
		xPosPirate3 = 955;
		xPosPirate4 = 1065;
		
		scoreNum = 0;
		yCordShip = 10;
	
		numberOfQuestions = 0; 
		numOfQuestionsWrong = 0;
		
		setLayout(new BorderLayout(5, 5));
		setBackground(new Color (227, 228, 230));	
		
		ButtonsJLabelsNorth bjln = new ButtonsJLabelsNorth();
		JTextAreaSouth jtas = new JTextAreaSouth();
		sw = new ShipWest();
		QuestionsAndPiratesCenter qapc = new QuestionsAndPiratesCenter();
		
		add(bjln, BorderLayout.NORTH);
		add(jtas, BorderLayout.SOUTH);
		add(sw, BorderLayout.WEST);
		add(qapc, BorderLayout.CENTER);
	}
	
	
	// paintComponent in this component simply gets the slider value from where
	// the user can choose the speed of the pirates
	public void paintComponent (Graphics g) 
	{
		super.paintComponent(g);
		speedPirates = playOptionsPanel.speedSVal();
	}
	
	// Getter method for scoreNum 
	public int getScoreNum() 
	{
		return scoreNum;
	}
	
	// Getter method for yCordShip 
	public int getyCordShip() 
	{
		return yCordShip;
	}
	
	// Getter method for answers array 
	public String[] getAnswers() 
	{
		return answers;
	}
	
	public String[] getQuestions() 
	{
		return questions;
	}
	
	// Getter method for randNum1
	public int getRandNum1() 
	{
		return randNum1;
	}
	
	// Setter method for randNum1
	public void setRandNum1() 
	{
		randNum1 = getQuestionNumber();
	}
	
	// Getter method for randNum2
	public int getRandNum2() 
	{
		return randNum2;
	}
	
	// Setter method for randNum2
	public void setRandNum2() 
	{
		randNum2 = getQuestionNumber();
	}
	
	// Getter method for randNum3
	public int getRandNum3() 
	{
		return randNum3;
	}
	
	// Setter method for randNum3
	public void setRandNum3() 
	{
		randNum3 = getQuestionNumber();
	}
	
	// Getter method for randNum4
	public int getRandNum4() 
	{
		return randNum4;
	}
	
	// Setter method for randNum4
	public void setRandNum4() 
	{
		randNum4 = getQuestionNumber();
	}

	// This method checks whether or not a question has been repeated. If it has, a new
	// question will be generated. The while loop in this method is always true so that it
	// can constantly check if a question has been repeated. The new question index is generated
	// and compares the index to the array that holds all of the previous question indexs. If they match,
	// the while loop will run again until it generates a question that hasn't been repeated. If match == false,
	// the new question index is added to the old question index and the random number is then returned.
	public int getQuestionNumber() 
	{
		while (true) 
		{
			int randNum = (int)(Math.random() * (numberOfQuestions)) + 0;
			boolean match = false;
			for (int i = 0; i < oldQuestionsIndex; i++) 
			{
				if (oldQuestions[i] == randNum) 
				{
					match = true;
				}
			}
			if (!match) 
			{
				oldQuestions[++oldQuestionsIndex] = randNum;
				return randNum;
			}
		}
	}
	
	public class ButtonsJLabelsNorth extends JPanel implements ActionListener
	{
		// This class creates the two JButton and three JLabels that are put in 
		// BL.NORTH. The two JButtons are Exit and Home, both of which have ActionListeners
		// so that they work. The three labels, waveJL, scoreJL, and livesJL will be 
		// used to display the game info to the user. These JLabels will be updated throughout 
		// the game.
		public ButtonsJLabelsNorth()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
		
			JButton homeJB = new JButton("Home");
			homeJB.setFont(new Font("Tahoma", Font.BOLD, 20));
			homeJB.setForeground(new Color(120, 120, 120));	
			homeJB.setBackground(Color.WHITE);
			homeJB.addActionListener(this);
			homeJB.setPreferredSize(new Dimension(75, 75));
			add(homeJB);
			
			waveJL = new JLabel ("Wave: 0");
			waveJL.setFont(new Font("Tahoma", Font.BOLD, 40));
			waveJL.setForeground(Color.WHITE);
			add(waveJL);
			
			scoreJL = new JLabel ("Score: 0");
			scoreJL.setFont(new Font("Tahoma", Font.BOLD, 40));
			scoreJL.setForeground(Color.WHITE);
			add(scoreJL);
			
			livesJL = new JLabel ("Lives: " + playOptionsPanel.getNumOflives());
			livesJL.setFont(new Font("Tahoma", Font.BOLD, 40));
			livesJL.setForeground(Color.WHITE);
			add(livesJL);
			
			JButton pauseJB = new JButton("Exit");
			pauseJB.setFont(new Font("Tahoma", Font.BOLD, 20));
			pauseJB.setForeground(new Color(120, 120, 120));	
			pauseJB.setBackground(Color.WHITE);
			pauseJB.addActionListener(this);
			pauseJB.setPreferredSize(new Dimension(75, 75));
			add(pauseJB);
			setBackground(new Color (0, 195, 255));
		}
		
		// If the user presses the a specific JButton, the corresponding panel/card will be shown.
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
			
			if (command.equals("Home"))
			{
				listOfCards.show(primaryPanel, "4");
				timer.stop();
			}
			else if (command.equals("Exit"))
			{
				System.exit(3);
			}
		}
	}
	
	public class JTextAreaSouth extends JPanel implements ActionListener
	{
		private JTextField answerJTF; // the JTextField where the user enters the answer
		
		// This class simply creates a JTextField where the user will input their 
		// answer. An action listener is added and used to save that input to a
		// field variable.
		public JTextAreaSouth()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER));
			
			answerJTF = new JTextField();
			answerJTF.setFont(new Font("Tahoma", Font.PLAIN, 50));
			answerJTF.setPreferredSize(new Dimension(620, 85));
			answerJTF.addActionListener(this);
			add(answerJTF);
			
			setBackground(new Color (0, 195, 255));
		}
		
		// This method first saves the input from the user to a FV.
		// If the yCordship is 10, the user is trying to get the pirate in the 
		// first row and so on. If the user's answer equals to the right answer, 
		// the score will go by up 1. The JLabels like the Score and Wave will 
		// then updated if needed. The pirate's xPosition is then reset so that
		// the pirates keep on coming. 
		// The random numbers are also then generated so that the random questinons keep 
		// on coming until all of the questions have been answered. Based on if the user answers right,
		// the scoreJL & waveJL will be updated. The x position of the pirate will also then be reset. 
		// If the user gets the question wrong, the xPositon will also reset and the timer will stop since 
		// the incorrect panel will be shown.
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
			userAnswer = command;
			
			if (yCordShip == 10)
			{
				if (userAnswer.equalsIgnoreCase(answers[randNum1]))
				{
					answerJTF.setText("");
					scoreNum++;
					scoreJL.setText("Score: " + scoreNum);
					waveJL.setText("Wave: " + scoreNum/10);
					xPosPirate1 = 960;
					randNum1 = getQuestionNumber();
					
					sw.requestFocus();
					
					if (scoreNum == numberOfQuestions)
					{
						listOfCards.show(primaryPanel, "10");
						
						playOptionsPanel.setNumOflives(13);
						numOfQuestionsWrong = 0;
						
						xPosPirate1 = 960;
						xPosPirate2 = 700;
						xPosPirate3 = 955;
						xPosPirate4 = 1065;
						
						scoreNum = 0;
						yCordShip = 10;

						numOfQuestionsWrong = 0;
					}
				}
				else
				{
					answerJTF.setText("");
					numOfQuestionsWrong+=1;
					xPosPirate1 = 960;
										
					timer.stop();
					listOfCards.show(primaryPanel, "9");
				}
			}
			else if (yCordShip == 128)  
			{
				if (userAnswer.equalsIgnoreCase(answers[randNum2]))
				{
					answerJTF.setText("");
					scoreNum++;
					scoreJL.setText("Score: " + scoreNum);
					waveJL.setText("Wave: " + scoreNum/10);
					xPosPirate2 = 1120;
					randNum2 = getQuestionNumber();
					
					sw.requestFocus();
					
					if (scoreNum == numberOfQuestions)
					{
						listOfCards.show(primaryPanel, "10");
						
						playOptionsPanel.setNumOflives(13);
						numOfQuestionsWrong = 0;
						
						xPosPirate1 = 960;
						xPosPirate2 = 700;
						xPosPirate3 = 955;
						xPosPirate4 = 1065;
						
						scoreNum = 0;
						yCordShip = 10;

						numOfQuestionsWrong = 0;
					}
				}
				else
				{
					answerJTF.setText("");
					numOfQuestionsWrong+=1;
					xPosPirate2 = 1120;
					
					timer.stop();
					listOfCards.show(primaryPanel, "9");
				}
			}
			else if (yCordShip == 246)   
			{
				if (userAnswer.equalsIgnoreCase(answers[randNum3]))
				{
					answerJTF.setText("");
					scoreNum++;
					scoreJL.setText("Score: " + scoreNum);
					waveJL.setText("Wave: " + scoreNum/10);
					xPosPirate3 = 955;
					randNum3 = getQuestionNumber();
					
					sw.requestFocus();
					
					if (scoreNum == numberOfQuestions)
					{
						listOfCards.show(primaryPanel, "10");
						
						playOptionsPanel.setNumOflives(13);
						numOfQuestionsWrong = 0;
						
						xPosPirate1 = 960;
						xPosPirate2 = 700;
						xPosPirate3 = 955;
						xPosPirate4 = 1065;
						
						scoreNum = 0;
						yCordShip = 10;

						numOfQuestionsWrong = 0;
					}
				}
				else
				{
					answerJTF.setText("");
					numOfQuestionsWrong+=1;
					xPosPirate3 = 955;
					
					timer.stop();
					listOfCards.show(primaryPanel, "9");
				}
			}
			else if (yCordShip == 364)    
			{
				if (userAnswer.equalsIgnoreCase(answers[randNum3]))
				{
					answerJTF.setText("");
					scoreNum++;
					scoreJL.setText("Score: " + scoreNum);
					waveJL.setText("Wave: " + scoreNum/10);
					xPosPirate4 = 1065;
					randNum4 = getQuestionNumber();
					
					sw.requestFocus();
					
					if (scoreNum == numberOfQuestions)
					{
						listOfCards.show(primaryPanel, "10");
						
						playOptionsPanel.setNumOflives(13);
						numOfQuestionsWrong = 0;
						
						xPosPirate1 = 960;
						xPosPirate2 = 700;
						xPosPirate3 = 955;
						xPosPirate4 = 1065;
						
						scoreNum = 0;
						yCordShip = 10;

						numOfQuestionsWrong = 0;
					}
				}
				else
				{
					answerJTF.setText("");
					numOfQuestionsWrong+=1;
					xPosPirate4 = 1065;
					
					timer.stop();
					listOfCards.show(primaryPanel, "9");
				}
			}
		}
	}
	
	public class ShipWest extends JPanel implements MouseListener, KeyListener 
	{
		// loadMyImage is simply called. The Key & Mouse Listeners are also added.
		public ShipWest()
		{
			setBackground(new Color (0, 195, 255));
			setPreferredSize(new Dimension (130, 100));
			
			loadMyImage();
			
			addKeyListener(this);
			addMouseListener(this);
		}
		
		// This method loads all of the images by using a for-loop and calling
		// getMyImage at the same time.
		public void loadMyImage()
		{
			for (int i = 0; i < names.length; i++)
			{
				images[i] = getMyImage(names[i]);			
			}
		}
		
		// This method uses a try-catch block to save all of the images to the
		// images array where they can then be drawn. The picture is then returned. 
		// This method is called from loadMyImage and will run until all of the images 
		// are loaded. 
		public Image getMyImage(String pictName) 
		{
			Image picture = null;
			File pictFile = new File (pictName);
			try 
			{
			
				picture = ImageIO.read(pictFile);
			}
			catch(IOException e)
			{
				System.err.printf("\n\n\nERROR: Cannot find/open file %s", pictName);
				System.exit(4);
			}
				
			return picture;
		}
		
		// This method draws the image of the ship with specified y coordinate.
		// This is so the ship can move up and down if the user presses the keys.
		public void paintComponent (Graphics g) 
		{
			super.paintComponent(g);
			g.drawImage(images[1], 10, yCordShip, this);
		}
	
		// This is only so the window has the focus.
		public void mousePressed(MouseEvent evt)
		{
			requestFocusInWindow();
		}
	
		// These are added or else the program won't work.
		public void mouseReleased (MouseEvent evt) {}
		public void mouseClicked (MouseEvent evt) {}
		public void mouseEntered (MouseEvent evt) {}
		public void mouseExited (MouseEvent evt) {}
		
		// If the user presses the up arrow key, the ship will go up, which is 
		// why the y coordinate decreases by 118 coordinates. If the y coordinate
		// is less than 10, the y coordinate will be set to 10 as the bounds. Repaint
		// is then called. This is the same concept for the down arrow key. If the 
		// y-coordinate is greater than 364, it will be set to 364.
		public void keyPressed (KeyEvent evt)
		{
			int code = evt.getKeyCode();
			
			if (code == KeyEvent.VK_UP)
			{
				yCordShip -=118;
				
				if (yCordShip <= 10)
				{
					yCordShip = 10;
				}
				
				repaint();
			}
			else if (code == KeyEvent.VK_DOWN)
			{
				yCordShip +=118;
				
				if (yCordShip >= 364)
				{
					yCordShip = 364;
				}
				
				repaint(); 
			}
		}
		
		// These are added or else the program won't work.
		public void keyTyped (KeyEvent evt) {}
		public void keyReleased (KeyEvent evt) {}
	}
	
	public class QuestionsAndPiratesCenter extends JPanel
	{
		private Scanner input; // object to read input file
		private File inFile; // file object to read the questions from
		
		public QuestionsAndPiratesCenter()
		{
		}
		
		// Opens file so it can then read from it
		public void openFile()
		{
			inFile = new File(playOptionsPanel.getFileOpenName());	
			try
			{
				input = new Scanner (inFile);
			}
			catch (FileNotFoundException e)
			{
				System.err.printf("\n\n\nERROR: Cannot find/open file %s.\n\n\n", inFileName);
				System.exit(5);
			}
		}
		
		// This method reads the file and filters it. It finds the index of the 
		// comma, which is then used to seperate the questions from the answers.
		// The questions and answers are then saved to the seperate arrays. 
		// Substring is used to do this. The question and answers will then be used
		// from the following arrays. Also, the counter variable is used to see how 
		// many questions there are, so the size of the questions array will be right.
		public void readFile()
		{
			String readLine = new String ("");
			int counter = 0;
			
			while (input.hasNext())
			{
				readLine = input.nextLine();
				counter++;
			}
			
			numberOfQuestions = counter;

			questions = new String[counter];
			answers = new String[counter];
			oldQuestions = new int[counter];

			try 
			{
				input = new Scanner (inFile);
			}
			catch (FileNotFoundException e)
			{
				System.err.printf("\n\n\nERROR: Cannot find/open file %s.\n\n\n", inFileName);
				System.exit(5);
			}
			counter = 0;
			
			while (input.hasNext())
			{
				readLine = input.nextLine();
				int index =  readLine.indexOf(",");
				String question = readLine.substring(0, index);
				String answer = readLine.substring(index + 2);
				
				questions[counter] = question;
				answers[counter] = answer;
				counter++;
			}
		}
	
		// ProgressBarHandler is instantiated and the object pdh is created with
		// a delay of 40. The timer is then started. The images of pirates are then
		// also drawn in their respective rows and the conjugation is above the pirates.
		// If the position of the pirate is less than 0, that means the user has not 
		// answered the question in time and will lose a life. The numOfLives will 
		// decrease by 1 and the Lives JLabel will be updated. If the user runs out of
		// lives, the game will be over. If the user hasn't not eliminated the pirate in time,
		// the pirate will be reset, a new question will be generated, and the livesJL will
		// also be updated depending on the scenario. The game will be over if the user 
		// has run out of lives.
		public void paintComponent (Graphics g) 
		{
			super.paintComponent(g);
			setBackground(new Color (0, 195, 255));
			
			if (playOptionsPanel.getNumOflives() - numOfQuestionsWrong <= 0) 
			{
				listOfCards.show(primaryPanel, "10");
			}
						
			livesJL.setText("Lives: " + (playOptionsPanel.getNumOflives() - numOfQuestionsWrong));

			if(timer == null)
			{
				openFile();
				readFile();
				
				randNum1 = getQuestionNumber(); 
				randNum2 = getQuestionNumber(); 
				randNum3 = getQuestionNumber(); 
				randNum4 = getQuestionNumber(); 

				PirateMovingHandler pmh = new PirateMovingHandler();
				timer = new Timer(100 - speedPirates, pmh);
				timer.start();
			} 
			else if (!timer.isRunning()) 
			{
				timer.restart();
			}
			
			g.setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			g.drawImage(images[0], xPosPirate1, 20, this);
			g.drawString(questions[randNum1], xPosPirate1 + 100, 80);
		
			g.drawImage(images[0], xPosPirate2, 140, this);
			g.drawString(questions[randNum2], xPosPirate2 + 100, 200);
			
			g.drawImage(images[0], xPosPirate3, 260, this);
			g.drawString(questions[randNum3], xPosPirate3 + 100, 320);
			
			g.drawImage(images[0], xPosPirate4, 390, this);
			g.drawString(questions[randNum4], xPosPirate4 + 100, 450);
			
			if (xPosPirate1 <= 0)
			{
				xPosPirate1=960;
				randNum1 = getQuestionNumber();  
				numOfQuestionsWrong+=1;
				livesJL.setText("Lives: " + (playOptionsPanel.getNumOflives() - numOfQuestionsWrong));
				
				if ((playOptionsPanel.getNumOflives() - numOfQuestionsWrong) <= 0)
				{
					listOfCards.show(primaryPanel, "10");
					
					playOptionsPanel.setNumOflives(13);
					numOfQuestionsWrong = 0;
					
					xPosPirate1 = 960;
					xPosPirate2 = 700;
					xPosPirate3 = 955;
					xPosPirate4 = 1065;
					
					scoreNum = 0;
					yCordShip = 10;

					numOfQuestionsWrong = 0;
				}
			}
			if (xPosPirate2 <= 0)
			{
				xPosPirate2=700;
				randNum2 = getQuestionNumber();  
				numOfQuestionsWrong+=1;
				livesJL.setText("Lives " + (playOptionsPanel.getNumOflives() - numOfQuestionsWrong));
				
				if ((playOptionsPanel.getNumOflives() - numOfQuestionsWrong) <= 0)
				{
					listOfCards.show(primaryPanel, "10");
					
					playOptionsPanel.setNumOflives(13);
					numOfQuestionsWrong = 0;
					
					xPosPirate1 = 960;
					xPosPirate2 = 700;
					xPosPirate3 = 955;
					xPosPirate4 = 1065;
					
					scoreNum = 0;
					yCordShip = 10;

					numOfQuestionsWrong = 0;
				}
			}
			if (xPosPirate3 <= 0)
			{
				xPosPirate3=955;
				randNum3 = getQuestionNumber(); 
				numOfQuestionsWrong+=1;
				livesJL.setText("Lives: " + (playOptionsPanel.getNumOflives() - numOfQuestionsWrong));
				
				if ((playOptionsPanel.getNumOflives() - numOfQuestionsWrong) <= 0)
				{
					listOfCards.show(primaryPanel, "10");
					
					playOptionsPanel.setNumOflives(13);
					numOfQuestionsWrong = 0;
					
					xPosPirate1 = 960;
					xPosPirate2 = 700;
					xPosPirate3 = 955;
					xPosPirate4 = 1065;
					
					scoreNum = 0;
					yCordShip = 10;

					numOfQuestionsWrong = 0;
				}
			}
			if (xPosPirate4 <= 0)
			{
				xPosPirate4=1065;
				randNum4 = getQuestionNumber(); 
				numOfQuestionsWrong+=1;
				livesJL.setText("Lives: " + (playOptionsPanel.getNumOflives() - numOfQuestionsWrong));
				
				if ((playOptionsPanel.getNumOflives() - numOfQuestionsWrong) <= 0)
				{
					listOfCards.show(primaryPanel, "10");
					
					playOptionsPanel.setNumOflives(13);
					numOfQuestionsWrong = 0;
					
					xPosPirate1 = 960;
					xPosPirate2 = 700;
					xPosPirate3 = 955;
					xPosPirate4 = 1065;
					
					scoreNum = 0;
					yCordShip = 10;

					numOfQuestionsWrong = 0;
				}
			}
		}
		
		// As long as the pirate's x-coordinate is greater than 0, the pirate will
		// slowly start moving towards the ship (x-coordinate decreasing). repaint()
		// is then called to paint the image with its new x-coordinate. This is what causes the animation.
		class PirateMovingHandler implements ActionListener
		{ 
			public void actionPerformed(ActionEvent evt)
			{
				if(xPosPirate1 > 0)
				{
					xPosPirate1--;
					repaint();
				}
				if(xPosPirate2 > 0)
				{
					xPosPirate2--;
					repaint();
				}
				if(xPosPirate3 > 0)
				{
					xPosPirate3--;
					repaint();
				}
				if(xPosPirate4 > 0)
				{
					xPosPirate4--;
					repaint();
				}
				else
				{
					timer.stop();
					timer = null;
				}
			}
		}
	}
}

class IncorrectPanel extends JPanel implements ActionListener
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	private GamePanel gamePanel; // gamePanel object to access FV's
	private JLabel corrAnswer; // correct answer for the corresponding question
	
	// Field variable are initialized and a GL(5,1). 5 methods are then called 
	// for each row in the grid layout.
	public IncorrectPanel (CardLayout c, CardForSpanishSailors5 p, GamePanel g)
	{
		listOfCards = c;
		primaryPanel = p;
		gamePanel = g;
		
		setLayout(new GridLayout(5, 1));
		setBackground(new Color(0, 214, 255));	
		
		makeGridRow1();
		makeGridRow2();
		makeGridRow3();
		makeGridRow4();
		makeGridRow5();	
	}

	// Makes the JLabel in the first row
	public void makeGridRow1()
	{
		JPanel holder1 = new JPanel();
		add(holder1);
		holder1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel incorrectNiceTry = new JLabel ("Incorrect, But Nice Try");
		incorrectNiceTry.setFont(new Font("Tahoma", Font.BOLD, 70));
		incorrectNiceTry.setForeground(Color.WHITE);
		holder1.add(incorrectNiceTry);
		
		holder1.setBackground(new Color(0, 214, 255));
	}
	
	// Makes the JLabel in the second row
	public void makeGridRow2()
	{
		JPanel holder2 = new JPanel();
		add(holder2);
		holder2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel pracLearn = new JLabel ("Practice Learning");
		pracLearn.setFont(new Font("Tahoma", Font.BOLD, 70));
		pracLearn.setForeground(Color.WHITE);
		holder2.add(pracLearn);
		
		holder2.setBackground(new Color(0, 214, 255));
	}
	
	// Makes 3 JButtons so that the user can learn again if wanted
	// Note: timer will be stopped when user wants to go to another panel
	// The timer will then be resumed when the user is back on the game panel
	public void makeGridRow3()
	{
		JPanel holder3 = new JPanel();
		add(holder3);
		holder3.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		
		JButton regularJB = new JButton("Regular");
		regularJB.setFont(new Font("Tahoma", Font.BOLD, 30));
		regularJB.setForeground(new Color(120, 120, 120));		
		regularJB.setBackground(Color.WHITE); 
		regularJB.setOpaque(true); 
		regularJB.setBorderPainted(false); 
		regularJB.addActionListener(this);
		regularJB.setPreferredSize(new Dimension(220, 80));
		holder3.add(regularJB);
		
		JButton preteriteJB = new JButton("Preterite");
		preteriteJB.setFont(new Font("Tahoma", Font.BOLD, 30));
		preteriteJB.setForeground(new Color(120, 120, 120));		
		preteriteJB.setBackground(Color.WHITE); 
		preteriteJB.setOpaque(true); 
		preteriteJB.setBorderPainted(false); 
		preteriteJB.addActionListener(this);
		preteriteJB.setPreferredSize(new Dimension(220, 80));
		holder3.add(preteriteJB);
		
		JButton presentPJB = new JButton("<html> <center> Present <br>" + "Progressive </center> </html>");
		presentPJB.setFont(new Font("Tahoma", Font.BOLD, 30));
		presentPJB.setForeground(new Color(120, 120, 120));		
		presentPJB.setBackground(Color.WHITE); 
		presentPJB.setOpaque(true); 
		presentPJB.setBorderPainted(false); 
		presentPJB.addActionListener(this);
		presentPJB.setPreferredSize(new Dimension(220, 80));
		holder3.add(presentPJB);	
		
		holder3.setBackground(new Color(0, 214, 255));	
	}
	
	// Creates the JLabel in the fourth row that tells the user the correct 
	// answer.
	public void makeGridRow4()
	{
		JPanel holder4 = new JPanel();
		add(holder4);
		holder4.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		corrAnswer = new JLabel ("Correct Answer: ");
		corrAnswer.setFont(new Font("Tahoma", Font.BOLD, 40));
		corrAnswer.setForeground(Color.WHITE);
		holder4.add(corrAnswer);
		
		holder4.setBackground(new Color(0, 214, 255));
	}
	
	// Creates a JButton to go back to the game in the fifth row
	public void makeGridRow5()
	{
		JPanel holder5 = new JPanel();
		add(holder5);
		holder5.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton presentPJB = new JButton("Return Back To Game");
		presentPJB.setFont(new Font("Tahoma", Font.BOLD, 40));
		presentPJB.setForeground(new Color(120, 120, 120));		
		presentPJB.setBackground(Color.WHITE); 
		presentPJB.setOpaque(true); 
		presentPJB.setBorderPainted(false); 
		presentPJB.addActionListener(this);
		presentPJB.setPreferredSize(new Dimension(490, 95));
		holder5.add(presentPJB);	
		
		holder5.setBackground(new Color(0, 214, 255));
	}
	
	// paintComponent in this case sees what the correct answer is depending on what row
	// the ship is on. For example, if the ship is in the first row, we know that the user
	// got the question wrong from the first row. A getter method is used to recieve the correct answer
	// from the answers array. After this, a random number is then generated so that the pirates keep on coming 
	// and so that their xPositions are also reset. The correct answer is then displayed to the user so that the 
	// user can learn from their mistakes.
	public void paintComponent(Graphics g) 
	{
		String correctAnswer = new String ("");
		String questionAsked = new String ("");
		
		if (gamePanel.getyCordShip() == 10) 
		{
			correctAnswer = gamePanel.getAnswers()[gamePanel.getRandNum1()];
			questionAsked = gamePanel.getQuestions()[gamePanel.getRandNum1()];
			gamePanel.setRandNum1();
			
		}
		else if (gamePanel.getyCordShip() == 128)   
		{
			correctAnswer = gamePanel.getAnswers()[gamePanel.getRandNum2()];
			questionAsked = gamePanel.getQuestions()[gamePanel.getRandNum2()];

			gamePanel.setRandNum2();

		}
		else if (gamePanel.getyCordShip() == 246) 
		{
			correctAnswer = gamePanel.getAnswers()[gamePanel.getRandNum3()];
			questionAsked = gamePanel.getQuestions()[gamePanel.getRandNum3()];

			gamePanel.setRandNum3();

		}
		else if (gamePanel.getyCordShip() == 364) 
		{
			correctAnswer = gamePanel.getAnswers()[gamePanel.getRandNum4()];
			questionAsked = gamePanel.getQuestions()[gamePanel.getRandNum4()];

			gamePanel.setRandNum4();

		}
		corrAnswer.setText(questionAsked + ", Correct Answer: " + correctAnswer);
	}
	

	// This handler method allows the user to navigate throughout the JPanels
	// by pressing the JButtons.
	public void actionPerformed(ActionEvent evt) 
	{

		String command = evt.getActionCommand();
			
		if (command.equals("Regular"))
		{
			listOfCards.show(primaryPanel, "5");
		}
		else if (command.equals("Preterite"))
		{
			listOfCards.show(primaryPanel, "6");
		}
		else if (command.equals("<html> <center> Present <br>" + "Progressive </center> </html>"))
		{
			listOfCards.show(primaryPanel, "7");
		}
		else if (command.equals("Return Back To Game"))
		{
			listOfCards.show(primaryPanel, "8");
		}
	}
}

class GameOverPanel extends JPanel implements ActionListener
{
	private CardLayout listOfCards; // CardLayout that holds all of the panels/cards
	private CardForSpanishSailors5 primaryPanel; // Parent JPanel in the CardLayout
	private String userName; // username that the user enters for the high score
	private GamePanel gameOverPanel; // GameOverPanel object
	private int scoreNum; // score that user gets
	
	// Field variable are initialized and a GridLayout is used.
	// Three methods are used to create the three rows.
	public GameOverPanel (CardLayout c, CardForSpanishSailors5 p, GamePanel g)
	{
		listOfCards = c;
		primaryPanel = p;
		gameOverPanel = g;
		
		scoreNum = 0;
		userName = new String ("");
		
		setLayout(new GridLayout(3, 1));
		
		createRow1();
		createRow2();
		createRow3();
	}
	
	// Creates the JLabel that says "Game over! Nice Try"
	public void createRow1()
	{
		JPanel holder1 = new JPanel();
		add(holder1);
		holder1.setBackground(new Color (0, 133, 255));
		holder1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel gameOverJL = new JLabel ("Game over! Nice Try");
		gameOverJL.setFont(new Font("Tahoma", Font.BOLD, 70));
		gameOverJL.setForeground(new Color(0, 227, 255));
		holder1.add(gameOverJL);
	}
	
	// Creates the JLabel that says "Enter Your Name:"
	public void createRow2()
	{
		JPanel holder2 = new JPanel();
		add(holder2);
		holder2.setBackground(new Color (0, 133, 255));
		holder2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel enterNameJL = new JLabel ("Enter Your Name:");
		enterNameJL.setFont(new Font("Tahoma", Font.BOLD, 70));
		enterNameJL.setForeground(Color.WHITE);
		holder2.add(enterNameJL);
	}
	
	// Creates the JTextField where the user enters their name
	public void createRow3()
	{
		JPanel holder3 = new JPanel();
		add(holder3);
		holder3.setBackground(new Color (0, 133, 255));
		holder3.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JTextField enterNameJTF = new JTextField("Enter Name");
		enterNameJTF.setFont(new Font("Tahoma", Font.PLAIN, 80));
		enterNameJTF.setPreferredSize(new Dimension(800, 125));
		enterNameJTF.addActionListener(this);
		holder3.add(enterNameJTF);
	}	
	
	// This handler method gets input from the JTextField.
	// This method also writes to the output file using FileWriter and PrintWriter.
	// They are both used to append to the .txt file. The userName and score are added 
	// to the text file with a comma between them. 
	// If HighScores.txt is not found, an error message will be printed to terminal.
	// In a way, the PrintWriter is wrapped around a FileWriter.
	public void actionPerformed(ActionEvent evt) 
	{
		String command = evt.getActionCommand();
		
		userName = command;
		scoreNum = gameOverPanel.getScoreNum();
	
        PrintWriter pw = null;
        String outFileName = new String ("HighScores.txt");
       
		File outFile = new File (outFileName);
		try
		{
			pw = new PrintWriter(new FileWriter(outFile, true));
		}
		catch (IOException e)
		{
			System.err.println("Cannot append to " + outFileName + " file.");
			System.exit(6);
		}
	
		pw.println(userName + " - " + scoreNum);
		pw.close();
        
        listOfCards.show(primaryPanel, "1");
	}
}
