package com.rtd.project.repository;

import com.rtd.project.entity.MachineUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineUsageRepository extends JpaRepository<MachineUsageEntity, Long> {
    List<MachineUsageEntity> findAllByDate();
    List<MachineUsageEntity> findAllByHasProblem();
    List<MachineUsageEntity> findAllByMachineId(Long machineId);
}
