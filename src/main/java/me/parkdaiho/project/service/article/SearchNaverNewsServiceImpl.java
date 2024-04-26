package me.parkdaiho.project.service.article;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.NaverSearchProperties;
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
public class SearchNaverNewsServiceImpl implements SearchNaverNewsService {

    private final NaverSearchProperties naverSearchProperties;

    @Override
    public SearchNaverNewsResponse searchNaverNews(SearchNaverNewsRequest dto) {
        if (dto.getQuery() == null || dto.getQuery().isBlank()
                || dto.getSort() == null || dto.getSort().isBlank()
                || dto.getStart() == null) {
            return null;
        }

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", dto.getQuery());
        params.add("sort", dto.getSort());
        params.add("start", String.valueOf(dto.getStart()));
        params.add("display", naverSearchProperties.getDisplay());

        URI uri = UriComponentsBuilder
                .fromUriString(naverSearchProperties.getBaseUrl())
                .path(naverSearchProperties.getNewsSearchPath())
                .queryParams(params)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add(naverSearchProperties.getClientIdHeaderName(), naverSearchProperties.getClientId());
        headers.add(naverSearchProperties.getClientSecretHeaderName(), naverSearchProperties.getClientSecret());

        RequestEntity<Void> request = RequestEntity
                .get(uri)
                .headers(headers)
                .build();

        return restTemplate.exchange(request, SearchNaverNewsResponse.class).getBody();
    }
}
