package co.edu.udea.covapi.messaging.service;

public enum NotificationColorEnum {

    DEFAULT("#FFFF00");

    private String value;

    NotificationColorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
