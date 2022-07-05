package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.data.remote.SoccerNewsApi;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    //a intenção foi criar um LiveData onde retornasse a lista de notícias
    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsApi api;

    public NewsViewModel() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://liviavilarinho.github.io/soccer-news-api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        api = retrofit.create(SoccerNewsApi.class);

        //metodo findNews tem a chamada http do retrofit
        this.findNews();

    }
//quando viewModel for construído (acima), ele vai fazer a chamada automaticamente
    //dando tudo certo, ele estimula o LiveData que é o responsável por "avisar" a nossa tela que a lista
    //esta pronta para ser exibida


    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()) {
                    news.setValue(response.body());
                } else {
                    //  TODO pensar em uma estratégia de tratamento de erros
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //  TODO pensar em uma estratégia de tratamento de erros

            }
        });
    }

    public LiveData<List<News>> getNews() { return this.news; }
}