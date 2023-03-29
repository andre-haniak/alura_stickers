package com.alura_stickers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class StickerGenerator {
    

    public void CreateSticker() throws Exception {
        
        //Image reading
        BufferedImage originalImage = ImageIO.read(new File("input/Filme.jpg"));


        //Create new image in memory with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 300;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        //Copy image to new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        graphics.drawImage(originalImage, 0, 0, null);

        //Write a sentence on the new image


        //Write new image to a file
        ImageIO.write(newImage, "png", new File("output/sticker.png"));
    }

    public static void main(String[] args) throws Exception {
        StickerGenerator generator = new StickerGenerator();

        generator.CreateSticker();
    }
}
