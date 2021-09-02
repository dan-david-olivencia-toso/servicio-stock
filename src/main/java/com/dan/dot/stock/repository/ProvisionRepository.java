package com.dan.dot.stock.repository;

import com.dan.dot.stock.domain.Provision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvisionRepository extends JpaRepository<Provision, Integer> {
    public Optional<Provision> getById(Provision provision);
    public boolean existsById(Integer id);
    public Provision save(Provision provision);

}
