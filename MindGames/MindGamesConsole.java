import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.Writer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

class MindGamesConsole{
	
	
	int levelNum = 0;
	int sectionNum = 0;
	String username = "";
	String levelHeader = "";
	String currentMode = "";
// >>>>	All attributes must be private.
	
	
	
	//For classes 
	//StatesCapitalsObject[] sco = new StatesCapitalsObject[50];
	ArrayList<StatesCapitalsObject> sco = new ArrayList<StatesCapitalsObject>();
	ArrayList<MathObject> mathList = new ArrayList<MathObject>();
	
	//
	
	MindGamesConsole(String command){
		if(command.equals("play")){
			startgame();
			readLevelFile();
		}
	}
	
	
	public void startgame(){
		readSCOFile("StatesCapitals");
		readMathFile();
		generateHeader();
		System.out.println("Level: "+levelHeader);
		pickMode();
		System.out.println("Mode: "+getMode());
		play();
		
		
		
	}
	
	public void pickMode(){
		Random r = new Random();
		int x = r.nextInt(3);
		//int x = 1;
		if(x == 2){
			currentMode = "States and Capitals";
		}
		if(x == 1){
			currentMode = "Math";
		}
		if(x == 0){
			pickMode();
		}
	}
	
	public void playAgain(){
		System.out.print("Play again? (y or n):");
		Scanner sc = new Scanner(System.in);
		String answer = sc.nextLine();
		if(answer.equals("y")){
			saveData();
			startgame();
		}
		else if(answer.equals("n")){
			System.out.println("You finished at "+levelHeader);
			saveData();
		}
	}
	
	
	public String getMode(){
		return currentMode;
	}
	
	public void play(){
		if(currentMode.equals("States and Capitals")){
			Random ro = new Random();
			int r = ro.nextInt(sco.size() - 1);
			StatesCapitalsObject c = sco.get(r);
			System.out.println("The capital of "+c.getState()+ " is? ");
			Scanner sc = new Scanner(System.in);
			String answer = sc.nextLine();
			if(answer.equals(c.getCapital())){
				System.out.println("Correct!");
				incrementLevel();
			}
			else{
				System.out.println("Incorrect! Answer is "+c.getCapital());
				playAgain();
				
			}
		}
		else if(currentMode.equals("Math")){
			Random ro = new Random();
			int t = ro.nextInt(mathList.size() - 1);
			MathObject y = mathList.get(t);
			while(!y.isNum1LessThan((levelNum + 1) * 20) && !y.isNum2LessThan((levelNum + 1) * 20)){
				int u = ro.nextInt(mathList.size() - 1);
				y = mathList.get(u);
			}
			System.out.println("What is the answer to "+y.getEquation());
			Scanner sc = new Scanner(System.in);
			String answer = sc.nextLine();
			if(answer.equals(y.getAns())){
				System.out.println("Correct!");
				incrementLevel();
			}
			else{
				System.out.println("Incorrect! Answer is "+y.getAns());
				playAgain();
			}	
		}
		
	}
	
	public void incrementLevel(){
		if(sectionNum == 9){
			levelNum+=1;
			sectionNum = 0;
		}
		else{
			sectionNum++;
		}
		startgame();
	}
	
	public void readSCOFile(String fileName){
        String csvFile = fileName+".csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] statesFile = line.split(cvsSplitBy);
				StatesCapitalsObject stateCap = new StatesCapitalsObject(statesFile[0],statesFile[1]);
               // System.out.println("State: "+stateCap.getState()+" Capital: "+stateCap.getCapital());
				sco.add(stateCap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	public void readLevelFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("levelFile.csv"))) {
			String splitBy = ",";
			String line = "";
            while ((line = br.readLine()) != null) {
                String[] levelFile = line.split(splitBy);
				levelNum = Integer.parseInt(levelFile[0]);
				sectionNum = Integer.parseInt(levelFile[1]);
				username = levelFile[2];
            }

        } catch (IOException e) {
           // e.printStackTrace();
			getUserName();
        }
		
	}
	
	
	
	public void readMathFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("MathFile.csv"))) {
			String splitBy = ",";
			String line = "";
            while ((line = br.readLine()) != null) {
                String[] math = line.split(splitBy);
				//System.out.println(Arrays.toString(math));
				//System.out.println(math[0]+","+math[1]+","+math[2]+""+math[3]);
				MathObject mo = new MathObject(math[0],math[1],Double.parseDouble(math[2]),Double.parseDouble(math[3]));
				mathList.add(mo);
              //  System.out.println("Level: "+levelNum+". Section: "+sectionNum+" Username: "+username);
            }

        } catch (IOException e) {
           // e.printStackTrace();
			getUserName();
        }
		
	}
	
	
	
	public void getUserName(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your username: ");
		username = sc.nextLine();
	}
	
	public void setLevel(int i){
		levelNum = i;
	}
	
	public void setSection(int i){
		sectionNum = i;
	}
	
	public int getLevel(){
		return levelNum;
	}
	
	public int getSection(){
		return sectionNum;
	}
	
	
	public void saveData(){
			try {
				      File file = new File("levelFile.csv");
				      if (file.createNewFile()){
				       // System.out.println("File is created!");
						FileWriter writer = new FileWriter("levelFile.csv", true);
						            writer.write(levelNum+","+sectionNum+","+username);
						            writer.close();
				      }else{
				       // System.out.println("File already exists.");
						file.delete();
						FileWriter writer = new FileWriter("levelFile.csv", true);
						            writer.write(levelNum+","+sectionNum+","+username);
						            writer.close();
									
				      }

			    	} catch (IOException e) {
				      e.printStackTrace();
				}
		}
		
	
		public void generateHeader(){
			levelHeader = ""+levelNum+"-"+sectionNum;
		}
	
	
	
	
	
}



