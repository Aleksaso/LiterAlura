package com.alura.literalura.util;

import java.net.http.HttpClient;

public class ApiClient {
    private static final String BASE_URL = "https://gutendex.com/books/";

    public static HttpClient createClient() {
        return HttpClient.newHttpClient();
    }
}
