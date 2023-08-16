package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class UpdateContactTests implements Helper {

    ContactDTO contactDTO;
    String id;
    String endPoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
//        RestAssured.basePath = PATH;

       contactDTO = ContactDTO.builder()
                .name("Lisa")
                .lastName("Egorova")
                .email("Lisa" + i + "@mail.ru")
                .phone("05312878" + i)
                .address("Tyumen")
                .description("best city")
                .build();

        String message = given()
                .header(authHeader, TOKEN)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("message");

        id = message.substring(message.lastIndexOf(" ") + 1);
    }


    @Test
    public void updateContactPositive(){

        contactDTO.setId(id);
        contactDTO.setName("Alice");
        given()
                .header(authHeader, TOKEN)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .put(endPoint)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was updated"));



    }


}
