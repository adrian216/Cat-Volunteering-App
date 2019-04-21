package vokra.vokraapp.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vokra.vokraapp.cat.Cat;
import vokra.vokraapp.cat.CatRepository;
import vokra.vokraapp.services.websitefetcher.WebsiteContentFetcher;

import java.util.List;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class DatabaseInitializer
{
    CatRepository catRepository;
    WebsiteContentFetcher<List<Cat>> websiteContentFetcher;

    public void initializeDatabase()
    {
        initializeCats();
    }

    private void initializeCats()
    {
        websiteContentFetcher.fetch()
                .forEach(this::supplyDatabase);
        deleteFirstElement();
    }

    private void supplyDatabase(Cat cat)
    {
        catRepository.save(cat);
    }

    private void deleteFirstElement()
    {
        catRepository.deleteById("ID");
    }

}
