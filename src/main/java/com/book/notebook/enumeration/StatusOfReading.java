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
}
