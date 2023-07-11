package gr.aueb.ct.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeachersSearchForm extends JFrame {
	private static final long serialVersionUID = 1L;
 	private JPanel contentPane;
	private JTextField txtLastname;
	private String lastname = "";


	/**
	 * Create the frame.
	 */
	public TeachersSearchForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtLastname.setText("");
			}
		});
		setForeground(new Color(0, 0, 0));
		setTitle("Εισαγωγή / Αναζήτηση εκπαιδευτή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(144, 238, 144));
		contentPane.setForeground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLastname = new JLabel("Επώνυμο");
		lblLastname.setBounds(185, 11, 64, 17);
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLastname.setForeground(new Color(139, 69, 19));
		contentPane.add(lblLastname);
		
		JButton searchBtn = new JButton("Αναζήτηση");
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchBtn.setForeground(new Color(0, 0, 255));
		searchBtn.setBounds(159, 94, 116, 23);
		contentPane.add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastname = txtLastname.getText();
				Main.getTeachersUpdateDeleteForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(false);
			}
		});
		
		txtLastname = new JTextField();
		txtLastname.setBounds(130, 39, 174, 44);
		contentPane.add(txtLastname);
		txtLastname.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(92, 11, 249, 124);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Εισαγωγή");
		btnNewButton.setBounds(159, 161, 116, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersInsertForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(false);
			}
		});
	}


	public String getLastname() {
		return lastname;
	}
	
	
}
