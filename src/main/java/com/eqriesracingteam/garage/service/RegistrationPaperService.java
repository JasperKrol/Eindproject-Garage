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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RegistrationPaperService {

    @Autowired
    private RegistrationPaperRepository registrationPaperRepository;

    // TODO: 16-1-2022 to use for localstorage
    public Stream<RegistrationPaper> getAllDocuments() {
        return registrationPaperRepository.findAll().stream();
    }

    public RegistrationPaper getFile(Long id) {

        var optionalRegistrationPaper = registrationPaperRepository.findById(id);

        if (optionalRegistrationPaper.isPresent()) {
            return registrationPaperRepository.findById(id).get();
        } else {
            throw new RecordNotFoundException("Document with id " + id + " does not exist");
        }
    }

    public RegistrationPaper store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        RegistrationPaper registrationPaper = new RegistrationPaper(fileName, file.getContentType(), file.getBytes());

        return registrationPaperRepository.save(registrationPaper);
    }

    public void deleteFile(Long id) {
        registrationPaperRepository.deleteById(id);
    }

    public RegistrationPaper getFileTest(Long fileId) {
        return registrationPaperRepository.findById(fileId)
                .orElseThrow(() -> new RecordNotFoundException("File not found with id " + fileId));
    }
}
