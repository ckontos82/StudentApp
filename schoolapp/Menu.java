package gr.aueb.ct.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection connection;

	/**
	 * Create the frame.
	 */
	public Menu() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String username = "schooldbuser";
				String password = System.getenv("SCHOOL_DB_USER");
				String url = "jdbc:mysql://127.0.0.1:3306/schooldb?serverTimeZone=UTC";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection(url, username, password);
					System.out.println("Connection Established");
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton techersBtn = new JButton("");
		techersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersSearchForm().setVisible(true);
				Main.getMenu().setEnabled(false);
			}
		});
		techersBtn.setBounds(51, 69, 48, 60);
		contentPane.add(techersBtn);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.setBounds(51, 160, 48, 60);
		contentPane.add(studentsBtn);
		
		JLabel lblTeachers = new JLabel("Εκπαιδευτές");
		lblTeachers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTeachers.setBounds(137, 92, 103, 14);
		contentPane.add(lblTeachers);
		
		JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
		lblStudents.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStudents.setBounds(137, 183, 128, 14);
		contentPane.add(lblStudents);
		
		JLabel lblQuality1 = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblQuality1.setForeground(new Color(0, 0, 205));
		lblQuality1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQuality1.setBounds(85, 11, 262, 22);
		contentPane.add(lblQuality1);
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
