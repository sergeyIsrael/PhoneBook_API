package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTests {

//  MediaType - okhttp3 выбирай.
//    Задали стандарт как мы будем обмениваться с сервером
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

//  объект для Доступа к методам
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public  void loginPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("sergei1@mail.com")
                .password("Ss34567$")
                .build();

//        Конвертируем данные выше в формат json для передачи на Сервер. JSON Это 16-я строка.
//        Создали тело запроса
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

//        Сам запрос
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

//        Ответ от сервера
//        Здесь посылается Наш request на сервер и его ответ сохраняеется в response
        Response response = client.newCall(request).execute();

//        Переводим ответ сервера обратно в java формат
        if (response.isSuccessful()) {
            AuthResponseDTO responseDTO = gson.fromJson(response.body().string(), AuthResponseDTO.class);
            System.out.println(responseDTO.getToken());
            System.out.println("Response code is : " + response.code());
            Assert.assertTrue(response.isSuccessful());
        } else {
            System.out.println("Response code is : " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() +
                    " " + errorDTO.getMessage() +
                    " " + errorDTO.getError());
            Assert.assertTrue(response.isSuccessful());

        }
    }
}

//  eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic2VyZ2VpMUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkyNTkwMTcxLCJpYXQiOjE2OTE5OTAxNzF9.ATQS6pmDdYP7Lk3PXa787tfIXsE0wT9P804IAU5F7ME
