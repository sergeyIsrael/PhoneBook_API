package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class AuthRequestDTO {

//        "username": "string",
//        "password": "\\K:K0|N8#L_%t,eKC)_y@BC'dl dNd-c]^J#7uA];SS%+Bl4AVRs \"}/ [TK]6s0"
    String username;
    String password;

}
