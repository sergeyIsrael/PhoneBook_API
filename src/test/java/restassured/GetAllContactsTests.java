package restassured;

import com.jayway.restassured.RestAssured;
import dto.ContactDTO;
import dto.ContactListDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsTests implements Helper {

    String endPoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
//        RestAssured.basePath = PATH;
    }

    @Test
    public void getAllContactsPositive() {

        ContactListDTO listDTO = given()
                .header(authHeader, TOKEN) // Авторизация
                .when()
                .get(endPoint) // Придём к .../v1/contacts
                .then()
                .assertThat().statusCode(200) // Затем убедимся что статус кот - 200
                .extract() // после этого извлеки Ответ
                .as(ContactListDTO.class);// В виде ContactListDTO.class, и сохрани в переменную - listDTO (25 стр)

//        Распечатай
        for (ContactDTO contactDTO : listDTO.getContacts()) {
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println("=================================");
        }
    }


}
