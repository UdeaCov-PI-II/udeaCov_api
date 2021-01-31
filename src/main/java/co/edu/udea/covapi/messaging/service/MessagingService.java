package co.edu.udea.covapi.messaging.service;

import co.edu.udea.covapi.messaging.dto.MessageRequestDTO;

public interface MessagingService {

    public void sendMessageToDevice(MessageRequestDTO request);

}
