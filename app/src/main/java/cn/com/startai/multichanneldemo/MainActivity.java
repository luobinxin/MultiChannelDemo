package cn.com.startai.multichanneldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String packageName = getApplication().getPackageName();
        setContentView(R.layout.activity_main);
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + getApplicationContext().getFilesDir());
        sb.append("\n" + getApplicationContext().getExternalCacheDir());
        sb.append("\n" + SignUtil.getAppSignatureMD5(getApplicationContext(), packageName));
        sb.append("\n" + SignUtil.getAppSignatureSHA1(getApplicationContext(), packageName));
        sb.append("\n" + SignUtil.getAppSignatureSHA256(getApplicationContext(), packageName));
        sb.append("\n" + getAppKey(getApplication(), "channel"));
        sb.append("\n" + BuildConfig.FLAVOR_evn);
        sb.append("\n" + BuildConfig.FLAVOR_app);
        sb.append("\n" + BuildConfig.FLAVOR);
        System.out.println(sb.toString());
        ((TextView) findViewById(R.id.tv)).setText(sb.toString());

    }


    public static String getAppKey(Context c, String appKey) {
        try {
            ApplicationInfo ai = c.getPackageManager().getApplicationInfo(
                    c.getPackageName(), PackageManager.GET_META_DATA);
            Object EP_APPKEY = ai.metaData.get(appKey);
            if (EP_APPKEY instanceof Integer) {
                long longValue = ((Integer) EP_APPKEY).longValue();
                String value = String.valueOf(longValue);
                return value;
            } else if (EP_APPKEY instanceof String) {
                String value = String.valueOf(EP_APPKEY);
                return value;
            }
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
        }
        return null;
    }

}
