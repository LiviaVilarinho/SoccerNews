package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    //a intenção foi criar um LiveData onde retornasse a lista de notícias
    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //criando um mock de dados
        //TODO remover mock de notícias
        List<News> news  = new ArrayList<>();
        news.add(new News("Ferroviária tem Desfalque Importante", "No jogo de hoje, a atleta Marta se machucou e acabou tendo uma lesão no joelho direito e irá passar por exames na tarde de amanhã."));
        news.add(new News("Ferrinha Joga no Sábado", "O jogo está marcado para as 15h de sábado e conta com a presença em massa dos torcedores."));
        news.add(new News("Copa do Mundo Feminina Está Terminando", "O Brasil segue bem na competição e jogará a disputa de semi-final no domingo contra os EUA."));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {

        return this.news;
    }
}