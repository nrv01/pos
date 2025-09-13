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
@Table(
        name = "product_attributes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"product_id", "variation_attribute_id"})
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SQLRestriction("deleted_at IS NULL")
@SQLDelete(sql = "UPDATE product_attributes SET deleted_at = NOW() WHERE id = ?")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "variation_attribute_id", nullable = false)
    private Long variationAttributeId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
