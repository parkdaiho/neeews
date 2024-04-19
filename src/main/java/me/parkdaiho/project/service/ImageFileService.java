package me.parkdaiho.project.service;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.ImageFileProperties;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.IncludingImages;
import me.parkdaiho.project.repository.ImageFileRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageFileService {

    private final ImageFileProperties imageFileProperties;
    private final ImageFileRepository imageFileRepository;

    public List<ImageFile> uploadImageFiles(List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) return null;

        List<ImageFile> images = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.getSize() == 0) break;

            String originalFileName = file.getOriginalFilename();
            String savedFileName = UUID.randomUUID() + originalFileName;

            images.add(ImageFile.builder()
                    .originalName(originalFileName)
                    .savedName(savedFileName)
                    .build());

            File temp = new File(imageFileProperties.getLocation() + "/temp/" + savedFileName);

            if (!temp.exists()) {
                if (temp.getParentFile().mkdirs()) {
                    temp.createNewFile();
                }
            }

            file.transferTo(temp);
        }

        return images;
    }

    public void moveFileToEntityDirectory(Domain domain, IncludingImages entity, List<ImageFile> images) throws IOException {
        for (ImageFile image : images) {
            String savedName = image.getSavedName();

            File srcFile = new File(imageFileProperties.getLocation() + "/temp/" + savedName);
            File destinedFolder = new File(imageFileProperties.getLocation() + "/" + domain.getDomainPl() + "/" + entity.getId());

            FileUtils.moveFileToDirectory(srcFile, destinedFolder, true);
        }
    }

    public void removeSourceFile(List<ImageFile> images) {
        for (ImageFile image : images) {
            String imageName = image.getSavedName();

            File srcFile = new File(imageFileProperties.getLocation() + "/temp/" + imageName);
            srcFile.delete();
        }
    }

    public void removeSavedFile(Domain domain, IncludingImages entity, List<ImageFile> images) {
        for (ImageFile image : images) {
            String savedName = image.getSavedName();

            File savedFile = new File(imageFileProperties.getLocation() + "/" + domain.getDomainPl() + "/" + entity.getId() + "/" + savedName);
            savedFile.delete();
        }
    }

    public List<ImageFile> modifyImages(Domain domain, IncludingImages entity, List<MultipartFile> newImages) throws IOException {
        List<ImageFile> existingImages = entity.getImageFiles();

        removeSavedFile(domain, entity, entity.getImageFiles());

        entity.initImages();
        imageFileRepository.deleteAll(existingImages);

        return uploadImageFiles(newImages);
    }
}
