package okhttp;

import dto.AuthRequestDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements Helper {

    @Test
    public void registrationPositive() throws IOException {

        String endPoint = "/v1/user/registration/usernamepassword";

//          Создали тело запроса
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("boss_" + i + "@mail.com")
                .password("$Qwer12345")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
//          Сам запрос
        Request request = new Request.Builder()
                .url(BASE_URI + endPoint)
                .post(requestBody)
                .build();

//          посылается Наш request на сервер и его ответ сохраняеется в response
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
    }


    @Test
    public void registrationNegativeWrongEmail() throws IOException {

        String endPoint = "/v1/user/registration/usernamepassword";

//          Создали тело запроса
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("boss_" + i + "mail.com")
                .password("$Qwer12345")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
//          Сам запрос
        Request request = new Request.Builder()
                .url(BASE_URI + endPoint)
                .post(requestBody)
                .build();

//          посылается Наш request на сервер и его ответ сохраняеется в response
        Response response = client.newCall(request).execute();

        System.out.println("Response code is : " + response.code());
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() +
                " " + errorDTO.getMessage() +
                " " + errorDTO.getError());
    }




}
