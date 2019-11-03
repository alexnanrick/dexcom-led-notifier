package alexnanrick.dexcomservice.reading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@Controller
class ReadingControllerTests {

    @Mock
    private NightscoutClient nightscoutClient;

    @Mock
    private ReadingService readingService;

    private ReadingController readingController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        readingController = new ReadingController(readingService);
    }

    @Test
    void readingControllerShouldReturnCurrentValueTest() throws NoReadingFoundException {
        Reading expectedReading = new Reading(180, LocalDateTime.now(), 4);
        List<Reading> expectedReadings = new ArrayList<>();
        expectedReadings.add(expectedReading);

        given(nightscoutClient.getReadings(1)).willReturn(expectedReadings);
        given(readingService.getCurrentReading()).willReturn(expectedReading);

        Reading reading = readingController.getCurrentReading();

        assertThat(reading.getGlucoseValue() == expectedReading.getGlucoseValue());
    }

}
