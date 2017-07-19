package sodexo.pe.com.sodexo.util;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;

public class AlertUtil {
    private static AlertDialog setBackground(AlertDialog.Builder mBuilder) {
        AlertDialog mAlertDialog = mBuilder.create();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return mAlertDialog;
    }

    private static void showAlertDialog(AlertDialog.Builder mBuilder, int resStyle) {
        if (resStyle != 0) {
            setBackground(mBuilder).show();
        } else {
            mBuilder.create().show();
        }
    }

    private static AlertDialog.Builder createBuilder(Context context, int resStyle) {
        AlertDialog.Builder builder;
        if (resStyle != 0) {
            builder = new AlertDialog.Builder(context, resStyle);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        return builder;
    }

    public static void showMessageAccept(Context context, int resStyle, Object titleText, Object messageText, Object positiveButtonText, DialogInterface.OnClickListener positiveButtonOnClick) {
        AlertDialog.Builder builder = createBuilder(context,resStyle);

        String title = null;
        if (titleText instanceof Integer) {
            title = context.getString((int)titleText);
        } else if (titleText instanceof String) {
            title = (String)titleText;
        }
        if (title != null) {
            builder.setTitle(title);
        }

        String message = null;
        if (messageText instanceof Integer) {
            message = context.getString((int)messageText);
        } else if (messageText instanceof String) {
            message = (String)messageText;
        }
        if (message != null) {
            builder.setMessage(message);
        }

        if (positiveButtonText != null) {
            String positiveText = null;
            if (positiveButtonText instanceof Integer) {
                positiveText = context.getString((int) positiveButtonText);
            } else if (positiveButtonText instanceof String) {
                positiveText = (String) positiveButtonText;
            }
            if (positiveText != null) {
                builder.setPositiveButton(positiveText,positiveButtonOnClick);
            }
        }

        builder.setCancelable(false);
        showAlertDialog(builder,resStyle);
    }

    public static void showMessageAccept(Context context, Object titleText, Object messageText, Object positiveButtonText, DialogInterface.OnClickListener positiveButtonOnClick) {
        showMessageAccept(context,0,titleText,messageText,positiveButtonText,positiveButtonOnClick);
    }

    public static void showMessageNeutral(Context context, int resStyle, Object titleText, Object messageText, Object neutralButtonText, DialogInterface.OnClickListener neutralButtonOnClick) {
        AlertDialog.Builder builder = createBuilder(context,resStyle);

        String title = null;
        if (titleText instanceof Integer) {
            title = context.getString((int)titleText);
        } else if (titleText instanceof String) {
            title = (String)titleText;
        }
        if (title != null) {
            builder.setTitle(title);
        }

        String message = null;
        if (messageText instanceof Integer) {
            message = context.getString((int)messageText);
        } else if (messageText instanceof String) {
            message = (String)messageText;
        }
        if (message != null) {
            builder.setMessage(message);
        }

        if (neutralButtonText != null) {
            String neutralText = null;
            if (neutralButtonText instanceof Integer) {
                neutralText = context.getString((int) neutralButtonText);
            } else if (neutralButtonText instanceof String) {
                neutralText = (String) neutralButtonText;
            }
            if (neutralText != null) {
                builder.setNeutralButton(neutralText, neutralButtonOnClick);
            }
        }

        builder.setCancelable(false);
        showAlertDialog(builder,resStyle);
    }

    public static void showMessageNeutral(Context context, Object titleText, Object messageText, Object neutralButtonText, DialogInterface.OnClickListener neutralButtonOnClick) {
        showMessageNeutral(context,0, titleText, messageText,neutralButtonText, neutralButtonOnClick);
    }

    public static void showMessageAcceptNegative(Context context, int resStyle, Object titleText, Object messageText, Object positiveButtonText, DialogInterface.OnClickListener positiveButtonOnClick, Object negativeButtonText, DialogInterface.OnClickListener negativeButtonOnClick) {
        AlertDialog.Builder builder = createBuilder(context,resStyle);

        String title = null;
        if (titleText instanceof Integer) {
            title = context.getString((int)titleText);
        } else if (titleText instanceof String) {
            title = (String)titleText;
        }
        if (title != null) {
            builder.setTitle(title);
        }

        String message = null;
        if (messageText instanceof Integer) {
            message = context.getString((int)messageText);
        } else if (messageText instanceof String) {
            message = (String)messageText;
        }
        if (message != null) {
            builder.setMessage(message);
        }

        if (positiveButtonText != null) {
            String positiveText = null;
            if (positiveButtonText instanceof Integer) {
                positiveText = context.getString((int) positiveButtonText);
            } else if (positiveButtonText instanceof String) {
                positiveText = (String) positiveButtonText;
            }
            if (positiveText != null) {
                builder.setPositiveButton(positiveText,positiveButtonOnClick);
            }
        }

        if (negativeButtonText != null) {
            String negativeText = null;
            if (negativeButtonText instanceof Integer) {
                negativeText = context.getString((int) negativeButtonText);
            } else if (negativeButtonText instanceof String) {
                negativeText = (String) negativeButtonText;
            }
            if (negativeText != null) {
                builder.setNegativeButton(negativeText,negativeButtonOnClick);
            }
        }

        builder.setCancelable(false);
        showAlertDialog(builder,resStyle);
    }

    public static void showMessageAcceptNegative(Context context, Object titleText, Object messageText, Object positiveButtonText, DialogInterface.OnClickListener positiveButtonOnClick, Object negativeButtonText, DialogInterface.OnClickListener negativeButtonOnClick) {
        showMessageAcceptNegative(context, 0, titleText, messageText, positiveButtonText, positiveButtonOnClick, negativeButtonText, negativeButtonOnClick);
    }

}
