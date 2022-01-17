package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.RegistrationPaper;
import com.eqriesracingteam.garage.repository.RegistrationPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class RegistrationPaperService {

    @Autowired
    private RegistrationPaperRepository registrationPaperRepository;

    // TODO: 16-1-2022 to use for localstorage
    public Stream<RegistrationPaper> getAllDocuments() {
        return registrationPaperRepository.findAll().stream();
    }

    public RegistrationPaper getPicture(Long id) {

        var optionalRegistrationPaper = registrationPaperRepository.findById(id);

        if (optionalRegistrationPaper.isPresent()) {
            return optionalRegistrationPaper.get();
        } else {
            throw new RecordNotFoundException("Document with id " + id + " does not exist");
        }
    }

    public RegistrationPaper getDocumentByName(String name) {

        var registrationPaper = registrationPaperRepository.findByNameEquals(name);

        return registrationPaper;
    }

    public RegistrationPaper uploadFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        RegistrationPaper registrationPaper = new RegistrationPaper(filename, file.getContentType(), file.getBytes());
        return registrationPaperRepository.save(registrationPaper);
    }

    public void deleteFile(Long id) {
        registrationPaperRepository.deleteById(id);
    }

    // TODO: 16-1-2022 download as resource
    private final Path root = Paths.get("uploads");
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
