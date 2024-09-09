package com.rtd.project.repository;

import com.rtd.project.entity.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<MachineEntity, Long> {
   Optional<MachineEntity> getMachineByMachineIPAddress(String ipAddress);
   List<MachineEntity> getAllByBrand(String brand);
}
