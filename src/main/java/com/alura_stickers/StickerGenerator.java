package com.alura_stickers;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
    

    public void CreateSticker(InputStream inputStream, String fileName) throws Exception {
        
        //Reading images
        InputStream inputCheck;
        if (fileName.equals("Javascript.png")){
            inputCheck = new FileInputStream(new File("input/dislike_drake.png"));
        } else {
            inputCheck = new FileInputStream(new File("input/rick_check.png"));
        }

        BufferedImage checkImage = ImageIO.read(inputCheck);
        BufferedImage originalImage = ImageIO.read(inputStream);

        //Create new image in memory with
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);

        //Copy image to new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        //Draw checkImage on newImage
        int checkWidth = checkImage.getWidth();
        int checkHeight = checkImage.getHeight();
        graphics.drawImage(checkImage, 0, height - checkHeight, checkWidth, checkHeight, null);

        //Write new image to a file
        ImageIO.write(newImage, "png", new File("output/" + fileName));
    }
}
