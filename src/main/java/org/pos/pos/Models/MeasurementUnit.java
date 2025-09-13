package org.pos.pos.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement_units")
@SQLRestriction(value = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE measurement_units SET deleted_at = NOW() WHERE id = ?")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasurementUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;


    @Column(name = "sunat_code", nullable = false, length = 10, unique = true)
    private String sunatCode;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}