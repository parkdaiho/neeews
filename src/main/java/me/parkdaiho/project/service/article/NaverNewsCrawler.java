package me.parkdaiho.project.service.article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NaverNewsCrawler {

    public String getContents(String link) throws IOException {
        Document document = Jsoup.connect(link)
                .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36")
                .get();

        Element contents = document.getElementById("dic_area") != null ? document.getElementById("dic_area") : document.getElementById("newsEndContents");

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

    public Press getPress(String[] originalLinkArr) {
        String domain = null;

        if(originalLinkArr[1].equals("biz")) {
            switch (originalLinkArr[2]) {
                case "sbs":
                    domain = "SBS_BIZ";
                    break;
                case "chosun":
                    domain = "CHOSUN_BIZ";
                    break;
            }
        }

        if(originalLinkArr[1].equals("weekly")) {
            switch (originalLinkArr[2]) {
                case "khan":
                    domain = "WEEKLY_KHAN";
                    break;
                case "donga":
                    domain = "WEEKLY_DONGA";
                    break;
                case "chosun":
                    domain = "WEEKLY_CHOSUN";
                    break;
            }
        }

        if(originalLinkArr[1].equals("magazine")) {
            domain = "MAEGAZINE_HANKYUNG";
        }

        if(originalLinkArr[1].equals("health")) {
            domain = "HEALTH_CHOSUN";
        }

        if(domain == null) {
            domain = originalLinkArr[1];
        }

        return Press.valueOf(domain.toUpperCase());
    }
}
