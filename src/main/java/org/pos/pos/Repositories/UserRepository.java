package org.pos.pos.Repositories;

import org.pos.pos.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByNameContainingAndSurnameContaining(String name, String surname, Pageable pageable);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
