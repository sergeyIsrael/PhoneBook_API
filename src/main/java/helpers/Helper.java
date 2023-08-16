package helpers;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {

    //  MediaType - okhttp3 выбирай.
//    Задали стандарт как мы будем обмениваться с сервером
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    //  объект для Доступа к методам
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic2VyZ2VpMUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkyNTkwMTcxLCJpYXQiOjE2OTE5OTAxNzF9.ATQS6pmDdYP7Lk3PXa787tfIXsE0wT9P804IAU5F7ME";
    String BASE_URI = "https://contactapp-telran-backend.herokuapp.com";
    String PATH = "v1";
    String authHeader = "Authorization";
    int i = new Random().nextInt(1000) + 1000;




}
