package pl.gredel.flashcard.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class AddressUser {
    private String city;
    private String street;
    private String postalCode;
    private String flatNumber;

    public AddressUser(String city, String street, String postalCode, String flatNumber) {
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.flatNumber = flatNumber;
    }
}
