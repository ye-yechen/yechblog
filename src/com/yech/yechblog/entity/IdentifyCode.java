package com.yech.yechblog.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 验证码工具类
 * 
 * @author Administrator
 *
 */
public class IdentifyCode {

	public static int WIDTH = 130;
	public static int HEIGHT = 30;

	private String checkCode="";// 验证码

	ByteArrayInputStream input=null;
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	/**
	 * 获取 验证码 图片
	 * 
	 * @return
	 */
	public ByteArrayInputStream  getIdentifyCodeImage() {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 1.设置背景色
		setBackground(g);
		// 2.设置边框
		setBorder(g);
		// 3.画干扰线
		drawRandomLine(g);
		// 4.写随机数
		drawRandomNumber((Graphics2D) g);
		
		//图象生效  
        g.dispose();  
         
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        try{  
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);  
            ImageIO.write(image, "JPEG", imageOut);  
            imageOut.close();  
            input = new ByteArrayInputStream(output.toByteArray());  
        }catch(Exception e){  
            System.out.println("验证码图片产生出现错误："+e.toString());  
        }  
		return input;
	}

	private void drawRandomNumber(Graphics2D g) {

		g.setColor(Color.RED);
		g.setFont(new Font("宋体", Font.BOLD, 25));
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		int x = 5;
		for (int i = 0; i < 4; i++) {
			int degree = new Random().nextInt() % 30;// 得到-30到30的随机数
			String ch = base.charAt(new Random().nextInt(base.length())) + "";
			// 记录产生的验证码
			checkCode += ch;
			g.rotate(degree * Math.PI / 180, x, 20);// 旋转的弧度
			g.drawString(ch, x, 20);
			g.rotate(-degree * Math.PI / 180, x, 20);// 旋转的为之前角度
			x += 30;
		}
	}

	private void drawRandomLine(Graphics g) {
		g.setColor(Color.GREEN);
		for (int i = 0; i < 20; i++) {
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);

			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	private void setBorder(Graphics g) {
		g.setColor(Color.ORANGE);
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	private void setBackground(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
}
