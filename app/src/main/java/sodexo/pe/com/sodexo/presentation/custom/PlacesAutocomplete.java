package sodexo.pe.com.sodexo.presentation.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by RONALD on 19/08/2016.
 */
public class PlacesAutocomplete extends AutoCompleteTextView {
    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int DEFAULT_AUTOCOMPLETE_DELAY = 750;

    private int mAutoCompleteDelay = DEFAULT_AUTOCOMPLETE_DELAY;
    private ProgressBar mLoadingIndicator;
    private ImageView ivCancel;

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            PlacesAutocomplete.super.performFiltering((CharSequence) msg.obj, msg.arg1);
        }
    };


    public PlacesAutocomplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        handler.removeMessages(MESSAGE_TEXT_CHANGED);
        handler.sendMessageDelayed(handler.obtainMessage(MESSAGE_TEXT_CHANGED, text), mAutoCompleteDelay);
    }

    @Override
    public void onFilterComplete(int count) {
        super.onFilterComplete(count);
    }
}
