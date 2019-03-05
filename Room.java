public class Room {
	//Data 
	private String type; 
	private double price; 
	private boolean availability; 
	
	
	//Constructor
	//initializes the price and availability of the room based on the type
	public Room (String type){
		this.type=type;
			if(type.equals("double")){
				this.price=90.0; 
				this.availability=true; 
			}else{
				if(type.equals("queen")){
					this.price=110.0; 
					this.availability=true; 
				}else{
					if(type.equals("king")){
						this.price=150.0; 
						this.availability=true; 
					}else{
						throw new IllegalArgumentException("This type of room does not exist.");
					}
				}
			}
	}	
	
	//method to return the type 
	public String getType(){
		return this.type; 
	}
	
	//method to return the price
	public double getPrice(){
		return this.price;
	}
	
	//method to return availability 
	public boolean getAvailability(){
		return this.availability;
	}
	
	//method to change availability from true--> false or false-->true 
	public void changeAvailability(){
		if(this.availability){
			this.availability=false;
		}else{
			this.availability=true; 
		}
	}
	//loops through array of rooms. if available room is found of the input type return that room. if not return null  
	public static Room findAvailableRoom(Room[]rooms, String type){
		for(int i=0;i<rooms.length;i++){
			if(rooms[i].getType().equals(type)&& rooms[i].getAvailability()==true){
				return rooms[i];
			}
		}
		return null; 
	}	
}