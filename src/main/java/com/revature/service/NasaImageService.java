package com.revature.service;

import com.revature.dto.ImageItems;
import com.revature.dto.NasaImageDTO;
import com.revature.models.FavNasaImage;
import com.revature.models.NasaImage;
import com.revature.repositories.FavoriteImageRepo;
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
    private final FavoriteImageRepo fav_image_repo;
    private int count = 0;
    private int old_count = 0;
    private final String[] search_terms = {"Spectators","apollo","gemini","space","planets","solar system","satellites","galaxies","space shuttle"};
    private final List<String> filter_terms = Arrays.asList(
            "groundbreaking","induction","hall of fame","stem","STEM","Inductee","teacher training",
            "ceremony","Kennedy Center for the Performing Arts","CEREMONIES","Apollo 40th Anniversary","U.S. Senator","U.S. Congresswoman","U.S. Congressman");
    private final Random rand = new Random();

    @Autowired
    public NasaImageService(final NasaImageRepo nasa_image_repo, final FavoriteImageRepo fav_image_repo) {
        this.nasa_image_repo = nasa_image_repo;
        this.fav_image_repo = fav_image_repo;
    }



    private boolean checkContainsKeyword(final List<String> keywords) {
        if(keywords != null) {
             return keywords.parallelStream().anyMatch(filter_terms::contains);
        }
        return false;
    }

    private boolean checkString(final String str) {
        return str != null && !"".equals(str.trim());
    }

    private boolean checkFieldsInImage(final NasaImage image) {
        return checkString(image.getDescription()) && checkString(image.getTitle()) && checkString(image.getLink());
    }

    private List<NasaImage> parseImageDTOIntoNasaImageObjectList(final NasaImageDTO dto) {
        final List<NasaImage> images = new LinkedList<>();
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

    private List<NasaImage> getListOfImages() {
        final String search_term = search_terms[rand.nextInt(search_terms.length)];
        System.out.println("\n\n\n==================\n\n\n Search term is: " + search_term);
        final String url = "https://images-api.nasa.gov/search?q=" + search_term + "&media_type=image&page=10";
        final NasaImageDTO dto = WebClient.create(url).get().retrieve().bodyToMono(NasaImageDTO.class).blockOptional().orElseThrow(RuntimeException::new);
        final List<NasaImage> images = parseImageDTOIntoNasaImageObjectList(dto);
        if(images.size() > 10) {
            old_count = count;
            return images;
        }
        else {
            count = old_count;
            return getListOfImages();
        }

    }

   @Scheduled(fixedRate = 86400000)
    private void setCollection() {
        nasa_image_repo.resetCounter();
        nasa_image_repo.truncateDB();
        count = 0;
        old_count = 0;
        for (int i = 0; i < 2; i++) {
            nasa_image_repo.saveAll(getListOfImages());
        }
    }

    public NasaImage getImage() {
        return nasa_image_repo.findById(rand.nextInt( count - 1) + 1).orElseThrow(RuntimeException::new);
    }


    public void addImageToFavorites(final int user_id,final int img_id,final String url) {
        List<FavNasaImage> images = fav_image_repo.findByUrl(url);
        if(images == null || images.size() == 0) {
            final NasaImage image = nasa_image_repo.findById(img_id).orElseThrow(RuntimeException::new);
            final FavNasaImage fav_img = convertNasaToFav(image);
            fav_image_repo.save(fav_img);
            images = fav_image_repo.findByUrl(url);
        }
        fav_image_repo.updateFavoriteImageReferences(user_id,images.get(0).getId());
    }

    private FavNasaImage convertNasaToFav(final NasaImage image) {
        final FavNasaImage fav = new FavNasaImage();
        fav.setDescription(image.getDescription());
        fav.setLink(image.getLink());
        fav.setTitle(image.getTitle());
        return fav;
    }

}