import java.util.Random;
import java.util.ArrayList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Alert;
import java.io.BufferedWriter;
import java.text.DecimalFormat;




class MGControlUnit{
	
	int level = 1;
	int section = 1;
	int interval = 0; 
	String levelHeader = ""+level+"-"+section;
	int time = 120;
	int score = 0;
	String currentMode = "Math";
	String[] modes = {"States","Math","Quotes","Countries","Facts"};//,"Math","Riddles","Facts","Quotes"};
	ArrayList<String> list = new ArrayList<String>();
	MGFileParser mg = new MGFileParser();
	String correctButton = "";
	Timer timer = null;
	boolean save = true;
	String correctAnswer = "";
	String question = "";
	int defaultTime = 120;
	
	
	
	//Object Lists//
	
	ArrayList<StatesCapitalsObject> sc = null;
	ArrayList<MathObject> mo = null;
	ArrayList<QuotesObject> qo = null;
	ArrayList<CountryCapitalObject> cco = null;
	ArrayList<FactsObject> fo = null;
	
	///////////////
	
	MGControlUnit(String startingMode){
		initArrayLists();
		//Pick a mode to start on, or have one randomly assigned using the other constructor.
		for(int i = 0;i<modes.length;i++){
			if(startingMode.toLowerCase().equals(modes[i].toLowerCase())){
				currentMode = modes[i];
				list.add( modes[i]);
			}
		}
	}
	
	MGControlUnit(){
		initArrayLists();
		Random r = new Random();
		int t = r.nextInt(modes.length);
		while(t < 0){
			t+=1;
		}
		currentMode = modes[t];
		list.add( modes[t]);
	}
	
	public String getMode(){
		return currentMode;
	}
	
	public void setMode(String mode){
		for(int i = 0;i<modes.length;i++){
			if(mode.toLowerCase().equals(modes[i].toLowerCase())){
				currentMode = modes[i];
			}
		}
	}
	
	public void setMode(){
		Random y = new Random();
		int t = y.nextInt(modes.length);
		currentMode = modes[t];
	}
	public int getLevelNum(){
		return level;
	}
	
	public int getDefaultTime(){
		return defaultTime;
	}
	
	public void completeReset(){
		level = 1;
		section = 0;
		time = 120;
		score = 0;
	}
	
	public String getLevel(){
		levelHeader = ""+level+"-"+section;
		return levelHeader;
	}
	
	public int getTime(){
		return time;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int value){
		score=value;
	}
	
	public void setLevel(int level, int section){
		this.level = level;
		this.section = section;
	}
	
	public int getIntLevel(){
		return level;
	}
	
	public int getIntSec(){
		return section;
	}
	
	public void setTime(int value){
		time = value;
	}
	
	
	
	public void initArrayLists(){
		mo = mg.readMath("mathFile");
		sc = mg.readGeo("StatesCapitals");
		qo = mg.readQuotes("Quotes");
		cco = mg.readCountry("countryCaps");
		fo = mg.readFacts("facts");
	}
	
	
	public void processSCO(StatesCapitalsObject s){
		if(currentMode.equals("States")){
			question = "What is the capital of "+s.getState()+"?";
			correctAnswer = s.getCapital();
		}
	}
	
	public void processCountry(CountryCapitalObject c){
		if(currentMode.equals("Countries")){
			question = "What is the capital of "+c.getCountry()+"?";
			correctAnswer = c.getCapital();
		}
	}
	
	
	public void processMath(MathObject m){
		DecimalFormat df = new DecimalFormat("##.####");
		if(currentMode.equals("Math")){
			question = m.equation+"= ? ";
			correctAnswer = "" + df.format(Double.parseDouble(m.getAns()));
		}
	}
	
	public void processFacts(FactsObject m){
		if(currentMode.equals("Facts")){
			question = m.getFact();
			correctAnswer = m.getAns();
		}
	}
	
	
	public void processQuotes(QuotesObject m){
		
		if(currentMode.equals("Quotes")){
			question = m.getQuote();
			correctAnswer = m.getQuoter();
			
		}
	}
	
	public String getAnswer(){
		return correctAnswer;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public void generateAnswerButtons(RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4){
		Random r = new Random();
		int t = r.nextInt(3);
		//System.out.println(currentMode);
		
		if(currentMode.equals("States")){
			//System.out.println("Type is Geo");
			int e = r.nextInt(sc.size());
			StatesCapitalsObject s = sc.get(e);
			processSCO(s);
			if(t == 0){
				rb1.setText(s.getCapital());
				correctButton = "1";
			}
			else if(t == 1){
				rb2.setText(s.getCapital());
				correctButton = "2";
			}
			else if(t == 2){
				rb3.setText(s.getCapital());
				correctButton = "3";
			}
			else if(t == 3){
				rb4.setText(s.getCapital());
				correctButton = "4";
			}
			if(correctButton.equals("1")){
				rb2.setText(mg.getRandomSCO(s,sc).getCapital());
				rb3.setText(mg.getRandomSCO(s,sc).getCapital());
				rb4.setText(mg.getRandomSCO(s,sc).getCapital());
				
			}
			if(correctButton.equals("2")){
				rb1.setText(mg.getRandomSCO(s,sc).getCapital());
				rb3.setText(mg.getRandomSCO(s,sc).getCapital());
				rb4.setText(mg.getRandomSCO(s,sc).getCapital());
				
			}
			if(correctButton.equals("3")){
				rb1.setText(mg.getRandomSCO(s,sc).getCapital());
				rb2.setText(mg.getRandomSCO(s,sc).getCapital());
				rb4.setText(mg.getRandomSCO(s,sc).getCapital());
				
			}
			if(correctButton.equals("4")){
				rb1.setText(mg.getRandomSCO(s,sc).getCapital());
				rb2.setText(mg.getRandomSCO(s,sc).getCapital());
				rb3.setText(mg.getRandomSCO(s,sc).getCapital());
				
			}
		}
		else if(currentMode.equals("Countries")){
			//System.out.println("Type is Geo");
			int e = r.nextInt(cco.size());
			CountryCapitalObject s = cco.get(e);
			processCountry(s);
			if(t == 0){
				rb1.setText(s.getCapital());
				correctButton = "1";
			}
			else if(t == 1){
				rb2.setText(s.getCapital());
				correctButton = "2";
			}
			else if(t == 2){
				rb3.setText(s.getCapital());
				correctButton = "3";
			}
			else if(t == 3){
				rb4.setText(s.getCapital());
				correctButton = "4";
			}
			if(correctButton.equals("1")){
				rb2.setText(mg.getRandomCountry(s,cco).getCapital());
				rb3.setText(mg.getRandomCountry(s,cco).getCapital());
				rb4.setText(mg.getRandomCountry(s,cco).getCapital());
				
			}
			if(correctButton.equals("2")){
				rb1.setText(mg.getRandomCountry(s,cco).getCapital());
				rb3.setText(mg.getRandomCountry(s,cco).getCapital());
				rb4.setText(mg.getRandomCountry(s,cco).getCapital());
				
			}
			if(correctButton.equals("3")){
				rb1.setText(mg.getRandomCountry(s,cco).getCapital());
				rb2.setText(mg.getRandomCountry(s,cco).getCapital());
				rb4.setText(mg.getRandomCountry(s,cco).getCapital());
				
			}
			if(correctButton.equals("4")){
				rb1.setText(mg.getRandomCountry(s,cco).getCapital());
				rb2.setText(mg.getRandomCountry(s,cco).getCapital());
				rb3.setText(mg.getRandomCountry(s,cco).getCapital());
				
			}
		}
		else if(currentMode.equals("Facts")){
			//System.out.println("Type is Geo");
			int e = r.nextInt(fo.size());
			FactsObject s = fo.get(e);
			processFacts(s);
			if(t == 0){
				rb1.setText(s.getAns());
				correctButton = "1";
			}
			else if(t == 1){
				rb2.setText(s.getAns());
				correctButton = "2";
			}
			else if(t == 2){
				rb3.setText(s.getAns());
				correctButton = "3";
			}
			else if(t == 3){
				rb4.setText(s.getAns());
				correctButton = "4";
			}
			if(correctButton.equals("1")){
				rb2.setText(s.getWrong1());
				rb3.setText(s.getWrong2());
				rb4.setText(s.getWrong3());
				
			}
			if(correctButton.equals("2")){
				rb1.setText(s.getWrong1());
				rb3.setText(s.getWrong2());
				rb4.setText(s.getWrong3());
				
			}
			if(correctButton.equals("3")){
				rb1.setText(s.getWrong1());
				rb2.setText(s.getWrong2());
				rb4.setText(s.getWrong3());
				
			}
			if(correctButton.equals("4")){
				rb1.setText(s.getWrong1());
				rb2.setText(s.getWrong2());
				rb3.setText(s.getWrong3());
				
			}
		}
		else if(currentMode.equals("Math")){
			//System.out.println("TYPE IS MATH!");
		DecimalFormat df = new DecimalFormat("##.##");
			int e = r.nextInt(mo.size());
			
			MathObject m = getMathObject();
			ArrayList<String> mol = new ArrayList<String>();
			mol.add(df.format(Double.parseDouble(m.getAns())));
			processMath(m);
			if(t == 0){
				rb1.setText(df.format(Double.parseDouble(m.getAns())));
				correctButton = "1";
			}
			else if(t == 1){
				rb2.setText(df.format(Double.parseDouble(m.getAns())));
				correctButton = "2";
			}
			else if(t == 2){
				rb3.setText(df.format(Double.parseDouble(m.getAns())));
				correctButton = "3";
			}
			else if(t == 3){
				rb4.setText(df.format(Double.parseDouble(m.getAns())));
				correctButton = "4";
			}
			if(correctButton.equals("1")){
				rb2.setText(mg.getRandomMath(m,mo,mol));
				rb3.setText(mg.getRandomMath(m,mo,mol));
				rb4.setText(mg.getRandomMath(m,mo,mol));
				
			}
			if(correctButton.equals("2")){
				rb1.setText(mg.getRandomMath(m,mo,mol));
				rb3.setText(mg.getRandomMath(m,mo,mol));
				rb4.setText(mg.getRandomMath(m,mo,mol));
				
			}
			if(correctButton.equals("3")){
				rb1.setText(mg.getRandomMath(m,mo,mol));
				rb2.setText(mg.getRandomMath(m,mo,mol));
				rb4.setText(mg.getRandomMath(m,mo,mol));
				
			}
			if(correctButton.equals("4")){
				rb1.setText(mg.getRandomMath(m,mo,mol));
				rb2.setText(mg.getRandomMath(m,mo,mol));
				rb3.setText(mg.getRandomMath(m,mo,mol));
				
			}
		
			
			
			
		}
		
		
		else if(currentMode.equals("Quotes")){
			//System.out.println("Type is Quotes!");
			int e = r.nextInt(qo.size());
		
			QuotesObject quo = qo.get(e);
			//System.out.println(quo);
			//System.out.println("Got a quote object: "+quo+ " with equation: "+quo.getQuoter());
			processQuotes(quo);
			ArrayList<String> lo = new ArrayList<String>();
			lo.add(quo.getQuoter());
			if(t == 0){
				rb1.setText(quo.getQuoter());
				correctButton = "1";
			}
			else if(t == 1){
				rb2.setText(quo.getQuoter());
				correctButton = "2";
			}
			else if(t == 2){
				rb3.setText(quo.getQuoter());
				correctButton = "3";
			}
			else if(t == 3){
				rb4.setText(quo.getQuoter());
				correctButton = "4";
			}
			if(correctButton.equals("1")){
				rb2.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
				rb3.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
				rb4.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
			
			}
			if(correctButton.equals("2")){
				rb1.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
				rb3.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
				rb4.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
			
			}
			if(correctButton.equals("3")){
				rb1.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
				rb2.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
				rb4.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
			
			}
			if(correctButton.equals("4")){
				rb1.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
				rb2.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
				rb3.setText(mg.getRandomQuotes(quo,qo,lo).getQuoter());
			
			}
		}
			
	
		
		
	
		
		
	}
	
	public MathObject getMathObject(){
		//gets a math object based on level
		Random r = new Random();
		int t = r.nextInt(mo.size());
		if(level <= 1){
			while(mo.get(t).isNum1LessThan(99) == false || mo.get(t).isNum2LessThan(99) == false){
				//System.out.println("this is true");
				t = r.nextInt(mo.size());
				
			}
		}
		else if(level <= 3 && level > 1){
			while(!mo.get(t).isNum1LessThan(199) || !mo.get(t).isNum2LessThan(199)){
				t = r.nextInt(mo.size());
				
			}
		}
		else if(level <= 4 && level > 2){
			while(!mo.get(t).isNum1LessThan(999) || !mo.get(t).isNum2LessThan(999)){
				t = r.nextInt(mo.size());
				
			}
		}
		else if(level <= 9 && level > 6){
			while(!mo.get(t).isNum1LessThan(9999) || !mo.get(t).isNum2LessThan(9999)){
				t = r.nextInt(mo.size());
				
			}
		}
		else{
			while(!mo.get(t).isNum1LessThan(1000000) || !mo.get(t).isNum2LessThan(1000000)){
				t = r.nextInt(mo.size());
				
			}
		}
		return mo.get(t);
	}
	
	
	
	
	public void getQuestion(Label l,String reg){
		l.setText(reg+""+getQuestion());
	}
	
	public void start(RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4){
		timer();
		if(getMode().equals("States") || getMode().equals("Math") || getMode().equals("Quotes") || getMode().equals("Countries") || getMode().equals("Facts")){
			generateAnswerButtons(rb1,rb2,rb3,rb4);
		}
		
	}
	
	public void saveData(String username,int mtime){
		if(save == true){
			try {
				      File file = new File("levelFile.csv");
				      if (file.createNewFile()){
				       // System.out.println("File is created!");
						FileWriter writer = new FileWriter("levelFile.csv",true);
						            writer.write(username+","+levelHeader+","+score+","+time+"\n");
						            writer.close();
				      }else{
				       System.out.println("File already exists.");
						//file.delete();
						FileWriter writer = new FileWriter("levelFile.csv",true);
						
						            writer.write(username+","+levelHeader+","+score+","+mtime+"\n");
						            writer.close();
									
				      }

			    	} catch (IOException e) {
				      e.printStackTrace();
				}
			}
		}
		
		/*
		public void saveData(String username){
			try(FileWriter fw = new FileWriter(username+".csv", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(username+","+levelHeader+","+score+","+time);
			} catch (IOException e) {
				e.printStackTrace();
			    //exception handling left as an exercise for the reader
			}
		} 
		*/
		
		
		
		
		public void timer(){
		    int delay = 3000;
		    int period = 3000;
		    timer = new Timer();
		    interval = time;
		    //System.out.println(time);
		    timer.scheduleAtFixedRate(new TimerTask() {

		        public void run() {
		            //System.out.println(setInterval());
					time = setInterval();
					//System.out.println(time);

		        }
		    }, delay, period);
		}
		
		
		public  int setInterval() {
		    if (interval == 1)
		        timer.cancel();
		    return --interval;
		}
		
		
	
		
	
	
	
	
	
}