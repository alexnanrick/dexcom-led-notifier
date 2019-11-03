package alexnanrick.dexcomservice.reading;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("dev")
public class NightscoutClientTests {

    @Autowired
    NightscoutClient nightscoutClient;

    @Test
    public void getLastEntryTest() throws NoReadingFoundException {
        Reading reading = nightscoutClient.getLatestReading();
        reading.getTime();
    }

}
