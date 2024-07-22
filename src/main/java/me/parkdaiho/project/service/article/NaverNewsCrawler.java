package me.parkdaiho.project.service.article;

import net.minidev.json.JSONUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NaverNewsCrawler {

    public String getText(String link) throws IOException {
        Document document = getDocument(link);

        Element textElement = getTextElement(document);

        if(textElement != null) {
            textElement.getAllElements()
                    .not("p")
                    .not("br")
                    .not("span")
                    .remove();

            return textElement.html();
        }

        return null;
    }

    public String getImgSrc(String link) throws IOException {
        Document document = getDocument(link);

        Element imgElement = getImgElement(document);

        if(imgElement == null) {
            return null;
        }

        String imgSrc = imgElement.attr("data-src");
        if(!imgSrc.isBlank()) return imgSrc;

        imgSrc = imgElement.attr("src");

        return imgSrc;
    }

    private Element getTextElement(Document document) {
        Element textElement = document.getElementById("dic_area");
        if(textElement != null) return textElement;

        textElement = document.getElementById("articeBody");
        if(textElement != null) return textElement;

        textElement = document.getElementById("newsEndContents");
        if(textElement != null) return textElement;

        textElement = document.getElementById("ijam_content");

        return textElement;
    }

    private Element getImgElement(Document document) {
        Element img = document.getElementById("img1");

        return img;
    }

    private Document getDocument(String link) throws IOException {
        return Jsoup.connect(link)
                .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36")
                .get();
    }
}
