package com.captainwat.api;

public class BookingFactory {
    public static Booking createDefaultBooking() {
        BookingDates dates = new BookingDates();
        dates.checkin = "2018-01-01";
        dates.checkout = "2018-01-01";

        Booking booking = new Booking();
        booking.firstname = "Jim";
        booking.lastname = "Brown";
        booking.totalprice = 111;
        booking.depositpaid = true;
        booking.additionalneeds = "Breakfast";
        booking.bookingdates = dates;
        
        return booking;
    }

    public static Booking createUpdatedBooking() {
        Booking updatedBooking = createDefaultBooking();
        updatedBooking.firstname = "Jim Update";
        return updatedBooking;
    }
}
