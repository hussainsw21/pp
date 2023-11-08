package Atm_sourc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Customer{
    Scanner sc =new Scanner(System.in);
    private int PIN;
    String Name,AtmNo;
    int Balance=0;
    String Type,Phone,PAN_no;
    
    public void Show_Balance(Connection con) throws SQLException{
        String q="select PIN from Atm where AtmNo=?";
        PreparedStatement ps=con.prepareStatement(q);
        System.out.print("Enter your Atm Number : ");
        ps.setString(1,sc.next());
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            PIN=rs.getInt("PIN");
        }
        int chk1=1,count=0;
        while(chk1!=0){
            System.out.print("Enter your PIN : ");
            if(PIN==sc.nextInt()){
                chk1=0;
            }
            else if(count==3){
                System.exit(1);
            }
            else{
                count++;
                System.out.println("Wrong PIN \n attempt left "+(3-count));
            }
        }
        
        String q2="select * from Atm where AtmNo=?";
        PreparedStatement ps1=con.prepareStatement(q2);
        ResultSet rs1=ps1.executeQuery();
        while(rs1.next()){
            AtmNo=rs1.getString("AtmNo");
            Name=rs1.getString("Name");
            System.out.println("Name : "+Name);
            Balance=rs1.getInt("Balance");
            System.out.println("Balance : Rs. "+Balance);
        }
    }

    public void deposit(Connection con) throws SQLException{
        int Ammount;
        System.out.print("Enter your Account Number : ");
        AtmNo=sc.next();
        String q3="select PIN,Balance from Atm where AtmNo=?";
        PreparedStatement ps3=con.prepareStatement(q3);
        ps3.setString(1,AtmNo);
        ResultSet rs3=ps3.executeQuery();
        while(rs3.next()){
            PIN=rs3.getInt("PIN");
            Balance=rs3.getInt("Balance");
        }
        int chk1=1,count=0;
        while(chk1!=0){
            System.out.print("Enter your PIN : ");
            if(PIN==sc.nextInt()){
                chk1=0;
            }
            else if(count==3){
                System.exit(1);
            }
            else{
                count++;
                System.out.println("Wrong PIN \n attempt left "+(3-count));
            }
        }
        System.out.print("Enter your Deposit Ammount : ");
        Ammount=sc.nextInt();
        Balance+=Ammount;
        String q4="update Atm set Balance=? where AtmNo=?";
        PreparedStatement ps4=con.prepareStatement(q4);
        ps4.setInt(1, Balance);
        ps4.setString(2,AtmNo);
        ps4.executeUpdate();
        System.out.print("Do you want to see your Balance[Y/N] :");
        char chk3=sc.next().charAt(0);
        if(chk3=='Y'||chk3=='y'){
            System.out.println("your Current Balance : "+Balance);
        }
    }

    public void withdraw(Connection con) throws SQLException{
        int Ammount;
        System.out.print("Enter your Account Number : ");
        AtmNo=sc.next();
        String q3="select PIN,Balance from Atm where AtmNo=?";
        PreparedStatement ps3=con.prepareStatement(q3);
        ps3.setString(1,AtmNo);
        ResultSet rs3=ps3.executeQuery();
        while(rs3.next()){
            PIN=rs3.getInt("PIN");
            Balance=rs3.getInt("Balance");
        }
        int chk1=1,count=0;
        while(chk1!=0&&count<=3){
            System.out.print("Enter your PIN : ");
            if(PIN==sc.nextInt()){
                chk1=0;
            }
             else if(count==3){
                System.exit(1);
            }
            else{
                count++;
                System.out.println("Wrong PIN \n attempt left "+(3-count));
            }
        }
        String q4="update Atm set Balance=? where AtmNo=?";
        PreparedStatement ps4=con.prepareStatement(q4);
        ps4.setInt(1, Balance);
        ps4.setString(2,AtmNo);
        ps4.executeUpdate();
        System.out.print("Enter your withdraw Ammount : ");
        Ammount=sc.nextInt();
        Balance-=Ammount;
        String q5="update Atm set Balance=? where AtmNo=?";
        PreparedStatement ps5=con.prepareStatement(q5);
        ps5.setInt(1, Balance);
        ps5.setString(2,AtmNo);
        ps5.executeUpdate();
        System.out.print("Do you want to see your Balance[Y/N] :");
        char chk3=sc.next().charAt(0);
        if(chk3=='Y'||chk3=='y'){
            System.out.println("your Current Balance : "+Balance);
        }
    }
   
    public void Change_Pin(Connection con) throws SQLException{
        int NewPin;
        String q="select PIN from Atm where AtmNo=?";
        PreparedStatement ps=con.prepareStatement(q);
        System.out.print("enter your ATM number :");
        AtmNo=sc.next();
        ps.setString(1, AtmNo);
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            PIN=rs.getInt("PIN");
        }
        int chk=1,count=0;
        System.out.print("enter PIN : ");
        while(chk!=0){
            if(PIN==sc.nextInt()){
                chk=0;
            }
            else if(count==3){
                System.exit(1);
            }
            else{
                count++;
                System.out.println("Wrong PIN \nleft attempt "+(3-count));
            }
        }
        System.out.println("enter New PIN ");
        NewPin=sc.nextInt();
        String q1="update Atm set PIN=? where AtmNo=?";
        PreparedStatement ps1=con.prepareStatement(q1);
        ps1.setInt(1,NewPin);
        ps1.setString(2,AtmNo);
        ps1.executeUpdate();
    }
}

