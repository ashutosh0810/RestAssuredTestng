package Pojo.Postrequest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// This is lombok dependency class and use to create POJO for post request
public class Bookingdates {
    private String checkin;
    private String checkout;
}
