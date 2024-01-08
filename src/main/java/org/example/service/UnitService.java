package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Unit;
import org.example.repository.UnitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    @Transactional
    public void saveUnit(String unitName) {
        Unit unit = new Unit();
        unit.setUnitName(unitName);
        unitRepository.save(unit);
    }
}
