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
}
