package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.service.article.SearchNaverNewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RestController
public class SearchNaverNewsApiController {

    private final SearchNaverNewsService service;

    @PostMapping("/api/naver-news")
    public ResponseEntity<SearchNaverNewsResponse> searchNews(@RequestBody SearchNaverNewsRequest dto)
            throws UnsupportedEncodingException {
        SearchNaverNewsResponse response = service.searchNaverNews(dto);

        return ResponseEntity.ok(response);
    }
}
