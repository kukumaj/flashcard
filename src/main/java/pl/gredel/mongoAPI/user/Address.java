package pl.gredel.mongoAPI.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String postalCode;
    private String flatNumber;
}
