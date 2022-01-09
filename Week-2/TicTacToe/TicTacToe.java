import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TicTacToe extends JFrame
{

	private JPanel contentPane;
	private char [][] board;
	private JButton [][] buttonBoard;
	private char turn;
	private Image imgX;
	private Image imgO;

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
					TicTacToe frame = new TicTacToe();
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
	public TicTacToe()
	{
		setup();
		
		createBoard();
		
		createImages();
	}
	
	public void setup()
	{
		// Content pane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Title at the top
		JLabel lblTitle = new JLabel("Tic Tac Toe");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 0, 416, 25);
		contentPane.add(lblTitle);
		// Exit button
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(255, 425, 130, 25);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		// Clear or reset button
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearBoard();
			}
		});
		btnClear.setBounds(55, 425, 130, 25);
		contentPane.add(btnClear);
		// Create board
		board = new char[3][3];
		buttonBoard = new JButton[3][3];
		
		turn = 'x';
	}
	
	public void createBoard()
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				// Create all the locations on a tic tac toe board
				JButton btn = new JButton("");
				btn.setBounds(j * 130 + 20, i * 130 + 30, 130, 130);
				contentPane.add(btn);
				buttonBoard[i][j] = btn;
				btn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						placeTile(btn);
					}
				});
				board[i][j] = 'n';
			}
		}
	}
	
	public void createImages()
	{
		// Create X and O images to be used later
		Image imgX2 = new ImageIcon(this.getClass().getResource("x.png")).getImage();
		Image imgO2 = new ImageIcon(this.getClass().getResource("o.png")).getImage();
		imgX = imgX2.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		imgO = imgO2.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	}
	
	public void placeTile(JButton btn)
	{
		// Find which button was clicked
		int x = 0;
		int y = 0;
		for (int i = 0; i < 3; i++)
		   	for (int j = 0; j < 3; j++)
		   		if (buttonBoard[i][j] == btn)
		   		{
		   			x = i;
		   			y = j;
		   		}
		
		// If location is empty change image to whoever's turn it is
		if (turn == 'x' && board[x][y] == 'n')
		{
			btn.setIcon(new ImageIcon(imgX));
			board[x][y] = 'x';
			turn = 'o';
		}
		else if (turn == 'o' && board[x][y] == 'n')
		{
			btn.setIcon(new ImageIcon(imgO));
			board[x][y] = 'o';
			turn = 'x';
		}
		
		// Check for winner
		char winner = checkWinner();
		System.out.println(winner);
		if (winner == 'n') return;
		else if (winner == 'd') JOptionPane.showMessageDialog(null, "Draw!");
		else if (winner == 'x') JOptionPane.showMessageDialog(null, "X wins!");
		else if (winner == 'o') JOptionPane.showMessageDialog(null, "O wins!");
		turn = 'n';
	}
	
	public void clearBoard()
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				// All locations are effectively emptied
				board[i][j] = 'n';
				buttonBoard[i][j].setIcon(null);
			}
		}
		turn = 'x';
	}
	
	public char checkWinner()
	{
		for (int i = 0; i < 3; i++)
	    {
	      if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) // Win by row
	        return board[i][0];
	      if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) // Win by column
	        return board[0][i];
	    }
	    
	    if (board[0][0] == board[1][1] && board[0][0] == board[2][2])  // Win by diagonal (down right)
	      return board[0][0];
	    if (board[0][2] == board[1][1] && board[0][2] == board[2][0])  // Win by diagonal (up right)
	      return board[0][2];
	    
	    for (int i = 0; i < 3; i++)
	    	for (int j = 0; j < 3; j++)
	    		if (board[i][j] == 'n')
	    			return 'n'; // Game isn't over yet

	    return 'd'; // No other ways to win, meaning it is a draw
	}
}
