package org.example.repository;

import org.example.entity.Consumption;
import org.example.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

    @Query("select c from Consumption c where c.users.Id = ?1")
    List<Consumption> findByUsersId(Long userNo);

    @Query("select c from Consumption c where c.users.Id = ?1 and c.unit.id = ?2")
    List<Consumption> findAllByUserNoAndUnitId(Long userNo, Long unitId);


    List<Consumption> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("select c from Consumption c where c.users.Id = ?1 and c.unit.id = ?2 and c.date = ?3")
   Consumption findAllByUserNoAndUnitIdAndDate(Long userNo, Long unitId, LocalDate date);

    @Query("select c from Consumption c where c.users.Id = ?1 and c.date = ?2")
   Consumption findAllByFindByUsersIdAndDate(Long userNo, LocalDate date);
}
