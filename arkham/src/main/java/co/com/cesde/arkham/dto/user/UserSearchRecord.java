package co.com.cesde.arkham.dto.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record UserSearchRecord(
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        LocalTime startTime,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date) {

}
