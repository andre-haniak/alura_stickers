package com.alura_stickers;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class StickerGenerator {
    

    public void CreateSticker() throws Exception {
        
        //Reading images
        InputStream inputCheck = new FileInputStream(new File("input/rick_check.png"));
        BufferedImage checkImage = ImageIO.read(inputCheck);

        InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);


        //Create new image in memory with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);

        //Copy image to new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        //Draw checkImage on newImage
        int checkWidth = checkImage.getWidth();
        int checkHeight = checkImage.getHeight();
        graphics.drawImage(checkImage, -50, height - checkHeight, checkWidth, checkHeight, null);

        //Write new image to a file
        ImageIO.write(newImage, "png", new File("output/sticker.png"));
    }

    public static void main(String[] args) throws Exception {
        StickerGenerator generator = new StickerGenerator();

        generator.CreateSticker();
    }
}
