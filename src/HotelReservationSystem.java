import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.lang.ClassNotFoundException;
public class HotelReservationSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "vishwa";
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(URL,username,password);
            System.out.println("Connected to Database");
            Statement statement = connection.createStatement();

            while(true){
                System.out.println("HOTEL RESERVATION SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Create Reservation");
                System.out.println("2. View Reservations");
                System.out.println("3. Update Reservation(s)");
                System.out.println("4. Delete Reservation(s)");
                System.out.println("5. Get Room Number");
                System.out.println("0. EXIT");
                System.out.println("Choose an Option : ");
                int choice = sc.nextInt();
                switch(choice){
                    case 1:
                        reserveRoom(statement,sc);
                        break;
                    case 2:
                        viewReservations(statement);
                        break;
                    case 3:
                        updateReservation(statement,sc);
                        break;
                    case 4:
                        deleteReservation(statement,sc);
                        break;
                    case 5:
                        getRoomNumber(statement,sc);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    private static void reserveRoom(Statement statement, Scanner sc){
        System.out.println("Enter Customer Name : ");
        sc.nextLine();
        String name =  sc.nextLine();
        System.out.println("Enter Room Number : ");
        int roomNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Contact Number : ");
        String contactNumber = sc.nextLine();
        String query = "INSERT INTO reservations(guest_name,room_number,contact)" + "VALUES ('" + name +"'," + roomNumber +",'" + contactNumber + "')";

        try{
            int rowsAffected = statement.executeUpdate(query);
            if(rowsAffected > 0){
                System.out.println("Reservation Created Successfully.");
            }else{
                System.out.println("Reservation Failed.");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void viewReservations(Statement statement){
        String query = "SELECT * FROM reservations";
        try{
            ResultSet resultTable = statement.executeQuery(query);
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");


            while(resultTable.next()){
                int reservationId = resultTable.getInt("reservation_id");
                String guestName = resultTable.getString("guest_name");
                int roomNumber = resultTable.getInt("room_number");
                String contactNumber = resultTable.getString("contact");
                String reservationDate = resultTable.getString("reservation_date");
                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                        reservationId, guestName, roomNumber, contactNumber, reservationDate);
            }
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void updateReservation(Statement statement,Scanner sc){
        try{
            System.out.println("Enter reservation ID to update : ");
            int reservationId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter new guest name : ");
            String guestName = sc.nextLine();
            //sc.nextLine();
            System.out.println("Enter new room number : ");
            int roomNumber = sc.nextInt();
            System.out.println("Enter new contact number : ");
            String contactNumber = sc.next();


            if(!reservationExists(statement,reservationId)){
                System.out.println("Reservation does not exist for the given reservation ID in the database.");
                return;
            }

            String query = "UPDATE reservations SET guest_name=' "+ guestName+" ',"+"room_number="+roomNumber+","+"contact= '"+contactNumber+"' WHERE reservation_id="+reservationId;
            int rowsAffected = statement.executeUpdate(query);
            if(rowsAffected > 0){
                System.out.println("Reservation Updated Successfully.");
            }else{
                System.out.println("Reservation Failed.");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void deleteReservation(Statement statement,Scanner sc){
        try{
            System.out.println("Enter reservation ID to delete : ");
            int reservationId = sc.nextInt();
            if(!reservationExists(statement,reservationId)){
                System.out.println("Reservation does not exist for the given reservation ID in the database.");
                return;
            }
            String query = "DELETE FROM reservations WHERE reservation_id="+reservationId;
            int rowsAffected = statement.executeUpdate(query);
            if(rowsAffected > 0){
                System.out.println("Reservation Deleted Successfully.");
            }else{
                System.out.println("Reservation Failed.");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void getRoomNumber(Statement statement,Scanner sc){
        try{
            System.out.println("Enter reservation ID to get room number : ");
            int reservationID = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter name : ");
            String name = sc.nextLine();

            String query = "SELECT room_number FROM reservations WHERE reservation_id="+reservationID+" AND guest_name='"+name+"'";

            ResultSet resultTable = statement.executeQuery(query);
            if(resultTable.next()){
                System.out.println("Room Number : "+ resultTable.getInt("room_number"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void exit(){
        try{
            System.out.print("Exiting");
            int count = 5;
            while(count > 0){
                System.out.print(".");
                Thread.sleep(500);
                count--;
            }
            System.out.println();
            System.out.println("Thank You for using the Hotel Reservation System!!!.");
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    private static boolean reservationExists(Statement statement,int reservationId){
        String query = "SELECT * FROM reservations WHERE reservation_id = "+reservationId;
        try{
            ResultSet resultTable = statement.executeQuery(query);
            return resultTable.next();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

}