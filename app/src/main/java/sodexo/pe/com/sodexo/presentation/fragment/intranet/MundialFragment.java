package sodexo.pe.com.sodexo.presentation.fragment.intranet;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tismart.tsmviews.utils.AlertUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.adapter.RVBlogListAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.MundialView;
import sodexo.pe.com.sodexo.presentation.presenter.BlogListPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.BlogListPresenterImplement;

/**
 * Created by Bit on 14/06/2018.
 */

public class MundialFragment extends Fragment implements MundialView {

    @BindView(R.id.webMundial)
    WebView webMundial;

    private MainView view;
    private ProgressCustomDialog progress;
    private String url;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("entra a fra,emte","entra");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("entra a fra,emte","entra");
        View view = inflater.inflate(R.layout.fragment_mundial, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("entra a fra,emte","entra");
        if (context instanceof MainView) {
            view = (MainView) context;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String usuario="";
        String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
        Log.d("usuario per",SodexoApplication.USER_DATA.toString());
        LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
        Log.d("dnieee",data.getDni().toString());
        usuario=data.getDni().toString();
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getBoolean(SodexoApplication.SAVE, false)) {
            usuario=PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString("usuario", "");
        }
        url= obtenerURL(usuario,"VAMOS.PERU!!!");
        Log.d("La url es"," "+url);
        Log.d("entra a fra,emte","entra");
        webMundial.loadUrl(url);
        webMundial.getSettings().setJavaScriptEnabled(true);
        webMundial.setWebViewClient(new WebViewClient());
    }

    public String obtenerURL(String UsuarioId, String palabra)
    {
        String UsuMasVMP = UsuarioId+palabra;
        String url="";
        String UsuMD5=md5(UsuMasVMP);
        System.out.println(UsuMD5);
        url="http://sodexoclub.com.pe//Mundial/FixtureWapper?u="+UsuarioId+"&h="+UsuMD5;
        System.out.println(url);
        return url;
    }

    public String md5(String s) {
        String resultMd5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] result = new byte[md5.getDigestLength()];
            md5.reset();
            md5.update(s.getBytes());
            result = md5.digest();

            StringBuffer buf = new StringBuffer(result.length * 2);

            for (int i = 0; i < result.length; i++) {
                int intVal = result[i] & 0xff;
                if (intVal < 0x10) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(intVal));
            }

            resultMd5 = buf.toString().toUpperCase();
            return resultMd5;
        } catch (NoSuchAlgorithmException e) {
        }

        return resultMd5;
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.d("entra a fra,emte","entra");
    }


    @Override
    public void showLoading() {
        if (progress == null) {
            progress = new ProgressCustomDialog();
        }
        progress.show(getFragmentManager(), ProgressCustomDialog.class.getName());
    }

    @Override
    public void openMundial() {
        Log.d("Tag","Mundial");
    }

    @Override
    public void hideLoading() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    @Override
    public void showError(String error) {
        AlertUtils.showMessageAccept(getActivity(), null, error, "ACEPTAR", null);
    }



    @OnClick(R.id.iv_back)
    public void back() {
        view.openIntranetOption();
    }
    }


