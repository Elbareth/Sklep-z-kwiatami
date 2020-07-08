package com.example.sklepZKwiatami.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Reader;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

@Service
public class ZxingHelperService {
    private static final Integer WIDTH = 200;
    private static final Integer HEIGHT = 200;

    public static Byte[] getBarCodeImage(String name, Float price){
        String text = name + " " + price.toString();
        try{
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Writer writer = new Code128Writer();
            BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.CODE_128, WIDTH, HEIGHT);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
            byte[] byteArray = outputStream.toByteArray();
            Byte[] byteObjectArray = new Byte[byteArray.length];
            for(int i=0;i<byteArray.length;i++){
                byteObjectArray[i] = byteArray[i];
            }
            return byteObjectArray;
        }
        catch (WriterException | IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public static String getInformationFromBarCode(Byte[] byteObjectArray){
        try {
            byte[] byteArray = new byte[byteObjectArray.length];
            for (int i = 0; i < byteObjectArray.length; i++) {
                byteArray[i] = byteObjectArray[i];
            }
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(byteArray));
            BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
            Reader reader = new Code128Reader();
            Hashtable<DecodeHintType, Object> hintMap = new Hashtable<>();
            hintMap.put(DecodeHintType.PURE_BARCODE, true);
            return reader.decode(binaryBitmap, hintMap).getText();
        }
        catch (IOException | NotFoundException | ChecksumException | FormatException e){
            e.printStackTrace();
            return null;
        }
    }
}
