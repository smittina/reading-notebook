package com.book.notebook.enumeration;

public enum StatusOfReading {
    IN_PROGRESS("en cours"),
    DNF("abandonné"),
    FINISHED("terminé");

    private String status;

    StatusOfReading(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static StatusOfReading getValue(String status) {
        return switch (status) {
            case "en cours" -> IN_PROGRESS;
            case "abandonné" -> DNF;
            case "terminé" -> FINISHED;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
    }
}
