package com.remmylife.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
public class CircleButton extends JButton 
{
    private Shape shape = null;// ���ڱ��水ť����״,����������������ť�¼�
    Image im = null;
    public CircleButton(String imgPath) 
    {
    	try
		{
			im = ImageIO.read(new File(imgPath));
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
        this.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(MouseEvent e) 
            {
                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) 
            {
                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        Dimension size = new Dimension(im.getWidth(null), im.getHeight(null));
        setPreferredSize(size);
        // ʹJbutton��������,������ʾ���α���,���������ǻ�һ��Բ�ı���
        setContentAreaFilled(false);
    }
    // ��ͼ�İ�ť�ı����ͱ�ǩ
    protected void paintComponent(Graphics g) 
    {   
        g.drawImage(im, 0, 0, im.getWidth(null), im.getHeight(null), null);
        super.paintComponents(g);
    }
    
    protected void paintBorder(Graphics g) 
    {
    }
    // �ж�����Ƿ���ڰ�ť��
    public boolean contains(int x, int y) 
    {
        // �����ť�߿�,λ�÷����ı�,�����һ���µ���״����
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) 
        {
            shape = new Ellipse2D.Float(0, 0, im.getWidth(null), im.getHeight(null));
        }
        return shape.contains(x, y);
    }
}