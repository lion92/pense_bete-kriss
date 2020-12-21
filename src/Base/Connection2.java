package Base;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connection2 {

	public static void main(String[] args) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Aricad2");
			
			Statement statement =con.createStatement();
			String sql="Select * from Utilisateur";
			
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) 
				
				
				System.out.println(rs.getString("Prenom")+"");
				
				con.close();
			
			
			
			
			
			
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		
		
		// TODO Auto-generated method stub

	}

}
