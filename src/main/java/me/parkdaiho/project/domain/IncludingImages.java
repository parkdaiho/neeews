package me.parkdaiho.project.domain;

import java.util.List;

public interface IncludingImages {

    void addImageFiles(List<ImageFile> imageFiles);
    void initImages();
    List<ImageFile> getImageFiles();
    Long getId();
}
