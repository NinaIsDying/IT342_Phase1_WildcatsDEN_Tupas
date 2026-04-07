package edu.cit.tupas.controller;

import edu.cit.tupas.entity.BookingEntity;
import edu.cit.tupas.facade.BookingFacade;
import edu.cit.tupas.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
    
     @Autowired
     private BookingFacade bookingFacade;
    
    @PostMapping
    public ResponseEntity<BookingEntity> createBooking(
            @RequestBody BookingEntity booking,
            @RequestParam Long userId) {
BookingEntity createdBooking = bookingFacade.createBooking(booking, userId);        return ResponseEntity.ok(createdBooking);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingEntity>> getUserBookings(@PathVariable Long userId) {
        List<BookingEntity> bookings = bookingFacade.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/user/{userId}/upcoming")
    public ResponseEntity<List<BookingEntity>> getUserUpcomingBookings(@PathVariable Long userId) {
        List<BookingEntity> bookings = bookingFacade.getUserUpcomingBookings(userId);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<BookingEntity>> getUserBookingsByStatus(@PathVariable Long userId, 
                                                                      @PathVariable String status) {
        List<BookingEntity> bookings = bookingFacade.getBookingsByUserAndStatus(userId, status);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/custodian/{custodianId}")
    public ResponseEntity<List<BookingEntity>> getCustodianBookings(@PathVariable Long custodianId) {
        List<BookingEntity> bookings = bookingFacade.getBookingsForCustodian(custodianId);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/custodian/{custodianId}/pending")
    public ResponseEntity<List<BookingEntity>> getCustodianPendingBookings(@PathVariable Long custodianId) {
        List<BookingEntity> bookings = bookingFacade.getPendingBookingsForCustodian(custodianId);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping
    public ResponseEntity<List<BookingEntity>> getAllBookings() {
        List<BookingEntity> bookings = bookingFacade.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BookingEntity> getBookingById(@PathVariable Long id) {
        return bookingFacade.getBookingById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BookingEntity> updateBooking(@PathVariable Long id, 
                                                     @RequestBody BookingEntity bookingDetails) {
        BookingEntity updatedBooking = bookingFacade.updateBooking(id, bookingDetails);
        return ResponseEntity.ok(updatedBooking);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<BookingEntity> updateBookingStatus(@PathVariable Long id,
                                                           @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        String cancelledBy = statusUpdate.get("cancelledBy");
        
        BookingEntity updatedBooking = bookingFacade.updateBookingStatus(id, status, cancelledBy);
        return ResponseEntity.ok(updatedBooking);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingFacade.deleteBooking(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<BookingEntity>> getBookingsByVenue(@PathVariable Long venueId) {
        List<BookingEntity> bookings = bookingFacade.getBookingsByVenue(venueId);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<BookingEntity>> getBookingsByDate(@PathVariable java.util.Date date) {
        List<BookingEntity> bookings = bookingFacade.getBookingsByDate(date);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<BookingEntity>> getBookingsByStatus(@PathVariable String status) {
        List<BookingEntity> bookings = bookingFacade.getBookingsByStatus(status);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/availability/{venueId}/{date}")
    public ResponseEntity<Boolean> checkVenueAvailability(@PathVariable Long venueId, 
                                                         @PathVariable java.util.Date date) {
        boolean isAvailable = bookingFacade.isVenueAvailable(venueId, date);
        return ResponseEntity.ok(isAvailable);
    }


    // Booking status summary for Admin Dashboard
    @GetMapping("/status-summary")
    public ResponseEntity<Map<String, Long>> getBookingStatusSummary() {
        Map<String, Long> summary = new HashMap<>();
        summary.put("pending",   bookingFacade.countByStatus("pending"));
        summary.put("approved",  bookingFacade.countByStatus("approved"));
        summary.put("rejected",  bookingFacade.countByStatus("rejected"));
        summary.put("canceled",  bookingFacade.countByStatus("canceled"));
        return ResponseEntity.ok(summary);
    }

}