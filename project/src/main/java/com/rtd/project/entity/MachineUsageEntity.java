package com.rtd.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "machine_usage")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MachineUsageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column()
    private LocalDate date;
    @Column()
    private boolean hasProblem;
    @Column()
    private Double temperature;
    @Column()
    private Double usage;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MachineEntity.class)
    private MachineEntity machine;
}
