package alexnanrick.dexcomservice.reading;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(NightscoutClient.class)
@ActiveProfiles("dev")
class NightscoutClientTests {

    @Value("${nightscout.api}")
    private String nightscoutApiBaseUrl;

    @Autowired
    private NightscoutClient nightscoutClient;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getEntriesShouldReturnListTest() throws NoReadingFoundException, JsonProcessingException {
        List<Reading> expectedReadingList = new ArrayList<>();
        expectedReadingList.add(new Reading(150, LocalDateTime.now(), 4));
        String readingListString = objectMapper.writeValueAsString(expectedReadingList);

        this.mockRestServiceServer.expect(requestTo(String.format(nightscoutApiBaseUrl + "/entries?count=%d", 10))).andRespond(withSuccess(readingListString, MediaType.APPLICATION_JSON));

        List<Reading> actualReadingList = this.nightscoutClient.getReadings(10);
        assertThat(actualReadingList.get(0).getGlucoseValue()).isEqualTo(150);
    }

    @Test
    void getEntriesShouldThrowNoReadingFoundExceptionTest() throws JsonProcessingException {
        List<Reading> expectedReadingList = new ArrayList<>();
        String readingListString = objectMapper.writeValueAsString(expectedReadingList);

        this.mockRestServiceServer.expect(requestTo(String.format(nightscoutApiBaseUrl + "/entries?count=%d", 5))).andRespond(withSuccess(readingListString, MediaType.APPLICATION_JSON));

        assertThrows(NoReadingFoundException.class, () -> {
            nightscoutClient.getReadings(5);
        });
    }

}
