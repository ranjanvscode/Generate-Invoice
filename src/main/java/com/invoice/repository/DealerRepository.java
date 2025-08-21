package com.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.entity.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, String> {

}
