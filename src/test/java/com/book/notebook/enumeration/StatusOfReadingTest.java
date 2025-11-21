package com.book.notebook.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatusOfReadingTest {

    @Test
    void getStatusWhenIsStatusInProgressTest() {
        // given
        // when
        String result = StatusOfReading.IN_PROGRESS.getStatus();
        // then
        assertEquals("en cours", result);
    }

    @Test
    void getStatusWhenIsStatusDnfTest() {
        // given
        // when
        String result = StatusOfReading.DNF.getStatus();
        // then
        assertEquals("abandonné", result);
    }

    @Test
    void getStatusWhenIsStatusFinishedTest() {
        // given
        // when
        String result = StatusOfReading.FINISHED.getStatus();
        // then
        assertEquals("terminé", result);
    }

    @Test
    void getValueWhenIsStatusInProgressTest() {
        // given
        // when
        StatusOfReading result = StatusOfReading.getValue("en cours");
        // then
        assertEquals(StatusOfReading.IN_PROGRESS, result);
    }

    @Test
    void getValueWhenIsStatusDnfTest() {
        // given
        // when
        StatusOfReading result = StatusOfReading.getValue("abandonné");
        // then
        assertEquals(StatusOfReading.DNF, result);
    }

    @Test
    void getValueWhenIsStatusFinishedTest() {
        // given
        // when
        StatusOfReading result = StatusOfReading.getValue("terminé");
        // then
        assertEquals(StatusOfReading.FINISHED, result);
    }

    @Test
    void getValueWhenIsUnknownStatusTest() {
        // given
        // when
        // then
        assertThrows(IllegalStateException.class, () -> StatusOfReading.getValue("not exist"));
    }
}
