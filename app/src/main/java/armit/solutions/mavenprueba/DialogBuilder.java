package armit.solutions.mavenprueba;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.view.ContextThemeWrapper;


public class DialogBuilder {

    public static ProgressDialog showProgressDialog(String message, Context context, boolean isCancelable){
        ProgressDialog progressDialog =  new ProgressDialog(context);
        progressDialog.setTitle("");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(isCancelable);
        progressDialog.show();
        return progressDialog;
    }


    public static AlertDialog showAlertDialog(String message, String button, Context context, boolean isCancelable) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton(button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setCancelable(isCancelable);
        alert.show();
        return alert;
    }

    public static AlertDialog showAlertDialog(String message, String button, Context context, boolean isCancelable, final SimpleAlertAction action) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        action.OnAcept(dialog, id);
                    }
                });
        AlertDialog alert = builder.create();
        alert.setCancelable(isCancelable);
        alert.show();
        return alert;
    }

    public static AlertDialog showQuestionDialog(String message, String buttonPositive, String buttonNegative, Context context, boolean isCancelable, final AlertAction action) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        action.PositiveMethod(dialog, id);
                    }
                })
                .setNegativeButton(buttonNegative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        action.NegativeMethod(dialog, id);
                    }
                });
        AlertDialog alert = builder.create();
        alert.setCancelable(isCancelable);
        if (isCancelable) {
            alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    action.onCancel();
                }
            });
        }
        alert.show();
        return alert;
    }


    public interface SingleChoiceAlertAction {
        void PositiveMethod(DialogInterface dialog, int id, int itemSelected);
        void NegativeMethod(DialogInterface dialog, int id);
    }

    public interface SelectAlertAction {
        void onSelect(DialogInterface dialog, int itemSelected);
    }

    public interface SimpleAlertAction {
        void OnAcept(DialogInterface dialog, int id);
    }

    public interface AlertAction {
        void PositiveMethod(DialogInterface dialog, int id);
        void NegativeMethod(DialogInterface dialog, int id);
        void onCancel();
    }

}
