class FactsObject{
	
	String fact, ans,wrong1,wrong2,wrong3;
	
	FactsObject(String fact, String ans,String wrong1,String wrong2, String wrong3){
		this.fact = fact;
		this.ans = ans;
		this.wrong1 = wrong1;
		this.wrong2 = wrong2;
		this.wrong3 = wrong3;
	}
	
	public String getFact(){
		return fact;
	}
	
	public String getAns(){
		return ans;
	}
	
	public String getWrong1(){
		return wrong1;
	}
	
	public String getWrong2(){
		return wrong2;
	}
	
	public String getWrong3(){
		return wrong3;
	}
	
	public int compareTo(FactsObject sco){
		if(this.getAns().equals(sco.getAns() ) && this.getFact().equals(sco.getFact())){
			return 0;
		}
		return -1;
	}
	
	
	public boolean equals(FactsObject sco){
		if(this.compareTo(sco) == 0){
			return true;
		}
		
			return false;
		
	}
	
}