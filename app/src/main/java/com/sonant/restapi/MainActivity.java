package com.sonant.restapi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    TextView resultTv;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.resultTv);

        // logging interceptor for logging in Http request
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        // client by using logging interceptor
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        getPost();
//        createPost();
//        updatePost();
//        deletePost();
    }

    private void deletePost() {
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (!response.isSuccessful()) {
                    resultTv.setText("Error : " + response.code());
                    return;
                }
                String content = "";
                content += "Code : " + response.code() + "\n";
                resultTv.append(content);
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                resultTv.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post(5, null, "New Body");
//        Call<Post> call = jsonPlaceHolderApi.putPost(31,post);
        Call<Post> call = jsonPlaceHolderApi.patchPost(31, post);
        call.enqueue(new Callback<Post>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<Post> call, @NotNull Response<Post> response) {
                if (!response.isSuccessful()) {
                    resultTv.setText("Error : " + response.code());
                    return;
                }
                Post post = response.body();
                String content = "";
                content += "Code : " + response.code() + "\n";
                assert post != null;
                content += "ID : " + post.getId() + "\n";
                content += "User ID : " + post.getUserId() + "\n";
                content += "Title : " + post.getTitle() + "\n";
                content += "Body : " + post.getText() + "\n\n";

                resultTv.append(content);
            }

            @Override
            public void onFailure(@NotNull Call<Post> call, @NotNull Throwable t) {
                resultTv.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
//        Post post = new Post(25, "New Title", "New Text");
//        Call<Post> call = jsonPlaceHolderApi.createPosts(post);

        // send values directly
        Call<Post> call = jsonPlaceHolderApi.createPosts(24, "New Title", "New Body");
        call.enqueue(new Callback<Post>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<Post> call, @NotNull Response<Post> response) {
                if (!response.isSuccessful()) {
                    resultTv.setText("Error : " + response.code());
                    return;
                }
                Post post = response.body();
                String content = "";
                content += "Code : " + response.code() + "\n";
                assert post != null;
                content += "ID : " + post.getId() + "\n";
                content += "User ID : " + post.getUserId() + "\n";
                content += "Title : " + post.getTitle() + "\n";
                content += "Body : " + post.getText() + "\n\n";

                resultTv.append(content);
            }

            @Override
            public void onFailure(@NotNull Call<Post> call, @NotNull Throwable t) {
                resultTv.setText(t.getMessage());
            }
        });
    }

    private void getPost() {
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<List<Post>> call, @NotNull Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    resultTv.setText("Error : " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                assert posts != null;
                for (Post post : posts) {
                    String content = "";
                    content += "ID : " + post.getId() + "\n";
                    content += "User ID : " + post.getUserId() + "\n";
                    content += "Title : " + post.getTitle() + "\n";
                    content += "Body : " + post.getText() + "\n\n";

                    resultTv.append(content);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Post>> call, @NotNull Throwable t) {
                resultTv.setText(t.getMessage());
            }
        });
    }
}
