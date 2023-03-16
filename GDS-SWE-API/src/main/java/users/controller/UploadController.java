package users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import users.constants.Constants;
import users.service.UploadService;

import java.io.IOException;

@RestController
public class UploadController {

    @Autowired
    UploadService uploadService;

    @CrossOrigin
    @PostMapping(value="upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        try {
            Integer response =  uploadService.uploadFile(file);
            if (response != 1) {
                objectNode.put(Constants.FAILURE_MESSAGE, response);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            } else {
                objectNode.put(Constants.SUCCESS_MESSAGE, response);
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            }
        } catch (Exception e) {
            objectNode.put(Constants.FAILURE_MESSAGE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
