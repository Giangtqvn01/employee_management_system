package com.truonggiang.employee_management_system.controller.image;

import com.truonggiang.employee_management_system.exception.AppException;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.image.ImageResponse;
import com.truonggiang.employee_management_system.service.image.IImageService;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    private static Logit log = Logit.getInstance(ImageController.class);
    @Autowired
    private IImageService imageService;


    @PostMapping
    public ResponseEntity create(@RequestParam(name = "file") MultipartFile[] files) {
        log.info("Save image firebase");
        long start = System.currentTimeMillis();
        ImageResponse imageResponse = new ImageResponse();
        for (MultipartFile file : files) {
            try {
                String fileName = imageService.save(file);
                String imageUrl = imageService.getImageUrl(fileName);
                imageResponse.setUrlImage(imageUrl);
            } catch (Exception e) {
                throw new AppException("Save image firebase error: "+e);
            }
        }
        ResponseModel model = new ResponseModel();
        model.setData(imageResponse);
        model.setResponseStatus(HttpStatus.OK);
        model.setDescription("Save image firebase");
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }
}
