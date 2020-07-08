package com.example.sklepZKwiatami.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Service
public class CaptchaService {
    private static final Integer TOTAL_CHAR = 6;
    private static final Integer WIDTH = 200;
    private static final Integer HEIGHT = 50;
    private static final Integer CYCLE = 15;
    private Font font;
    private Random random;

    public CaptchaService() {
        font = new Font("Arial", Font.ITALIC, 30);
        random = new Random();
    }

    public String createCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg"); // odpowiedz serwera będzie obrazkiem
        String imageCode = Long.toString(Math.abs(random.nextLong()), 36).substring(0, TOTAL_CHAR); // dodawanie kolejnych elementów captcha
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);// wymiary oraz właściwości captcha
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        for(int i=0;i<CYCLE;i++){
            graphics2D.setColor(
                    new Color(random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255))
            );
        }
        graphics2D.setFont(font);
        for(int i=0;i<TOTAL_CHAR;i++){
            graphics2D.setColor(
                    new Color(random.nextInt(255),
                            random.nextInt(255),
                            random.nextInt(255))
            );
            if(i%2==0) graphics2D.drawString(imageCode.substring(i,i+1),25*i,24);
            else graphics2D.drawString(imageCode.substring(i,i+1),25*i,35);
        }
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "jpeg", outputStream);
        graphics2D.dispose();
        graphics2D.create();
        return imageCode;
    }
}
