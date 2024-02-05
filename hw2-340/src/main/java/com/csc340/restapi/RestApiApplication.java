package com.csc340.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        getDogFact();
       
    }

    /**
     * Get a random cat fact and print to console
     */
   public static void getDogFact() {
        try {
            String url = "https://dogapi.dog/api/v2/breeds";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonFact = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonFact);

            String name = root.findValue("name").asText();
            String fact = root.findValue("description").asText();

            System.out.println("Dog Breed Facts:");
            System.out.println("Name: " + name);
            System.out.println("Fact: " + fact);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiApplication.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error in getDogFact");

        }
    }

}
