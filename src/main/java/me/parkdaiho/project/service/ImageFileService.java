package me.parkdaiho.project.service;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.ImageFileProperties;
import me.parkdaiho.project.domain.ImageFile;
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

    private  final ImageFileProperties imageFileProperties;

    public List<ImageFile> uploadImageFiles(List<MultipartFile> files) throws IOException {
        if(files == null || files.isEmpty()) return null;

        List<ImageFile> images = new ArrayList<>();

        for(MultipartFile file : files) {
            if(file.getSize() == 0) break;

            String originalFileName = file.getOriginalFilename();
            String savedFileName = UUID.randomUUID() + originalFileName;

            images.add(ImageFile.builder()
                    .originalName(originalFileName)
                    .savedName(savedFileName)
                    .build());

            File temp = new File(imageFileProperties.getLocation() + "/temp/" + savedFileName);

            if(!temp.exists()) {
                if(temp.getParentFile().mkdirs()) {
                    temp.createNewFile();
                }
            }

            file.transferTo(temp);
        }

        return images;
    }

    public void moveFileToPostDirectory(List<ImageFile> images, Long savedPostId) throws IOException {
        for(ImageFile image : images) {
            String savedName = image.getSavedName();

            File srcFile = new File(imageFileProperties.getLocation() + "/temp/" + savedName);
            File destinedFolder = new File(imageFileProperties.getLocation() + "/posts/" + savedPostId);

            FileUtils.moveFileToDirectory(srcFile, destinedFolder, true);
        }
    }

    public void removeSourceFile(List<ImageFile> images) {
        for(ImageFile image : images) {
            String imageName = image.getOriginalName();

            File srcFile = new File(imageFileProperties.getLocation() + "/temp/" + imageName);
            srcFile.delete();
        }
    }

    public List<String> getPostImageFilePath(List<ImageFile> images) {
        List<String> filePaths = new ArrayList<>();

        for(ImageFile image : images) {
            String filePath = "/post-image/" + image.getPost().getId() + "/" + image.getSavedName();

            filePaths.add(filePath);
        }

        return filePaths;
    }
}
