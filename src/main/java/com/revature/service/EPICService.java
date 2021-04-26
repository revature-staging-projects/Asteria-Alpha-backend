package com.revature.service;

import com.revature.dto.EPICMetadata;
import com.revature.models.EPICImage;
import com.revature.repositories.EPICRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EPICService {

    private final EPICRepo epic_repo;

    @Autowired
    public EPICService(final EPICRepo epic_repo) {
        this.epic_repo = epic_repo;
    }

    private EPICImage parseDTO(final EPICMetadata dto) {
        final EPICImage image = new EPICImage();
        image.setCaption(dto.getImages_metadata().get(0).getCaption());
        image.setImage_title(dto.getImages_metadata().get(0).getImage_title());
        final String date = (dto.getImages_metadata().get(0).getImage_date().replaceAll("-","/");
        image.setImage_date(date.substring(0,date.indexOf(" ")));
        return image;
    }

    private void getDailyImage() {
        final String url = "https://epic.gsfc.nasa.gov/api/natural/";
        final EPICMetadata dto = WebClient.create(url).get().retrieve().bodyToMono(EPICMetadata.class).blockOptional().orElseThrow(RuntimeException::new);
        epic_repo.save(parseDTO(dto));
    }

}
