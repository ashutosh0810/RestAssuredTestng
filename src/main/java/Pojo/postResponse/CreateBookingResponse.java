package Pojo.postResponse;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonNull
@ToString
// This is lombok dependency class and use to create POJO for post response
public class CreateBookingResponse {

    private String bookingid;
    private BookingResponse booking;




}

