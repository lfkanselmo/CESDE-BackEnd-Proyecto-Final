package co.com.cesde.arkham.dto.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PropertyRegisterRecord(
        @NotNull
        Double price,
        @NotNull
        Boolean availability,
        @NotNull
        Long ownerId,
        @NotBlank
        String offer,
        @NotBlank
        String propertyType,
        @NotNull
        Integer rooms,
        @NotNull
        Integer bathrooms,
        @NotNull
        Boolean courtyard,
        @NotNull
        Integer level,
        @NotNull
        Double area,
        @NotNull
        Boolean naturalGas,
        @NotNull
        Boolean laundryArea,
        @NotBlank
        String address,
        @NotBlank
        String district,
        @NotBlank
        String city) {
}
