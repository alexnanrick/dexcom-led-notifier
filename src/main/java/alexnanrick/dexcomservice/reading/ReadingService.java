package alexnanrick.dexcomservice.reading;

import org.springframework.stereotype.Service;

@Service
public class ReadingService {

    private NightscoutClient nightscoutClient;

    public ReadingService(NightscoutClient nightscoutClient) {
        this.nightscoutClient = nightscoutClient;
    }

    Reading getCurrentReading() throws NoReadingFoundException {
        Reading reading = nightscoutClient.getReadings(1).get(0);
        return reading.calculateGlucoseValueMmol();
    }

}
