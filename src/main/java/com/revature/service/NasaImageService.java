package com.revature.service;

import com.revature.dto.ImageItems;
import com.revature.dto.NasaImageDTO;
import com.revature.models.NasaImage;
import com.revature.repositories.NasaImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


@Service
@EnableScheduling
public class NasaImageService {

    private final NasaImageRepo nasa_image_repo;
    private int count = 0;
    private final String[] search_terms = {"apollo","gemini","space","planets","solar system","satellites","galaxies"};
    private final String[] filter_terms = {"induction","hall of fame","stem","STEM","Inductee","teacher training","ceremony","Kennedy Center for the Performing Arts","CEREMONIES","Apollo 40th Anniversary","U.S. Senator"};
    private final Random rand = new Random();

    @Autowired
    public NasaImageService(NasaImageRepo nasa_image_repo) {
        this.nasa_image_repo = nasa_image_repo;
    }



    private boolean checkContainsKeyword(final List<String> keywords) {
        boolean checked = false;
        if(keywords != null) {
            checked = Arrays.stream(filter_terms).anyMatch(keywords::contains);
        }
        return checked;
    }

    private boolean checkString(final String str) {
        return str != null && !"".equals(str.trim());
    }

    private boolean checkFieldsInImage(final NasaImage image) {
        return checkString(image.getDescription()) && checkString(image.getTitle()) && checkString(image.getLink());
    }

    private List<NasaImage> parseImageDTOIntoNasaImageObjectList(final NasaImageDTO dto) {
        final List<NasaImage> images = new LinkedList<>();
        count = 0;
        for(ImageItems item: dto.getCollection().getItems()) {
            if(!checkContainsKeyword(item.getData().get(0).getKeywords())) {
                final NasaImage image = new NasaImage();
                image.setLink(item.getLinks().get(0).getHref());
                image.setDescription(item.getData().get(0).getDescription());
                image.setTitle(item.getData().get(0).getTitle());
                if (checkFieldsInImage(image)) {
                    images.add(image);
                    count++;
                }
            }
        }
            return images;
    }

   @Scheduled(fixedRate = 259200000)
    public void setCollection() {
        final String search_term = search_terms[rand.nextInt(search_terms.length)];
        final String url = "https://images-api.nasa.gov/search?q=" + search_term + "&media_type=image&page=10";
        final NasaImageDTO dto = WebClient.create(url).get().retrieve().bodyToMono(NasaImageDTO.class).blockOptional().orElseThrow(RuntimeException::new);
        nasa_image_repo.resetCounter();
        nasa_image_repo.truncateDB();
        nasa_image_repo.saveAll(parseImageDTOIntoNasaImageObjectList(dto));
    }

    public NasaImage getImage() {
        return nasa_image_repo.findById(rand.nextInt( count - 1) + 1).orElseThrow(RuntimeException::new);
    }

}