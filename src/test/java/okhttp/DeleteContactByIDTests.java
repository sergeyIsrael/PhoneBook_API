package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIDTests implements Helper {

    String endPoint = "/v1/contacts/";
    String id;

    @BeforeMethod
//    Создаем контакт чтобы потом его удалить
    public  void precondition() throws IOException {
        ContactDTO contactDTO = ContactDTO.builder()
                .name("Liza")
                .lastName("Egorova")
                .email("Liza" + i + "@mail.ru")
                .phone("05312878" + i)
                .address("Tyumen")
                .description("best city")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);
        Request request = new Request.Builder()
                .url(BASE_URI + endPoint)
                .addHeader(authHeader, TOKEN)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        Assert.assertTrue(response.isSuccessful());

        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ") + 1);
    }


    @Test
    public void deleteContactByIDPositive() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URI + endPoint + id)
                .addHeader(authHeader, TOKEN)
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        Assert.assertTrue(response.isSuccessful());

        String message = contactResponseDTO.getMessage();
        System.out.println(message);
    }



}
