package alexnanrick.dexcomservice.reading;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reading {

    @JsonProperty("sgv")
    private int glucoseValue;

    @JsonProperty("dateString")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private LocalDateTime time;

    @JsonProperty("trend")
    private int trendCode;

}