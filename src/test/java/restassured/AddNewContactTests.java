package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddNewContactTests implements Helper {

    String endPoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
//        RestAssured.basePath = PATH;
    }

    @Test
    public void addNewContactPositive(){
        ContactDTO contactDTO = ContactDTO.builder()
                .name("Lisa")
                .lastName("Egorova")
                .email("Lisa" + i + "@mail.ru")
                .phone("05312878" + i)
                .address("Tyumen")
                .description("best city")
                .build();

        given()
                .header(authHeader, TOKEN)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(200);
    }

}
