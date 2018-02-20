class QuotesObject{
	private String quote = "";
	private String quoter = "";
	
	
	QuotesObject(String quote, String quoter){
		this.quote = quote;
		this.quoter = quoter;
	}
	
	public String getQuote(){
		return quote;
	}
	
	public String getQuoter(){
		return quoter;
	}
		
		public boolean equals(QuotesObject t){
			if(this.quote.equals(t.getQuote()) || this.quoter.equals(t.getQuoter())){
				return true;
			}
			return false;
		}
	
}