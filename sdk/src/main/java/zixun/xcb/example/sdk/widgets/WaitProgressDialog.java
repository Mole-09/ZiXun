package zixun.xcb.example.sdk.widgets;

import android.app.ProgressDialog;
import android.content.Context;

public class WaitProgressDialog extends ProgressDialog {

    public WaitProgressDialog(Context context){
        this(context,0);
    }

    public WaitProgressDialog(Context context,int themes){
        super(context,themes);
        setCanceledOnTouchOutside(false);
    }
}
