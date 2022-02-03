package com.example.q2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.CellIdentity;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView cellidTV;
    int cellID=0;
    //cellInfo instanceof CellInfoLte;
//CellIdentityLte cellId = ((CellInfoLte) cellInfo).getCellIdentity();
    //CellIdentityLte cellIdentityLte = new CellIdentity=();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cellidTV = (TextView) findViewById(R.id.cellidtextbox);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        List<CellInfo> cellInfoList = tm.getAllCellInfo();

        for (CellInfo cellInfo : cellInfoList)
        {
            if (cellInfo instanceof CellInfoLte) {
                CellIdentityLte cellIdentityLte = ((CellInfoLte) cellInfo).getCellIdentity();
                cellID = cellIdentityLte.getCi();
                if (cellIdentityLte.getCi() == 0 || cellIdentityLte.getCi() ==Integer.MAX_VALUE)
                {
                    cellID = cellIdentityLte.getCi();
                }
            }
        }
        cellidTV.setText("myid "+String.valueOf(cellID));
    }
}