package Helper;
import java.sql.*;

/**
 * Datenbankverbindung herstellen
 * @author Omer
 *
 */
public class DBConnection {
	Connection c = null;
	
	public DBConnection() {}
	
	/**
	 * @return
	 */
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital?user=root&passwort=hr");
		} catch (SQLException maria) {
			maria.printStackTrace();
		}
		
		return c;
	}
}
