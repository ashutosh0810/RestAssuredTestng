package Pojo.postResponse;

import Pojo.Postrequest.Bookingdates;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonNull
@ToString

// This is lombok dependency class and use to create POJO for post response
public class BookingResponse {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

}
