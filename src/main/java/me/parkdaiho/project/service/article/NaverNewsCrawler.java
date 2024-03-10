package me.parkdaiho.project.service.article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NaverNewsCrawler {

    public String getContents(String link) throws IOException {
        Document document = Jsoup.connect(link)
                .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36")
                .get();

        Element contents = contentsElement(document);

        if(contents != null) {
            contents.getAllElements().not("br").remove();

            return contents.html();
        }

        return null;
    }

    public Element contentsElement(Document document) {
        Element contents = document.getElementById("dic_area");
        if(contents != null) return contents;

        contents = document.getElementById("articeBody");
        if(contents != null) return contents;

        contents = document.getElementById("newsEndContents");
        return contents;
    }
}
