package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ConsumeRequestDTO;
import org.example.dto.ConsumeResponseDTO;
import org.example.dto.EditRequestDto;
import org.example.dto.EditResponseDTO;
import org.example.entity.Consumption;
import org.example.repository.ConsumptionRepository;
import org.example.repository.UnitRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumeService {

    private final ConsumptionRepository consumptionRepository;
    private final UserRepository userRepository;
    private final UnitRepository unitRepository;

    @Transactional
    public void saveConsumption(ConsumeRequestDTO requestDTO, Long user) {
        var a = userRepository.findById(user).get();
        var b = unitRepository.findById(requestDTO.getUnit()).get();
        log.info("a {} b {}", a.getId(), b.getId());
        Consumption consumption =
                new Consumption().builder()
                        .users(a)
                        .value(requestDTO.getValue())
                        .unit(b)
                        .date(requestDTO.getDate()).build();

        consumptionRepository.save(consumption);
    }

    public List<ConsumeResponseDTO> getAllConsumption(Long user){

        var a = consumptionRepository.findByUsersId(user).stream()
                .map(ConsumeResponseDTO::new).collect(Collectors.toList());
        return a;
    }

    public List<ConsumeResponseDTO> getConsumptionWithUnit(Long user, Long unitId){

        var a = consumptionRepository.findAllByUserNoAndUnitId(user, unitId).stream()
                .map(ConsumeResponseDTO::new).collect(Collectors.toList());
        return a;
    }

    public List<ConsumeResponseDTO> getConsumptionWithUnitandDates(Long user, Long unitId, LocalDate startDate ,LocalDate endDate){

        var a = consumptionRepository.findAllByDateBetween(startDate, endDate).stream()
                .map(ConsumeResponseDTO::new).collect(Collectors.toList());;


        return a;
    }

    public Long getConsumeSum(Long user, Long unitId){

        return consumptionRepository.findAllByUserNoAndUnitId(user, unitId).stream()
                .mapToLong(consumption -> consumption.getValue().intValue()).sum();
    }

    @Transactional
    public EditResponseDTO editConsume(Long user, EditRequestDto editRequestDto){

        Consumption dto = consumptionRepository.findAllByUserNoAndUnitIdAndDate(user, editRequestDto.getUnit(), editRequestDto.getDate());
        dto.setDate(editRequestDto.getDate());
        dto.setValue(editRequestDto.getValue());
        consumptionRepository.save(dto);
       return new EditResponseDTO(dto);
    }

    @Transactional
    public void deleteConsume(Long user, LocalDate date){


        consumptionRepository.delete(consumptionRepository.findAllByFindByUsersIdAndDate(user, date));
    }

}
