package hei.school.sarisary.service.event;

import hei.school.sarisary.repository.SarisaryRepository;
import hei.school.sarisary.repository.model.SarisaryModel;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.List;

@Service
public class SarisaryService {

    private final SarisaryRepository sarisaryRepository;

    public SarisaryService(SarisaryRepository sarisaryRepository) {
        this.sarisaryRepository = sarisaryRepository;
    }

    public List<SarisaryModel> findAll(){
        return sarisaryRepository.findAll();
    }

    private byte[] convertToBlackAndWhite(MultipartFile image) throws IOException {
        BufferedImage img = ImageIO.read(image.getInputStream());
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                Color c = new Color(img.getRGB(j, i));
                int r = (int)(c.getRed() *   0.299);
                int g = (int)(c.getGreen() *   0.587);
                int b = (int)(c.getBlue() *   0.114);
                Color newcolor = new Color(r+g+b, r+g+b, r+g+b);
                img.setRGB(j, i, newcolor.getRGB());
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        return baos.toByteArray();
    }

    public void addOrUpdateSarisaryModelWithImage(MultipartFile image) throws IOException {
        byte[] convertedImage = convertToBlackAndWhite(image);
    }

}