package com.company;

import java.sql.*;
import java.util.Scanner;

public class db_Connection {
    private String url = "jdbc:mysql://127.0.0.1:3306/mydb?user=root&password=root";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public  db_Connection(){
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.executeQuery("SET SQL_SAFE_UPDATES=0");
            statement.executeUpdate("SET SQL_SAFE_UPDATES=0");
        } catch (SQLException exception) {
            System.out.println("connection failed!");
        }
    }


    //This is the method that will start the program after we create an object from this class and call it;
    public void startUp(){
        System.out.println("\nWarm welcome to you to our Planet Care app.");
        System.out.println("It is an application small in size but big in value.");
        System.out.println("our main goal is to reduce food waste by helping stores to sell their" +
                " about to expire products for a cheaper price.");
        System.out.println("This will also help you as a customer to buy your products for a reduced price" +
                " as well as saving the planet from unnecessary food wastage.");
        System.out.println("\nPlease, state who you are:" );
        System.out.println("\t1. Are you a staff member? (press 1) ");
        System.out.println("\t2. Are you an end consumer? (press 2) ");
        Scanner keyboard = new Scanner(System.in);
        int userChoice = keyboard.nextInt();
        if (userChoice ==1){
            Staff staff = new Staff();
            staff.verifyStaffInfo();
            staff.showStaffOptions();
        }
        if (userChoice == 2){
            Customer customer = new Customer();
            customer.showCustomerOptions();
        }
    }


    public void disconnect(){
        try {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                connection.close();
        } catch (SQLException exception) {
            System.out.println("Failed to disconnect!");
        }
    }
}