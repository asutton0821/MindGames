class CountryCapitalObject{
	
	String country, capital;
	
	CountryCapitalObject(String country, String capital){
		this.country = country;
		this.capital = capital;
	}
	
	public String getCapital(){
		return capital;
	}
	
	public String getCountry(){
		return country;
	}
	
	
	public int compareTo(CountryCapitalObject sco){
		if(this.getCountry().equals(sco.getCountry() ) || this.getCapital().equals(sco.getCapital())){
			return 0;
		}
		/*else if(this.getCapital().equals(sco.getCapital())){
			return 0;
		}
		else if(this.getCountry().equals(sco.getCountry())){
			return 0;
		}
		else{
			return this.getCountry().compareTo(sco.getCountry()) + this.getCapital().compareTo(sco.getCapital()); 
			
		}*/
			return this.getCountry().compareTo(sco.getCountry()) + this.getCapital().compareTo(sco.getCapital());
		
	}
	
	
	public boolean equals(CountryCapitalObject sco){
		if(this.compareTo(sco) == 0){
			return true;
		}
		
			return false;
		
	}
	
}