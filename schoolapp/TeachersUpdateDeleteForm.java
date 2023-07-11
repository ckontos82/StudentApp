package gr.aueb.ct.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeachersUpdateDeleteForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public TeachersUpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
				Connection connection = Menu.getConnection();
				try {
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
					ps.setString(1, Main.getTeachersSearchForm().getLastname() + "%");
					rs = ps.executeQuery();
					
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					} else {
						JOptionPane.showMessageDialog(null, "No records found", "Info", JOptionPane.INFORMATION_MESSAGE);
						Main.getTeachersUpdateDeleteForm().setVisible(false);
						Main.getTeachersSearchForm().setEnabled(true);
						
						return;
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(81, 52, 17, 30);
		contentPane.add(lblId);
		
		idTxt = new JTextField();
		idTxt.setBackground(new Color(255, 255, 0));
		idTxt.setEditable(false);
		idTxt.setBounds(123, 59, 86, 20);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel lblFirstName = new JLabel("Όνομα");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFirstName.setBounds(50, 93, 48, 14);
		contentPane.add(lblFirstName);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setColumns(10);
		firstnameTxt.setBackground(new Color(255, 255, 255));
		firstnameTxt.setBounds(123, 92, 148, 20);
		contentPane.add(firstnameTxt);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBackground(Color.WHITE);
		lastnameTxt.setBounds(123, 123, 148, 20);
		contentPane.add(lastnameTxt);
		
		JLabel lblLastname = new JLabel("Επώνυμο");
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLastname.setBounds(34, 124, 64, 14);
		contentPane.add(lblLastname);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 154, 414, 2);
		contentPane.add(separator);
		
		JButton prevBtn = new JButton("Προηγούμενο");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					
				}
			}
		});
		prevBtn.setBounds(39, 201, 111, 23);
		contentPane.add(prevBtn);
		
		JButton nextBtn = new JButton("Επόμενο");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setBounds(160, 201, 111, 23);
		contentPane.add(nextBtn);
		
		JButton firstBtn = new JButton("Αρχή");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		firstBtn.setBounds(39, 167, 111, 23);
		contentPane.add(firstBtn);
		
		JButton lastBtn = new JButton("Τέλος");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					}
				} catch (SQLException e1) {
					
				}
			}
		});
		lastBtn.setBounds(160, 167, 111, 23);
		contentPane.add(lastBtn);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
				
				try {
					Connection connection = Menu.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					String firstname = firstnameTxt.getText().trim();
					String lastname = lastnameTxt.getText().trim();
					String id = idTxt.getText();
					
					if (firstname.equals("") || lastname.equals("")) {
						JOptionPane.showMessageDialog(null, "Empty firstname/lastname", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					ps.setString(1, firstname);
					ps.setString(2, lastname);
					ps.setInt(3, Integer.parseInt(id));
					
					int n = ps.executeUpdate();
					
					if (n > 0) {
						JOptionPane.showMessageDialog(null, "Update successful", "Update", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Uodate error", "Udate error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (ps != null) {
							ps.close();
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnUpdate.setBounds(313, 58, 111, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM TEACHERS WHERE ID = ?";
				try {
					Connection connection = Menu.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(idTxt.getText()));
					
					int response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						int n = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, n + " rows Affected", "Delete", JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(313, 91, 111, 23);
		contentPane.add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersUpdateDeleteForm().setVisible(false);
				Main.getTeachersSearchForm().setEnabled(true);
			}
		});
		btnClose.setBounds(313, 201, 111, 23);
		contentPane.add(btnClose);
	}
}
