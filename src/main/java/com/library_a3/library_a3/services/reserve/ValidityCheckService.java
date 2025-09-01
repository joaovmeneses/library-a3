package com.library_a3.library_a3.services.reserve;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.library_a3.library_a3.domains.Book;
import com.library_a3.library_a3.domains.Reserve;
import com.library_a3.library_a3.repositories.BookRepository;
import com.library_a3.library_a3.repositories.ReserveRepository;
import com.library_a3.library_a3.shared.enums.BookStatusEnum;
import com.library_a3.library_a3.shared.enums.reserve.ReservationStatusEnum;

import jakarta.transaction.Transactional;

@Service
public class ValidityCheckService {

    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private BookRepository bookRepository;

    @Scheduled(cron= "0 0 * * * *")
    @Transactional
    public void execute(){

        Date now = new Date();

        List<Reserve> openReserves = reserveRepository.findByStatus(ReservationStatusEnum.OPEN);

        for (Reserve reserve : openReserves) {
            if(reserve.getExpiresAt().before(now)){
                reserve.setStatus(ReservationStatusEnum.EXPIRED);
                reserve.setUpdatedAt(now);

                Book book = reserve.getBook();
                if (book.getStatus() == BookStatusEnum.RESERVED) {
                    book.setStatus(BookStatusEnum.AVAILABLE);
                    book.setUpdatedAt(now);
                    bookRepository.save(book);
                }
            }
        }
        reserveRepository.saveAll(openReserves);
    }
}
