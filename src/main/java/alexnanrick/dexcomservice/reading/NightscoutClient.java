package alexnanrick.dexcomservice.reading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NightscoutClient {

    @Value("${nightscout.api}")
    private String nightscoutApiBaseUrl;

    private static final String ENTRIES_TEMPLATE = "/entries";

    public Reading getLatestReading() throws NoReadingFoundException {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(nightscoutApiBaseUrl + ENTRIES_TEMPLATE);
        uriComponentsBuilder.queryParam("count", 1);

        RestTemplate restTemplate = new RestTemplate();
        Reading[] readingList = restTemplate.getForObject(uriComponentsBuilder.toUriString(), Reading[].class);

        if (readingList == null)
            throw new NoReadingFoundException("No entry returned from Nightscout");

        return readingList[0];
    }

}
