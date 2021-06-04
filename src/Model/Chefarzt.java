package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * OOP Concept
 * 
 * @author Omer
 *
 */
public class Chefarzt extends User {
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;
	
	/**
	 * 
	 * @param id
	 * @param tcno
	 * @param name
	 * @param password
	 * @param type
	 */
	public Chefarzt(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}

	public Chefarzt() {
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<User> getDoctorList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException query) {
			query.printStackTrace();
		}

		return list;
	}
	
	/**
	 * 
	 * @param tcno
	 * @param password
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public boolean addDoctor(String tcno, String password, String name) throws SQLException {

		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception update) {
			update.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteDoctor(int id) throws SQLException {

		String query = "DELETE FROM user WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			// preparedStatement.executeUpdate();
			key = true;
		} catch (Exception prepare) {
			prepare.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param id
	 * @param tcno
	 * @param password
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException {

		String query = "UPDATE user SET name = ?, tcno = ?, password = ?, WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception eU) {
			eU.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}
}
