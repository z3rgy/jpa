package org.example.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumption {

    @Id
    @Column(name = "consumption_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long value;

    @Nullable
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
//    @MapsId
    @JoinColumn(name = "user_no")
    private Users users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private Unit unit;


}
