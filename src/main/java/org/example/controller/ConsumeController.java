package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ConsumeRequestDTO;
import org.example.dto.ConsumeResponseDTO;
import org.example.dto.EditRequestDto;
import org.example.dto.EditResponseDTO;
import org.example.entity.Users;
import org.example.service.ConsumeService;
import org.example.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/consume")
@Slf4j
public class ConsumeController {

    private final ConsumeService consumeService;
    private final UserService userService;
    private final long user = 1;

    @PostMapping
    public ResponseEntity<String> postConsume(@RequestBody ConsumeRequestDTO requestDTO) {

        log.info("----------------------------------------------");
        log.info("request DTO {} {} {} " , requestDTO.getValue(), requestDTO.getDate(), requestDTO.getUnit());

        consumeService.saveConsumption(requestDTO, user);
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public ResponseEntity<List<ConsumeResponseDTO>> getConsume() {
        return ResponseEntity.ok(consumeService.getAllConsumption(user));
    }

//    @GetMapping("/units/{unitId}")
//    public ResponseEntity<List<ConsumeResponseDTO>> getConsumeUnits(@PathVariable("unitId") Long unitId) {
//        return ResponseEntity.ok(consumeService.getConsumptionWithUnit(user, unitId));
//    }

    @GetMapping("/units/{unitId}")
    public ResponseEntity<List<ConsumeResponseDTO>> getConsumeUnitsWithDates(@PathVariable("unitId") Long unitId,
                                                                    @RequestParam(name = "start_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                             @RequestParam(name = "end_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        if (startDate == null) {
            return ResponseEntity.ok(consumeService.getConsumptionWithUnit(user, unitId));
        }
        return ResponseEntity.ok(consumeService.getConsumptionWithUnitandDates(user, unitId, startDate, endDate));
    }

    @GetMapping("/units/{unitId}/sum")
    public ResponseEntity<Long> getConsumeSum(@PathVariable("unitId") Long unitId) {

        log.debug("total {}",  consumeService.getConsumeSum(user, unitId));
//        consumeService.getConsumeSum(user, unitId);
        return ResponseEntity.ok(consumeService.getConsumeSum(user, unitId)
        );
    }

    @PutMapping("/units/edit")
    public ResponseEntity<EditResponseDTO> editConsume(@RequestBody EditRequestDto editRequestDto) {

        return ResponseEntity.ok(consumeService.editConsume(user, editRequestDto));
    }

    @DeleteMapping("/units/delete")
    public ResponseEntity<String> deleteConsume( @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        consumeService.deleteConsume(user, date);
        return ResponseEntity.ok("ok");
    }

}
