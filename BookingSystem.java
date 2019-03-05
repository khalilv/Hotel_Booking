import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class BookingSystem{
    
    private static String[] typeOfRooms = {"double","queen","king"};
    private static Random r = new Random(123);
    
    //returns a random String from the above array. 
    private static String getRandomType(){
        int index = r.nextInt(typeOfRooms.length);
        return typeOfRooms[index];
    }
    //returns a random number of rooms between 5 and 50.
    private static int getRandomNumberOfRooms(){
        return r.nextInt(50)+1;
    }
   
    
    public static void main(String[] args){
        
    	System.out.println("Welcome to Khalil's hotel booking system.");
    	System.out.print("Please enter the name of the hotel that you would like to book: ");
    	Scanner input1 = new Scanner(System.in);
    	String hotelName = input1.nextLine(); 
    	Room [] createRoomArray = new Room[getRandomNumberOfRooms()];
    	for(int i=0;i<createRoomArray.length;i++){
    		createRoomArray[i]= new Room(getRandomType());
    	}
    	Hotel newHotel = new Hotel(hotelName,createRoomArray); //create new hotel with the input name and random size/type methods given above
    	for(;;){
    		try{
    			System.out.println();
        		System.out.println("*********************");
        		System.out.println("Welcome to the "+hotelName+". Please choose one of the following options:");
            	System.out.println("1) Make a reservation");
            	System.out.println("2) Cancel a reservation");
            	System.out.println("3) See an invoice");
            	System.out.println("4) See hotel info");
            	System.out.println("5) Exit the Booking System");
            	System.out.println("*********************"); //print out hotel options
            	Scanner input2=new Scanner(System.in);
            	int chosenOption=input2.nextInt();
            	if(chosenOption ==1){ 
            		//will ask for user name and type of room then call createReservation with those inputs
            		System.out.print("Please enter your name: ");
            		Scanner input3=new Scanner(System.in);
            		String personName=input3.nextLine();
            		System.out.print("What type of room would you like to reserve? ");
            		Scanner input4=new Scanner(System.in);
            		String roomType = input4.next(); 
            		newHotel.createReservation(personName, roomType);
            	}
            	if(chosenOption ==2){
            			//will ask user for name and type of room then call cancelReservation with those inputs
            			System.out.print("Please enter the name you used to make the reservation: ");
                		Scanner input5 = new Scanner(System.in);
                		String personName = input5.nextLine();
                		System.out.print("What type of room did you reserve? ");
                		Scanner input6 = new Scanner(System.in); 
                		String roomType = input6.next(); 
                		newHotel.cancelReservation(personName, roomType);
             	}
            	if(chosenOption ==3){
            		//will ask for users name then print his/her invoice using printInvoice
            		System.out.print("Please enter your name: ");
            		Scanner input7= new Scanner (System.in);
            		String personName = input7.nextLine();
            		newHotel.printInvoice(personName);
            	}
            	if(chosenOption ==4){
            		//will print hotel info without any user input
            		System.out.println("Here is the hotel info");
            		String info= newHotel.toString();
            		System.out.println(info);
            	}
            	if(chosenOption ==5){
            		//will print a message then exit the loop 
            		System.out.println("Exiting hotel booking system. It was a pleasure doing business with you!");
            		break; 
            	}
            	//next lines of code are to catch exceptions that might be caused by the user
            	if(chosenOption>5||chosenOption<1){
            		System.out.println("Not a valid option. Returning to main screen");
            	}
            }catch(InputMismatchException e){
            	System.out.println("Wrong format for input. Returning to main screen");
            }catch(NullPointerException e){
            	System.out.println("No reservation has been made under such name. Returning to main screen.");
            }
        }    	
    }
    	
}

