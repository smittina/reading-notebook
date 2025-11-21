package com.book.notebook.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeOfReadingTest {

    @Test
    void getTypeWhenIsHardbackTest() {
        // given
        // when
        String result = TypeOfReading.HARDBACK.getType();
        // then
        assertEquals("relié", result);
    }

    @Test
    void getTypeWhenIsPaperbackTest() {
        // given
        // when
        String result = TypeOfReading.PAPERBACK.getType();
        // then
        assertEquals("broché", result);
    }

    @Test
    void getTypeWhenIsAudioBookTest() {
        // given
        // when
        String result = TypeOfReading.AUDIO.getType();
        // then
        assertEquals("livre audio", result);
    }

    @Test
    void getTypeWhenIsEbookTest() {
        // given
        // when
        String result = TypeOfReading.EBOOK.getType();
        // then
        assertEquals("e-book", result);
    }

    @Test
    void getValueWhenIsHardbackTest() {
        // given
        // when
        TypeOfReading result = TypeOfReading.getValue("relié");
        // then
        assertEquals(TypeOfReading.HARDBACK, result);
    }

    @Test
    void getValueWhenIsPaperbackTest() {
        // given
        // when
        TypeOfReading result = TypeOfReading.getValue("broché");
        // then
        assertEquals(TypeOfReading.PAPERBACK, result);
    }

    @Test
    void getValueWhenIsAudioTest() {
        // given
        // when
        TypeOfReading result = TypeOfReading.getValue("livre audio");
        // then
        assertEquals(TypeOfReading.AUDIO, result);
    }

    @Test
    void getValueWhenIsEbookTest() {
        // given
        // when
        TypeOfReading result = TypeOfReading.getValue("e-book");
        // then
        assertEquals(TypeOfReading.EBOOK, result);
    }

    @Test
    void getValueWhenIsUnknownTypeTest() {
        // given
        // when
        // then
        assertThrows(IllegalStateException.class, () -> TypeOfReading.getValue("unknown type"));
    }
}
