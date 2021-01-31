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
    }

    public MessageRequestBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MessageRequestBuilder setMessage(String message) {
        this.message = message;
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
