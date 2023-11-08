import java.sql.*;
import java.util.Scanner;
import Atm_sourc.Customer;

public class Atm {
    

public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/atm";
        String user="root";
        String  pass="hsw123";
        Connection con = DriverManager.getConnection(url, user, pass);
        Customer c=new Customer();
        int choice=0;
        while(choice!=5){
        System.out.println("Welcome to HS-ATM");
        System.out.print("1.View Balance\n2.Withdraw\n3.Deposit\n4.Change Pin\n");
        System.out.print("Enter Your Choice: ");
        choice=sc.nextInt();
        switch(choice){
            case 1:
                c.Show_Balance(con);
                break;
            case 2:
                c.withdraw(con);
                break;
            case 3:
                c.deposit(con);
                break;
            case 4:
                c.Change_Pin(con);
                break;
            default:
                break;                    
        }
    }
        sc.close();
    }
}

