package alexnanrick.dexcomservice.reading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingTests {

    @Test
    public void glucoseValueShouldReturnCorrectMmolValue() {
        Reading reading = new Reading();
        reading.setGlucoseValue(180);
        assertEquals(10, reading.getGlucoseValueAsMmol());
    }

}
