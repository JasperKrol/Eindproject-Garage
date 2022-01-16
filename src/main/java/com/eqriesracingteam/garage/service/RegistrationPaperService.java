package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.FileStorageException;
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

        if(optionalRegistrationPaper.isPresent()) {

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

    // TODO: 12-1-2022   Upload to designated map -> to add path in app.prop

    //    public Iterable<RegistrationPaper> getDocuments() {
    //        return registrationPaperRepository.findAll();
    //    }
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
    //
    //    public long uploadDocument(MultipartFile file) {
    //
    //        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
    //        RegistrationPaper newFileToStore = new RegistrationPaper();
    //        newFileToStore.setFileName(originalFilename);
    //
    //        RegistrationPaper storedFile = registrationPaperRepository.save(newFileToStore);
    //
    //        return storedFile.getId();
    //
    //    }
    //
    //      public Resource downloadDocument(long id) {
    //        Optional<RegistrationPaper> stored = registrationPaperRepository.findById(id);
    //
    //        if (stored.isPresent()) {
    //            String filename = stored.get().getFileName();
    //            Path path = this.uploads.resolve(filename);
    //
    //            Resource resource = null;
    //            try {
    //                resource = new UrlResource(path.toUri());
    //                return resource;
    //            } catch (MalformedURLException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        else {
    //            throw new RecordNotFoundException("Registration paper with id " + id + " not found");
    //        }
    //
    //        return null;
    //    }
}
