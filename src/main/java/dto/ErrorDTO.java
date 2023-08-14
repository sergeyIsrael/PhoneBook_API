package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class ErrorDTO {

//  "timestamp": "2023-08-14T03:08:46.936Z",
//  "status": 0,
//  "error": "string",
//  "message": {},
//  "path": "string"

    int status;
    String error;
    String message;

}
