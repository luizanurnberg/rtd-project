package com.rtd.project.entity;

import com.rtd.project.model.MachineUsage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name ="machine")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MachineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column()
    private String name;
    @Column()
    private String brand;
    @Column()
    private LocalTime startTime;
    @Column()
    private LocalTime endTime;
    @Column()
    private String machineIPAddress;
    @Column()
    @OneToMany(targetEntity = MachineUsageEntity.class, cascade = CascadeType.ALL)
    private List<MachineUsageEntity> usage;
}
