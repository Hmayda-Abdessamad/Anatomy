package com.example.modelisation3dducorpshumain.controller;
import com.example.modelisation3dducorpshumain.service.UploadDowloadservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin("*")
public class UploadDownloadcontroller {
    private final UploadDowloadservice uploadDowloadservice;

    public UploadDownloadcontroller(UploadDowloadservice uploadDowloadservice) {
        this.uploadDowloadservice = uploadDowloadservice;
    }

    @PostMapping(
            path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, //This specifies that the method expects the request content to be in the multipart/form-data
            produces = MediaType.APPLICATION_JSON_VALUE // This indicates that the response from the method will be in the JSON format
    )
    public ResponseEntity uploadUserImage(@RequestParam("file") MultipartFile file)  {
        try {
            Map<String,String> response = new HashMap<>();
            response.put("image",uploadDowloadservice.uploadFile(file,"img"));
            return ResponseEntity.ok(response);
        }catch (IllegalStateException e){
            Map<String,String> response = new HashMap<>();
            response.put("message","faild to upload");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

        @GetMapping(path = "/download/{path}"   , produces = MediaType.MULTIPART_FORM_DATA_VALUE //This specifies that the method expects the request content to be in the multipart/form-data
                //produces = MediaType.APPLICATION_JSON_VALUE // This indicates that the response from the method will be in the JSON format
        )
        public byte[] downloadUserImage( @PathVariable("path") String path) {
            try {
                path = path.replaceFirst("-", "/");
                System.out.println(path);
                return uploadDowloadservice.downloadFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

}
