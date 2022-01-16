package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.RegistrationPaper;
import com.eqriesracingteam.garage.repository.RegistrationPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class RegistrationPaperService {

    @Autowired
    private RegistrationPaperRepository registrationPaperRepository;

    // TODO: 16-1-2022 to use for localstorage
    public Stream<RegistrationPaper> getPictures() {
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

    public RegistrationPaper getPictureByNameEquals(String name) {

        var registrationPaper = registrationPaperRepository.findByNameEquals(name);

        return registrationPaper;
    }

    public RegistrationPaper storePicture(MultipartFile file) throws IOException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        RegistrationPaper registrationPaper = new RegistrationPaper(filename, file.getContentType(), file.getBytes());

        return registrationPaperRepository.save(registrationPaper);

    }

    public void deletePicture(Long id) {

        registrationPaperRepository.deleteById(id);

    }
}
