package me.parkdaiho.project.service.article;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.PaginationProperties;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.Order;
import me.parkdaiho.project.domain.Article;
import me.parkdaiho.project.dto.IndexViewResponse;
import me.parkdaiho.project.dto.article.*;
import me.parkdaiho.project.repository.ArticleRepository;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final NaverNewsCrawler naverNewsCrawler;

    private final PaginationProperties paginationProperties;

    public SearchNaverNewsResponse getSearchNewsResult(SearchedArticlesRequest dto) {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/api/naver-news") // SearchNaverNewsApiController
                .build().toUri();

        if (dto.getPage() == null) dto.setPage(1);
        int start = (dto.getPage() - 1) * paginationProperties.getNewsItemsPerPage() + 1;

        RequestEntity<SearchNaverNewsRequest> request = RequestEntity.post(uri)
                .body(SearchNaverNewsRequest.builder()
                        .query(dto.getQuery())
                        .sort(dto.getSort())
                        .start(start).build());

        ResponseEntity<SearchNaverNewsResponse> response = restTemplate.exchange(request, SearchNaverNewsResponse.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("fait to search news");
        }

        return response.getBody();
    }

    private Article findArticleByLink(ArticleViewRequest dto) throws IOException {
        Article article = articleRepository.findByLink(dto.getLink())
                .orElse(dto.toEntity());

        if (article.getId() != null) {
            return article;
        }

        if (!article.getLink().equals(article.getOriginalLink())) {
            setArticleContents(article);
        }

        return articleRepository.save(article);
    }

    private void setArticleContents(Article article) throws IOException {
        String text = naverNewsCrawler.getText(article.getLink());
        String imgLink = naverNewsCrawler.getImgSrc(article.getLink());

        article.setText(text);
        article.setImgSrc(imgLink);
    }

    public Long getArticleId(ArticleViewRequest dto) throws IOException {
        Article article = findArticleByLink(dto);

        return article.getId();
    }

    @Transactional
    public ArticleViewResponse getArticleView(Long id, HttpServletRequest request, HttpServletResponse response) {
        Article article = findArticleById(id);

        if (!CookieUtils.checkViewed(request, response, Domain.ARTICLE, id)) article.addViews();

        return new ArticleViewResponse(article);
    }

    public Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected articleId: " + id));
    }

    public List<IndexViewResponse> getArticlesForIndex(Order order) {
        Pageable pageable = getPageable(paginationProperties.getIndexViews(), 1, order);
        Page<Article> articles = articleRepository.findAll(pageable);

        switch (order) {
            case POPULARITY -> {
                return articles.stream()
                        .map(entity -> IndexViewResponse.builder()
                                .title(entity.getTitle())
                                .link("/" + Domain.ARTICLE.getDomainPl() + "/" + entity.getId())
                                .figure(entity.getGood())
                                .build()).toList();
            }
            case VIEWS -> {
                return articles.stream()
                        .map(entity -> IndexViewResponse.builder()
                                .title(entity.getTitle())
                                .link("/" + Domain.ARTICLE.getDomainPl() + "/" + entity.getId())
                                .figure(entity.getViews())
                                .build()).toList();
            }

            default -> throw new IllegalArgumentException("Unexpected order: " + order.getValue());
        }
    }

    private Pageable getPageable(int size, int page, Order order) {
        org.springframework.data.domain.Sort pageableSort = null;
        switch (order) {
            case LATEST, POPULARITY, VIEWS -> pageableSort = org.springframework.data.domain.Sort.by(
                    org.springframework.data.domain.Sort.Direction.DESC, order.getProperty());
            case EARLIEST -> pageableSort = org.springframework.data.domain.Sort.by(
                    org.springframework.data.domain.Sort.Direction.ASC, order.getProperty());

            default -> throw new IllegalArgumentException("Unexpected order:" + order.getValue());
        }

        return PageRequest.of(page - 1, size, pageableSort);
    }

    public void addSearchedNewsResponseToModel(SearchNaverNewsResponse response, Model model) {
        int page = response.getStart() / response.getDisplay() + 1;
        int totalPages = response.getTotal() / response.getDisplay() + 1;
        if (totalPages > 100) totalPages = 100;
        int pageBlock = (page - 1) / response.getDisplay();
        int startNumOfPageBlock = pageBlock * paginationProperties.getNewsPagesPerBlock() + 1;
        int lastNumOfPageBLock = startNumOfPageBlock + paginationProperties.getNewsPagesPerBlock() - 1;
        if (lastNumOfPageBLock > totalPages) lastNumOfPageBLock = totalPages;

        int nextPage = page == totalPages ? page : page + 1;
        int previousPage = page == 1 ? page : page - 1;

        model.addAttribute(paginationProperties.getPageName(), page);
        model.addAttribute(paginationProperties.getTotalPagesName(), totalPages);
        model.addAttribute(paginationProperties.getTotalElementsName(), response.getTotal());
        model.addAttribute(paginationProperties.getStartNumOfPageBlockName(), startNumOfPageBlock);
        model.addAttribute(paginationProperties.getLastNumOfPageBlockName(), lastNumOfPageBLock);
        model.addAttribute(paginationProperties.getNextPageName(), nextPage);
        model.addAttribute(paginationProperties.getPreviousPageName(), previousPage);
        model.addAttribute("items", response.getItems());
    }

    public void addArticleToModel(ArticleViewResponse article, Model model) {
        model.addAttribute("id", article.getId());
        model.addAttribute("title", article.getTitle());
        model.addAttribute("text", article.getText());
        model.addAttribute("pubDate", article.getPubDate());
        model.addAttribute("views", article.getViews());
        model.addAttribute("isProvided", article.getIsProvided());
        model.addAttribute("originalLink", article.getOriginalLink());
        model.addAttribute("imgSrc", article.getImgSrc());
        model.addAttribute("link", article.getLink());
    }
}
