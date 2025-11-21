package com.book.notebook.enumeration;

public enum TypeOfReading {
    HARDBACK("relié"),
    PAPERBACK("broché"),
    AUDIO("livre audio"),
    EBOOK ("e-book");

    private String type;

    TypeOfReading(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static TypeOfReading getValue(String type) {
        return switch (type) {
            case "relié" -> HARDBACK;
            case "broché" -> PAPERBACK;
            case "livre audio" -> AUDIO;
            case "e-book" -> EBOOK;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
