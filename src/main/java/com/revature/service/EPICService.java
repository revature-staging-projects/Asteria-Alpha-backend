package com.revature.service;

import com.revature.models.EPICImage;
import com.revature.repositories.EPICRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Service
public class EPICService {

    private final EPICRepo epic_repo;

    @Autowired
    public EPICService(final EPICRepo epic_repo) {
        this.epic_repo = epic_repo;
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

}
