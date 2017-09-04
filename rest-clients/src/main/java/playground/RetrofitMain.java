package playground;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMain {

    public static void main(String[] args) {

        String API_BASE_URL = "https://api.github.com/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder
            .client(httpClient.build())
            .build();

        GitHubClient client = retrofit.create(GitHubClient.class);

        // Fetch a list of the Github repositories.
        Call<List<GitHubRepo>> call = client.reposForUser("p0gram3r");

        //// Execute the call asynchronously. Get a positive or negative callback.
        //call.enqueue(new Callback<List<GitHubRepo>>() {
        //    @Override
        //    public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
        //        // The network call was a success and we got a response
        //        // TODO: use the repository list and display it
        //    }
        //
        //    @Override
        //    public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
        //        // the network call was a failure
        //        // TODO: handle error
        //    }
        //});
    }
}
