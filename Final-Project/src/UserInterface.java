/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This class implements the MusicProcessor interface
 * so that it can analyze spotify listening history
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import org.json.simple.parser.ParseException;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;


public class UserInterface extends JFrame
{

	private JPanel contentPane;
	private JTable tableOverTime;
	private DefaultTableModel model;
	private JButton btnDuringPeriod;
	private JButton btnOverTime;
	private JPanel intro;
	private JPanel overTime;
	private IMusicProcessor mp;
	private JPanel duringPeriod;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JTextField txtStartYear;
	private JTextField txtEndYear;
	private JTextField txtStartMonth;
	private JTextField txtEndMonth;
	private JTextField txtStartDay;
	private JTextField txtEndDay;
	private JLabel lblYear;
	private JLabel lblMonth;
	private JLabel lblDay;
	private JLabel lblHours;
	private JLabel lblArtist;
	private JLabel lblTrack;
	private JLabel lblAlbum;
	private JLabel lblMostPlayed;
	private JTextField txtArtist;
	private JTextField txtTrack;
	private JTextField txtAlbum;
	private JTextField txtHours;
	private JButton btnHome;
	private JButton btnCalculate;
	private JLabel lblNewLabel;
	private JButton btnClear;

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
					UserInterface frame = new UserInterface();
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
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public UserInterface() throws IOException, ParseException
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String [] files = {"StreamingHistory.json"};
		mp = new SpotifyMusicProcessor(files);
		
		// Set up all three windows
		setupOverTime();
		setupDuringPeriod();
		setupHome();
		
		
	}
	
	public void setupHome()
	{
		intro = new JPanel();
		intro.setBounds(0, 0, 700, 500);
		contentPane.add(intro);
		intro.setVisible(true);
		
		lblNewLabel = new JLabel("Spotify Data Analysis");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(38, 31, 616, 189);
		intro.add(lblNewLabel);
		
		createHomeButtons();
	}
	
	public void createHomeButtons()
	{
		// Takes you to the During Period window
		btnDuringPeriod = new JButton("Data During Period");
		btnDuringPeriod.setBounds(160, 250, 375, 50);
		btnDuringPeriod.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				intro.setVisible(false);
				overTime.setVisible(false);
				duringPeriod.setVisible(true);
			}
		});
		intro.setLayout(null);
		btnDuringPeriod.setFont(new Font("Tahoma", Font.PLAIN, 20));
		intro.add(btnDuringPeriod);
		
		// Takes you to the Over Time window
		btnOverTime = new JButton("Data Over Time");
		btnOverTime.setBounds(160, 325, 375, 50);
		btnOverTime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				intro.setVisible(false);
				overTime.setVisible(true);
				duringPeriod.setVisible(false);
			}
		});
		btnOverTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		intro.add(btnOverTime);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);		// Quit program
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(160, 400, 375, 50);
		intro.add(btnExit);
	}
	
	public void setupOverTime()
	{
		// Set up over time window
		overTime = new JPanel();
		overTime.setBounds(0, 0, 700, 500);
		contentPane.add(overTime);
		overTime.setLayout(null);
		overTime.setVisible(false);
		
		JLabel lblOverTimeTitle = new JLabel("Data Over Time");
		lblOverTimeTitle.setBounds(30, 20, 294, 52);
		lblOverTimeTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		overTime.add(lblOverTimeTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 95, 621, 344);
		overTime.add(scrollPane);
		
		tableOverTime = new JTable();
		tableOverTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(tableOverTime);
		model = (DefaultTableModel) tableOverTime.getModel();
		String [] headers = {"Month", "Top Artist", "Top Song", "Top Album", "Total Hours"};
		model.setColumnIdentifiers(headers);
		
		
		JButton btnOTLoad = new JButton("Load Data");
		btnOTLoad.setBounds(350, 30, 135, 35);
		btnOTLoad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		overTime.add(btnOTLoad);
		btnOTLoad.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Date [] firstLast = mp.firstLastDate();	// Get the first and last dates with data
				// Get the first and last days in the starting month
				Date start = new Date(firstLast[0].getYear(), firstLast[0].getMonth(), 1);
				Date end = start.copy();
				end.toNextMonth();
				
				while (start.before(firstLast[1]))
				{
					String [] data = mp.getAllData(start, end);		// Get the most played track, artist, and album for the month
					String [] rowData = {start.toString(), data[0], data[1], data[2], data[3]};		// Add a row to the table with the data
					model.addRow(rowData);
					// Go to the next month
					start.toNextMonth();
					end.toNextMonth();
				}
			}
		});
		
		// Takes you back to the home page
		JButton btnOTHome = new JButton("Home");
		btnOTHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOTHome.setBounds(530, 30, 135, 35);
		overTime.add(btnOTHome);
		btnOTHome.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				intro.setVisible(true);
				overTime.setVisible(false);
				duringPeriod.setVisible(false);
			}
		});
	}
	
	public void setupDuringPeriod()
	{
		// Set up during period window
		duringPeriod = new JPanel();
		duringPeriod.setBounds(0, 0, 700, 500);
		contentPane.add(duringPeriod);
		duringPeriod.setLayout(null);
		duringPeriod.setVisible(false);
		
		JLabel lblDuringPeriod = new JLabel("Data During Period");
		lblDuringPeriod.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDuringPeriod.setBounds(30, 20, 294, 52);
		duringPeriod.add(lblDuringPeriod);
		
		createDPLabels();
		createDPTexts();
		
		// Takes you back to the home page
		btnHome = new JButton("Home");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHome.setBounds(530, 30, 135, 35);
		duringPeriod.add(btnHome);
		btnHome.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				intro.setVisible(true);
				overTime.setVisible(false);
				duringPeriod.setVisible(false);
			}
		});
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Get the dates entered by the user
				int startYear = Integer.parseInt(txtStartYear.getText());
				int endYear = Integer.parseInt(txtEndYear.getText());
				int startMonth = Integer.parseInt(txtStartMonth.getText());
				int endMonth = Integer.parseInt(txtEndMonth.getText());
				int startDay = Integer.parseInt(txtStartDay.getText());
				int endDay = Integer.parseInt(txtEndDay.getText());
				Date start = new Date(startYear, startMonth, startDay);
				Date end = new Date(endYear, endMonth, endDay);
				
				// Calculate and display results
				String [] data = mp.getAllData(start, end);
				txtArtist.setText(data[0]);
				txtTrack.setText(data[1]);
				txtAlbum.setText(data[2]);
				txtHours.setText(data[3]);
			}
		});
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCalculate.setBounds(350, 30, 135, 35);
		duringPeriod.add(btnCalculate);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Clear all the text boxes
				txtStartYear.setText("");
				txtEndYear.setText("");
				txtStartMonth.setText("");
				txtEndMonth.setText("");
				txtStartDay.setText("");
				txtEndDay.setText("");
				txtArtist.setText("");
				txtTrack.setText("");
				txtAlbum.setText("");
				txtHours.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(530, 94, 135, 35);
		duringPeriod.add(btnClear);
	}
	
	public void createDPLabels()
	{
		// Set up all the labels on the during period window
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDate.setBounds(417, 171, 111, 25);
		duringPeriod.add(lblStartDate);
		
		lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDate.setBounds(543, 171, 111, 25);
		duringPeriod.add(lblEndDate);
		
		lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYear.setBounds(336, 221, 50, 25);
		duringPeriod.add(lblYear);
		
		lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMonth.setBounds(336, 269, 64, 25);
		duringPeriod.add(lblMonth);
		
		lblDay = new JLabel("Day");
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDay.setBounds(336, 320, 50, 25);
		duringPeriod.add(lblDay);
		
		lblHours = new JLabel("Total Hours");
		lblHours.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHours.setBounds(20, 116, 111, 25);
		duringPeriod.add(lblHours);
		
		lblArtist = new JLabel("Top Artist");
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtist.setBounds(20, 230, 111, 25);
		duringPeriod.add(lblArtist);
		
		lblTrack = new JLabel("Top Song");
		lblTrack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrack.setBounds(20, 290, 111, 25);
		duringPeriod.add(lblTrack);
		
		lblAlbum = new JLabel("Top Album");
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAlbum.setBounds(20, 350, 111, 25);
		duringPeriod.add(lblAlbum);
		
		lblMostPlayed = new JLabel("Most Played");
		lblMostPlayed.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostPlayed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMostPlayed.setBounds(20, 182, 275, 25);
		duringPeriod.add(lblMostPlayed);
	}
	
	public void createDPTexts()
	{
		// Set up all the text boxes on the during period window
		txtStartYear = new JTextField();
		txtStartYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartYear.setBounds(417, 221, 111, 25);
		duringPeriod.add(txtStartYear);
		txtStartYear.setColumns(10);
		
		txtEndYear = new JTextField();
		txtEndYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndYear.setColumns(10);
		txtEndYear.setBounds(543, 221, 111, 25);
		duringPeriod.add(txtEndYear);
		
		txtStartMonth = new JTextField();
		txtStartMonth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartMonth.setColumns(10);
		txtStartMonth.setBounds(417, 269, 111, 25);
		duringPeriod.add(txtStartMonth);
		
		txtEndMonth = new JTextField();
		txtEndMonth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndMonth.setColumns(10);
		txtEndMonth.setBounds(543, 269, 111, 25);
		duringPeriod.add(txtEndMonth);
		
		txtStartDay = new JTextField();
		txtStartDay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartDay.setColumns(10);
		txtStartDay.setBounds(417, 320, 111, 25);
		duringPeriod.add(txtStartDay);
		
		txtEndDay = new JTextField();
		txtEndDay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndDay.setColumns(10);
		txtEndDay.setBounds(543, 320, 111, 25);
		duringPeriod.add(txtEndDay);
		
		txtArtist = new JTextField();
		txtArtist.setHorizontalAlignment(SwingConstants.CENTER);
		txtArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtArtist.setColumns(10);
		txtArtist.setBounds(120, 230, 175, 25);
		duringPeriod.add(txtArtist);
		
		txtTrack = new JTextField();
		txtTrack.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTrack.setColumns(10);
		txtTrack.setBounds(120, 290, 175, 25);
		duringPeriod.add(txtTrack);
		
		txtAlbum = new JTextField();
		txtAlbum.setHorizontalAlignment(SwingConstants.CENTER);
		txtAlbum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtAlbum.setColumns(10);
		txtAlbum.setBounds(120, 350, 175, 25);
		duringPeriod.add(txtAlbum);
		
		txtHours = new JTextField();
		txtHours.setHorizontalAlignment(SwingConstants.CENTER);
		txtHours.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHours.setColumns(10);
		txtHours.setBounds(143, 116, 152, 25);
		duringPeriod.add(txtHours);
	}
}
