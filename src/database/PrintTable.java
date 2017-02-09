package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PrintTable {
	
	public static void print(Connection connect, String tableName) {
    	System.out.println("TABLE: " + tableName);
    	for(int i = 0; i < tableName.length() + 10; i++) {
    		System.out.print("-");
    	}
    	System.out.println();
    	
    	Statement statem;
    	StringBuilder output = new StringBuilder();
		try {
			statem = connect.createStatement();
			ResultSet rs = statem.executeQuery("select * from " + tableName + ";");
			
			String[][] colInfo = getColumnInfo(connect, tableName);
			int columnCount = colInfo.length;
			
			while(rs.next()) {
				for(int column = 1; column <= columnCount; column++) {
					System.out.print(rs.getString(column));
//					output.append( rs.getString(i) );
					if(column < columnCount) {
						System.out.print("     \t");
//						output.append(" ");
					}
				}
				System.out.println();
//				output.append("\n");
			}
			
		} catch (SQLException e) {
			System.err.println("There was a problem printing the table");
//			e.printStackTrace();
		}
    	
		System.out.println(output);
	}
	
    private static String[][] getColumnInfo(Connection connection, String tableName) {
//	    System.out.println("GettingColumn info");
    	ArrayList<String> names = new ArrayList<String>();
	    ArrayList<String> types = new ArrayList<String>();
	    try{
            Statement stat = connection.createStatement();
	        ResultSet rs = stat.executeQuery("PRAGMA table_info(" + tableName + ");");
	    
	        while (rs.next()) {
	            names.add(rs.getString("name"));
	            types.add(rs.getString("type"));
            }
	        rs.close();
	    }catch(SQLException sqlException) {
	        System.err.println("Could NOT get column names for " + tableName);
	    }
	  
	    String[][] result = new String[names.size()][2];
	    for(int i = 0; i < names.size(); i++) {
		    result[i][0] = names.get(i);
		    result[i][1] = types.get(i);
	    }
//	    System.out.println("done GettingColumn info");
	    return result;
    }
	

}
