package sodexo.pe.com.sodexo.presentation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;

/**
 * Created by RONALD on 09/10/2016.
 */

public class ProgressCustomDialog extends DialogFragment {

    @BindView(R.id.iv_progress)
    ImageView ivProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.DialogFullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        ivProgress.setBackgroundResource(R.drawable.animation_progress);
        AnimationDrawable frameAnimation = (AnimationDrawable) ivProgress.getBackground();
        frameAnimation.start();
    }
}
