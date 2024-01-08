package org.example.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.entity.Consumption;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class EditResponseDTO {

    private Long value;

    private Long unit;

    private LocalDate date;

    public EditResponseDTO (Consumption dto) {
        this.value = dto.getValue();
        this.unit = dto.getUnit().getId();
        this.date = dto.getDate();
    };
}
