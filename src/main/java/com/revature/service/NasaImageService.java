package com.revature.service;

import com.revature.dto.ImageCollectionDTO;
import com.revature.models.NasaImage;
import com.revature.repositories.NasaImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NasaImageService {

    private final NasaImageRepo nasa_image_repo;

    @Autowired
    public NasaImageService(NasaImageRepo nasa_image_repo) {
        this.nasa_image_repo = nasa_image_repo;
    }


    public ImageCollectionDTO getCollection() {
        final String url = "https://images-api.nasa.gov/search?q=apollo&media_type=image";
        return WebClient.create(url).get().retrieve().bodyToMono(ImageCollectionDTO.class).blockOptional().orElseThrow(RuntimeException::new);
    }

    //public NasaImage getNasaImage() {

    //}

}
