package edu.cit.tupas.factory;

import edu.cit.tupas.entity.BookingEntity;

import java.util.Locale;

public class BookingFactory {

    public static BookingEntity create(String type, BookingEntity booking) {
        String status = normalizeStatus(type);
        if (status != null) {
            booking.setStatus(status);
        }
        return booking;
    }

    public static String normalizeStatus(String type) {
        if (type == null || type.isBlank()) {
            return null;
        }

        switch (type.trim().toUpperCase(Locale.ROOT)) {
            case "REQUEST":
            case "PENDING":
                return "pending";
            case "APPROVED":
                return "approved";
            case "REJECTED":
                return "rejected";
            case "CANCELLED":
            case "CANCELED":
                return "canceled";
            default:
                return null;
        }
    }
}