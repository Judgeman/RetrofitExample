package de.judgeman.SimpleRequestsWithRetrofit.Utilities;

import de.judgeman.SimpleRequestsWithRetrofit.Model.ExampleObject;
import de.judgeman.SimpleRequestsWithRetrofit.Model.ExampleObjectForCreatingViaRequests;
import retrofit2.Call;
import retrofit2.http.*;

public interface jsonPlaceholderTypicodeWebService {

    // Base Url "https://jsonplaceholder.typicode.com";

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/posts")
    Call<ExampleObject> PostExampleObject(@Body ExampleObjectForCreatingViaRequests exampleObjectForCreatingViaRequests);

    @Headers({
            "Accept: application/json"
    })
    @GET("/posts/{id}")
    Call<ExampleObject> GetExampleObject(@Path("id") int id);
}
