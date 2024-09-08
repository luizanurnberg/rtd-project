package com.rtd.project.entity;

import com.rtd.project.model.MachineUsage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name ="machine")
@NoArgsConstructor
@Data
public class MachineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;
    @Column(nullable = false)
    private String machineIPAddress;
    @Column(nullable = false)
    @OneToMany(mappedBy = "machine", targetEntity = MachineUsageEntity.class, cascade = CascadeType.ALL)
    private List<MachineUsageEntity> usage;
    @Column(nullable = false,updatable = false)
    public LocalDateTime createdAt;
    @Column(nullable = false)
    public LocalDateTime updatedAt;

    @PrePersist
    private void onCrate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
