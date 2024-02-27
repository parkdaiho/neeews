package me.parkdaiho.project.service.article;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.SearchNaverNewsProperties;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class SearchNaverNewsServiceImpl implements SearchNaverNewsService{

    private final SearchNaverNewsProperties properties;

    @Override
    public SearchNaverNewsResponse searchNaverNews(SearchNaverNewsRequest dto) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", dto.getQuery());
        params.add("sort", dto.getSort());

        URI uri = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .path(PATH)
                .queryParams(params)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", properties.getClientId());
        headers.add("X-Naver-Client-Secret", properties.getClientSecret());

        RequestEntity<Void> request = RequestEntity
                .get(uri)
                .headers(headers)
                .build();

        return restTemplate.exchange(request, SearchNaverNewsResponse.class).getBody();
    }
}
