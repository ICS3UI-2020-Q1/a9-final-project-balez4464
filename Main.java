import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements Runnable, ActionListener{

  // Class Variables  
  JLabel subTitleLabel;
  JLabel usersGuessLabel;
  JLabel opponentsGuessLabel;
  JLabel startScreenLabel;
  JLabel instructionLabel;
  JLabel resultLabel;

  
  JLabel usersMovePic;
  JLabel opponentsMovePic;
  ImageIcon usersMovePicture;
  ImageIcon opponentMovePicture;
  ImageIcon rockPic;
  ImageIcon paperPic;
  ImageIcon scissorsPic;


  JButton rockButton;
  JButton paperButton;
  JButton scissorsButton;
  JButton nextRoundButton;
  JButton startButton;
  JButton newGameButton;
  JButton continueButton;


  JPanel gameScreen;
  JPanel titleScreen;
  JPanel winGameOverScreen;
  JPanel mainPanel;

  CardLayout screens;

  //variables for points
  int opponentPoints = 0;
  int userPoints = 0;

  //random number for oppenets guess where 1 is rock, 2 is paper and 3 is scissors
  int randNum = randNum = (int)(Math.random()*(3 - 1 + 1))+ 1;


  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Rock, Paper, Scissors");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true);

    //creates the panel for the game screens
    gameScreen = new JPanel();
    gameScreen.setLayout(null);

    //seeting up images
    usersMovePicture = new ImageIcon("rock-paper-scissors.jpg"); 
    opponentMovePicture = new ImageIcon("rock-paper-scissors.jpg");
    rockPic = new ImageIcon("Rock.png");
    paperPic = new ImageIcon("Paper.png");
    scissorsPic = new ImageIcon("Scissors.png");

    //setting up the picture labels
    usersMovePic = new JLabel(usersMovePicture);
    opponentsMovePic = new JLabel(opponentMovePicture);
    //setting bounds for them
    usersMovePic.setBounds(150, 100, 200, 200);
    opponentsMovePic.setBounds(475, 100, 200, 200);

    //adding the pictures to the mainPanel
    gameScreen.add(usersMovePic);
    gameScreen.add(opponentsMovePic);

    //setting up labrls at the top and underneath images
    subTitleLabel = new JLabel("Battle Grounds");
    subTitleLabel.setBounds(360, 50, 150, 30);
    usersGuessLabel = new JLabel("YOUR POINTS : 0");
    usersGuessLabel.setBounds(150, 315, 200, 30);
    opponentsGuessLabel = new JLabel("OPPONENTS POINTS : 0");
    opponentsGuessLabel.setBounds(475, 315, 200, 30);

    //adds labesl to main frame
    gameScreen.add(subTitleLabel);
    gameScreen.add(usersGuessLabel);
    gameScreen.add(opponentsGuessLabel); 

    //setting up the rock paper scissors buttons and input and reset buttons
    rockButton = new JButton("ROCK");
    rockButton.setBounds(205, 370, 130, 30);
    paperButton = new JButton("PAPER");
    paperButton.setBounds(346, 370, 130, 30);
    scissorsButton = new JButton("SCISSORS");
    scissorsButton.setBounds(487, 370, 130, 30);

    nextRoundButton = new JButton("Next Round");
    nextRoundButton.setBounds(260, 440, 305, 35);

    continueButton = new JButton("CONTINUE");
    continueButton.setBounds(260, 500, 305, 35);

    //sets action command and adds action Listener
    rockButton.setActionCommand("rockButton");
    rockButton.addActionListener(this);
    paperButton.setActionCommand("paperButton");
    paperButton.addActionListener(this);
    scissorsButton.setActionCommand("scissorsButton");
    scissorsButton.addActionListener(this);

    nextRoundButton.setActionCommand("nextRound");
    nextRoundButton.addActionListener(this);

    continueButton.setActionCommand("continue");
    continueButton.addActionListener(this);
    continueButton.setEnabled(false);

    //make buttons set  as visible
    gameScreen.add(rockButton);
    gameScreen.add(paperButton);
    gameScreen.add(scissorsButton);
    gameScreen.add(nextRoundButton);
    gameScreen.add(continueButton);

    
    //sets up title screen panel
    titleScreen = new JPanel();
    titleScreen.setLayout(null);

    //to create a label for the start screen
    startScreenLabel = new JLabel("Welcome to Rock, Paper, Scissors!");
    startScreenLabel.setBounds(310, 190, 300, 50);
    instructionLabel = new JLabel("Get 5 Points to Win the Game!");
    instructionLabel.setBounds(326, 240, 300, 50);

    //sets up start button for title screen
    startButton = new JButton("START GAME");
    startButton.setBounds(335, 300, 200, 50);
    startButton.setActionCommand("start");
    startButton.addActionListener(this);

    //adds start button to the title screen
    titleScreen.add(startButton);
    titleScreen.add(startScreenLabel);
    titleScreen.add(instructionLabel);

    //creating game over or win screen
    winGameOverScreen = new JPanel();
    winGameOverScreen.setLayout(null);

    //setting up new game button
    newGameButton = new JButton("NEW GAME");
    newGameButton.setBounds(335, 300, 200, 50);
    newGameButton.setActionCommand("newGame");
    newGameButton.addActionListener(this);

    //setting up final labels
    resultLabel = new JLabel("You shouldnt even see this message");
    resultLabel.setBounds(400, 220, 300, 30);
    
    //adding these to the win or gameoevr screen
    winGameOverScreen.add(newGameButton);
    winGameOverScreen.add(resultLabel);

    //setting up th ecard CardLayout
    screens = new CardLayout();
    mainPanel = new JPanel();
    mainPanel.setLayout(screens);

    //adds the other screens to the main panel
    mainPanel.add(titleScreen, "titleScreen");
    mainPanel.add(gameScreen, "gameScreen");
    mainPanel.add(winGameOverScreen, "winGameOverScreen");

    frame.add(mainPanel);

    //show title screen first
    screens.show(mainPanel, "titleScreen");

  }



  // method called when a button is pressed
  public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand();

    //if the start button is hit
    if(command.equals("start")){
      screens.show(mainPanel, "gameScreen");
      
    }


    //for if user chooses rock
    if(command.equals("rockButton")){
      usersMovePic.setIcon(rockPic);
      //to set the buttons as un touchable after a turn
      rockButton.setEnabled(false);
      paperButton.setEnabled(false);
      scissorsButton.setEnabled(false);
      //win as rock
      if(randNum == 3){
        subTitleLabel.setText("You won the round");
        opponentsMovePic.setIcon(scissorsPic);
        //adds 1 point to the users point total
        userPoints = userPoints + 1;
        usersGuessLabel.setText("YOUR POINTS : " + userPoints);
        //if they lose as rock
      }else if(randNum == 2){
        subTitleLabel.setText("You lost the round");
        opponentsMovePic.setIcon(paperPic);
        //adds 1 point to the opponents point total
        opponentPoints = opponentPoints + 1;
        opponentsGuessLabel.setText("OPPENENTS POINTS : " + opponentPoints);
      //if they tie as rock
      }else if(randNum == 1){
        subTitleLabel.setText("It's a Tie!");
        opponentsMovePic.setIcon(rockPic);
        }
    }
      
    //if use chooses paper
    if(command.equals("paperButton")){
      usersMovePic.setIcon(paperPic);
      //to set the buttons as un touchable after a turn
      rockButton.setEnabled(false);
      paperButton.setEnabled(false);
      scissorsButton.setEnabled(false);
      //win
      if(randNum == 1){
        subTitleLabel.setText("You won the round");
        opponentsMovePic.setIcon(rockPic);
        //adds 1 point to the users point total
        userPoints = userPoints + 1;
        usersGuessLabel.setText("YOUR POINTS : " + userPoints);
      //lose
      }else if(randNum == 3){
        subTitleLabel.setText("You lost the round");
        opponentsMovePic.setIcon(scissorsPic);
        //adds 1 point to the opponents point total
        opponentPoints = opponentPoints + 1;
        opponentsGuessLabel.setText("OPPONENTS POINTS : " + opponentPoints);
      //tie
      }else if(randNum == 2){
        subTitleLabel.setText("It's a Tie!");
        opponentsMovePic.setIcon(paperPic);

       }
     }
    

    //if they choose scissors
    if(command.equals("scissorsButton")){
      usersMovePic.setIcon(scissorsPic);
      //to set the buttons as un touchable after a turn
      rockButton.setEnabled(false);
      paperButton.setEnabled(false);
      scissorsButton.setEnabled(false);
      //win
      if(randNum == 2){
        subTitleLabel.setText("You won the round");
        opponentsMovePic.setIcon(paperPic);
        //adds 1 point to the users point total
        userPoints = userPoints + 1;
        usersGuessLabel.setText("YOUR POINTS : " + userPoints);
      //lose
      }else if(randNum == 1){
        subTitleLabel.setText("You lost the round");
        opponentsMovePic.setIcon(rockPic);
        //adds 1 point to the opponents point total
        opponentPoints = opponentPoints + 1;
        opponentsGuessLabel.setText("OPPONENTS POINTS : " + opponentPoints);
      //tie
      }else if(randNum == 3){
        subTitleLabel.setText("It's a Tie!");
         opponentsMovePic.setIcon(scissorsPic);
      }
    }     
      

    //for if they click the next round button  
    if(command.equals("nextRound")){
      //re setting the random number
      randNum = (int)(Math.random()*(3 - 1 + 1))+ 1;
      //resetting the text areas
      subTitleLabel.setText("Battle Grounds");
      //to re set the pics to default
      usersMovePic.setIcon(usersMovePicture);
      opponentsMovePic.setIcon(opponentMovePicture);
      //to re set the buttons to be hittable
      rockButton.setEnabled(true);
      paperButton.setEnabled(true);
      scissorsButton.setEnabled(true);
    }

    //if user finishes game
    if(userPoints == 5){
      resultLabel.setText("You Won!");
      continueButton.setEnabled(true);
      nextRoundButton.setEnabled(false);
    //if loser losese the game
    }else if(opponentPoints == 5){
      resultLabel.setText("You Lost");
      continueButton.setEnabled(true);
      nextRoundButton.setEnabled(false);

    }

    //to continue to end screen
    if(command.equals("continue")){
      //to switch screens
      screens.show(mainPanel, "winGameOverScreen");
    }

    //if the user hits the new game button
    if(command.equals("newGame")){
      //resetting game
      usersGuessLabel.setText("YOUR POINTS : 0");
      opponentsGuessLabel.setText("OPPONENTS POINTS : 0");
      userPoints = 0;
      opponentPoints = 0;
      screens.show(mainPanel, "titleScreen");
      continueButton.setEnabled(false);
      nextRoundButton.setEnabled(true);
      //resetting the text areas
      subTitleLabel.setText("Battle Grounds");
      //to re set the pics to default
      usersMovePic.setIcon(usersMovePicture);
      opponentsMovePic.setIcon(opponentMovePicture);
      //to re set the buttons to be hittable
      rockButton.setEnabled(true);
      paperButton.setEnabled(true);
      scissorsButton.setEnabled(true);

    }

    

  }


  // Main method to start our program
  public static void main(String[] args){
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}

