package com.remmylife.gui;

import javax.swing.*;
import java.awt.*;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.MistAquaSkin;

public class SearchWindow extends JFrame
{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 60;
	
	private String[] searchTypes = new String[]{
			"内容",
			"标题",
			"日期(年/月/日)", 
			"日记类型"};
	
	BGPanel bgPanel = new BGPanel("img/searchbg.png");
	JComboBox searchTypeSelector = new JComboBox(searchTypes);
	OpaqueTextField textField = new OpaqueTextField(20);
	JButton searchButton = new JButton("搜索");
	
	public SearchWindow()
	{
		init();
	}
	
	private void init()
	{	
		bgPanel.setLayout(new BoxLayout(bgPanel, BoxLayout.X_AXIS));
		searchTypeSelector.setOpaque(false);
		bgPanel.add(searchTypeSelector);
		bgPanel.add(textField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(searchButton);
		buttonPanel.setOpaque(false);
		bgPanel.add(buttonPanel);
		
		this.add(bgPanel);
		SubstanceLookAndFeel.setSkin(new MistAquaSkin());
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		new SearchWindow();
	}
}
