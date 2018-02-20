import java.text.DecimalFormat;
class MathObject{
	String equation, ans;
	double num1,num2;
	
	
	MathObject(String equation, String ans,double num1,double num2){
		this.equation = equation;
		this.ans = ans;
		this.num1 = num1;
		this.num2 = num2;
	}
	
	MathObject(String equation, String ans){
		this.equation = equation;
		this.ans = ans;
		//this.num1 = num1;
		//this.num2 = num2;
	}
	
	MathObject(String ans){
		this.ans = ans;
	}
	
	public String getEquation(){
		return equation;
		
	}
	
	public double getNum1(){
		return num1;
	}
	
	public double getNum2(){
		return num2;
	}
	
	public boolean isNum1LessThan(int n){
		return (num1 < (double) n) ? true : false;
	}
	public boolean isNum2LessThan(int n){
		return (num2 < (double) n) ? true : false;
	}
	
	public boolean isNum1LessThan(double n){
		return (num1 < n) ? true : false;
	}
	
	public boolean isNum2LessThan(double n){
		return (num2 < n) ? true : false;
	}
	
	public String getOperation(){
		if(this.equation.contains("+")){
			return "+";
		}
		else if(this.equation.contains("-")){
			return "-";
		}
		else if(this.equation.contains("/")){
			return "/";
		}
		else if(this.equation.contains("*")){
			return "*";
		}
		else{
			return "%";
		}
	}
	
	
	public boolean equals(MathObject mo){
		if(this.equation.equals(mo.getEquation()) && this.ans.equals(mo.getAns())){
			System.out.println("Math objects are equal");
			return true;
		}
		return false;
	}
	
	public String getAns(){
		return ans;
	}
	
	
	
}