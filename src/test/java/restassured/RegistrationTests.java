package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class RegistrationTests implements Helper {

    String endPoint = "/v1/user/registration/usernamepassword";

    @BeforeMethod
    public void precondition(){
         RestAssured.baseURI = BASE_URI;
//        RestAssured.basePath = PATH;
    }

    @Test
    public void registrationPositive(){
        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("Mona_" + i + "@mail.ru")
                .password("$Aa456789")
                .build();

        String token = given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("token");

        System.out.println(token);
    }


    @Test
    public void registrationNegativeWrongEmail(){
        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("Mona_" + i + "mail.ru")
                .password("$Aa456789")
                .build();

        given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message.username", containsString("must be a well-formed email address"));
    }



}
