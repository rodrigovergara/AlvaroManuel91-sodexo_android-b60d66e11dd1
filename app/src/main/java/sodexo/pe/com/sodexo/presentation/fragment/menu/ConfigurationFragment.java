package sodexo.pe.com.sodexo.presentation.fragment.menu;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.activity.SplashActivity;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;

public class ConfigurationFragment extends MenuBaseFragment {

    @BindView(R.id.cb_intranet)
    CheckBox cbIntranet;
    /*@BindView(R.id.tv_close_session)
    TextView tvCloseSession;*/
    private Context ctx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuration, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getBoolean(SodexoApplication.SAVE, false)) {
            cbIntranet.setChecked(true);
        } else {
            cbIntranet.setChecked(false);
        }
        cbIntranet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).edit().putBoolean(SodexoApplication.SAVE, b).commit();
            }
        });
        /*tvCloseSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) == null) {
                    AlertUtil.showMessageAccept(getActivity(), "", "Debe logearse primero para luego cerrar sesión", "Aceptar", null);
                } else {
                    askForConfirmation();
                }
            }
        });*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;
    }

    private void askForConfirmation() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes log out
                        PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).edit().putString(SodexoApplication.USER_DATA, null).commit();
                        AlertUtil.showMessageAccept(getActivity(), "", "Su sesión a sido cerrada correctamente", "Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getActivity().startActivity(new Intent(getActivity(), SplashActivity.class));
                                dialogInterface.dismiss();
                                getActivity().finish();
                            }
                        });
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No mistake
                        break;
                }
            }
        };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ctx);
        builder.setMessage(ctx.getString(R.string.log_out_confirmation)).setPositiveButton(ctx.getString(R.string.yes), dialogClickListener)
                .setNegativeButton(ctx.getString(R.string.no), dialogClickListener).setCancelable(false).show();
    }
}
