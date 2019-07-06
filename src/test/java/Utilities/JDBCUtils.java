package Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {

        private static Connection connection;

        private static Statement statement;

        private static ResultSet resultSet;


        public static void establishConnection() throws SQLException {

            connection= DriverManager.getConnection(Configuration.getProperty("dbUrl"),
                    Configuration.getProperty("dbUsername"),Configuration.getProperty("dbPassword"));

        }

        public static List<Map<String,Object>> runSQLQuery(String query) throws SQLException{

            statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            resultSet=statement.executeQuery(query);

            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            int columnCount = resultSetMetaData.getColumnCount();

            List<Map<String,Object>> dbData=new ArrayList<>();

            while(resultSet.next()){
                Map<String,Object> rowMap=new HashMap<>();
                for(int i=1;i<=columnCount; i++){
                    rowMap.put(resultSetMetaData.getColumnName(i),
                            resultSet.getObject(resultSetMetaData.getColumnName(i)));
                }
                dbData.add(rowMap);
            }
            return dbData;
        }

        public static void closeConnections() throws SQLException{
            if(connection!=null){
                connection.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }

        }

        public static int rowCount(String query) throws SQLException{

            statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            resultSet=statement.executeQuery(query);
            ResultSet resultSet1=resultSet;

            resultSet1.last();

            int numberOfRow= resultSet1.getRow();

            return numberOfRow;
        }

    }


