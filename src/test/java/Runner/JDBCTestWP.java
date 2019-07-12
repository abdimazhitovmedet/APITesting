package Runner;

import Utilities.MySQLJDBC;

import java.sql.SQLException;

public class JDBCTestWP {
    public static void main(String[] args) throws SQLException {

        MySQLJDBC.establishConnection();
        String salaryFirtsYmployee= MySQLJDBC.runSQLQuery("Select post_title from wp_posts").toString();
        System.out.println(salaryFirtsYmployee);


//        String employeeFirstName= MySQLJDBC.runSQLQuery("select first_name from employees").get(0).get("FIRST_NAME").toString();
//        System.out.println(employeeFirstName);
//
//        System.out.println(MySQLJDBC.rowCount("select * from employees"));
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//        //print all names from employees table
//
//        List<Map<String,Object>> tableWithFirstNames= MySQLJDBC.runSQLQuery("select * from employees");
//
//        for(int i=0; i<tableWithFirstNames.size(); i++){
//            System.out.print(tableWithFirstNames.get(i).get("FIRST_NAME"));
//            System.out.println("  "+ tableWithFirstNames.get(i).get("LAST_NAME"));
//
//        }


        MySQLJDBC.closeConnections();

    }

}
