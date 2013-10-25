package com.remmylife.gui;

import java.awt.Dimension;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.remmylife.head.*;

public class ListUnit extends BGPanel
{
	String name = "章泽天";
	DiaryType diaryType = DiaryType.TEXT_DIARY;
	String content = "做作业做作业做作业做作业做作业做作业做作业做作业做作业";
	Date date = new Date();
	
	ImageIcon headIcon = new ImageIcon("img/smallhead.png");
	ImageIcon backgroundIcon = new ImageIcon("img/listunit.png");
	ImageIcon shareIcon = new ImageIcon("img/share.png");
	ImageIcon commentIcon = new ImageIcon("img/comment.png");
	ImageIcon textIcon = new ImageIcon("img/text.png");
	ImageIcon voiceIcon = new ImageIcon("img/voice.png");
	ImageIcon imageIcon = new ImageIcon("img/image.png");
	
	JLabel backgroundIconLabel = new JLabel(backgroundIcon);
	JLabel shareIconLabel = new JLabel(shareIcon);
	JLabel commentIconLabel = new JLabel(commentIcon);
	JLabel shareLabel = new JLabel("分享");
	JLabel commentLabel = new JLabel("评论");
	JLabel textIconLabel = new JLabel(textIcon);
	JLabel voiceIconLabel = new JLabel(voiceIcon);
	JLabel imageIconLabel = new JLabel(imageIcon); 
	JLabel headIconLabel = new JLabel();
	JLabel nameLabel = new JLabel();
	JLabel contentLabel = new JLabel();
	JLabel dateLabel = new JLabel();
	
	public ListUnit(DiaryType diaryType)
	{
		super();
		this.diaryType = diaryType;
		init();
	}
	
	private void init()
	{
		this.setPreferredSize(new Dimension(270, 80));
		this.setLayout(null);
		
		shareIconLabel.setBounds(160, 58, 20, 20);
		this.add(shareIconLabel);
		shareLabel.setBounds(180, 54, 30, 30);
		this.add(shareLabel);
		
		commentIconLabel.setBounds(200, 58, 20, 20);
		this.add(commentIconLabel);
		commentLabel.setBounds(220, 54, 30, 30);
		this.add(commentLabel);
		
		headIconLabel = new JLabel(headIcon);
		headIconLabel.setBounds(0, 5, 40, 40);
		this.add(headIconLabel);
		nameLabel.setText(name);
		nameLabel.setBounds(40, 15, 50, 30);
		this.add(nameLabel);
		
		switch(diaryType)
		{
			case TEXT_DIARY:
				textIconLabel.setBounds(0, 40, 40, 40);
		        this.add(textIconLabel);
		        break;
			case VOICE_DIARY:
				voiceIconLabel.setBounds(0, 40, 40, 40);
				this.add(voiceIconLabel);
				break;
			case IMAGE_DIARY:
				imageIconLabel.setBounds(0, 40, 40, 40);
				this.add(imageIconLabel);
				break;
		}
		
		contentLabel.setBounds(40, 35, 180, 30);
		contentLabel.setText(content);
		this.add(contentLabel);
		
		dateLabel.setBounds(40, 54, 150, 30);
		dateLabel.setText(getDateString(date));
		this.add(dateLabel);
		
		backgroundIconLabel.setBounds(0, 20, 250, 60);
		add(backgroundIconLabel);

		this.setOpaque(false);
	}
	
	public void setContent(String content)
	{
		this.content = content;
		contentLabel.setText(content);
	}
	
	public String getDateString(Date date)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
