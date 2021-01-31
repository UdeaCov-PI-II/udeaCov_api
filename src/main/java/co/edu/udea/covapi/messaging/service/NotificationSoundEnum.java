package co.edu.udea.covapi.messaging.service;

public enum  NotificationSoundEnum {

    DEFAULT("default");

    private String value;

    NotificationSoundEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
