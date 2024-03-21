package me.parkdaiho.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.ImageFileProperties;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.board.Post;
import me.parkdaiho.project.repository.ImageFileRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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

    public void moveFileToPostDirectory(List<ImageFile> images, Long savedPostId) throws IOException {
        for (ImageFile image : images) {
            String savedName = image.getSavedName();

            File srcFile = new File(imageFileProperties.getLocation() + "/temp/" + savedName);
            File destinedFolder = new File(imageFileProperties.getLocation() + "/posts/" + savedPostId);

            FileUtils.moveFileToDirectory(srcFile, destinedFolder, true);
        }
    }

    public void removeSourceFile(List<ImageFile> images) {
        for (ImageFile image : images) {
            String imageName = image.getOriginalName();

            File srcFile = new File(imageFileProperties.getLocation() + "/temp/" + imageName);
            srcFile.delete();
        }
    }

    public void removeSavedFile(Post post, List<ImageFile> images) {
        for (ImageFile image : images) {
            String savedName = image.getSavedName();

            File savedFile = new File(imageFileProperties.getLocation() + "/posts/" + post.getId() + "/" + savedName);
            savedFile.delete();
        }
    }

    public List<String> getPostSavedFileName(List<ImageFile> images) {
        return images.stream()
                .map(image -> image.getSavedName())
                .toList();
    }

    public List<ImageFile> modifyImages(Post post, List<MultipartFile> newImages) throws IOException {
        List<ImageFile> existingImages = post.getImages();

        removeSavedFile(post, post.getImages());

        post.initImages();
        imageFileRepository.deleteAll(existingImages);

        return uploadImageFiles(newImages);
    }
}
