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
@Table(name = "clients")
@SQLRestriction(value = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE clients SET deleted_at = NOW() WHERE id = ?")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "last_name", nullable = true, length = 150)
    private String lastName;

    @Column(nullable = true, length = 150, unique = true)
    private String email;

    @Column(nullable = true, length = 20)
    private String phone;

    @Column(nullable = true, length = 255)
    private String address;

    @Column(nullable = true, length = 100)
    private String city;

    @Column(nullable = true, length = 100)
    private String state;

    @Column(nullable = true, length = 100)
    private String country;

    @Column(name="document_type_id", nullable = false)
    private Long documentTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", insertable = false, updatable = false)
    private DocumentType documentType;

    @Column(nullable = true, length = 30, unique = true)
    private String documentNumber;

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

