package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.entity.Consumption;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class ConsumeResponseDTO {

    private Long value;

    private Long unit;

    private LocalDate date;

    public ConsumeResponseDTO (Consumption dto) {
        this.value = dto.getValue();
        this.unit = dto.getUnit().getId();
        this.date = dto.getDate();
    };
}

