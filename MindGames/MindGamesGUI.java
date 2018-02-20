import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import java.util.Arrays;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.event.Event;
import javafx.scene.input.ScrollEvent;
import javafx.event.EventHandler;
import javafx.scene.text.TextAlignment;
import javafx.beans.value.ChangeListener; 
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.animation.AnimationTimer;
import java.util.Random;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;



public class MindGamesGUI extends Application{
	
	Stage stage;
	ArrayList<MGObject> scores;
	MGFileParser mg = new MGFileParser();
	MGControlUnit mgc = new MGControlUnit();
	String currentUsername = "";
	int score = mgc.getScore();
	String scoreHeader = "Score: "+score;
	int time = mgc.getTime();
	String timeHeader = "Time: "+time;
	String question = "Question";
	String questionHeader = question;
	String levelHeader = mgc.getLevel();
	String modeHeader = mgc.getMode();
	AnimationTimerExt timer = null;
	Label scoreLabel = new Label("Score: "+mgc.getScore());
	Label status = new Label("\t");
	TextField [][] tf2D = null;
	int skipNum = 5;
	int maxTime = 0;
	
	int incScoreValue = 5, 
		incTimeValue = 5, 
		decScoreValue = 5, 
		decTimeValue = 5;
	
		String STYLE_TF = 
"-fx-background-color: #ffffe0; -fx-border-color: #d3d3d3; " +
"-fx-border-radius: 2;";
		String buttonStyle = "-fx-faint-focus-color: transparent;" ;
		Font FONT = new Font(null,10);
// >>>>	All attributes must be private.
	
	public MindGamesGUI(){
		super();
		stage = null;
	}
	/*
	levelHeader = mgc.getLevel();
	modeHeader = mgc.getMode();
	
	
	*/
	
	
	
	public String updateLevel(){
		return mgc.getLevel();
	}
	
	public String updateScore(){
		score = mgc.getScore();
		return "Score: "+score;
		
	}
	
	public String updateMode(){
		return mgc.getMode();
	}
		
		
		
    @Override
    public void start( Stage stage0 ) {
	if ( stage == null )
	    stage = stage0;
	stage.setResizable(false);
	startScreen();
	
    }
	
	public void startScreen(){
		mg.clearList();
		mg.readFile("levelFile");
		scores = mg.getList();
	
		status.setText("");
		Label userName = new Label("Enter your username");
		userName.setFont(new Font(9));
		userName.setAlignment(Pos.CENTER);
		userName.setMaxWidth(Double.MAX_VALUE);
	
	
		Label label = new Label("\t   Welcome to Mind Games!");
		label.setFont(new Font("Arial Black",15));
		//label.setAlignment(Pos.CENTER);
		label.setMaxWidth(Double.MAX_VALUE);
	
		Label scoreLabel2 = new Label("");
	
		if(scores.size() > 0){
			String scoreText = "SCORES";
			scoreLabel2 = new Label(scoreText);
			scoreLabel2.setFont(new Font("Arial Black",15));
			scoreLabel2.setMaxWidth(Double.MAX_VALUE);
			scoreLabel2.setAlignment( Pos.CENTER );
			scoreLabel2.setPrefWidth(200);
		}
	
		Label label1 = new Label("\t  Username\t\t    Level\t\t      Score\t\t 	Time");
		label1.setFont(new Font(10));
		
		
		
	
	
		TextField nameButton = new TextField("");
		nameButton.setPrefWidth( 80 );
		nameButton.setAlignment( Pos.CENTER );
		nameButton.setPromptText("Enter Username");
		nameButton.setMaxWidth(150);
	

	
		//text.setAlignment(Pos.CENTER);
		Button b1 = new Button("Start");
		b1.setFont(new Font("System",13));
		b1.setOnAction( e -> startGame(nameButton.getText()));
		b1.setPrefWidth(93);
		//b1.setDisable(true);
		
		
		
	
		tf2D = buildScoreList();
		
		ScrollPane sp = new ScrollPane();
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		sp.setContent(wrapTF2D(tf2D));
		sp.setFitToHeight(true);
		sp.setMaxHeight(150);
		//sp.setMaxWidth(500);
		sp.setVmax(50);
		//sp.setHmax(160);
		//sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	
		GridPane labelHolder = new GridPane();
		labelHolder.addRow(0,label);
	
		GridPane gp = new GridPane();
		gp.setFillWidth(label,true);
		gp.setMargin(b1,new Insets(10,0,0,0));
		gp.addRow( 0, labelHolder);
		gp.addRow(1,b1);
		gp.addRow(4,scoreLabel2);
		gp.addRow(2,nameButton);
		gp.addRow(3,userName);
		gp.addRow(5,label1);
		gp.addRow(6,sp);
		
		gp.setHalignment(label,HPos.CENTER);
		gp.setHalignment(b1,HPos.CENTER);
		gp.setHalignment(nameButton,HPos.CENTER);
		gp.setHalignment(scoreLabel,HPos.CENTER);
		
	
	

		b1.setDisable(true);
	
		nameButton.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
	               //System.out.println("Label Text Changed");
				   if(nameButton.getText().isEmpty() || nameButton.getText().equals("") || nameButton.getText().equals(" ")){
					   b1.setDisable(true);
				   }
				   else{
					   b1.setDisable(false);
				   }
	            }
	        }); 

		stage.setTitle( "Welcome to Mind Games!" );

		BorderPane bp = new BorderPane();
		//bp.setPrefSize(500,300);
		bp.setCenter( gp );
	//	bp.setBottom( buildBottom() );
		Scene scene = new Scene( new Group() );
		( ( Group )scene.getRoot() ).getChildren().addAll( bp ); 
		
		
		
		
		
		
		
		
		stage.setScene( scene );
		stage.show();
	}
	
    private TextField [][] buildScoreList() {
	int rows = scores.size(), //scores.size(),
	    cols = scores.get(0).getList().size();
	TextField [][] tf2D = new TextField[ rows ][ cols ];
	for ( int r = 0; r < rows; r++ ) {
	    for ( int c = 0; c < cols; c++ ) {
		tf2D[ r ][ c ] = new TextField( "" );
		tf2D[ r ][ c ].setPrefWidth( 80 );
		tf2D[ r ][ c ].setFont( FONT );
		tf2D[ r ][ c ].setAlignment( Pos.BASELINE_RIGHT );
		tf2D[ r ][ c ].setStyle( STYLE_TF );
		tf2D[ r ][ c ].setEditable( false );
	}
	
		ArrayList<String> list = scores.get(r).getList();
		int size = list.size();
		
		for(int j =0;j<size;j++){
			tf2D[r][j].setText(list.get(j));
		}
		
	}

	return tf2D;
    }
	
    private GridPane wrapTF2D( TextField [][] tf2D ) {
	GridPane gridP = new GridPane();
	gridP.setPadding( new Insets( 0, 0, 10, 0 ) );

	for ( int r = 0; r < tf2D.length; r++ )
	    for ( int c = 0; c < tf2D[ 0 ].length; c++ )
		gridP.add( tf2D[ r ][ c ], c, r );

	return gridP;
    }
	
	public void startGame(String text){
		stage.close();
		//System.out.println("Implement startGame()");
		currentUsername = text;
		//System.out.println("Current Username is "+currentUsername);
		makeGameUI();
		stage.setResizable(false);
		
	}
	
	
	public void makeGameUI(){
		BorderPane bp1 = new BorderPane();
		bp1.setPrefSize(500,350);
		
		Label l1 = new Label(questionHeader);
		l1.setFont(new Font(15));
		//l1.setPrefWidth(400);
		l1.setAlignment(Pos.CENTER);
		l1.setWrapText(true);
		
		scoreLabel = new Label(scoreHeader);
		scoreLabel.setFont(new Font(12));
		scoreLabel.setPrefWidth(750);
		scoreLabel.setAlignment(Pos.BASELINE_LEFT);
		
		Label timeLabel = new Label(timeHeader);
		timeLabel.setFont(new Font(12));
		timeLabel.setPrefWidth(750);
		timeLabel.setAlignment(Pos.BASELINE_RIGHT);
		
		Label levelLabel = new Label(levelHeader);
		levelLabel.setFont(new Font(12));
		levelLabel.setPrefWidth(750);
		levelLabel.setAlignment(Pos.CENTER);
		
		Label modeLabel = new Label(modeHeader);
		modeLabel.setFont(new Font(20));
		modeLabel.setPrefWidth(750);
		modeLabel.setAlignment(Pos.CENTER);
		
		RadioButton rb1 = new RadioButton("Answer1");
		rb1.setMaxWidth(500);
		rb1.setOnAction(e -> processAnswer(rb1.getText(),rb1,scoreLabel));
		rb1.setStyle(buttonStyle);
		
		
		
		RadioButton rb2 = new RadioButton("Answer2");
		rb2.setMaxWidth(500);
		rb2.setOnAction(e -> processAnswer(rb2.getText(),rb2,scoreLabel));
		RadioButton rb3 = new RadioButton("Answer3");
		rb3.setMaxWidth(500);
		rb3.setOnAction(e -> processAnswer(rb3.getText(),rb3,scoreLabel));
		RadioButton rb4 = new RadioButton("Answer4");
		rb4.setMaxWidth(500);
		rb4.setOnAction(e -> processAnswer(rb4.getText(),rb4,scoreLabel));
		
		Button rb5 = new Button("Skip (" +skipNum+ ")");
		rb5.setMaxWidth(100);
		rb5.setOnAction(e -> processAnswer("skip",rb4,scoreLabel));
		
		if(skipNum <= 0){
			rb5.setVisible(false);
			
		
		
		
	}
	
		
		status.setFont(new Font(12));
		status.setPrefWidth(600);
		status.setAlignment(Pos.CENTER);
		
		
			
		
		GridPane bottom = new GridPane();
		
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        vbox.getChildren().add(rb1);
        vbox.getChildren().add(rb2);
        vbox.getChildren().add(rb3);
		vbox.getChildren().add(rb4);
		vbox.getChildren().add(rb5);
        vbox.setSpacing(10);
        hbox.getChildren().add(vbox);
        hbox.setSpacing(50);
		hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 10, 10, 20));
			
		//bottom.addRow(0,hbox2);
		
		bottom.addRow(0,hbox);
		bottom.addRow(1,status);
		//bottom.addRow(2,rb5);
		
		//bottom.setHalignment(rb5,HPos.CENTER);
		
		GridPane top2 = new GridPane();
		top2.add(timeLabel,1,0);
		top2.add(scoreLabel,0,0);
		
		
		GridPane top = new GridPane();
		//top.add(timeLabel,1,0);
		//top.add(scoreLabel,0,0);	
		//top.addRow(1,hbox2);
	    top.addRow(1,modeLabel);
		top.addRow(0,levelLabel);
		top.addRow(2,l1);
		
		top.setHalignment(modeLabel,HPos.CENTER);
		top.setHalignment(levelLabel,HPos.CENTER);
		top.setHalignment(l1,HPos.CENTER);
		
		
		
		bp1.setTop( top2 );
		bp1.setCenter(top);
		bp1.setBottom(bottom);
		
		
		
		Scene scene = new Scene( new Group() );
	/*	scene.widthProperty().addListener( 
		    new ChangeListener() {
		        public void changed(ObservableValue observable, 
		                            Object oldValue, Object newValue) {
		            Double width = (Double)newValue;
		            bp1.setPrefWidth(width);
		        }
		    });

		scene.heightProperty().addListener(
		    new ChangeListener() {
		        public void changed(ObservableValue observable, 
		                            Object oldValue, Object newValue) {
		            Double height = (Double)newValue;
		            bp1.setPrefHeight(height);
		        }
		    });
*/
		( ( Group )scene.getRoot() ).getChildren().addAll( bp1 ); 
		stage.setScene( scene );
		stage.show();
		
		mgc.start(rb1,rb2,rb3,rb4);
		mgc.getQuestion(l1,"\n");
		changeScoreLabel(scoreLabel);
		startTime(time,timeLabel);
		
	}
	
	
	public void incrementTime(int value){
		time+=value;
	}
	
	public void decrementTime(int value){
		time=time-value;
	}
	
	public void resetTime(int value){
		time=value;
	}
	public void incrementScore(int value){
		mgc.setScore(mgc.getScore() + mgc.getLevelNum());
		score = mgc.getScore();
		//.. score goes up by level number
		
		
		
		//scoreLabel.setText("Score: "+value);
	}
	
	public void decrementScore(int value){
		mgc.setScore(mgc.getScore() - decScoreValue);
		score = mgc.getScore();
		//scoreLabel.setText("Score: "+value);
		
	}
	
	public void resetScore(int value){
		mgc.setScore(0);
		score = mgc.getScore();
		//scoreLabel.setText("Score: "+value);
		
	}
	
	
	public void changeLabel(Label timeLabel){
		timeLabel.setText("Time: "+time);
		//System.out.println("changed");
		if(time == 0 || time < 0){
			timer.stop();
			timer = null; //deallocate and try to cancel thread.
			time = 0;
			endGame(timeLabel);
			time = mgc.getDefaultTime();
		}
		
	}
	
	public void changeScoreLabel(Label scoreLabel){
		//System.out.println("score is "+mgc.getScore());
		score = mgc.getScore();
		//System.out.println(score);
		scoreLabel.setText("Score: "+mgc.getScore());
		//System.out.println(scoreLabel.getText());
	}
	
	
	public void endGame(Label timeLabel){
		playWrongSound();
		timeLabel.setText("Time: "+time);
		mgc.saveData(currentUsername,maxTime);
		scores = new ArrayList<MGObject>();
		stage.close();
		tf2D = null;
		mgc = new MGControlUnit();
		mgc.setScore(0);
		scoreLabel = new Label("Score: "+mgc.getScore());
		levelHeader = "1-1";
		updateMode();
		mg.getHighestScore();
		skipNum = 5;
		maxTime = 0;
		start(stage);
		
	}
	
	
	
	public void incrementLevel(){
		if(mgc.getIntLevel() == 10){
			incrementTime(10);
			return;
		}
		if(mgc.getIntSec() < 9){
			mgc.setLevel(mgc.getIntLevel(),mgc.getIntSec()+1);
		}
		else{
			mgc.setLevel(mgc.getIntLevel()+1,0);
			incrementTime(45);
			skipNum += 5;
		}
	}
	//scoreLabel,levelLabel,modeLabel
	
	public void processAnswer(String ans,RadioButton btn,Label scoreLabel){
		timer.stop();
		String correct = mgc.getAnswer();
		if(ans.equals(correct)){
			changeStatus("Correct!");
			//incrementTime(incTimeValue);
			incrementScore(incScoreValue);
			incrementLevel();
			mgc.setMode();
			btn.setSelected(false);
			levelHeader = mgc.getLevel();
			modeHeader = mgc.getMode();
			makeGameUI();
		}
		else if(ans.equals("skip")){
			changeStatus("The correct answer was "+mgc.getAnswer());
			decrementTime(decTimeValue);
			//decrementScore(decScoreValue);
			mgc.setMode();
			btn.setSelected(false);
			levelHeader = mgc.getLevel();
			modeHeader = mgc.getMode();
			skipNum -= 1;
			makeGameUI();
		}
		else{
			changeStatus("The correct answer was "+mgc.getAnswer());
			decrementTime(decTimeValue);
			//decrementScore(decScoreValue);
			mgc.setMode();
			btn.setSelected(false);
			levelHeader = mgc.getLevel();
			modeHeader = mgc.getMode();
			makeGameUI();
			
			
			
		}
		changeScoreLabel(scoreLabel);
		timer.start();
		
	}
	
	public void changeStatus(String status){
		this.status.setText(status);
	}

	public void startTime(int time,Label timeLabel){
		timer = new AnimationTimerExt(1000) {

		    
		    public void handle() {
				decrementTime(1);
				changeLabel(timeLabel);
				maxTime += 1;
				//System.out.println(maxTime);
				//makeGameUI();
				//stage.show();

		    }
		};

		timer.start();
	}
	
	public void playRightSound(){
/*
		String musicFile = "correct.mp4"; 
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
 */
	}
	
	public void playWrongSound(){
/*
		String musicFile = "fail.mp4"; 
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		mediaPlayer.stop();
 */
	}
   
	public void clearArrayList(){
		for(int i = 0;i<scores.size();i++){
			scores.remove(i);
		}
	}
   
   
	
	
}



/*  	

	TODO:

	... FIX SCOREBOARD > 
	... Detect Level Changes > 
	... Add more Objects
	... add skip feature, gives new question but does not progress; lose time , but less than that of getting it wrong > 

	.... if wrong, 5 seconds. skip, 3. > 
	... text = red, buttons = white, background = black. 
*/



