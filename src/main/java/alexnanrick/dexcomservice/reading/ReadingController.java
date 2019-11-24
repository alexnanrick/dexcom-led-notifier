package alexnanrick.dexcomservice.reading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
        log.info(String.format("getCurrentReading got [reading: %s]", currentReading));
        return currentReading;
    }

}
