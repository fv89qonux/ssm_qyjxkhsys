package com.xmjd.info.commons.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class RandomNumUtil {
	private ByteArrayInputStream image;// 需要生成的图片
	private String str;// 显示的字符串

	private RandomNumUtil() {
		init();// 初始化
	}

	/*
	 * 得到当前是实例
	 */
	public static RandomNumUtil Instance() {
		return new RandomNumUtil();
	}

	/*
	 * 返回图像
	 */
	public ByteArrayInputStream getImage() {
		return this.image;
	}

	/*
	 * 返回字符
	 */
	public String getString() {
		return this.str;
	}

	private void init() {
		// 设置宽高
		int width = 85, height = 30;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 作画
		Graphics g = image.getGraphics();
		// 随机对象
		Random random = new Random();
		// 设置颜色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设置字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		//设置随机产生的颜色
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";
		// for (int i=0;i<6;i++){
		// String rand=String.valueOf(random.nextInt(10));
		// sRand+=rand;
		char selectChar[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z' };// 可能产生的字符数组
		for (int i = 0; i < 4; i++) {
			// String rand=String.valueOf(random.nextInt(10));
			// sRand+=rand;

			int charIndex = (int) Math.floor(Math.random() * 62);
			String rand = String.valueOf(selectChar[charIndex]);
			sRand += selectChar[charIndex];
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(rand, 13 * i + 6, 25);
			this.str = sRand;
		}

		g.dispose();
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOut = ImageIO
					.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
			imageOut.close();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
			System.out.println("��֤��ͼƬ�������ִ���" + e.toString());
		}

		this.image = input;/* ��ֵͼ�� */
	}

	/*
	 * 产生随机的颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
