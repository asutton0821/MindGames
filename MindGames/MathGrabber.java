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
import java.util.Random;
import java.lang.Math;
import java.text.DecimalFormat;


class MathGrabber{
	
	static int x = 1;
	static Random r = new Random();
	static String op = "";
	static String equation = "";
	private static String ans = "";
	static int num1=0,num2=0;
	static final DecimalFormat df = new DecimalFormat("##.##");
	
	public static void passOp(int t, int g){
		double y = (double) t;
		double z = (double) g;
		int q = r.nextInt(5);
		if(q == 0){
			op="+";
			equation = df.format(y)+""+op+""+df.format(z);
			ans = df.format(y+z);
		}
		if(q == 1){
			op="-";
			equation = df.format(y)+""+op+""+df.format(z);
			ans = df.format(y-z);
		}
		if(q == 2){
			op = "*";
			equation = df.format(y)+""+op+""+df.format(z);
			ans = df.format(y*z);
		}
		if(q == 3){
			op = "/";
			equation = df.format(y)+""+op+""+df.format(z);
			ans = df.format(y/z);
		}
		if(q == 4 && (y > 1000 && z > 1000)){
			System.out.println("q is 4");
			op="%";
			equation = df.format(y)+""+op+""+df.format(z);
			ans = df.format(y%z);
		}
		saveData("mathFile");
		
	}
	

	public static void saveData(String fileName){
		
			try {
				      File file = new File(fileName+".csv");
				      if (file.createNewFile()){
				       // System.out.println("File is created!");
						FileWriter writer = new FileWriter(fileName+".csv",true);
						            writer.write(equation+","+ans+","+num1+","+num2+"\n");
									System.out.println("Wrote: "+equation+ " with answer: "+ans );
						            writer.close();
				      }else{
				       //System.out.println("File already exists.");
						//file.delete();
						FileWriter writer = new FileWriter(fileName+".csv",true);
						
						            writer.write(equation+","+ans+","+num1+","+num2+"\n");
									System.out.println("Wrote: "+equation+ " with answer: "+ans );
						            writer.close();
									
				      }

			    	} catch (IOException e) {
				      e.printStackTrace();
				}
			
		}
		
		public static void delete(String fileName){
	      File file = new File(fileName+".csv");
	      file.delete();
			
		}

	public static void generateMathEquation(){
			num1 = r.nextInt(x);
			num2 = r.nextInt(x);
			passOp(num1, num2);
	}
	
	
	public static void main(String[] args){
		delete("mathFile");
		int total = Integer.parseInt(args[0]);
		while(x <= total){
			generateMathEquation();
			x+=1;
		}
	}
}
