package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/unit")
@Slf4j
public class UnitController {

    private final UnitService unitService;

    @PostMapping
    public ResponseEntity<String> unitInput(@RequestParam("unit") String unit) {
        unitService.saveUnit(unit);
        return ResponseEntity.ok("ok");
    }
}
