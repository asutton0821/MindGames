class StatesCapitalsObject{
	
	String state, capital;
	
	StatesCapitalsObject(String state, String capital){
		this.state = state;
		this.capital = capital;
	}
	
	public String getCapital(){
		return capital;
	}
	
	public String getState(){
		return state;
	}
	
	
	public int compareTo(StatesCapitalsObject sco){
		if(this.getState().equals(sco.getState() ) || this.getCapital().equals(sco.getCapital())){
			return 0;
		}
		/*else if(this.getCapital().equals(sco.getCapital())){
			return 0;
		}
		else if(this.getState().equals(sco.getState())){
			return 0;
		}
		else{
			return this.getState().compareTo(sco.getState()) + this.getCapital().compareTo(sco.getCapital()); 
			
		}*/
			return this.getState().compareTo(sco.getState()) + this.getCapital().compareTo(sco.getCapital());
	}
	
	
	public boolean equals(StatesCapitalsObject sco){
		if(this.compareTo(sco) == 0){
			return true;
		}
		
			return false;
		
	}
	
}