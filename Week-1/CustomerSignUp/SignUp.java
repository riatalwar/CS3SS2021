import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame
{
	private JPanel contentPane;
	private JPasswordField pswd1;
	private JPasswordField pswd2;
	private JTextField txtFirst;
	private JTextField txtLast;
	private JPasswordField pswdE;
	private JTextField txtFirstE;
	private JTextField txtLastE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() throws IOException
	{
		Scanner scan = new Scanner(new File("customers.txt"));
		int numPpl = Integer.parseInt(scan.nextLine());
		ArrayList<Customer> customers = new ArrayList<Customer>();
		for (int i = 0; i < numPpl; i++)
		{
			String[] arr = scan.nextLine().split(" ");
			customers.add(new Customer(arr[0], arr[1], arr[2]));
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 516, 341);
		contentPane.add(tabbedPane);
		
		JPanel pnlNew = new JPanel();
		tabbedPane.addTab("New Customers", null, pnlNew, null);
		pnlNew.setLayout(null);
		
		JLabel lblFirst = new JLabel("First name:");
		lblFirst.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirst.setBounds(55, 36, 145, 32);
		pnlNew.add(lblFirst);
		
		JLabel lblLast = new JLabel("Last name:");
		lblLast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLast.setBounds(55, 87, 145, 32);
		pnlNew.add(lblLast);
		
		JLabel lblPass1 = new JLabel("Password:");
		lblPass1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPass1.setBounds(55, 138, 145, 32);
		pnlNew.add(lblPass1);
		
		JLabel lblPass2 = new JLabel("Confirm password:");
		lblPass2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPass2.setBounds(55, 188, 172, 32);
		pnlNew.add(lblPass2);
		
		pswd1 = new JPasswordField();
		pswd1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pswd1.setBounds(274, 135, 172, 26);
		pnlNew.add(pswd1);
		
		pswd2 = new JPasswordField();
		pswd2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pswd2.setBounds(274, 186, 172, 26);
		pnlNew.add(pswd2);
		
		txtFirst = new JTextField();
		txtFirst.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFirst.setBounds(274, 36, 172, 26);
		pnlNew.add(txtFirst);
		txtFirst.setColumns(10);
		
		txtLast = new JTextField();
		txtLast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLast.setColumns(10);
		txtLast.setBounds(274, 87, 172, 26);
		pnlNew.add(txtLast);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (pswd1.getText().equals(pswd2.getText()))
				{
					customers.add(new Customer(txtFirst.getText(), txtLast.getText(), pswd1.getText()));
					JOptionPane.showMessageDialog(null, "You have sucessfully signed up!");
					txtFirst.setText("");
					txtLast.setText("");
					pswd1.setText("");
					pswd2.setText("");
					return;
				}
				JOptionPane.showMessageDialog(null, "Signup unsuccessful.");
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignUp.setBounds(161, 241, 177, 40);
		pnlNew.add(btnSignUp);
		
		JPanel pnlExist = new JPanel();
		tabbedPane.addTab("Existing Customers", null, pnlExist, null);
		pnlExist.setLayout(null);
		
		JLabel lblFirstE = new JLabel("First name:");
		lblFirstE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirstE.setBounds(85, 53, 145, 32);
		pnlExist.add(lblFirstE);
		
		JLabel lblLastE = new JLabel("Last name:");
		lblLastE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastE.setBounds(85, 104, 145, 32);
		pnlExist.add(lblLastE);
		
		JLabel lblPassE = new JLabel("Password:");
		lblPassE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassE.setBounds(85, 155, 145, 32);
		pnlExist.add(lblPassE);
		
		pswdE = new JPasswordField();
		pswdE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pswdE.setBounds(260, 158, 172, 26);
		pnlExist.add(pswdE);
		
		txtFirstE = new JTextField();
		txtFirstE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFirstE.setColumns(10);
		txtFirstE.setBounds(260, 59, 172, 26);
		pnlExist.add(txtFirstE);
		
		txtLastE = new JTextField();
		txtLastE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLastE.setColumns(10);
		txtLastE.setBounds(260, 110, 172, 26);
		pnlExist.add(txtLastE);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String first = txtFirstE.getText();
				String last = txtLastE.getText();
				String pswd = pswdE.getText();
				for (Customer c : customers)
				{
					if (c.getFirst().equals(first) && c.getLast().equals(last) && c.getPassword().equals(pswd))
					{
						JOptionPane.showMessageDialog(null, "You have successfully signed in!");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "Signin unsuccessful.");
			}
		});
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignIn.setBounds(171, 233, 177, 40);
		pnlExist.add(btnSignIn);
	}
}
