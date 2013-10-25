package com.remmylife.gui;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

import org.jvnet.substance.*;
import org.jvnet.substance.theme.*;
import org.jvnet.substance.skin.*;

public class LoginWindow extends JFrame
{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 250;
	
	private JLabel userNameLabel = new JLabel("�û���: ");
	private JTextField userNameField = new JTextField(20);
	private JLabel passwordLabel = new JLabel("��   ��: ");
	private JTextField passwordField = new JTextField(20);
	private JButton loginButton = new JButton("��½");
	private JButton registerButton = new JButton("ע��");
	
	public LoginWindow()
	{
		init();
	}
	
	private void init()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
//		SubstanceLookAndFeel.setCurrentTheme(new SubstancePurpleTheme());
//		setBackground(icon);
		
		BGPanel logoPanel = new BGPanel("img/login1.png");
		
		JPanel userNamePanel = new JPanel();
		userNamePanel.add(userNameLabel);
		userNamePanel.add(userNameField);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		fieldPanel.add(userNamePanel);
		fieldPanel.add(passwordPanel);
		
		BGPanel buttonPanel = new BGPanel("img/login3.png");
		LoginButtonListener loginButtonListener = new LoginButtonListener();
		loginButton.addActionListener(loginButtonListener);
		registerButton.addActionListener(loginButtonListener);
		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		
		logoPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT * 8 / 20));
		fieldPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT * 9 / 20));
		buttonPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT * 3 / 20));
		
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.add(logoPanel);
		this.add(fieldPanel);
		this.add(buttonPanel);
		
		userNamePanel.setOpaque(false);
		passwordPanel.setOpaque(false);
//		fieldPanel.setOpaque(false);
//		buttonPanel.setOpaque(false);
		
		SubstanceLookAndFeel.setSkin(new MistAquaSkin());
		try
		{
			this.setIconImage(ImageIO.read(new File("img/icon.jpg")));
		}
		catch(Exception e){}
		this.setTitle("Login");
		this.setSize(WIDTH, HEIGHT);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=this.getSize();
		int w=(screenSize.width-frameSize.width)/2;
		int h=(screenSize.height-frameSize.width)/2;
		setLocation(w,h);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setBackground(ImageIcon icon)
	{
		JLabel label = new JLabel(icon);
		label.setBounds(0, 0, WIDTH, HEIGHT);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		JPanel panel = (JPanel)this.getContentPane();
		panel.setOpaque(false);
	}
	
	private void alert(String message)
	{
		JOptionPane.showMessageDialog(this, message, "��ʾ", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args)
	{
		new LoginWindow();
	}
	
	class LoginButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton)e.getSource();
			if(button == loginButton)
			{
				String userName = userNameField.getText().trim();
				String password = passwordField.getText().trim();
				if(userName.equals(""))
				{
					alert("�û�������Ϊ��");
				}
				
				else if(password.equals(""))
				{
					alert("���벻��Ϊ��");
				}
				
				else
				{
//					boolean b = true;
					if(ManagerFactory.getManager().loginByName(userName, password))
//					if(b)
					{
						System.out.println("��½�ɹ�");
						dispose();
						new MainWindow(new DiaryListWindow());
					}
					else
					{
						System.out.println("��½ʧ��");
					}
				}
			}
			
			else if(button == registerButton)
			{
				dispose();
				new MainWindow(new RegisterWindow());
			}
		}
	}
}
