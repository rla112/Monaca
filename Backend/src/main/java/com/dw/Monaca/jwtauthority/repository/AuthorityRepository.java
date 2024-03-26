package com.dw.Monaca.jwtauthority.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

	Optional<Authority> findByAuthorityName(String authorityName);

}
