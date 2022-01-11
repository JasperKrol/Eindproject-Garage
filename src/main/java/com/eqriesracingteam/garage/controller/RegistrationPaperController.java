package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.RegistrationPaperRequestDto;
import com.eqriesracingteam.garage.model.RegistrationPaper;
import com.eqriesracingteam.garage.repository.RegistrationPaperRepository;
import com.eqriesracingteam.garage.service.RegistrationPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/garage/registration_papers")
@CrossOrigin
public class RegistrationPaperController {

    private RegistrationPaperService registrationPaperService;

    @Autowired
    public RegistrationPaperController(RegistrationPaperService registrationPaperService) {
        this.registrationPaperService = registrationPaperService;
    }

    // Methods
    // Get
    @GetMapping
    public ResponseEntity<Object> getDocuments() {
        Iterable<RegistrationPaper> documents = registrationPaperService.getDocuments();
        return ResponseEntity.ok().body(documents);
    }

    // Get file info
    // Upload document
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> uploadDocument(RegistrationPaperRequestDto dto) {
        long newId = registrationPaperService.uploadDocument(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }
}
