package Lab22;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FINALP1 {
    // Q1: Implement the relationship between the classes (Check the UML)تم

    // Q2: Go to the class ((Lab)) and override the method toStringتم
    /*----------------------------------------------------------------------
    Q3: define here 4 ArrayLists: 
    1- ArrayList to save all Room objects and name it ((room))
    2- ArrayList to save all Event objects and name it ((event)) 
    3- ArrayList to save all Employee objects and name it ((employee))
    4- ArrayList to save all Booking objects and name it ((booking))
    تم
------------------------------------------------------------------------*/
    //Solution:
    static ArrayList <Room> room = new ArrayList <Room> ();
    static ArrayList <Event> event = new ArrayList <Event> ();
    static ArrayList <Employee> employee = new ArrayList <Employee> ();
    static ArrayList <Booking> booking = new ArrayList <Booking> ();
    //===================================================================== 
    public static void main(String[] args) throws FileNotFoundException {
        File F1 = new File("INPUT.txt");
        File F2 = new File("OUTPUT.txt");
        if (!F1.exists()) {
            System.out.println("Input file " + F1.getName() + " does not exist");
            System.exit(0);
        }
        Scanner input = new Scanner(F1);
        PrintWriter output = new PrintWriter(F2);
        output.println("\nWelcome to the Room Booking System!\n");
        output.println("***********************************************");

        String command;

        do {
            command = input.next();
            if (command.equalsIgnoreCase("ADD_ROOM")) {
                AddRoom(input, output);// // Reading data from the file
            } else if (command.equalsIgnoreCase("ADD_EVENT")) {
                AddEvent(input, output);// // Reading data from the file
            } else if (command.equalsIgnoreCase("ADD_Employee")) {
                AddEmployee(input, output);// // Reading data from the file
            } else if (command.equalsIgnoreCase("PRINT_Employees_Info")) {
                PrintEmployeesInfo(output);
            } else if (command.equalsIgnoreCase("BOOKING")) {
                Booking(input, output);// // Reading data from the file
            }
        } while (!command.equalsIgnoreCase("Quit"));

        output.println("\nThank you for using the Room Booking System, Good Bye!");
        output.flush(); // immediately save in the file
        output.close(); // closing file write object
        input.close();// closing file read object
    }//end of the main method

    public static void AddRoom(Scanner input, PrintWriter output) {
        /*----------------------------------------------------------------------
             Q4: Implement ADD_ROOM Command here. This command is used to add a new room object to the system, 
             the Room could be ClassRoom or Lab.
             Hint: use polymorphic referencing 
            ------------------------------------------------------------------------*/
        //Solution:
         String Type = input.next();
         int RoomNo = input.nextInt();
         int Floor = input.nextInt();
         int Capacity = input.nextInt();
         String Status = input.next();
         
         
         if(Type.equals("ClassRoom")){ 
             String projector= input.next();
             room.add(new ClassRoom( RoomNo, Floor, Capacity, Status, projector));                
         }
         else {
             String opreatingSysrs= input.next();
             room.add(new Lab( RoomNo, Floor, Capacity, Status, opreatingSysrs));
       }
            output.println( Type + " " +  RoomNo + " was added successfully");
        //==============================================================================
    }//end of AddRoom method

    public static void AddEvent(Scanner input, PrintWriter output) {
        String eventName = input.next();
        event.add(new Event(eventName, input.next(), new Date(Long.parseLong(input.next())),
                Double.parseDouble(input.next().replace(":", ".")),
                Double.parseDouble(input.next().replace(":", ".")),
                input.nextInt()));
        output.println("Event " + eventName + " was added successfully");

    }
    //================================================================================   

    public static void AddEmployee(Scanner input, PrintWriter output) {
        int Emp_id = input.nextInt();
        employee.add(new Employee(Emp_id, input.next(), input.next(), input.nextInt(), input.nextInt(), input.next(), input.next()));
        output.println("Employee with ID " + Emp_id + " was added successfully");
    }
    //==================================================================================  

    public static void PrintEmployeesInfo(PrintWriter output) {
        /*----------------------------------------------------------------------
		Q5: Implement PRINT_Employees_Info here. This command is 
		used to print the information of all added Employees 
------------------------------------------------------------------------*/
        output.println("----------------------Employees Info--------------------");
        for(int i = 0 ; i < employee.size(); i++ ){
            output.println(employee.get(i).toString());
        }
        output.println("---------------------------------------------------------");
                
                
        //==================================================================================
    }//end of PrintEmployeesInfo method

    public static void Booking(Scanner input, PrintWriter output) {
        /*----------------------------------------------------------------------
        Q6: Implement BOOKING Command here. This command is used 
        to add a new booking object to the system.
   ----------------------------------------------------------------------------------*/
        //Solution
           int Room_Num = input.nextInt();
           String Event_Name = input.next();
           int Emp_ID = input.nextInt();
           
           int indexRoomNum = searchRoomIndex(Room_Num);
           int indexEventName = searchEventIndex(Event_Name);
           int indexEmpID =  searchEmployeeIndex(Emp_ID);
           
           if(indexEmpID == -1){
               output.println("Unsuccessfull Booking");
               output.println("Incoreect Employee ID");
               output.println("----------------------------------------");
           }
           else if(indexEventName == -1){
               output.println("Unsuccessfull Booking");
               output.println("The Event is not found the System");
               output.println("----------------------------------------");
           }
            else if(room.get(indexRoomNum).getStatus().equals("Reserved")){
              output.println("Unsuccessfull Booking");
               output.println("The Room is Reserved ");
               output.println("----------------------------------------");  
           }
            else if(room.get(indexRoomNum).Capacity < event.get(indexEventName).AttendeesNo){
                 output.println("Unsuccessfull Booking");
               output.println("The Room capacity is not enough");
               output.println("----------------------------------------");
            }
            else{
                
                //Booking(int BNo,Room room,Event event,Employee modarator)
                int BNo = 1+ (int)(Math.random()*100);
                Room CRoom = room.get(indexRoomNum);
                Event CEvent = event.get(indexEventName);
                Employee CEmployee = employee.get(indexEmpID);

                booking.add(new Booking (BNo,CRoom,CEvent,CEmployee));
                output.println("Booking with Number (" + BNo + ") was added successfully");
                output.println("----------------------------------------");

            }
           
           
        //==================================================================================  
    } //end of Booking method

    //This method search about room number and returns its index, returns -1 if not found
    public static int searchRoomIndex(int Room_Num) {
        int index = -1;
        for (int i = 0; i < room.size(); i++) {
            if (room.get(i).getRoomNo() == Room_Num) {
                index = i;
            }
        }
        return index;
    }
    //This method search about Event name and returns its index, returns -1 if not found

    public static int searchEventIndex(String Event_Name) {
        int index = -1;
        for (int i = 0; i < event.size(); i++) {
            if (event.get(i).getEventName().equalsIgnoreCase(Event_Name)) {
                index = i;
            }
        }
        return index;
    }
    //This method search about Employee ID and returns its index, returns -1 if not found   

    public static int searchEmployeeIndex(int Emp_ID) {
        int index = -1;
        for (int i = 0; i < employee.size(); i++) {
            if (employee.get(i).getEmpID() == Emp_ID) {
                index = i;
            }
        }
        return index;
    }

}//end of class

