import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class FastFood extends JFrame
{

	private JPanel contentPane;
	private JTextField txtCash;

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
					FastFood frame = new FastFood();
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
	public FastFood()
	{
		JPanel [] panels = setup();
		
		JRadioButton [] sandwiches = createSandwich(panels[0]);
		JCheckBox [] sides = createSide(panels[0]);
		
		JCheckBox [] drinks = createDrinks(panels[1]);
		JCheckBox [] shakes = createShakes(panels[1]);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 369, 666, 83);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblPayment = new JLabel("Payment Method");
		lblPayment.setBounds(10, 10, 117, 24);
		panels[2].add(lblPayment);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 35, 99, 23);
		panels[2].add(comboBox);
		comboBox.addItem("Cash");
		comboBox.addItem("Credit");
		comboBox.addItem("Debit");
		
		txtCash = new JTextField();
		txtCash.setBounds(10, 70, 99, 20);
		panels[2].add(txtCash);
		txtCash.setColumns(10);
		
		JLabel lblChange = new JLabel("Change     $");
		lblChange.setBounds(155, 10, 111, 24);
		panels[2].add(lblChange);
		
		JLabel lblTax = new JLabel("Tax (7%)   $");
		lblTax.setBounds(155, 35, 110, 24);
		panels[2].add(lblTax);
		
		JLabel lblSubtotal = new JLabel("Subtotal    $");
		lblSubtotal.setBounds(155, 60, 109, 24);
		panels[2].add(lblSubtotal);
		
		JLabel lblTotal = new JLabel("Total          $");
		lblTotal.setBounds(155, 85, 108, 24);
		panels[2].add(lblTotal);
		
		JButton btnTotal = new JButton("Total");
		btnTotal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				double subtotal = 0.0;
				
				for (JRadioButton s : sandwiches)
					if (s.isSelected())
					{
						subtotal += 7.5;
						textArea.append(s.getText() + "\n");
					}
				for (JCheckBox s : sides)
					if (s.isSelected())
					{
						subtotal += 2.5;
						textArea.append(s.getText() + "\n");
					}
				for (JCheckBox d : drinks)
					if (d.isSelected())
					{
						subtotal += 2;
						textArea.append(d.getText() + "\n");
					}
				for (JCheckBox s : shakes)
					if (s.isSelected())
					{
						subtotal += 2.75;
						textArea.append(s.getText() + "\n");
					}
				
				if (comboBox.getSelectedItem().equals("Cash")) lblChange.setText("Change     $" + (Integer.parseInt(txtCash.getText()) - (subtotal + (subtotal * 0.07))));
				else lblChange.setText("Change     $0.00");
				lblTax.setText("Tax (7%)   $" + (subtotal * 0.07));
				lblSubtotal.setText("Subtotal    $" + subtotal);
				lblTotal.setText("Total          $" + (subtotal + (subtotal * 0.07)));
			}
		});
		btnTotal.setBounds(10, 110, 72, 23);
		panels[2].add(btnTotal);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (JRadioButton s : sandwiches)
					s.setSelected(false);
				for (JCheckBox s : sides)
					s.setSelected(false);
				for (JCheckBox d : drinks)
					d.setSelected(false);
				for (JCheckBox s : shakes)
					s.setSelected(false);
				textArea.setText("");
				txtCash.setText("");
				lblChange.setText("Change     $");
				lblTax.setText("Tax (7%)   $");
				lblSubtotal.setText("Subtotal    $");
				lblTotal.setText("Total          $");
			}
		});
		btnReset.setBounds(102, 110, 72, 23);
		panels[2].add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		btnExit.setBounds(191, 110, 72, 23);
		panels[2].add(btnExit);
		
		setImage();
	}
	
	public JPanel [] setup()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel [] panels = new JPanel[3];
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Fast Food Restaurant");
		lblTitle.setBounds(187, 11, 337, 41);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblTitle);

		JPanel FoodPanel = new JPanel();
		FoodPanel.setBounds(10, 60, 180, 298);
		FoodPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(FoodPanel);
		FoodPanel.setLayout(null);
		panels[0] = FoodPanel;
		
		JPanel BeveragesPanel = new JPanel();
		BeveragesPanel.setBounds(496, 60, 180, 298);
		BeveragesPanel.setLayout(null);
		BeveragesPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(BeveragesPanel);
		panels[1] = BeveragesPanel;
		
		JPanel PaymentPanel = new JPanel();
		PaymentPanel.setBounds(205, 214, 275, 143);
		PaymentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(PaymentPanel);
		PaymentPanel.setLayout(null);
		panels[2] = PaymentPanel;
		
		return panels;
	}
	
	public JRadioButton [] createSandwich(JPanel FoodPanel)
	{
		JLabel lblSandwich = new JLabel("Sandwiches (7.50$ each)");
		lblSandwich.setBounds(15, 15, 155, 14);
		FoodPanel.add(lblSandwich);
		JRadioButton [] sandwiches = new JRadioButton[4];
		
		JRadioButton rdbFish = new JRadioButton("Fish Sandwich");
		rdbFish.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (JRadioButton rdb : sandwiches)
					if (rdb != rdbFish) rdb.setSelected(false);
			}
		});
		rdbFish.setBounds(25, 45, 137, 23);
		FoodPanel.add(rdbFish);
		sandwiches[0] = rdbFish;
		
		JRadioButton rdbChicken = new JRadioButton("Chicken Sandwich");
		rdbChicken.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (JRadioButton rdb : sandwiches)
					if (rdb != rdbChicken) rdb.setSelected(false);
			}
		});
		rdbChicken.setBounds(25, 75, 137, 23);
		FoodPanel.add(rdbChicken);
		sandwiches[1] = rdbChicken;
		
		JRadioButton rdbTurkey = new JRadioButton("Turkey Sandwich");
		rdbTurkey.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (JRadioButton rdb : sandwiches)
					if (rdb != rdbTurkey) rdb.setSelected(false);
			}
		});
		rdbTurkey.setBounds(25, 105, 137, 23);
		FoodPanel.add(rdbTurkey);
		sandwiches[2] = rdbTurkey;
		
		JRadioButton rdbBurger = new JRadioButton("Burger");
		rdbBurger.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (JRadioButton rdb : sandwiches)
					if (rdb != rdbBurger) rdb.setSelected(false);
			}
		});
		rdbBurger.setBounds(25, 135, 111, 23);
		FoodPanel.add(rdbBurger);
		sandwiches[3] = rdbBurger;
		
		return sandwiches;
	}

	public JCheckBox [] createSide(JPanel FoodPanel)
	{
		JLabel lblSides = new JLabel("Sides (2.50$ each)");
		lblSides.setBounds(15, 170, 116, 24);
		FoodPanel.add(lblSides);
		JCheckBox [] sides = new JCheckBox[3];
		
		JCheckBox chkFries = new JCheckBox("Fries");
		chkFries.setBounds(25, 200, 99, 23);
		FoodPanel.add(chkFries);
		sides[0] = chkFries;
		
		JCheckBox chkSalad = new JCheckBox("Salad");
		chkSalad.setBounds(25, 230, 99, 23);
		FoodPanel.add(chkSalad);
		sides[1] = chkSalad;
		
		JCheckBox chkOnion = new JCheckBox("Onion Rings");
		chkOnion.setBounds(25, 260, 137, 23);
		FoodPanel.add(chkOnion);
		sides[2] = chkOnion;
		
		return sides;
	}

	public JCheckBox [] createDrinks(JPanel BeveragesPanel)
	{
		JLabel lblDrinks = new JLabel("Drinks (2.00$ each)");
		lblDrinks.setBounds(15, 15, 132, 33);
		BeveragesPanel.add(lblDrinks);
		JCheckBox [] drinks = new JCheckBox[3];
		
		JCheckBox chkIcedTea = new JCheckBox("Iced Tea");
		chkIcedTea.setBounds(25, 45, 100, 25);
		BeveragesPanel.add(chkIcedTea);
		drinks[0] = chkIcedTea;
		
		JCheckBox chkCoffee = new JCheckBox("Coffee");
		chkCoffee.setBounds(25, 75, 100, 25);
		BeveragesPanel.add(chkCoffee);
		drinks[1] = chkCoffee;
		
		JCheckBox chkOrange = new JCheckBox("Orange");
		chkOrange.setBounds(25, 105, 100, 25);
		BeveragesPanel.add(chkOrange);
		drinks[2] = chkOrange;
		
		return drinks;
	}
	
	public JCheckBox [] createShakes(JPanel BeveragesPanel)
	{
		JLabel lblShakes = new JLabel("Shakes (2.75$ each)");
		lblShakes.setBounds(15, 170, 116, 33);
		BeveragesPanel.add(lblShakes);
		JCheckBox [] shakes = new JCheckBox[3];
		
		JCheckBox chkCone = new JCheckBox("Vanilla Cone");
		chkCone.setBounds(25, 200, 143, 23);
		BeveragesPanel.add(chkCone);
		shakes[0] = chkCone;
		
		JCheckBox chkVShake = new JCheckBox("Vanilla Shake");
		chkVShake.setBounds(25, 230, 143, 23);
		BeveragesPanel.add(chkVShake);
		shakes[1] = chkVShake;
		
		JCheckBox chkSShake = new JCheckBox("Strawberry Shake");
		chkSShake.setBounds(25, 260, 143, 23);
		BeveragesPanel.add(chkSShake);
		shakes[2] = chkSShake;
		
		return shakes;
	}
	
	public void setImage()
	{
		JLabel lblPicture = new JLabel("");
		lblPicture.setBounds(205, 63, 275, 143);
		contentPane.add(lblPicture);
		Image img = new ImageIcon(this.getClass().getResource("burger.jpg")).getImage();
		Image img2 = img.getScaledInstance(lblPicture.getWidth(), lblPicture.getHeight(), Image.SCALE_SMOOTH);
		lblPicture.setIcon(new ImageIcon(img2));
	}
}
