package com.remmylife.gui;

import java.awt.Dimension;

import javax.swing.*;
import java.util.ArrayList;

public class DiaryListWindow extends BGPanel
{
	ImageIcon headIcon = new ImageIcon("img/head.png");
	ImageIcon writeIcon = new ImageIcon("img/write.png");
	ImageIcon searchIcon = new ImageIcon("img/search.png");
	ImageIcon sortIcon = new ImageIcon("img/sort.png");
	
	JLabel headIconLabel = new JLabel(headIcon);
	JLabel writeIconLabel = new JLabel(writeIcon);
	JLabel searchIconLabel = new JLabel(searchIcon);
	JLabel sortIconLabel = new JLabel(sortIcon);
	JLabel nameLabel = new JLabel("章泽天");
	JLabel writeLabel = new JLabel("写日志");
	JLabel searchLabel = new JLabel("查找");
	JLabel sortLabel = new JLabel("排序");
	
	ArrayList<ListUnit> unitList = null;
	
	public DiaryListWindow()
	{
		super();
		init();
	}
	
	private void init()
	{
		this.setPreferredSize(new Dimension(MainWindow.WIDTH, MainWindow.HEIGHT));
		this.setBackground("img/list.jpg");
		this.setLayout(null);
		
		headIconLabel.setBounds(20, 90, 80, 80);
		this.add(headIconLabel);
		nameLabel.setBounds(40, 160, 80, 30);
		this.add(nameLabel);
		
		writeIconLabel.setBounds(20, 210, 80, 80);
		this.add(writeIconLabel);
		writeLabel.setBounds(40, 270, 80, 30);
		this.add(writeLabel);
		
		searchIconLabel.setBounds(20, 320, 80, 80);
		this.add(searchIconLabel);
		searchLabel.setBounds(40, 390, 80, 30);
		this.add(searchLabel);
		
		sortIconLabel.setBounds(20, 440, 80, 80);
		this.add(sortIconLabel);
		sortLabel.setBounds(40, 510, 80, 30);
		this.add(sortLabel);
		
		ListUnit unit1 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit2 = new ListUnit(com.remmylife.head.DiaryType.TEXT_DIARY);
		ListUnit unit3 = new ListUnit(com.remmylife.head.DiaryType.VOICE_DIARY);
		ListUnit unit4 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit5 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit6 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit7 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit8 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit9 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit10 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit11 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		ListUnit unit12 = new ListUnit(com.remmylife.head.DiaryType.IMAGE_DIARY);
		
		Box box = Box.createVerticalBox();
		box.add(unit1);
		box.add(unit2);
		box.add(unit3);
		box.add(unit4);
		box.add(unit5);
		box.add(unit6);
		box.add(unit7);
		box.add(unit8);
		box.add(unit9);
		box.add(unit10);
		box.add(unit11);
		box.add(unit12);
		JScrollPane unitPane = new JScrollPane(box);
		unitPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		unitPane.setOpaque(false);
	    unitPane.getViewport().setOpaque(false);
	    unitPane.setBorder(BorderFactory.createEmptyBorder());
	    unitPane.setBounds(130, 60, 265, 480);
		this.add(unitPane);
	}
}
