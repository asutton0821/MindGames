class Main{
	
	
	public static void main(String[] args){
		MindGamesConsole o = new MindGamesConsole("play");
		o.saveData();

	}
}

/*
>>>>	Code compiles but with warnings, most of which you can eliminate.

$ javac -g -Xlint:all *va
MGObject.java:3: warning: [rawtypes] found raw type: Comparable
class MGObject implements Comparable{
                          ^
  missing type arguments for generic class Comparable<T>
  where T is a type-variable:
    T extends Object declared in interface Comparable
MindGamesGUI.java:190: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		gp.setFillWidth(label,true);
		  ^
MindGamesGUI.java:191: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		gp.setMargin(b1,new Insets(10,0,0,0));
		  ^
MindGamesGUI.java:200: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		gp.setHalignment(label,HPos.CENTER);
		  ^
MindGamesGUI.java:201: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		gp.setHalignment(b1,HPos.CENTER);
		  ^
MindGamesGUI.java:202: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		gp.setHalignment(nameButton,HPos.CENTER);
		  ^
MindGamesGUI.java:203: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		gp.setHalignment(scoreLabel,HPos.CENTER);
		  ^
MindGamesGUI.java:394: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		top.setHalignment(modeLabel,HPos.CENTER);
		   ^
MindGamesGUI.java:395: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		top.setHalignment(levelLabel,HPos.CENTER);
		   ^
MindGamesGUI.java:396: warning: [static] static method should be qualified by type name, GridPane, instead of by an expression
		top.setHalignment(l1,HPos.CENTER);
		   ^
10 warnings

	I got a run time error.

Exception in thread "JavaFX Application Thread" MediaException: UNKNOWN : com.sun.media.jfxmedia.MediaException: Could not create player! : com.sun.media.jfxmedia.MediaException: Could not create player!
	at javafx.scene.media.MediaException.exceptionToMediaException(MediaException.java:146)
	at javafx.scene.media.MediaPlayer.init(MediaPlayer.java:511)
	at javafx.scene.media.MediaPlayer.<init>(MediaPlayer.java:414)
	at MindGamesGUI.playWrongSound(MindGamesGUI.java:607)
	at MindGamesGUI.endGame(MindGamesGUI.java:496)
	at MindGamesGUI.changeLabel(MindGamesGUI.java:480)
	at MindGamesGUI$2.handle(MindGamesGUI.java:585)

	You did not send me the sound files.

	Commenting out the playSound() methods removes the error.

	The program requires control C to exit.

	Lookbacks are missing.

	Comments are completely missing.
	All attributes must be private. The default is public.

	Several methods are much too long. Each should be broken into logical
	parts using a shorter method. A rule of thumb is that maintainable
	code has methods of length at most 60 lines.
	generateAnswerButteons in MGControlUnit is 258 lines.
	makeGameUI in MindGanesGUI is 143 lines.

	You wrote abotu 2400 lines of code which indicates a complex project.

	The use of non private attributes and lack of comments are serious
	errors in my course. I really should not grade code without comments.
	I certainly would not attempt to maintain code without comments.

	Grade: C+
	11/15
 */
