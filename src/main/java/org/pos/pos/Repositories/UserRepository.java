package org.pos.pos.Repositories;

import org.pos.pos.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT u
        FROM User u 
        WHERE LOWER(CONCAT(u.name, ' ' , u.surname)) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
             OR LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
    """)
    Page<User> searchByFullnameAndEmail(@Param("searchTerm") String searchTerm, Pageable pageable);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
