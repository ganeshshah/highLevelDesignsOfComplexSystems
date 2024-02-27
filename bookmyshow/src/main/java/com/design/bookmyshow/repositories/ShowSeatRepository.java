package com.design.bookmyshow.repositories;


import com.design.bookmyshow.models.ShowSeat;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ShowSeatRepository
extends JpaRepository<ShowSeat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "30000")
    })
    List<ShowSeat> findByIdIn(List<Long> showSeatIds);
    // select * from show_seats
    // where show_seat id in ()
    // for update;

//    @Query("select * from show_seat_mapping where show_seat_mapping.seat_id in ()")
//    List<ShowSeat> findByIdIn(List<Long> showSeatIds);

    ShowSeat save(ShowSeat showSeat);
}
