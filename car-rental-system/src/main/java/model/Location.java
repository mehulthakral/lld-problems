package model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Location {

    String address;
    String city;
    String state;
    int pincode;

}
