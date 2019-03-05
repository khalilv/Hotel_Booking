public class Reservation {
	//Data
	private String name; 
	private Room roomReserved; 
	
	//Constructor
	//initializes reservation object with the input parameters  
	public Reservation(Room roomReserved, String name){
		this.roomReserved=roomReserved; 
		this.name=name;
	}
	
	//return name 
	public String getName(){
		return this.name;
	}
	
	//return the reserved room 
	public Room getRoom(){
		return this.roomReserved; 
	}

}
