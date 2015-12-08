package dev.trident.googlespreadsheet.spreadsheetclient;

import com.pras.SpreadSheetFactory;

import dev.trident.googlespreadsheet.spreadsheetclient.callback.SpreadsheetClientPrepareCallback;
import dev.trident.googlespreadsheet.spreadsheetclient.callback.SpreadsheetListCallback;

/**
 * The class for interacting with google spreadsheets
 * Implements singleton pattern
 * Worldtrack 08.12.15.
 */
public class MySpreadsheetClient {

    private static MySpreadsheetClient _instance = new MySpreadsheetClient();

    public static MySpreadsheetClient getInstance(){return _instance;}

    private MySpreadsheetClient() {}

    AndroidAuthenticator authenticator;
    SpreadSheetFactory factory;


    public void init(final AndroidAuthenticator authenticator, final SpreadsheetClientPrepareCallback callback)
    {
        this.authenticator = authenticator;
        Runnable getSpreadsheetFactoryRunnable = new Runnable() {
            @Override
            public void run() {
                factory = SpreadSheetFactory.getInstance(authenticator);
                callback.onPrepared();
            }
        };
        doInBackground(getSpreadsheetFactoryRunnable);

    }

    public void getSpreadsheets(final SpreadsheetListCallback callback)
    {

        Runnable getSpreadsheetsRunnable = new Runnable() {
            @Override
            public void run() {
                callback.onResult(factory.getAllSpreadSheets());
            }
        };
        doInBackground(getSpreadsheetsRunnable);
    }


    private void doInBackground(Runnable runnable)
    {
        Thread thread = new Thread(runnable);
        thread.start();
    }


}
