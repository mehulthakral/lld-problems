package model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    int userId;
    String drivingLicense;

}
