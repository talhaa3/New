package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Staff extends db_Connection{

    public Staff(){
    }

    // overloaded method;
    public void updateProductPrice(String name, int newPrice) {
        try {
            statement.executeUpdate("UPDATE product SET product_sale_price =" + newPrice + "WHERE product_name = '" + name + "'") ;
        } catch (SQLException exception) {
            System.out.println("An error occurred while trying to update the price of this product!");
        }
    }

    //This method is to update a product quantity;
    public void updateProductQuantity(int id, int newQuantity){
        try{
            statement.executeUpdate ("UPDATE product SET product_quantity =" + newQuantity + "WHERE product_id = " + id);
        } catch (SQLException exception){
        }
    }

    // overloaded method;
    public void updateProductQuantity(String name, int quantity){
        try{
            statement.executeUpdate ("UPDATE product SET product_quantity =" + quantity + "WHERE product_name = '" + name + "'");
        } catch (SQLException exception){
            System.out.println("An error occurred while trying to update the quantity of this product!");
        }
    }
    //This method is to add a new product to the database;
    public void addNewProduct(int id, String name, int price, int quantity, char gluten_free, char vegan, int staff_id){
        try{
            statement.executeUpdate("INSERT INTO products VALUES('" + id + "','" + price + "','" + quantity + "','" + gluten_free + "','" + vegan + "','" + staff_id + "')");
        }catch (SQLException exception){
            System.out.println("An error occurred while trying to this item!");

        }
    }

    public void removeProduct(int id){
        try{
            statement.executeUpdate("DELETE FROM products WHERE product_id = " + id);
        }catch (SQLException exception){
            System.out.println("An error occurred while trying to this item!");
        }
    }

    //This method is check whether the name entered by the user exists in the database as staff member;
    public boolean checkStaffName(String name){
        boolean found = false;
        try {
            resultSet = statement.executeQuery("SELECT first_name FROM staff");
            while (resultSet.next() && !found){
                if (resultSet.getString("first_name").equalsIgnoreCase(name)){
                    found = true;
                }
            }
        } catch (SQLException exception){
        }
        return found;
    }

    //This method is to verify whether the password is correct;
    public boolean checkStaffPassword(String password){
        boolean correct = false;
        try {
            resultSet = statement.executeQuery("SELECT password FROM staff");
            while (resultSet.next() && !correct){
                if (resultSet.getString("password").equals(password)){
                    correct = true;
                }
            }
        } catch (SQLException exception){
        }
        return correct;
    }

    public void verifyStaffInfo(){

        System.out.print("Please, enter your name: ");
        Scanner keyboard = new Scanner(System.in);
        String staffInput = keyboard.nextLine();

        while (!(checkStaffName(staffInput))) {
            System.out.println("Wrong name! Try again!");
            System.out.print("Please, enter your name: ");
            staffInput = keyboard.nextLine();
        }
        System.out.println("\nWelcome " + staffInput);
        System.out.print("Please, be kind to enter your password: ");
        String staffPassword = keyboard.nextLine();

        while(!(checkStaffPassword(staffPassword))) {
            System.out.println("Wrong password! Try again!");
            System.out.print("Please, be kind to enter your password: ");
            staffPassword = keyboard.nextLine();
        }
    }
    public void showStaffOptionsMenu(){
        System.out.println("Choose one of the following options to modify your products database:");
        System.out.println("\t1- Update a product price");
        System.out.println("\t2- Update a product quantity");
        System.out.println("\t3- Add a new product to the database.");
        System.out.println("\t4- Remove a product from the database.");
        System.out.println("\t5- Exit!");
    }

    public void showStaffOptions(){
        showStaffOptionsMenu();
        Scanner keyboard = new Scanner(System.in);
        int staffInput = keyboard.nextInt();
        while(staffInput!=5){
            if (staffInput == 1){
                System.out.print("Please, enter the product name you would like to update its price: ");
                String productName = keyboard.nextLine();
                keyboard.nextLine();
                System.out.print("Please, enter the product new price: ");
                int newPrice = keyboard.nextInt();
                try {
                    updateProductPrice(productName, newPrice);
                } catch (Exception exception){
                    System.out.println("An error occurred while trying to update" + productName);
                }
                showStaffOptionsMenu();
                staffInput = keyboard.nextInt();
            }
            else if (staffInput == 2){
                System.out.print("Please, enter the product name you would like to update its quantity: ");
                String productName = keyboard.nextLine().trim();
                keyboard.nextLine();
                System.out.print("Please, enter the products new quantity: ");
                int newQuantity = keyboard.nextInt();
                updateProductQuantity(productName, newQuantity);
                showStaffOptionsMenu();
                staffInput = keyboard.nextInt();
            }
            else if (staffInput == 3){
                System.out.println("Enter your id number: ");
                int staff_id = keyboard.nextInt();
                System.out.println("Enter the product id to be registered: ");
                int product_id = keyboard.nextInt();
                keyboard.nextLine();
                System.out.print("Enter the product name: ");
                String name = keyboard.nextLine();
                System.out.print("Enter the product sale price: ");
                int price = keyboard.nextInt();
                System.out.print("Enter the product quantity: ");
                int quantity = keyboard.nextInt();
                System.out.print("Is the product gluten-free? (y or n): ");
                String verifyGluten = keyboard.nextLine();
                keyboard.nextLine();
                char gluten;
                if (verifyGluten.equalsIgnoreCase("y")){
                    gluten = 'T';
                } else {
                    gluten = 'F';
                }
                System.out.print("Is the product vegan? (y or n): ");
                String verifyVegan = keyboard.nextLine();
                char vegan;
                if (verifyVegan.equalsIgnoreCase("y")){
                    vegan = 'T';
                } else {
                    vegan = 'F';
                }
                addNewProduct(product_id, name, price, quantity,gluten, vegan, staff_id );
                showStaffOptionsMenu();
                staffInput = keyboard.nextInt();

            }
            else if (staffInput == 4){
                System.out.print("Enter the name of the product you would like to remove: ");
                String product_name  = keyboard.nextLine();
                keyboard.nextLine();
                System.out.print("Enter the id of the product you would like to remove: ");
                int product_id = keyboard.nextInt();
                removeProduct(product_id);
                System.out.println("\n");
                showStaffOptionsMenu();
                staffInput = keyboard.nextInt();
            }
        }
    }


}


