import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BMI extends JFrame
{

	private JPanel contentPane;
	private JTextField txtHeight;
	private JTextField txtWeight;
	private JTextField txtBMI;

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
					BMI frame = new BMI();
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
	public BMI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Body Mass Index");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(100, 11, 230, 44);
		contentPane.add(lblTitle);
		
		JLabel lblHeight = new JLabel("Height (inches):");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHeight.setBounds(22, 71, 142, 22);
		contentPane.add(lblHeight);
		
		JLabel lblWeight = new JLabel("Weight (pounds):");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWeight.setBounds(22, 118, 161, 22);
		contentPane.add(lblWeight);
		
		JLabel lblBMI = new JLabel("BMI:");
		lblBMI.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBMI.setBounds(22, 164, 70, 22);
		contentPane.add(lblBMI);
		
		txtHeight = new JTextField();
		txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHeight.setBounds(191, 66, 121, 31);
		contentPane.add(txtHeight);
		txtHeight.setColumns(10);
		
		txtWeight = new JTextField();
		txtWeight.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtWeight.setColumns(10);
		txtWeight.setBounds(192, 113, 121, 31);
		contentPane.add(txtWeight);
		
		txtBMI = new JTextField();
		txtBMI.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtBMI.setColumns(10);
		txtBMI.setBounds(193, 160, 121, 31);
		contentPane.add(txtBMI);
		
		JButton btnCalc = new JButton("Calculate");
		btnCalc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int height = Integer.parseInt(txtHeight.getText());
				int weight = Integer.parseInt(txtWeight.getText());
				int BMI = (int) ((weight / Math.pow(height, 2)) * 703);
				txtBMI.setText("" + BMI);
			}
		});
		btnCalc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCalc.setBounds(24, 223, 117, 25);
		contentPane.add(btnCalc);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				txtWeight.setText("");
				txtHeight.setText("");
				txtBMI.setText("");
			}
		});
		btnReset.setBounds(162, 222, 117, 25);
		contentPane.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		btnExit.setBounds(299, 222, 117, 25);
		contentPane.add(btnExit);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(330, 80, 85, 110);
		contentPane.add(lblPic);
		Image img = new ImageIcon(this.getClass().getResource("Oreo2.jpg")).getImage();
		Image img2 = img.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(), Image.SCALE_SMOOTH);
		lblPic.setIcon(new ImageIcon(img2));
	}
}
