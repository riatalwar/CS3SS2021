import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class LuckySeven extends JFrame
{
	private JPanel contentPane;
	private JLabel lblTitle;
	private JLabel lblSpent;
	private int spent;
	private JLabel lblWon;
	private int won;
	private JButton btnSpin;
	private JButton btnEnd;
	private JButton btnReset;
	private JLabel lblSpin1;
	private JLabel lblSpin2;
	private JLabel lblSpin3;
	private JLabel lblPic;

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
					LuckySeven frame = new LuckySeven();
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
	 * @throws LineUnavailableException 
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 */
	public LuckySeven() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		setup();
		
		createButtons();
		
		createImage();
		
		//while (!spin())
		//	if (false) System.out.println();
	}
	
	public void setup()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Lucky Seven");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Stencil", Font.BOLD, 40));
		lblTitle.setBounds(15, 395, 319, 57);
		contentPane.add(lblTitle);
		
		lblSpent = new JLabel("Money Spent:");
		lblSpent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSpent.setBounds(150, 20, 200, 40);
		contentPane.add(lblSpent);
		
		lblWon = new JLabel("Money Earned:");
		lblWon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWon.setBounds(400, 20, 200, 40);
		contentPane.add(lblWon);
		
		
		lblSpin1 = new JLabel("-");
		lblSpin1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpin1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblSpin1.setBounds(229, 100, 100, 100);
		contentPane.add(lblSpin1);
		
		lblSpin2 = new JLabel("-");
		lblSpin2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpin2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblSpin2.setBounds(379, 100, 100, 100);
		contentPane.add(lblSpin2);
		
		lblSpin3 = new JLabel("-");
		lblSpin3.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpin3.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblSpin3.setBounds(529, 100, 100, 100);
		contentPane.add(lblSpin3);
	}
	
	public void createButtons()
	{
		btnSpin = new JButton("Spin");
		btnSpin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					spin();
				}
				catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnSpin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSpin.setBounds(15, 120, 150, 40);
		contentPane.add(btnSpin);
		
		btnEnd = new JButton("End");
		btnEnd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		btnEnd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnd.setBounds(15, 195, 150, 40);
		contentPane.add(btnEnd);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				lblSpent.setText("Money Spent: 0");
				lblWon.setText("Money Earned: 0");
				lblSpin1.setText("-");
				lblSpin2.setText("-");
				lblSpin3.setText("-");
				spent = 0;
				won = 0;
				lblPic.hide();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.setBounds(15, 270, 150, 40);
		contentPane.add(btnReset);
	}
	
	public boolean spin() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		lblPic.hide();
		
		int n1 = (int) (Math.random() * 7 + 1);
		int n2 = (int) (Math.random() * 7 + 1);
		int n3 = (int) (Math.random() * 7 + 1);
		
		lblSpin1.setText("" + n1);
		lblSpin2.setText("" + n2);
		lblSpin3.setText("" + n3);
		
		if (n1 == 7 && n2 == 7 && n3 == 7)
		{
			won += 1000;
			lblWon.setText("Money Earned: " + won);
			lblPic.show();
			playAudio();
			return true;
		}
		else
		{
			spent += 10;
			lblSpent.setText("Money Spent: " + spent);
			return false;
		}
	}
	
	public void createImage()
	{
		lblPic = new JLabel("");
		lblPic.setBounds(240, 200, 400, 170);
		Image img = new ImageIcon(this.getClass().getResource("lucky.jpg")).getImage();
		Image img2 = img.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(), Image.SCALE_SMOOTH);
		lblPic.setIcon(new ImageIcon(img2));
		contentPane.add(lblPic);
		lblPic.hide();
	}
	
	public void playAudio()
			throws UnsupportedAudioFileException,
	        IOException, LineUnavailableException
	{
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("lucky.wav").getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(ais);
		clip.loop(1);
	}
}
