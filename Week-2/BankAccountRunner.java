import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BankAccountRunner extends JFrame
{

	private JPanel contentPane;
	private JTable accounts;
	private DefaultTableModel model;
	private JButton btnLoad;
	private JTextField txtFirst;
	private JTextField txtLast;
	private JTextField txtAge;
	private JTextField txtCity;
	private JTextField txtBalance;
	private JButton btnClear;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnRemove;

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
					BankAccountRunner frame = new BankAccountRunner();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BankAccountRunner()
	{
		setup();
		
		createTable();
		
		createLabels();
		
		createTextFields();
		
		createButtons();
	}
	
	public void setup()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Bank Accounts");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(225, 22, 247, 28);
		contentPane.add(lblTitle);
	}
	
	public void createTable()
	{
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(259, 101, 415, 260);
		contentPane.add(scrollPane);
		
		accounts = new JTable();
		accounts.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				int row = accounts.getSelectedRow();
				txtFirst.setText((String) accounts.getValueAt(row, 0));
				txtLast.setText((String) accounts.getValueAt(row, 1));
				txtAge.setText((String) accounts.getValueAt(row, 2));
				txtCity.setText((String) accounts.getValueAt(row, 3));
				txtBalance.setText((String) accounts.getValueAt(row, 4));
			}
		});
		scrollPane.setViewportView(accounts);
		model = (DefaultTableModel) accounts.getModel();
		String [] headers = {"First Name", "Last Name", "Age", "City", "Balance"};
		model.setColumnIdentifiers(headers);
		
		btnLoad = new JButton("Load Data");
		btnLoad.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				loadData();
			}
		});
		btnLoad.setBounds(500, 50, 150, 35);
		contentPane.add(btnLoad);
		
	}
	
	public void createButtons()
	{
		btnClear = new JButton("Clear Fields");
		btnClear.setBounds(14, 390, 150, 35);
		contentPane.add(btnClear);
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearText();
			}
		});
		
		btnAdd = new JButton("Add Account");
		btnAdd.setBounds(184, 390, 150, 35);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addAccount();
			}
		});
		
		btnUpdate = new JButton("Update Account");
		btnUpdate.setBounds(354, 390, 150, 35);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateAccount();
			}
		});
		
		btnRemove = new JButton("Remove Account");
		btnRemove.setBounds(524, 390, 150, 35);
		contentPane.add(btnRemove);
		btnRemove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				removeAccount();
			}
		});
	}
	
	public void createLabels()
	{
		JLabel lblFirst = new JLabel("First Name:");
		lblFirst.setBounds(22, 101, 85, 28);
		contentPane.add(lblFirst);
		
		JLabel lblLast = new JLabel("Last Name:");
		lblLast.setBounds(22, 140, 85, 28);
		contentPane.add(lblLast);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(22, 179, 85, 28);
		contentPane.add(lblAge);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(22, 218, 85, 28);
		contentPane.add(lblCity);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(22, 257, 85, 28);
		contentPane.add(lblBalance);
	}

	public void createTextFields()
	{
		txtFirst = new JTextField();
		txtFirst.setBounds(112, 109, 96, 20);
		contentPane.add(txtFirst);
		txtFirst.setColumns(10);
		
		txtLast = new JTextField();
		txtLast.setColumns(10);
		txtLast.setBounds(112, 148, 96, 20);
		contentPane.add(txtLast);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(112, 187, 96, 20);
		contentPane.add(txtAge);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(112, 226, 96, 20);
		contentPane.add(txtCity);
		
		txtBalance = new JTextField();
		txtBalance.setColumns(10);
		txtBalance.setBounds(112, 265, 96, 20);
		contentPane.add(txtBalance);
	}

	public void loadData()
	{
		try
		{
			Scanner file = new Scanner(new File("accounts.txt"));
			file.nextLine();	// Skip the first line because it is just headers
			while (file.hasNextLine())
			{
				String line = file.nextLine();
				String [] arr = line.split(" ");
				model.addRow(arr);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void clearText()
	{
		txtFirst.setText("");
		txtLast.setText("");
		txtAge.setText("");
		txtCity.setText("");
		txtBalance.setText("");
	}

	public void addAccount()
	{
		String first = txtFirst.getText();
		String last = txtLast.getText();
		String age = txtAge.getText();
		String city = txtCity.getText();
		String balance = txtBalance.getText();
		String [] newRow = {first, last, age, city, balance};
		model.addRow(newRow);
	}
	
	public void updateAccount()
	{
		int row = accounts.getSelectedRow();
		accounts.setValueAt(txtFirst.getText(), row, 0);
		accounts.setValueAt(txtLast.getText(), row, 1);
		accounts.setValueAt(txtAge.getText(), row, 2);
		accounts.setValueAt(txtCity.getText(), row, 3);
		accounts.setValueAt(txtBalance.getText(), row, 4);
	}
	
	public void removeAccount()
	{
		int row = accounts.getSelectedRow();
		model.removeRow(row);
	}
}
