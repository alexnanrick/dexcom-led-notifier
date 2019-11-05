package alexnanrick.dexcomservice.reading;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Reading {

    private static final int MMOL_CONVERSION = 18;

    Reading(int glucoseValue, LocalDateTime time, int trendCode) {
        this.glucoseValue = glucoseValue;
        this.time = time;
        this.trendCode = trendCode;
    }

    @JsonProperty("sgv")
    private int glucoseValue;

    @JsonProperty("dateString")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime time;

    @JsonProperty("trend")
    private int trendCode;

    @JsonProperty("glucoseValueAsMmol")
    private BigDecimal glucoseValueMmol;

    BigDecimal getGlucoseValueMmol() {
        if (this.glucoseValueMmol == null)
            calculateGlucoseValueMmol();

        return this.glucoseValueMmol;
    }

    Reading calculateGlucoseValueMmol() {
        BigDecimal glucoseValueMgdl = BigDecimal.valueOf(this.glucoseValue);
        BigDecimal divisor = BigDecimal.valueOf(MMOL_CONVERSION);
        this.setGlucoseValueMmol(glucoseValueMgdl.divide(divisor, 1, RoundingMode.DOWN));
        return this;
    }

}
