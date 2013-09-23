package com.company;

import java.sql.*;  //import the whole sql class

public class DBConnect {

    private Connection conn;   //defines the database connection
    private Statement st;      //defines a statement
    private ResultSet rs;      //contains the statements' result

    public DBConnect()
    {
        try{
         Class.forName("com.mysql.jdbc.Driver");   //dynamically loads the class, in runtime
            //you have to add the Java connection class to the external libraries
            //Download the zip here: http://dev.mysql.com/downloads/connector/j/#downloads
            //In Intellij IDEA, click on the "Project" window, on the left, and press F4
            //In the Libaray Settings window, click on the "+" on the right, and then browse your
            //downloaded and unziped javac jar, and add it to the project

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_monopoly","root","root");
            //Connects to the local database in port 3306, with username/password
            st = conn.createStatement();

        }   catch(Exception exception) {
            System.out.println("Error: "+ exception.toString());
        }
    }

    //get some data from the database
    public void getData(){
        try
        {
            String query = "SELECT * FROM test";
            rs = st.executeQuery(query); //Executes the query
            System.out.println("Records from DB: ");
            while( rs.next() ){ //while the resultset has any elements
                //the ResultSet has some methods like "getBoolean", "getArray", and a lots of stuff... like:
                Integer id = rs.getInt("test_id");
                String name = rs.getString("test_name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        }catch (Exception ex){
            System.out.println("Error: "+ex.toString());
        }
    }
}
