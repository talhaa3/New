package com.company;

import java.sql.*;
import java.util.Scanner;

public class Customer extends db_Connection {

    public Customer(){
    }

    //This method is to show all vegan products;
    public void searchForVegan(){
        try {
            resultSet = statement.executeQuery("SELECT product_name, product_sale_price, product_quantity FROM product WHERE vegan = 'T'");
            System.out.println("--How cool it is that you are a VEGAN!--");
            System.out.println("\tHere are the items we have just for you:\n");
            while (resultSet.next()){
                System.out.print("\n\t" + resultSet.getString("product_name") + " is available for: ");
                System.out.print(resultSet.getInt(2) + "kr");
                System.out.print(" (only " + resultSet.getInt(3) + " items are left)");
                System.out.print("");

                System.out.println("");
            }
        }catch (SQLException exception){
        }
    }


    //This method is to show only gluten-free products;
    public void searchForGlutenFree(){
        try {
            resultSet = statement.executeQuery("SELECT product_name, product_sale_price, product_quantity FROM product WHERE gluten_free = 'T'");
            System.out.println("\nOh, sorry to hear that you have intolerance to gluten!");
            System.out.println("but DO NOT WORRY. We have products just for you:\n");
            while (resultSet.next()){
                System.out.print("\t-->" + resultSet.getString("product_name") + " is available for: ");
                System.out.print(resultSet.getInt(2) + "kr");
                System.out.print("(only " + resultSet.getInt(3) + " items are left)");
                System.out.println("");
            }
        }catch (SQLException exception){
        }
    }


    //This method is to show all products;
    public void searchForAll() {
        try {
            resultSet = statement.executeQuery("SELECT product_name, product_sale_price, product_quantity FROM product");
            while (resultSet.next()) {
                System.out.print("\t-->" + resultSet.getString("product_name") + " is available for: ");
                System.out.print(resultSet.getInt(2) + "kr");
                System.out.print("--> (only " + resultSet.getInt(3) + " items are left)");
                System.out.println("");
            }
        } catch (SQLException exception) {
        }
    }

    //This method is to update a product price;
    public void updateProductPrice(int id, int newPrice) {
        try {
            statement.executeUpdate("UPDATE product SET product_sale_price =" + newPrice + "WHERE product_id = " + id);
        } catch (SQLException exception) {
        }
    }

    public void showCustomerOptions(){
        int userChoice;
        do {
            System.out.println("\n\nSelect one of the following options to view the available products" +
                    " according to your needs: ");
            System.out.println("\t1- Show me all the products on sale.");
            System.out.println("\t2- I am vegetarian. Show me only vegetarian product on sale.");
            System.out.println("\t3- I have Gluten-intolerance. Please, show me only gluten-free products.");
            System.out.println("\t4- I am a student on budget. Show me only products whose price dont exceed 100 kr.");
            System.out.println("\t5- Exit.");
            System.out.println("...............................................................");
            Scanner keyboard = new Scanner(System.in);
            userChoice = keyboard.nextInt();
            if (userChoice == 1){
                searchForAll();
            }
            else if (userChoice == 2){
                searchForVegan();
            }
            else if (userChoice == 3){
                searchForGlutenFree();
            }
        } while (userChoice != 5);
        disconnect();
        System.exit(0);
    }


}


