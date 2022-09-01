package de.judgeman.SimpleRequestsWithRetrofit.Services;

import de.judgeman.SimpleRequestsWithRetrofit.Model.ExampleObject;
import de.judgeman.SimpleRequestsWithRetrofit.Model.ExampleObjectForCreatingViaRequests;
import de.judgeman.SimpleRequestsWithRetrofit.Utilities.jsonPlaceholderTypicodeWebService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class JsonPlaceholderTypicodeRequestService {

    private jsonPlaceholderTypicodeWebService jsonPlaceholderTypiconWebService;

    public JsonPlaceholderTypicodeRequestService(String baseUrl) {
        jsonPlaceholderTypiconWebService = createNewJsonPlaceholderTypiconWebService(baseUrl);
    }

    public ExampleObject postNewExampleObject(int userId, String title, String body) throws IOException {
        ExampleObjectForCreatingViaRequests requestObject = new ExampleObjectForCreatingViaRequests();
        requestObject.setUserId(userId);
        requestObject.setTitle(title);
        requestObject.setBody(body);

        Response<ExampleObject> response = jsonPlaceholderTypiconWebService.PostExampleObject(requestObject).execute();
        ExampleObject exampleObject = response.body();

        System.out.println("received: " + exampleObject);

        return exampleObject;
    }

    public ExampleObject getExampleObject(int userId) throws IOException {
        Response<ExampleObject> response = jsonPlaceholderTypiconWebService.GetExampleObject(userId).execute();
        ExampleObject exampleObject = response.body();

        System.out.println("received: " + exampleObject);

        return exampleObject;
    }

    private jsonPlaceholderTypicodeWebService createNewJsonPlaceholderTypiconWebService(String url) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .followSslRedirects(true)
                .followRedirects(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.MINUTES)
                .writeTimeout(60, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(jsonPlaceholderTypicodeWebService.class);
    }
}
