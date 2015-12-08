package dev.trident.googlespreadsheet.spreadsheetclient;

import com.pras.SpreadSheetFactory;

import dev.trident.googlespreadsheet.spreadsheetclient.callback.SpreadsheetClientPrepareCallback;
import dev.trident.googlespreadsheet.spreadsheetclient.callback.SpreadsheetCreateCallback;
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
                try {
                    factory = SpreadSheetFactory.getInstance(authenticator);
                    callback.onPrepared();
                } catch (Exception e){
                    callback.onError(e.getMessage());
                }

            }
        };
        doInBackground(getSpreadsheetFactoryRunnable);

    }

    public void getSpreadsheets(final SpreadsheetListCallback callback)
    {

        Runnable getSpreadsheetsRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    callback.onResult(factory.getAllSpreadSheets());
                } catch (Exception e){
                    callback.onError(e.getMessage());
                }

            }
        };
        doInBackground(getSpreadsheetsRunnable);
    }

    public void createSpreadsheet(final String name,final SpreadsheetCreateCallback callback)
    {
        Runnable getSpreadsheetsRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    factory.createSpreadSheet(name);
                    callback.onCreated(name);
                } catch (Exception e){
                    callback.onError(e.getMessage());
                }

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
