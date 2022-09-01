package de.judgeman.SimpleRequestWithRetrofit;

import de.judgeman.SimpleRequestsWithRetrofit.Model.ExampleObject;
import de.judgeman.SimpleRequestsWithRetrofit.Services.JsonPlaceholderTypicodeRequestService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RequestServiceTests {

    private static String BASE_TEST_URL = "https://jsonplaceholder.typicode.com";

    private static JsonPlaceholderTypicodeRequestService requestService;

    @BeforeAll
    public static void initRequestService() {
        requestService = new JsonPlaceholderTypicodeRequestService(BASE_TEST_URL);
    }

    @Test
    public void postToCreateNewElement() throws IOException {
        int userId = 1;
        String title = "Titel";
        String body = "Body";

        ExampleObject exampleObject = requestService.postNewExampleObject(userId, title, body);
        assertNotNull(exampleObject);
        assertSame(userId, exampleObject.getUserId());
        assertEquals(title, exampleObject.getTitle());
        assertEquals(body, exampleObject.getBody());

        // static setting of the test api -> always 101
        assertSame(exampleObject.getId(), exampleObject.getId());
    }

    @Test
    public void getTestElement() throws IOException {
        int userId = 1;

        ExampleObject exampleObject = requestService.getExampleObject(userId);
        assertNotNull(exampleObject);
        assertSame(userId, exampleObject.getUserId());
        // static setting of the test api -> always 1 for id, not empty values for title and body
        assertSame(1, exampleObject.getId());
        assertNotNull(exampleObject.getTitle());
        assertNotNull(exampleObject.getBody());
    }
}
