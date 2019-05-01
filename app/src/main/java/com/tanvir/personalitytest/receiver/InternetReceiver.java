package com.tanvir.personalitytest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.tanvir.personalitytest.model.PersonalityFormRepository;
import com.tanvir.personalitytest.util.NetworkUtil;
import com.tanvir.personalitytest.view.MainActivity;


public class InternetReceiver extends BroadcastReceiver {
    public  static String hasInternet;
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if(status.isEmpty()) {
            status="No Internet Connection";

        }
else {

    hasInternet = status;

        }
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        PersonalityFormRepository.internetState.setValue(status);
    }
}
