package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.interactor.BlogListInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.GetBlogListInterface;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public class BlogListInteractorImplement implements BlogListInteractor {
    private IntranetRepository repository;

    public BlogListInteractorImplement(IntranetRepository repository) {
        this.repository = repository;
    }


    @Override
    public void getBlogList(GetBlogListInterface getBlogListInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.getBlogList(data.getDni(), getBlogListInterface);
        }
    }
}

