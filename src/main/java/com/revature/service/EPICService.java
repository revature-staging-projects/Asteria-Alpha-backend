package com.revature.service;

import com.revature.models.epicImages.EPICImage;
import com.revature.models.epicImages.FavEpicImage;
import com.revature.repositories.epic.EPICRepo;
import com.revature.repositories.epic.FavEPICRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

/**
 * Service layer to handle request concerning EPIC images.
 */
@Service
public class EPICService {

    private final EPICRepo epic_repo;
    private final FavEPICRepo fav_epic_repo;

    @Autowired
    public EPICService(final EPICRepo epic_repo,final FavEPICRepo fav_epic_repo) {
        this.epic_repo      = epic_repo;
        this.fav_epic_repo  = fav_epic_repo;
    }

    public EPICImage getEpicImageMetadata() {
        return epic_repo.findById(1).orElseThrow(RuntimeException::new);
    }

    //once a day refresh the database with current metadata form teh EPIC API.
    @Scheduled(fixedRate = 86400000)
    public void getDailyImage() {
        final String url = "https://epic.gsfc.nasa.gov/api/natural";
        final EPICImage[] dto = WebClient.create(url)
                .get()
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(EPICImage[].class)
                .blockOptional().orElseThrow(RuntimeException::new);
        epic_repo.truncateDB();
        epic_repo.resetCounter();
        epic_repo.save(dto[0]);
    }

    private boolean checkIfPresent(final EPICImage image) {
        return !fav_epic_repo.findByImage(image.getImage_title()).isEmpty();
    }

    private FavEpicImage parseIntoFav(final EPICImage image) {
        final FavEpicImage fav = new FavEpicImage();
        fav.setCaption(image.getCaption());
        fav.setImage_title(image.getImage_title());
        fav.setImage_date(image.getImage_date());
        return fav;
    }

    public void addEPICImageToFavorites(final String username) {
        final Optional<EPICImage> image = epic_repo.findById(1);
        if(!checkIfPresent(image.orElseThrow(RuntimeException::new))) {
            fav_epic_repo.save(parseIntoFav(image.get()));
        }
        fav_epic_repo.updateRefTable(username);
    }

    public List<FavEpicImage> getFavImages(final String username) {
        return fav_epic_repo.getUserFavoriteImages(username);
    }

}
