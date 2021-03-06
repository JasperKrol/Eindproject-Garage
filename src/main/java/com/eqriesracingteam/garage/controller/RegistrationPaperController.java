package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.RegistrationPaperResponseFile;
import com.eqriesracingteam.garage.exceptions.ResponseMessage;
import com.eqriesracingteam.garage.model.RegistrationPaper;
import com.eqriesracingteam.garage.service.RegistrationPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/garage/registration_papers")
@CrossOrigin("http://localhost:8081")
public class RegistrationPaperController {

    private RegistrationPaperService registrationPaperService;

    @Autowired
    public RegistrationPaperController(RegistrationPaperService registrationPaperService) {
        this.registrationPaperService = registrationPaperService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationPaperResponseFile>> getAllDocuments() {

        List<RegistrationPaperResponseFile> files = registrationPaperService.getAllDocuments().map(picture -> {

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/registration_papers/").path(String.valueOf(picture.getId())).toUriString();

            return new RegistrationPaperResponseFile(picture.getName(), fileDownloadUri, picture.getType(), picture.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        RegistrationPaper fileDB = registrationPaperService.getFile(id);
        String mediaType = "application/octet-stream";
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mediaType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"").body(fileDB.getData());
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            registrationPaperService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable("id") Long id) {
        registrationPaperService.deleteFile(id);
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long fileId) {
        // Load file from database
        RegistrationPaper dbFile = registrationPaperService.downloadFile(fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"").body(new ByteArrayResource(dbFile.getData()));
    }
}
