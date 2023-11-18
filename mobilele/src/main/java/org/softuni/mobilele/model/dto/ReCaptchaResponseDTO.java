package org.softuni.mobilele.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReCaptchaResponseDTO(
    boolean success,
    ZonedDateTime challengeTs,
    String hostname,
    @JsonProperty("error-codes") List<String> errorCodes
) {

}
