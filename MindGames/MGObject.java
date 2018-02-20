import java.util.ArrayList;

class MGObject implements Comparable{
	
	
	
	private ArrayList<String> list = new ArrayList<String>();
	
	
	MGObject(String value){
		list.add(value);
	}
	
	MGObject(){
		//default
	}
	
	public ArrayList<String> getList(){
		return list;
	}
	
	public String getObject(int index){
		return list.get(index);
	}
	
	public void insert(String value){
		list.add(value);
	}
	
	public int getSize(){
		return list.size();
	}
	
	@Override
	public int compareTo(Object s){
		MGObject t = (MGObject) s;
		if(Integer.parseInt(this.getObject(2)) > Integer.parseInt(t.getObject(2))){
			return -1;
		}
		else if(Integer.parseInt(this.getObject(2)) < Integer.parseInt(t.getObject(2))){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	
	
	
	
}
