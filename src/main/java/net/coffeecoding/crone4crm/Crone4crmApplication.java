package net.coffeecoding.crone4crm;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//mvn spring-boot:run -Dspring-boot.run.arguments=hash
@SpringBootApplication
public class Crone4crmApplication {

    public static void main(String[] args) {

        SpringApplication.run(Crone4crmApplication.class, args);

        if (args.length == 1) {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://coffeecoding.net/crm/" + args[0]);
            System.out.println("Starting refreshing database ...");
            try {
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                // Read the contents of an entity and return it as a String.
                String content = EntityUtils.toString(entity);
                System.out.println(content);
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("Bad argument!");
        }
    }
}

