package alexnanrick.dexcomservice.reading;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadingTests {

    @Test
    void glucoseValueShouldReturnCorrectMmolValue() {
        Reading reading = new Reading(180, LocalDateTime.now(), 4);
        assertEquals(10, reading.getGlucoseValueAsMmol());
    }

}
