package restassured;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTests implements Helper {

    String endPoint = "/v1/user/login/usernamepassword";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASE_URI;
//        RestAssured.basePath = PATH;
    }

    @Test
    public void loginPositive(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("sergei1@mail.com")
                .password("Ss34567$")
                .build();

//        Задав этот запрос мы получим ответ виде AuthResponseDTO.class
        AuthResponseDTO responseDTO =
                given() // дано:
                .body(requestDTO) // Тело
                .contentType(ContentType.JSON) // Тип данных для обмена
                .when() // Когда метод post..
                .post(endPoint) // направляется на endPoint
                .then() // тогда
                .assertThat().statusCode(200) // я утверждаю что статус код будет 200
                .extract() // И если это так - вытащи данные
                .as(AuthResponseDTO.class);// в виде AuthResponseDTO.class

        System.out.println(responseDTO.getToken());
    }


    @Test
    public void loginNegativeWrongEmail(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("sergei1mail.com")
                .password("Ss34567$")
                .build();

        ErrorDTO  errorDTO =
                given()
                        .body(requestDTO)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(endPoint)
                        .then()
                        .assertThat().statusCode(401)
                        .extract()
                        .as(ErrorDTO.class);

        System.out.println("GetMessage: " + errorDTO.getMessage());
    }




}
