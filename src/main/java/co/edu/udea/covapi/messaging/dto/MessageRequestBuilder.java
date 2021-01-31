package co.edu.udea.covapi.messaging.dto;

import co.edu.udea.covapi.messaging.MessagingConstants;

public class MessageRequestBuilder {

    private String title;
    private String message;
    private String topic;
    private String deviceToken;

    public static MessageRequestBuilder builder(){
        return new MessageRequestBuilder();
    }

    private MessageRequestBuilder(){
        this.topic = MessagingConstants.DEFAULT_TOPIC;
        this.title = MessagingConstants.DEFAULT_TITLE;
    }


    public MessageRequestBuilder setMessage(String message) {
        this.message = String.format(MessagingConstants.DEFAULT_MESSAGE, message);
        return this;
    }

    public MessageRequestBuilder setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        return this;
    }

    public MessageRequestDTO build() {
        MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
        messageRequestDTO.setTitle(this.title);
        messageRequestDTO.setMessage(this.message);
        messageRequestDTO.setTopic(this.topic);
        messageRequestDTO.setToken(this.deviceToken);
        return messageRequestDTO;
    }

}
