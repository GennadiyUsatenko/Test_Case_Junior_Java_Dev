package main;

import model.CsvFileInfo;
import model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@SpringBootApplication(scanBasePackages = { "main","service","controller" })
@EnableJpaRepositories("repository")
@EntityScan("model")
public class MetroApplication implements CommandLineRunner {

	public static void main(String[] args) {
        SpringApplication.run(MetroApplication.class, args);
	}

	@Override
	public void run(String... arg0) {
        final String uri = "http://localhost:8080/customer";
        RestTemplate restTemplate = new RestTemplate();
        try{
            //Task number 1 - Sending request - Protocol: POST | URI: localhost:8080/customer
            RequestEntity<Customer> postRequest = RequestEntity
                    .post(new URI( uri ))
                    .accept(APPLICATION_JSON_UTF8)
                    .body(Customer.getForTestCase());
            //Response with status 201
            ResponseEntity<CsvFileInfo> postResponse = restTemplate.exchange(postRequest, CsvFileInfo.class);
            //Task number 2 - Sending request - Protocol: GET | URI: localhost:8080/customer/{uniqueFileName}
            ResponseEntity<Customer> getResponse = restTemplate
                    .getForEntity(new URI(uri + "/" + postResponse.getBody().getFile_name()), Customer.class);

        }catch (URISyntaxException e){
            e.printStackTrace();
        }

    }
}
