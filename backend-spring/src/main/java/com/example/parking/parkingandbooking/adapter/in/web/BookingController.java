package com.example.parking.parkingandbooking.adapter.in.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.parking.parkingandbooking.adapter.out.persistence.entity.BookingEntity;
import com.example.parking.parkingandbooking.adapter.out.persistence.repository.BookingRepository;
import com.example.parking.parkingandbooking.dto.BookingRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/reserve")
    public String reserve(@RequestBody BookingRequest request) {

        Optional<BookingEntity> existing =
                bookingRepository.findBySlotNumber(request.getSlotNumber());

        if (existing.isPresent()) {
            return "Chỗ này đã được đặt";
        }

        BookingEntity booking = new BookingEntity();

        booking.setPlateNumber(request.getPlateNumber());
        booking.setSlotNumber(request.getSlotNumber());
        booking.setStatus("BOOKED");

        bookingRepository.save(booking);

        return "Đặt chỗ thành công";
    }

    @GetMapping("/all")
    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable Long id) {

        if (!bookingRepository.existsById(id)) {
            return "Không tìm thấy booking";
        }

        bookingRepository.deleteById(id);

        return "Hủy đặt chỗ thành công";
    }

    @GetMapping("/available")
    public List<Integer> availableSlots() {

        List<Integer> available = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {

            Optional<BookingEntity> booking =
                    bookingRepository.findBySlotNumber(i);

            if (booking.isEmpty()) {
                available.add(i);
            }
        }

        return available;
    }
}
