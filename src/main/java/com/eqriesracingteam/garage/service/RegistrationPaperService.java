package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.RegistrationPaperRequestDto;
import com.eqriesracingteam.garage.exceptions.FileStorageException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.RegistrationPaper;
import com.eqriesracingteam.garage.repository.RegistrationPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class RegistrationPaperService {

    @Value("${app.upload.dir:${user.home}}")
    private String uploadDirectory;  // relative to root
    private final Path uploads = Paths.get("uploads");

    @Autowired
    private RegistrationPaperRepository registrationPaperRepository;

    public Iterable<RegistrationPaper> getDocuments() {
        return registrationPaperRepository.findAll();
    }

    // Get one

    // TODO: 12-1-2022   Upload to designated map -> to add path in app.prop
//    public long uploadDocument(RegistrationPaperRequestDto dto) {
//
//        MultipartFile file = dto.getFile();
//
//        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
//        Path copyLocation = this.uploads.resolve(file.getOriginalFilename());
//
//        try {
//            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception e) {
//            throw new FileStorageException("Could not store file " + originalFilename + ". Please try again!");
//        }
//
//        RegistrationPaper newFileToStore = new RegistrationPaper();
//        newFileToStore.setFileName(originalFilename);
//        newFileToStore.setTitle(dto.getTitle());
//        newFileToStore.setDescription(dto.getDescription());
//
//        RegistrationPaper saved = registrationPaperRepository.save(newFileToStore);
//
//        return saved.getId();
//    }

    public long uploadDocument(MultipartFile file) {

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        RegistrationPaper newFileToStore = new RegistrationPaper();
        newFileToStore.setFileName(originalFilename);

        RegistrationPaper storedFile = registrationPaperRepository.save(newFileToStore);

        return storedFile.getId();

    }

    public Resource downloadDocument(long id) {
        Optional<RegistrationPaper> stored = registrationPaperRepository.findById(id);

        if (stored.isPresent()) {
            String filename = stored.get().getFileName();
            Path path = this.uploads.resolve(filename);

            Resource resource = null;
            try {
                resource = new UrlResource(path.toUri());
                return resource;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new RecordNotFoundException("Registration paper with id " + id + " not found");
        }

        return null;
    }
}
