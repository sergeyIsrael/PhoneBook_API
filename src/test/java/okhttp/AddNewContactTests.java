package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContactTests implements Helper {

    String endPoint = "/v1/contacts";

    @Test
    public void addNewContactPositive() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                .name("Lisa")
                .lastName("Egorova")
                .email("Lisa" + i + "@mail.ru")
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
        System.out.println("SSS: " + message);
        String id = message.substring(message.lastIndexOf(" ") + 1);
        System.out.println(id);
        System.out.println();

    }
}
