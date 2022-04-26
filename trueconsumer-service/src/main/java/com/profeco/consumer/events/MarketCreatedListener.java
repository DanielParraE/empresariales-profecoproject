package com.profeco.consumer.events;

import com.profeco.consumer.dto.MarketDTO;
import com.profeco.consumer.queues.MarketQueueConfig;
import com.profeco.consumer.utils.HttpDownloadUtility;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MarketCreatedListener {

    @RabbitListener(queues = MarketQueueConfig.QUEUE)
    public void listener(MarketDTO marketDTO) {
        if (marketDTO.getImage() == "abc-market.png") return;

        String fileURL = "http://localhost:8092/files/"+ marketDTO.getImage();
        String saveDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "upload-dir";

        try {
            HttpDownloadUtility.downloadFile(fileURL, saveDir);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
