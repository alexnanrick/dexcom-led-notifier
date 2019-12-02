package alexnanrick.dexcomservice.reading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/reading")
public class ReadingController {

    private final ReadingService readingService;

    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Reading getCurrentReading() throws NoReadingFoundException {
        Reading currentReading = readingService.getCurrentReading();
        log.info("Get current reading retrieved [reading: {}]", currentReading);
        return currentReading;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reading> getReadings(@RequestParam(defaultValue = "5") int size) throws NoReadingFoundException {
        if (size < 1 || size > 50) {
            size = 5;
        }

        List<Reading> readings = readingService.getReadings(size);
        log.info("Get {} readings retrieved [readings: {}]", size, readings);
        return readings;
    }

}
