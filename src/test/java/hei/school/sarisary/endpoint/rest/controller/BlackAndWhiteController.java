package hei.school.sarisary.endpoint.rest.controller;

import hei.school.sarisary.service.event.SarisaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlackAndWhiteController {

    private final SarisaryService sarisaryService;

    @Autowired
    public BlackAndWhiteController(SarisaryService sarisaryService) {
        this.sarisaryService = sarisaryService;
    }

    @PutMapping("/black-and-white/{id}")
    public ResponseEntity<Void> processImage(@PathVariable String id, @RequestBody MultipartFile image) {
        try {
            sarisaryService.addOrUpdateSarisaryModelWithImage(image);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
