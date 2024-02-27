package com.design.bookmyshow.repositories;


import com.design.bookmyshow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository
extends JpaRepository<Seat, Long> {
}
