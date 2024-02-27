package me.parkdaiho.project.service.article;

import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

public interface SearchNaverNewsService {

    public static final String BASE_URL = "https://openapi.naver.com";
    public static final String PATH = "/v1/search/news.json";

    public SearchNaverNewsResponse searchNaverNews(SearchNaverNewsRequest dto) throws UnsupportedEncodingException;
}
