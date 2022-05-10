package com.profeco.events;

import com.profeco.dto.InconsistencyDTO;
import com.profeco.entities.Inconsistency;
import com.profeco.MQConfig;
import com.profeco.service.inconsistency.InconsistencyService;
import com.profeco.utils.HttpDownloadUtility;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InconsistencyCreatedListener {

    @Autowired
    private InconsistencyService inconsistencyService;

    @RabbitListener(queues = MQConfig.NEW_INCONSISTENCY_QUEUE)
    public void listener(InconsistencyDTO inconsistencyDTO) {
        Inconsistency inconsistency = inconsistencyDTO.convertToBO();
        System.out.println(inconsistency.getEvidence());
        if (inconsistency.getEvidence() == ""){
            inconsistencyService.createInconsistency(inconsistency);
            return;
        }


        String fileURL = "http://localhost:8091/files/"+ inconsistency.getEvidence();
        String saveDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "upload-dir";

        try {
            String fileName = HttpDownloadUtility.downloadFile(fileURL, saveDir);
            inconsistency.setEvidence("http://localhost:8091/files/" +fileName);
            inconsistencyService.createInconsistency(inconsistency);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
