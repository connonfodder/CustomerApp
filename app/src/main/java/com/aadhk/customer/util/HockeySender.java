package com.aadhk.customer.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HockeySender implements ReportSender {
    private static final String BASE_URL = "https://rink.hockeyapp.net/api/2/apps/";
    private static final String CRASHES_PATH = "/crashes";

    private String createCrashLog(CrashReportData report, Context context) {
        Date now = new Date();
        StringBuilder log = new StringBuilder();

        log.append("Package: ").append(report.get(ReportField.PACKAGE_NAME)).append("\n");
        log.append("Version: ").append(report.get(ReportField.APP_VERSION_CODE)).append("\n");
        log.append("Android: ").append(report.get(ReportField.ANDROID_VERSION)).append("\n");
        log.append("Manufacturer: ").append(android.os.Build.MANUFACTURER).append("\n");
        log.append("Model: ").append(android.os.Build.MODEL).append("\n");

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        log.append("display: ").append(size.x+"x"+size.y).append("\n");
        log.append("Default Locale: ").append(Locale.getDefault()).append("\n");
        log.append("Date: ").append(now).append("\n");
        log.append("\n");
        log.append("custom data:"+report.get(ReportField.CUSTOM_DATA));
        log.append("\n");
        log.append(report.get(ReportField.STACK_TRACE));

        return log.toString();
    }

    @Override
    public void send(Context arg0, CrashReportData report) throws ReportSenderException {
        String log = createCrashLog(report, arg0);
        String url = BASE_URL + ACRA.getConfig().formUri() + CRASHES_PATH;

        try {

            postValuePair(url,new String[]{"raw", log},new String[]{"userID",report.get(ReportField.INSTALLATION_ID)},new String[]{"contact",report.get(ReportField.USER_EMAIL)},new String[]{"description",report.get(ReportField.USER_COMMENT)});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //postValuePair("www.google.com",new String["username","abc"],new String["password","123"]);
    public String postValuePair(String url,String[]... namevaluepairs) throws IOException{

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder();
        for(String[] namevaluepair:namevaluepairs){
            if(!TextUtils.isEmpty(namevaluepair[0])&& !TextUtils.isEmpty(namevaluepair[1])){
                formBuilder.add(namevaluepair[0], namevaluepair[1]);
            }
        }
        RequestBody body = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body).
                        build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
