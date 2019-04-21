package vokra.vokraapp.services.websitefetcher;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import vokra.vokraapp.cat.Cat;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

@Service
public class VokraContentFetcher implements WebsiteContentFetcher<List<Cat>>
{

    private static final String LOGIN_URL = "link";
    private static final String AVAILABLE_CATS_URL = "link2";
    private static final String VOKRA_USERNAME = "--";
    private static final String VOKRA_PASSWORD = "---";

    @Override
    public List<Cat> fetch()
    {
        return getListOfCats();
    }

    @SneakyThrows
    private List<Cat> getListOfCats()
    {
        WebClient webClient = new WebClient();
        webClient = vokraLogin(webClient);
        HtmlPage currentPage = webClient.getPage(AVAILABLE_CATS_URL);

        Document document = Jsoup.parse(currentPage.asXml());

        List<Cat> cats = new ArrayList<Cat>();
        for (Element table : document.select("table")) {
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                cats.add(buildCat(tds));
            }
        }
        return cats;
    }

    private Cat buildCat(Elements htmlElement)
    {
        return Cat.builder()
                .id(htmlElement.get(VokraDbColumn.ID.getHierarchy()).text())
                .name(htmlElement.get(VokraDbColumn.NAME.getHierarchy()).text())
                .age(htmlElement.get(VokraDbColumn.AGE.getHierarchy()).text())
                .gender(htmlElement.get(VokraDbColumn.GENDER.getHierarchy()).text())
                .colour(htmlElement.get(VokraDbColumn.COLOUR.getHierarchy()).text())
                .foster(htmlElement.get(VokraDbColumn.FOSTER.getHierarchy()).text())
                .city(htmlElement.get(VokraDbColumn.CITY.getHierarchy()).text())
                .notes(htmlElement.get(VokraDbColumn.NOTES.getHierarchy()).text())
                .createdAt(htmlElement.get(VokraDbColumn.CREATED.getHierarchy()).text())
                .build();
    }

    @SneakyThrows
    private static WebClient vokraLogin(WebClient webClient)
    {
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage currentPage = webClient.getPage(LOGIN_URL);
        HtmlInput username = currentPage.getElementByName("username");
        username.setValueAttribute(VOKRA_USERNAME);
        HtmlInput password = currentPage.getElementByName("password");
        password.setValueAttribute(VOKRA_PASSWORD);
        HtmlSubmitInput submitBtn = currentPage.getElementByName("submit");
        currentPage = submitBtn.click();

        return webClient;
    }
}
