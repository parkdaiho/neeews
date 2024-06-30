package me.parkdaiho.project.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Getter
@Setter
@Component
@ConfigurationProperties("pagination")
public class PaginationProperties {

    private Integer usersPerPage;
    private Integer userPagesPerBlock;

    private Integer postsPerPage;
    private Integer postPagesPerBlock;

    private Integer noticePerPage;
    private Integer fixedNoticePerPage;
    private Integer noticePagesPerBlock;

    private Integer commentsPerPage;
    private Integer commentPagesPerBlock;

    private Integer indexViews;

    private Integer newsItemsPerPage;
    private Integer newsPagesPerBlock;

    private Integer clippingsPerPage;
    private Integer clippingsPagesPerBlock;

    private Integer myPostsPerPage;
    private Integer myPostsPagesPerBlock;

    private Integer myCommentsPerPage;
    private Integer myCommentsPagesPerBlock;

    private String pageName;
    private String totalPagesName;
    private String totalElementsName;
    private String startNumOfPageBlockName;
    private String lastNumOfPageBlockName;
    private String nextPageName;
    private String previousPageName;

    private Integer articleListInArticles;

    public void addPaginationAttributesToModel(Page items, Model model, int pageBlockSize) {
        int page = items.getNumber() + 1;
        int totalPages = items.getTotalPages();
        int pageBlock = (page - 1) / pageBlockSize;
        int startNumOfPageBlock = pageBlock * pageBlockSize + 1;
        int lastNumOfPageBlock = startNumOfPageBlock + pageBlockSize - 1;
        if(lastNumOfPageBlock > totalPages) lastNumOfPageBlock = totalPages;

        int nextPage = items.hasNext() ? page + 1 : page;
        int previousPage = items.hasPrevious() ? page - 1 : page;

        model.addAttribute(pageName, page);
        model.addAttribute(totalElementsName, items.getTotalElements());
        model.addAttribute(totalPagesName, totalPages);
        model.addAttribute(startNumOfPageBlockName, startNumOfPageBlock);
        model.addAttribute(lastNumOfPageBlockName, lastNumOfPageBlock);
        model.addAttribute(nextPageName, nextPage);
        model.addAttribute(previousPageName, previousPage);
    }
}
