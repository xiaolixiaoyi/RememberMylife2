package com.remmylife.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.jvnet.substance.*;
import org.jvnet.substance.theme.*;
import org.jvnet.substance.skin.*;

import com.remmylife.diary.*;

public class MainWindow extends JFrame
{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 640;
	
	private User user = new User();
	
	public MainWindow()
	{
		init();
	}
	
	public MainWindow(JPanel panel)
	{
		init();
		setPanel(panel);
	}
	
	private void init()
	{
		SubstanceLookAndFeel.setSkin(new MistAquaSkin());
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int w=(screenSize.width-WIDTH)/2;
		int h=(screenSize.height-HEIGHT)/2;
		setLocation(w,h);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setPanel(JPanel panel)
	{
		this.setContentPane(panel);
		revalidate();
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public static void main(String[] args)
	{
		MainWindow mainWindow = new MainWindow();
		mainWindow.setPanel(new DiaryListWindow());
	}
}
