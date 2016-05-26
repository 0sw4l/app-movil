package com.oswal.app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Methods extends Activity {

    public static boolean HayInternet(Context self) {
        boolean conectado = false;
        ConnectivityManager estados =
                (ConnectivityManager) self.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = estados.getAllNetworkInfo();
        for (int i = 0; i < 2; i++)
            if (redes[i].getState() == NetworkInfo.State.CONNECTED)
                conectado = true;
        return conectado;
    }

    public static void Toast(Context c, String s){
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }


}
