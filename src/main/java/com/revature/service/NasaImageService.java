package com.revature.service;

import com.revature.dto.nasaImages.ImageItems;
import com.revature.dto.nasaImages.NasaImageDTO;
import com.revature.models.nasaImages.FavNasaImage;
import com.revature.models.nasaImages.NasaImage;
import com.revature.repositories.nasaImages.FavoriteImageRepo;
import com.revature.repositories.nasaImages.NasaImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;


/**
 * Service layer which handles request concerning NASA images.
 */
@Service
@EnableScheduling
public class NasaImageService {

    private final NasaImageRepo nasa_image_repo;
    private final FavoriteImageRepo fav_image_repo;
    private int count = 0;
    private int old_count = 0;
    //search terms to search the NASA API for.  represents image categories.
    private final List<String> search_terms = Collections.unmodifiableList(Arrays.asList
            (
                "apollo","gemini","space","planets","rocket","space station",
                "solar system","satellites","galaxies","space shuttle","comet",
                "Mercury Project","nebula","rover","soyuz","Lunar Module"
            ));

    //Keywords to filter out with images retrieved via the NASA API.
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

    //if image contains any of the keywords in the filter list then return true so it can be discarded.
    private boolean checkContainsKeyword(final List<String> keywords) {
        if(keywords != null) {
            //if keywords is an array, then use it as such, if it is a single string then split by comma
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

    //parse the DTO into a List of NASA image objects.
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
            return Collections.unmodifiableList(images);
    }

    //get a random search term which doesn't match the previous search terms used.
    private String getRandSearchTerm(final List<Integer> prev_i) {
        int i;
        do {
            i = rand.nextInt(search_terms.size());
        }while(prev_i.contains(i));
        prev_i.add(i);
        return search_terms.get(i);
    }

    private List<NasaImage> getListOfImages(final List<Integer> prev_i) {
        final String search_term = getRandSearchTerm(prev_i);
        final String url = "https://images-api.nasa.gov/search?q=" + search_term + "&media_type=image&page=15";
        final NasaImageDTO dto = WebClient.create(url).get().retrieve().bodyToMono(NasaImageDTO.class).blockOptional().orElseThrow(RuntimeException::new);
        final List<NasaImage> images = parseImageDTOIntoNasaImageObjectList(dto);
        if(images.size() > 10) {
            old_count = count;
            return images;
        }
        //if less than ten results then start over.
        else {
            count = old_count;
            return getListOfImages(prev_i);
        }
    }

    //once a day refresh the database with new images from the NASA API.
   @Scheduled(fixedRate = 86400000)
    public void setCollection() {
        nasa_image_repo.resetCounter();
        nasa_image_repo.truncateDB();
        count = 0;
        old_count = 0;
        final List<Integer> prev_i = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            nasa_image_repo.saveAll(getListOfImages(prev_i));
        }
    }

    public NasaImage getImage() {
        return nasa_image_repo.findById(rand.nextInt( count - 1) + 1).orElseThrow(RuntimeException::new);
    }

    private boolean checkForImageFavorite(final String url) {
        return fav_image_repo.findByUrl(url).size() > 0;
    }

    public void addImageToFavorites(final String username,final String url) {
        if(!checkForImageFavorite(url)) {
            fav_image_repo.addImageToFav(url);
        }
        fav_image_repo.updateFavoriteImageReferences(username,url);
    }

    public List<FavNasaImage> getFavImage(final String username) {
        return Collections.unmodifiableList(fav_image_repo.getUserFavoriteImages(username));
    }

}