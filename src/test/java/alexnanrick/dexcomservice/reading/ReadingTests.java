package alexnanrick.dexcomservice.reading;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadingTests {

    @Test
    void glucoseValueOf180ShouldReturnCorrectMmolValueTest() {
        Reading reading = new Reading(180, LocalDateTime.now(), 4);
        assertEquals(new BigDecimal(10).setScale(1, RoundingMode.HALF_EVEN), reading.getGlucoseValueMmol());
    }

    @Test
    void glucoseValueOf217ShouldReturnCorrectMmolValueTest() {
        Reading reading = new Reading(217, LocalDateTime.now(), 4);
        assertEquals(new BigDecimal(12).setScale(1, RoundingMode.HALF_EVEN), reading.getGlucoseValueMmol());
    }

    @Test
    void glucoseValueOf218ShouldReturnCorrectMmolValueTest() {
        Reading reading = new Reading(218, LocalDateTime.now(), 4);
        assertEquals(new BigDecimal(12.1).setScale(1, RoundingMode.HALF_EVEN), reading.getGlucoseValueMmol());
    }

}
