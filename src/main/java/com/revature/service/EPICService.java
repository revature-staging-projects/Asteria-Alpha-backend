package com.revature.service;

import com.revature.models.EPICImage;
import com.revature.models.FavEpicImage;
import com.revature.repositories.EPICRepo;
import com.revature.repositories.FavEPICRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EPICService {

    private final EPICRepo epic_repo;
    private final FavEPICRepo fav_epic_repo;

    @Autowired
    public EPICService(final EPICRepo epic_repo,FavEPICRepo fav_epic_repo) {
        this.epic_repo = epic_repo;
        this.fav_epic_repo = fav_epic_repo;
    }

    public EPICImage getEpicImageMetadata() {
        return epic_repo.findById(1).orElseThrow(RuntimeException::new);
    }

    @Scheduled(fixedRate = 86400000)
    private void getDailyImage() {
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


    private FavEpicImage parseEpicImageIntoFav(final EPICImage image) {
        final FavEpicImage fav_image = new FavEpicImage();
        fav_image.setImage_date(image.getImage_date());
        fav_image.setImage_title(image.getImage_title());
        fav_image.setCaption(image.getCaption());
        return fav_image;
    }

    public void addEPICImageToFavorites(final EPICImage image) {
        List<FavEpicImage> db_images = fav_epic_repo.findByTitle(image.getImage_title());
        if(db_images == null || db_images.size() < 1) {
            fav_epic_repo.save(parseEpicImageIntoFav(image));
            db_images = fav_epic_repo.findByTitle(image.getImage_title());
        }
        fav_epic_repo.updateRefTable(1,db_images.get(0).getId());
    }

    public List<FavEpicImage> getFavImages(final int user) {
        return fav_epic_repo.getUserFavoriteImages(user);
    }

}
