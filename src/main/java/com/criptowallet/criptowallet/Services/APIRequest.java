package com.criptowallet.criptowallet.Services;

import com.criptowallet.criptowallet.entities.CriptoCurrency;
import com.criptowallet.criptowallet.entities.USD;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class APIRequest {
    public static Double requestCriptoCurrencyLastValue(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            CriptoCurrency criptoCurrency = new ObjectMapper().readValue(responseBody, CriptoCurrency.class);
            return criptoCurrency.getLast_price();
        }
    }

    public static Double requestValueUSD(String url) throws IOException {
        JSONParser jsonParser = new JSONParser();
        Object object;

        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            object = jsonParser.parse(responseBody);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject compose = (JSONObject) jsonObject.get("rates");
            return (Double) compose.get("USD");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
