package edu.cit.tupas.facade;

import edu.cit.tupas.entity.BookingEntity;
import edu.cit.tupas.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingFacade {

    @Autowired
    private BookingService bookingService;

    public BookingEntity createBooking(BookingEntity booking, Long userId) {
        return bookingService.createBooking(booking, userId);
    }

    public List<BookingEntity> getUserBookings(Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    public List<BookingEntity> getUserUpcomingBookings(Long userId) {
        return bookingService.getUpcomingBookingsByUser(userId);
    }

    public List<BookingEntity> getBookingsByUserAndStatus(Long userId, String status) {
        return bookingService.getBookingsByUserAndStatus(userId, status);
    }

    public List<BookingEntity> getBookingsForCustodian(Long custodianId) {
        return bookingService.getBookingsForCustodian(custodianId);
    }

    public List<BookingEntity> getPendingBookingsForCustodian(Long custodianId) {
        return bookingService.getPendingBookingsForCustodian(custodianId);
    }

    public List<BookingEntity> getAllBookings() {
        return bookingService.getAllBookings();
    }

    public Optional<BookingEntity> getBookingById(Long id) {
        return bookingService.getBookingById(id);
    }

    public BookingEntity updateBooking(Long id, BookingEntity bookingDetails) {
        return bookingService.updateBooking(id, bookingDetails);
    }

    public BookingEntity updateBookingStatus(Long id, String status, String cancelledBy) {
        return bookingService.updateBookingStatus(id, status, cancelledBy);
    }

    public void deleteBooking(Long id) {
        bookingService.deleteBooking(id);
    }

    public List<BookingEntity> getBookingsByVenue(Long venueId) {
        return bookingService.getBookingsByVenue(venueId);
    }

    public List<BookingEntity> getBookingsByDate(Date date) {
        return bookingService.getBookingsByDate(date);
    }

    public List<BookingEntity> getBookingsByStatus(String status) {
        return bookingService.getBookingsByStatus(status);
    }

    public boolean isVenueAvailable(Long venueId, Date date) {
        return bookingService.isVenueAvailable(venueId, date);
    }

    public long countByStatus(String status) {
        return bookingService.countByStatus(status);
    }
}