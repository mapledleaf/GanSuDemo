/**
 * Copyright 2010 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 校验码辅助类.
 */
public class VerifyCodeHelper {

    /** The Constant verifyCodeHelper. */
	private static final VerifyCodeHelper verifyCodeHelper = new VerifyCodeHelper();

    /** The WIDTH. */
	private static final int WIDTH = 15;// 图片的宽度

    /** The HEIGHT. */
    private static final int HEIGHT = 21;// 图片的高度

    /** The COD e_ length. */
	private static final int CODE_LENGTH = 4;// 字符串长度

    /** 随机字符串范围. */
    // private final String RAND_RANGE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	//private static final String RAND_RANGE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    private static final String RAND_RANGE = "1234567890";

    /** The CHARS. */
	private static final char[] CHARS = RAND_RANGE.toCharArray();// 随机字符串范围

    /** The random. */
	private Random random = new Random();

    /**
     * Instantiates a new verify code helper.
     */
	private VerifyCodeHelper() {
		// 
	}

    /**
     * Gets the single instance of VerifyCodeHelper.
     * 
     * @return single instance of VerifyCodeHelper
     */
	public static VerifyCodeHelper getInstance() {
		return verifyCodeHelper;
	}

    /**
     * 生成指定字符串的图像数据.
     * 
     * @param verifyCode
     *            即将被打印的随机字符串
     * @return 生成的图像数据
     */
	private BufferedImage getImage(String verifyCode) {
		BufferedImage image = new BufferedImage(WIDTH * CODE_LENGTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics graphics = image.getGraphics();
		// 设置背景色
        // graphics.setColor(getRandColor(1, 50));
        graphics.setColor(new Color(255, 255, 255));
		// 填充背景色
		graphics.fillRect(0, 0, WIDTH * 4, HEIGHT);

		// 设置边框颜色
        graphics.setColor(new Color(165, 172, 178));
        // 画边框
        for (int i = 0; i < 1; i++)
            graphics.drawRect(i, i, WIDTH * CODE_LENGTH - i * 2 - 1, HEIGHT - i * 2 - 1);

		// 设置随机干扰线条颜色
        //graphics.setColor(getRandColor(200, 255));

		// 产生50条干扰线条
        /*
		for (int i = 0; i < 255; i++) {
            // graphics.setColor(getRandColor(200, 255));

			int x1 = random.nextInt(WIDTH * CODE_LENGTH - 4) + 2;
			int y1 = random.nextInt(HEIGHT - 4) + 2;
			int x2 = random.nextInt(WIDTH * CODE_LENGTH - 2 - x1) + x1;
			int y2 = y1;
			graphics.drawLine(x1, y1, x2, y2);
		}*/
		// 设置字体
        graphics.setFont(new Font("Arial", Font.ITALIC, 20));
		// 画字符串
		for (int i = 0; i < VerifyCodeHelper.CODE_LENGTH; i++) {
			String temp = verifyCode.substring(i, i + 1);
            //graphics.setColor(getRandColor(25, 125));
			graphics.setColor(new Color(0, 0, 0));
            graphics.drawString(temp, 13 * i + 6, 18);
		}
		// 图像生效
		graphics.dispose();
		return image;
	}
	
    /**
     * Gets the verfy image.
     * 
     * @param verifyCode
     *            the verify code
     * @return the verfy image
     */
	public ByteArrayInputStream getVerfyImage(String verifyCode) {
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
			ImageIO.write(getImage(verifyCode), "JPEG", imageOut);
			imageOut.close();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
			System.out.println("验证码图片产生出现错误：" + e.toString());
		}
		return input;
	}

    /**
     * 生成随机字符串.
     * 
     * @return 随机字符串
     */
	public String getVerifyCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < CODE_LENGTH; i++)
			sb.append(CHARS[random.nextInt(CHARS.length)]);
		return sb.toString();
	}

    /**
     * 给定范围获得随机颜色.
     * 
     * @param fc
     *            the fc
     * @param bc
     *            the bc
     * @return the rand color
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
