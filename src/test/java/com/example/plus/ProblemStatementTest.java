package com.example.plus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProblemStatementTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void rawPlus_inQueryParam() {
        String value = "1+1";

        String response = getValueEchoedThroughQueryParam(value);

        assertThat(response, is(equalTo(value)));
    }

    @Test
    public void urlencodedPlus_inQueryParam() {
        String value = "1%2B1";

        String response = getValueEchoedThroughQueryParam(value);

        assertThat(response, is(equalTo(value)));
    }

    @Test
    public void rawPlus_inPathVariable() {
        String value = "1+1";

        String response = getValueEchoedThroughPathVariable(value);

        assertThat(response, is(equalTo(value)));
    }

    @Test
    public void urlencodedPlus_inPathVariable() {
        String value = "1%2B1";

        String response = getValueEchoedThroughPathVariable(value);

        assertThat(response, is(equalTo(value)));
    }

    private String getValueEchoedThroughQueryParam(String value) {
        return webTestClient.get()
                .uri(builder -> {
                    return builder
                            .path("/param")
                            .queryParam("p", value)
                            .build();
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    private String getValueEchoedThroughPathVariable(String value) {
        return webTestClient.get()
                .uri("/path-variable/" + value)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }
}
