package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;
import Helper.*;
import Model.Chefarzt;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception loginPage) {
					loginPage.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Krankenhaus Automation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		/**
		 * Ein Logo(jpg) wurde hinzugefügt
		 */
		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("medizin.jpg")));
		lblNewLabel.setText("");
		lblNewLabel.setBounds(62, 10, 371, 100);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 155, 476, 207);
		w_pane.add(w_tabpane);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		w_tabpane.addTab("Patient/in Login", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ihre T.C. Nummer  :");
		lblNewLabel_1.setBounds(10, 10, 153, 25);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));

		JLabel lblNewLabel_1_1 = new JLabel("Ihre Password   :");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(10, 58, 153, 25);
		panel.add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.setBounds(166, 10, 295, 25);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(166, 58, 295, 25);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Anmelden");
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnNewButton.setBounds(10, 109, 221, 48);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnNewButton_1.setBounds(241, 109, 220, 48);
		panel.add(btnNewButton_1);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Arzt/in Login", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(0, 0, 471, 180);
		w_doctorLogin.add(panel_1);

		JLabel lblNewLabel_1_2 = new JLabel("Ihre T.C. Nummer :");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblNewLabel_1_2.setBounds(10, 10, 153, 25);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Ihre Password   :");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(10, 58, 153, 25);
		panel_1.add(lblNewLabel_1_1_1);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(160, 9, 270, 33);
		panel_1.add(fld_doctorTc);

		JButton btn_doctorLogin = new JButton("Login");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					//JOptionPane.showMessageDialog(null, "Bitte füllen Sie alle Felder aus");
					Helper.showMsg("fill");
				} else {
					try {  
						Connection con = conn.connDb();
						Statement st = con.createStatement();//Es fragt ab, ob sich der eingegebene Wert in der Datenbank
						                                     //befindet und hilft uns, falls vorhanden, Maßnahmen zu ergreifen.
						ResultSet rs = st.executeQuery("SELECT * FROM user");//Alle Values 
						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))
									&& fld_doctorPass.getText().equals(rs.getString("password"))) {
								Chefarzt cArzt = new Chefarzt();
								cArzt.setId(rs.getInt("id"));  
								cArzt.setPassword("password");
								cArzt.setTcno(rs.getString("tcno"));
								cArzt.setName(rs.getString("name"));
								cArzt.setType(rs.getString("type"));
								ChefarztGUI cGUI = new ChefarztGUI(cArzt);  //wurde zwischen den GUI's gewechselt
								cGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException gui) {
						gui.printStackTrace();
					}
				}
			}
		});
		btn_doctorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_doctorLogin.setBounds(10, 116, 420, 42);
		panel_1.add(btn_doctorLogin);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(160, 57, 270, 33);
		panel_1.add(fld_doctorPass);

		/**Ein Message in Homepage*/
		JLabel lblNewLabel_1_3 = new JLabel("Willkommen Krankenhaus Management System");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblNewLabel_1_3.setBounds(62, 104, 476, 47);
		w_pane.add(lblNewLabel_1_3);
	}
}
