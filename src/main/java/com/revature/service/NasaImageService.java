package com.revature.service;

import com.revature.dto.ImageItems;
import com.revature.dto.NasaImageDTO;
import com.revature.models.nasaImages.FavNasaImage;
import com.revature.models.nasaImages.NasaImage;
import com.revature.repositories.FavoriteImageRepo;
import com.revature.repositories.NasaImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;


@Service
@EnableScheduling
public class NasaImageService {

    private final NasaImageRepo nasa_image_repo;
    private final FavoriteImageRepo fav_image_repo;
    private int count = 0;
    private int old_count = 0;
    private int prev_i;
    private final List<String> search_terms = Collections.unmodifiableList(Arrays.asList
            (
                "apollo","gemini","space","planets","rocket",
                "solar system","satellites","galaxies","space shuttle"
            ));

    private final List<String> filter_terms = Collections.unmodifiableList(Arrays.asList
            (
                "groundbreaking","induction","hall of fame","stem","STEM","Inductee","teacher training",
                "ceremony","Kennedy Center for the Performing Arts","CEREMONIES","Apollo 40th Anniversary","U.S. Senator",
                "U.S. Congresswoman","U.S. Congressman","NASA Administrator","Space Symposium","All Hands Meeting",
                "U.S. Vice President","Spectators","50th anniversary","Apollo 11 50th Anniversary", "National Symphony Orchestra",
                "VIP Visits","Vice President Mike Pence","ACCLLP"
            ));

    private final Random rand = new Random();

    @Autowired
    public NasaImageService(final NasaImageRepo nasa_image_repo, final FavoriteImageRepo fav_image_repo) {
        this.nasa_image_repo = nasa_image_repo;
        this.fav_image_repo = fav_image_repo;
    }



    private boolean checkContainsKeyword(final List<String> keywords) {
        if(keywords != null) {
             return ((keywords.size() == 1)? Arrays.asList(keywords.get(0).split(",")) : keywords)
                     .parallelStream().anyMatch(filter_terms::contains);
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

    private String getRandSearchTerm() {
        int i;
        do {
            i = rand.nextInt(search_terms.size());
        }while(i == prev_i);
        prev_i = i;
        return search_terms.get(i);
    }

    private List<NasaImage> getListOfImages() {
        final String search_term = getRandSearchTerm();
        final String url = "https://images-api.nasa.gov/search?q=" + search_term + "&media_type=image&page=15";
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
    public void setCollection() {
//        nasa_image_repo.resetCounter();
//        nasa_image_repo.truncateDB();
//        count = 0;
//        old_count = 0;
//        prev_i = -1;
//        for (int i = 0; i < 2; i++) {
//            nasa_image_repo.saveAll(getListOfImages());
//        }
    }

    public NasaImage getImage() {
        return nasa_image_repo.findById(rand.nextInt( count - 1) + 1).orElseThrow(RuntimeException::new);
    }


    private boolean checkForImageFavorite(final String url) {
        List<FavNasaImage> favorites = fav_image_repo.findByUrl(url);
        return favorites != null && favorites.size() > 0;
    }

    public void addImageToFavorites(final String username,final String url) {
        if(!checkForImageFavorite(url)) {
            fav_image_repo.addImageToFav(url);
        }
        fav_image_repo.updateFavoriteImageReferences(username,url);
    }

    public List<FavNasaImage> getFavImage(final String username) {
        return fav_image_repo.getUserFavoriteImages(username);
    }

}