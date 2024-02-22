package hei.school.sarisary.endpoint.rest.controller;

import hei.school.sarisary.file.BucketComponent;
import hei.school.sarisary.service.event.SarisaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BlackAndWhiteController {

    private final SarisaryService sarisaryService;
    private final BucketComponent bucketComponent;

    @Autowired
    public BlackAndWhiteController(SarisaryService sarisaryService, BucketComponent bucketComponent) {
        this.sarisaryService = sarisaryService;
        this.bucketComponent = bucketComponent;
    }

    @PutMapping("/black-and-white/{id}")
    public ResponseEntity<Void> processImage(@PathVariable String id, @RequestBody MultipartFile image) {
        try {
            sarisaryService.addOrUpdateSarisaryModelWithImage(id, image);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/black-and-white/{id}")
    public ResponseEntity<Object> getBlackAndWhiteUrls(@PathVariable String id) {
        try {
            String originalImageKey = "original/" + id;
            String transformedImageKey = "transformed/" + id;

            String originalUrl = bucketComponent.presign(originalImageKey, Duration.ofMinutes(15)).toString();
            String transformedUrl = bucketComponent.presign(transformedImageKey, Duration.ofMinutes(15)).toString();

            Map<String, String> response = new HashMap<>();
            response.put("original_url", originalUrl);
            response.put("transformed_url", transformedUrl);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.ok().build();
        }
    }
}
