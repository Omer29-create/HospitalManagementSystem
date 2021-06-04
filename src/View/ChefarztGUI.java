package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Chefarzt;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import Helper.*;

/**
 * Hier wird die Interface der Chefarztseite definiert
 * @author Omer
 *
 */
public class ChefarztGUI extends JFrame {

	static Chefarzt chefarzt = new Chefarzt();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_Pass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChefarztGUI frame = new ChefarztGUI(chefarzt);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ChefarztGUI(Chefarzt chefarzt) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Vor- & Nachname ";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Password";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < chefarzt.getDoctorList().size(); i++) {
			doctorData[0] = chefarzt.getDoctorList().get(i).getId();
			doctorData[1] = chefarzt.getDoctorList().get(i).getName();
			doctorData[2] = chefarzt.getDoctorList().get(i).getTcno();
			doctorData[3] = chefarzt.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		setTitle("Krankenhaus Management System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Willkommen, Dear " + chefarzt.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 291, 24);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Ausloggen");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnNewButton.setBounds(616, 15, 108, 24);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 77, 726, 385);
		w_pane.add(w_tab);

		JPanel w_doctor = new JPanel();
		w_tab.addTab("Arzt Management", null, w_doctor, null);
		w_doctor.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		w_doctor.setLayout(null);

		JLabel label = new JLabel("Vor- & Nachname");
		label.setBounds(515, 19, 172, 22);
		label.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		w_doctor.add(label);

		fld_dName = new JTextField();
		fld_dName.setBounds(515, 51, 172, 19);
		w_doctor.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel label_1 = new JLabel("T.C. No");
		label_1.setBounds(515, 80, 169, 22);
		label_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		w_doctor.add(label_1);

		fld_dTcno = new JTextField();
		fld_dTcno.setBounds(515, 112, 169, 19);
		w_doctor.add(fld_dTcno);
		fld_dTcno.setColumns(10);

		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(515, 151, 169, 22);
		label_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		w_doctor.add(label_2);

		fld_Pass = new JTextField();
		fld_Pass.setBounds(515, 183, 169, 19);
		w_doctor.add(fld_Pass);
		fld_Pass.setColumns(10);
		
		JButton btn_addDoctor = new JButton("Add");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_Pass.getText().length() == 0
						|| fld_dTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {  //Wir fügen an Datensystem hier Doctor ein
					try {
						boolean control = chefarzt.addDoctor(fld_dTcno.getText(), fld_Pass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success"); // In Helper Class wird scuccess Message definiert 
							fld_dName.setText(null);   //Alle Feldern wird geleert
							fld_dTcno.setText(null);
							fld_Pass.setText(null);
							//updateDoctorModel();
						}
					} catch (SQLException leer) {
						leer.printStackTrace();
					}
				}
			}
		});
		btn_addDoctor.setBounds(515, 212, 169, 31);
		btn_addDoctor.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		w_doctor.add(btn_addDoctor);

		JLabel lblKullaniciId = new JLabel("Benutzer/in ID");
		lblKullaniciId.setBounds(515, 247, 169, 22);
		lblKullaniciId.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		w_doctor.add(lblKullaniciId);

		/**Ein Scrollpane wird erstellt, damit wir nach unten ziehen können.*/
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(669, 19, 2, 2);
		w_doctor.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(676, 19, 2, 2);
		w_doctor.add(scrollPane_1);

		fld_doctorID = new JTextField();
		fld_doctorID.setBounds(515, 279, 169, 19);
		fld_doctorID.setBackground(SystemColor.controlHighlight);
		fld_doctorID.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		w_doctor.add(fld_doctorID);
		fld_doctorID.setColumns(10);

		JButton btn_delDoctor = new JButton("Löschen");
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Helper.showMsg("Bitte wählen Sie gültig Arzt aus");
				}else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = chefarzt.deleteDoctor(selectID);
							if(control) {
								Helper.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delDoctor.setBounds(515, 308, 169, 31);
		btn_delDoctor.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		w_doctor.add(btn_delDoctor);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 19, 496, 320);
		w_doctor.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
				
			}
		});
		
		table_doctor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					
					try {
						boolean control = chefarzt.updateDoctor(selectID, selectTcno, selectPass, selectName);
						if (control) {
							Helper.showMsg("success");
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
			}
			
		});

	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < chefarzt.getDoctorList().size(); i++) {
			doctorData[0] = chefarzt.getDoctorList().get(i).getId();
			doctorData[1] = chefarzt.getDoctorList().get(i).getName();
			doctorData[2] = chefarzt.getDoctorList().get(i).getTcno();
			doctorData[3] = chefarzt.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}
}
