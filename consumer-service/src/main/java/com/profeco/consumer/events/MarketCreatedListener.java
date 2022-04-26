package com.profeco.consumer.events;

import com.profeco.consumer.dto.MarketDTO;
import com.profeco.consumer.queues.MarketQueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;

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
