package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.ImageFileProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageFileProperties properties;

    @GetMapping("/post-image")
    public Resource loadImage(Long id, String savedName) throws MalformedURLException {
        return new UrlResource("file:" + properties.getLocation() + "/posts/" + id + "/" + savedName);
    }
}
