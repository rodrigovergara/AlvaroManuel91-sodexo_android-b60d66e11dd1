package sodexo.pe.com.sodexo.presentation.adapter;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.data.model.TokenResponse;
import sodexo.pe.com.sodexo.domain.entity.OptionIntranetEntity;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.activity.SplashActivity;
import sodexo.pe.com.sodexo.presentation.interfaces.LogOutInterface;
import sodexo.pe.com.sodexo.presentation.interfaces.OnClickOptions;
import sodexo.pe.com.sodexo.util.AlertUtil;

/**
 * Created by RONALD on 05/10/2016.
 */

public class RVOptionIntranetAdapter extends RecyclerView.Adapter<RVOptionIntranetAdapter.OptionIntranetViewHolder> {

    private List<OptionIntranetEntity> list;
    private Context context;
    private OnClickOptions onClickOptions;
    private LogOutInterface mListener;

    public RVOptionIntranetAdapter(Context context, OnClickOptions onClickOptions, Fragment fragment) {
        this.list = new ArrayList<>();
        this.context = context;
        mListener = (LogOutInterface) fragment;
        this.onClickOptions = onClickOptions;
    }

    @Override
    public OptionIntranetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OptionIntranetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option_intranet, parent, false));
    }

    @Override
    public void onBindViewHolder(OptionIntranetViewHolder holder, int position) {
        if (position < list.size()) {
            final OptionIntranetEntity optionIntranetEntity = list.get(position);
            holder.ivBackgroundOptionIntranet.setBackgroundColor(Color.parseColor(optionIntranetEntity.getImageBackground()));
            Glide.with(context).load(optionIntranetEntity.getUrlImage()).into(holder.ivOptionIntranet);
            holder.tvOptionIntranet.setText(optionIntranetEntity.getTitle());
            holder.ivBackgroundOptionIntranet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickOptions.clickListener(optionIntranetEntity.getId());
                }
            });
        } else {
            holder.ivBackgroundOptionIntranet.setBackgroundColor(Color.GRAY);
            holder.ivOptionIntranet.setImageResource(R.drawable.cerrar_sesion);
            holder.tvOptionIntranet.setText("Cerrar sesión");
            holder.ivBackgroundOptionIntranet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("intranet", "cerrar sesion");
                    if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) == null) {
                        AlertUtil.showMessageAccept(context, "", "Debe logearse primero para luego cerrar sesión", "Aceptar", null);
                    } else {
                        askForConfirmation();
                    }
                }
            });
        }
    }

    private void askForConfirmation() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes log out
                        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
                            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
                            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
                            if (!TextUtils.isEmpty(data.getDni())) {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("Usuario", data.getDni());
                                params.put("AppToken", "");
                                params.put("Dispositivo", "AND");
                                Call<TokenResponse> call = ApiClient.getSodexoApiClient().postAppToken(params);
                                call.enqueue(new Callback<TokenResponse>() {
                                    @Override
                                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                                        if (response.body().getUserId() != 0) {
                                            PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).edit().putBoolean("token_sent", false).apply();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<TokenResponse> call, Throwable t) {

                                    }
                                });
                            }
                        }

                        PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).edit().putString(SodexoApplication.USER_DATA, null).commit();
                        AlertUtil.showMessageAccept(context, "", "Su sesión a sido cerrada correctamente", "Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                context.startActivity(new Intent(context, SplashActivity.class));
                                dialogInterface.dismiss();


                                NotificationManager notificationManager = (NotificationManager) context
                                        .getSystemService(Context.NOTIFICATION_SERVICE);
                                notificationManager.cancelAll();

                                mListener.logout();
                            }
                        });
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No mistake
                        break;
                }
            }
        };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.log_out_confirmation)).setPositiveButton(context.getString(R.string.yes), dialogClickListener)
                .setNegativeButton(context.getString(R.string.no), dialogClickListener).setCancelable(false).show();
    }

    public void addOptions(List<OptionIntranetEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    class OptionIntranetViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_background_option_intranet)
        ImageView ivBackgroundOptionIntranet;
        @BindView(R.id.iv_option_intranet)
        ImageView ivOptionIntranet;
        @BindView(R.id.tv_option_intranet)
        TextView tvOptionIntranet;

        public OptionIntranetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
