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
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Collections;

class MGFileParser{
	
	ArrayList<MGObject> MGList;
	
	MGFileParser(){
		MGList = new ArrayList<MGObject>();
	}
	
	MGFileParser(String fileName){
		MGList = new ArrayList<MGObject>();
		readFile(fileName);
	}
	
	
	public void clearList(){
		MGList = new ArrayList<MGObject>();
	}
	
	
	
	public void readFile(String fileName){
		
	        try (BufferedReader br = new BufferedReader(new FileReader(fileName+".csv"))) {
				String splitBy = ",";
				String line = "";
	            while ((line = br.readLine()) != null) {
	                String[] fileArray = line.split(splitBy);
					MGObject mg = new MGObject();
					for(int i=0;i<fileArray.length;i++){
						mg.insert(fileArray[i]);
					}
					MGList.add(mg);
	            }
				
	        } catch (IOException e) {
	           e.printStackTrace();
	        }
		getHighestScore();
		}
		
		
		public ArrayList<MathObject> readMath(String fileName){
			ArrayList<MathObject> mo = new ArrayList<MathObject>();
		
		        try (BufferedReader br = new BufferedReader(new FileReader(fileName+".csv"))) {
					String splitBy = ",";
					String line = "";
		            while ((line = br.readLine()) != null) {
		                String[] fileArray = line.split(splitBy);
						MathObject mg = null;
						for(int i=0;i<fileArray.length;i++){
							mg = new MathObject(fileArray[0],fileArray[1],Double.parseDouble(fileArray[2]),Double.parseDouble(fileArray[3]));
						}
						mo.add(mg);
		            }
				
		        } catch (IOException e) {
		           e.printStackTrace();
		        }
				
				
				return mo;
		
			}
		
		
		
	
			@SuppressWarnings("unchecked")
			public void getHighestScore(){
				Collections.sort(MGList);
			}
			
		public ArrayList<MGObject> getList(){
			return MGList;
		}
		
		
		public ArrayList<StatesCapitalsObject> readGeo(String fileName){
			ArrayList<StatesCapitalsObject> sc = new ArrayList<StatesCapitalsObject>();
		
		        try (BufferedReader br = new BufferedReader(new FileReader(fileName+".csv"))) {
					String splitBy = ",";
					String line = "";
		            while ((line = br.readLine()) != null) {
		                String[] fileArray = line.split(splitBy);
						StatesCapitalsObject sc0 = null;
						for(int i=0;i<fileArray.length;i++){
							sc0 = new StatesCapitalsObject(fileArray[0],fileArray[1]);
						}
						sc.add(sc0);
		            }
				
		        } catch (IOException e) {
		           e.printStackTrace();
		        }
				
				return sc;
		
			}
			
			
			public ArrayList<CountryCapitalObject> readCountry(String fileName){
				ArrayList<CountryCapitalObject> sc = new ArrayList<CountryCapitalObject>();
		
			        try (BufferedReader br = new BufferedReader(new FileReader(fileName+".csv"))) {
						String splitBy = ",";
						String line = "";
			            while ((line = br.readLine()) != null) {
			                String[] fileArray = line.split(splitBy);
							//System.out.println(Arrays.toString(fileArray));
							CountryCapitalObject sc0 = null;
							for(int i=0;i<fileArray.length;i++){
								sc0 = new CountryCapitalObject(fileArray[0],fileArray[1]);
							}
							sc.add(sc0);
			            }
				
			        } catch (IOException e) {
			           e.printStackTrace();
			        }
				
					return sc;
		
				}
				
				
				public ArrayList<FactsObject> readFacts(String fileName){
					ArrayList<FactsObject> sc = new ArrayList<FactsObject>();
		
				        try (BufferedReader br = new BufferedReader(new FileReader(fileName+".csv"))) {
							String splitBy = ",";
							String line = "";
				            while ((line = br.readLine()) != null) {
				                String[] fileArray = line.split(splitBy);
								//System.out.println(Arrays.toString(fileArray));
								FactsObject sc0 = null;
								//System.out.println(Arrays.toString(fileArray));
								for(int i=0;i<fileArray.length;i++){
									sc0 = new FactsObject(fileArray[0],fileArray[1],fileArray[2],fileArray[3],fileArray[4]);
									//System.out.println(sc0.getFact());
								}
								sc.add(sc0);
				            }
				
				        } catch (IOException e) {
				           e.printStackTrace();
				        }
						//System.out.println(sc.get(0).getAns());
						return sc;
		
					}
			
			
			public ArrayList<QuotesObject> readQuotes(String fileName){
				ArrayList<QuotesObject> qo = new ArrayList<QuotesObject>();
		
			        try (BufferedReader br = new BufferedReader(new FileReader(fileName+".csv"))) {
						String splitBy = ",";
						String line = "";
			            while ((line = br.readLine()) != null) {
			                String[] fileArray = line.split(splitBy);
							QuotesObject sc0 = null;
							for(int i=0;i<fileArray.length;i++){
								sc0 = new QuotesObject(fileArray[0],fileArray[1]);
								//System.out.println("read: "+sc0.getQuote());
							}
							qo.add(sc0);
			            }
				
			        } catch (IOException e) {
			           e.printStackTrace();
			        }
				
					return qo;
		
				}
		
		
		
		
			public StatesCapitalsObject getRandomSCO(StatesCapitalsObject sco, ArrayList<StatesCapitalsObject> list){
				Random r = new Random();
				int t = r.nextInt(list.size() - 1);
				while(list.get(t).equals(sco)){
					System.out.println("found equal states");
					t = r.nextInt(list.size() - 1);
				}
				
					return list.get(t);
				
			}
			
			
			public CountryCapitalObject getRandomCountry(CountryCapitalObject sco, ArrayList<CountryCapitalObject> list){
				Random r = new Random();
				int t = r.nextInt(list.size() - 1);
				while(list.get(t).equals(sco)){
					System.out.println("found equal countries");
					t = r.nextInt(list.size() - 1);
				}
				
					return list.get(t);
				
			}
			
			public FactsObject getRandomFact(FactsObject sco, ArrayList<FactsObject> list){
				Random r = new Random();
				int t = r.nextInt(list.size() - 1);
				while(list.get(t).equals(sco)){
					System.out.println("found equal facts");
					t = r.nextInt(list.size() - 1);
				}
				
					return list.get(t);
				
			}
			
			public QuotesObject getRandomQuotes(QuotesObject sco, ArrayList<QuotesObject> list, ArrayList<String> sqo){
				Random r = new Random();
				int t = r.nextInt(list.size() - 1);
				for(int i=0;i<sqo.size();i++){
					if(sqo.get(i).equals(list.get(t).getQuoter()) || list.get(t).getQuoter().equals(sco.getQuoter())){
						System.out.println(list.get(t).getQuoter());
						System.out.println("found equal quotes");
						t = r.nextInt(list.size());
					}
				}
			//	while(list.get(t).getQuoter().equals(sco.getQuoter())){
				//	System.out.println("equal quotes!! Don't display");
				//	t+=1;
				//}
				sqo.add(list.get(t).getQuoter());
					return list.get(t);
				
			}
			
			
			
		
			public String getRandomMath(MathObject mo, ArrayList<MathObject> list,ArrayList<String> sco){
				//System.out.println("List is "+sco);
				Random r = new Random();
				if(Double.parseDouble(mo.getAns()) > 10.0){
				int t = r.nextInt(5);
				for(int i =0; i<sco.size();i++){
					//System.out.println("Greater than 10... ");
					//System.out.println(sco.get(i) + " " + t);
					while(t == 0){
						t = r.nextInt(10);
					}
					while(Double.parseDouble(mo.getAns()) - t == Double.parseDouble(sco.get(i))){
						//System.out.println("true");
						t = r.nextInt(10);
					}
				}
				DecimalFormat df = new DecimalFormat("##.##");
				//System.out.println("" + df.format(Double.parseDouble(mo.getAns()) - t));
				sco.add("" + df.format(Double.parseDouble(mo.getAns()) - t));
				return df.format(Double.parseDouble(mo.getAns()) - t);
				
			}
			else if(Double.parseDouble(mo.getAns()) > 0.0 && Double.parseDouble(mo.getAns()) < 10.0 ){
				//System.out.println("number is greater than 0 and less than 1!");
				double t = r.nextDouble();
				//System.out.println("Random generate double: "+t);
				for(int i =0; i<sco.size();i++){
					while(t == 0){
						t = r.nextDouble();
					}
					while(Double.parseDouble(mo.getAns()) - t== Double.parseDouble(sco.get(i))){
						//System.out.println("true");
						t = r.nextDouble();
						//System.out.println("New decimal is: "+t);
					}
				}
				
				
				int y = r.nextInt(2);
				if(y == 0){
				
				DecimalFormat df = new DecimalFormat("##.##");
				//System.out.println("" + df.format(Double.parseDouble(mo.getAns()) - t));
				sco.add("" + df.format(Double.parseDouble(mo.getAns()) - t));
				return df.format(Double.parseDouble(mo.getAns()) - t);
				}
				else{
					DecimalFormat df = new DecimalFormat("##.##");
					//System.out.println("" + df.format(Double.parseDouble(mo.getAns()) + t));
					sco.add("" + df.format(Double.parseDouble(mo.getAns()) + t));
					return df.format(Double.parseDouble(mo.getAns()) + t);
				}
			}
			else if( Double.parseDouble(mo.getAns()) == 0.0){
				//System.out.println("number is greater than 0 and less than 1!");
				double t = r.nextDouble();
				//System.out.println("Random generate double: "+t);
				for(int i =0; i<sco.size();i++){
					while(t == 0){
						t = r.nextDouble() * 0.01;
					}
					while(Double.parseDouble(mo.getAns()) - t== Double.parseDouble(sco.get(i))){
						//System.out.println("true");
						t = r.nextDouble() * 0.01;
						//System.out.println("New decimal is: "+t);
					}
				}
				
				
				int y = r.nextInt(2);
				if(y == 0){
				
				DecimalFormat df = new DecimalFormat("##.##");
				//System.out.println("" + df.format(Double.parseDouble(mo.getAns()) - t));
				sco.add("" + df.format(Double.parseDouble(mo.getAns()) - t));
				return df.format(Double.parseDouble(mo.getAns()) - t);
				}
				else{
					DecimalFormat df = new DecimalFormat("##.##");
					//System.out.println("" + df.format(Double.parseDouble(mo.getAns()) + t));
					sco.add("" + df.format(Double.parseDouble(mo.getAns()) + t));
					return df.format(Double.parseDouble(mo.getAns()) + t);
				}
			
			}
			
				int t = r.nextInt(5);
				for(int i =0; i<sco.size();i++){
					while(t == 0){
						t = r.nextInt(10);
					}
					while(Double.parseDouble(mo.getAns()) - t == Double.parseDouble(sco.get(i))){
						//System.out.println("true");
						t = r.nextInt(10);
					}
				}
				DecimalFormat df = new DecimalFormat("##.##");
				//System.out.println("" + df.format(Double.parseDouble(mo.getAns()) - t));
				sco.add("" + df.format(Double.parseDouble(mo.getAns()) - t));
				return df.format(Double.parseDouble(mo.getAns()) - t);
			
				
			}
	
			
	
		
				
	
	
	
}