package alexnanrick.dexcomservice.reading;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Reading {

    private static final int MMOL_CONVERSION = 18;

    @JsonProperty("sgv")
    private int glucoseValue;

    @JsonProperty("dateString")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private LocalDateTime time;

    @JsonProperty("trend")
    private int trendCode;

    public float getGlucoseValueAsMmol() {
        return (float)this.glucoseValue/MMOL_CONVERSION;
    }

}
