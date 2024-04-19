package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.ImageFileProperties;
import me.parkdaiho.project.domain.Domain;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageFileProperties properties;

    @GetMapping("/image")
    public Resource loadImage(Long id, String domain, String savedFileName) throws MalformedURLException {
        return new UrlResource("file:" + properties.getLocation() + "/" + Domain.getDomainPl(domain) + "/" + id + "/" + savedFileName);
    }
}
