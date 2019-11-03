package alexnanrick.dexcomservice.reading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class NightscoutClient {

    @Value("${nightscout.api}")
    private String nightscoutApiBaseUrl;

    private final RestTemplate restTemplate;

    private static final String ENTRIES_TEMPLATE = "/entries";

    public NightscoutClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    List<Reading> getReadings(Integer count) throws NoReadingFoundException {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(nightscoutApiBaseUrl + ENTRIES_TEMPLATE);
        uriComponentsBuilder.queryParam("count", count);

        ResponseEntity<List<Reading>> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Reading>>() {});

        List<Reading> readings = responseEntity.getBody();

        if (readings == null || readings.size() == 0)
            throw new NoReadingFoundException("No readings found");

        return readings;
    }

}
