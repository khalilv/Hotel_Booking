import java.util.NoSuchElementException;

public class Hotel {
	//Data
	private String name; 
	private Room[] rooms; 
	private Reservation[] reservations; 
	
	//Constructor
	//initializes attributes and copies input array of rooms to the array of rooms for this instance(hotel)
	public Hotel(String name, Room[]hotelRooms){
		this.name=name;
		this.rooms= new Room[hotelRooms.length];
		for(int i=0;i<this.rooms.length;i++){
			this.rooms[i]= hotelRooms[i];
		}
	}
	
	//creates new array of size 1+ the previous size, copies all reservations from this.reservations and adds the input reservation
	private void addReservation(Reservation inputReservation){
		Reservation[]addingReservation;
		if(this.reservations==null){ //if this.reservations is empty, create array of size 1 
			addingReservation= new Reservation[1];
		}else{
			addingReservation= new Reservation[1+this.reservations.length];
		}
		for(int i=0;i<addingReservation.length-1;i++){
			addingReservation[i]=this.reservations[i];
			}
		addingReservation[addingReservation.length-1]=inputReservation; 
		this.reservations = addingReservation; //point this.reservations to the same address in memory of the created array of 1+ size
	}
	
	//creates a new array of size -1 this.reservations
	//loops through this.reservations to see if there is a reservation under the input name and input type
	//if yes it will remember that spot and start populating the new array with this.reservations but will skip the input reservation 
	//if no it will throw an exception that will be caught in a following method
	private void removeReservation(String name, String type){
		boolean foundRoom=false; 
		int placeHolder = 0; 
		Reservation [] removingReservation = new Reservation[this.reservations.length-1];
		for(int i=0;i<this.reservations.length;i++){
			if(this.reservations[i].getName().equals(name)&& this.reservations[i].getRoom().getType().equals(type)){
				foundRoom = true; 
				placeHolder = i; 
			}
		}
		if(foundRoom==false){
			throw new NoSuchElementException();
		}
		if(foundRoom==true){
			for(int i=0;i<removingReservation.length;i++){
				if(i<placeHolder){
					removingReservation[i]=this.reservations[i];
				}if(i>=placeHolder){
					removingReservation[i]=this.reservations[1+i];
				}
			}
			this.reservations=removingReservation; 
		}
	}
	//checks if there is an available room of the given type
	//if yes will add reservation with the given attributes to the reservation array(using addReservation). Will also change availability of that room to false 
	
	public void createReservation(String name, String type){
		Room availableRoom = Room.findAvailableRoom(rooms, type);
		if(availableRoom==null){
			System.out.println("This hotel has no available rooms of the requested type");
		}else{
			Reservation newReservation = new Reservation(availableRoom,name);
			addReservation(newReservation);
			newReservation.getRoom().changeAvailability();
			System.out.println("The Reservation was succesfully completed under the name "+name);
		}
	}
	//tries to cancel reservation using removeReservation 
	//if it can it will change availability of that room to true
	//if it can't find a reservation, it will let the user know 
	public void cancelReservation(String name, String type){
		try{
			removeReservation(name,type);
			for(int i=0;i<this.rooms.length;i++){
				if(this.rooms[i].getAvailability()==false && this.rooms[i].getType().equals(type)){
					this.rooms[i].changeAvailability();
					break;
				}
			}
			System.out.println(name+", your reservation for a "+type+" room has succesfully been cancelled.");
		}catch(NoSuchElementException e){
			System.out.println("No such reservation for a "+type+" room under the name of "+name);
		}
	}
	
	//will loop through reservations under input name, get the price of those rooms and add it to the invoice 
	public void printInvoice(String name){
		double price=0.0; 
		for(int i=0;i<this.reservations.length;i++){
			if(this.reservations[i].getName().equals(name)){
				price +=this.reservations[i].getRoom().getPrice();
			}
		}
		if(price==0){
			System.out.println("No Reservations have been made at this time");
		}else{
			System.out.println("The invoice under the name "+name+" is: $"+ price);
		}
	}
	
	//a method to print the hotel info
	//will print hotel name 
	//will loop through rooms and if they are available will increase that count by 1. then will return the count of all the rooms separately 
	public String toString(){
		String info="";
		info+= "Hotel name: " + this.name + "\n";
		int doubleRoom=0;
		int king=0;
		int queen=0; 
		for(int i=0;i<rooms.length;i++){
			if(rooms[i].getType().equals("double")&&rooms[i].getAvailability()==true){
				doubleRoom +=1; 
			}if(rooms[i].getType().equals("king")&&rooms[i].getAvailability()==true){
				king+=1;
			}if(rooms[i].getType().equals("queen")&&rooms[i].getAvailability()==true){
				queen+=1; 
			}
		}
		info+="There are "+doubleRoom+" double rooms left" + "\n";
		info+="There are "+king+" king rooms left" + "\n";
		info+="There are "+queen+" queen rooms left";
		return info; 
	}
}