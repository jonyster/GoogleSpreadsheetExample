package dev.trident.googlespreadsheet;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.AccountPicker;
import com.pras.SpreadSheet;

import java.util.ArrayList;

import dev.trident.googlespreadsheet.spreadsheetclient.AndroidAuthenticator;
import dev.trident.googlespreadsheet.spreadsheetclient.MySpreadsheetClient;
import dev.trident.googlespreadsheet.spreadsheetclient.callback.SpreadsheetClientPrepareCallback;
import dev.trident.googlespreadsheet.spreadsheetclient.callback.SpreadsheetCreateCallback;
import dev.trident.googlespreadsheet.spreadsheetclient.callback.SpreadsheetListCallback;

public class MainActivity extends AppCompatActivity {

    AccountManager manager;
    private final String TAG = "MainActivity";
    String mSpreadsheetName = "sheet"+System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = AccountManager.get(getApplicationContext());

        // let's get the list of the device' accounts and authenticate for using google sheets
        Account[] acs = manager.getAccountsByType("com.google");
		Log.i(TAG, "Num of Matching account: " + acs.length);

		if( acs.length == 0){
			Toast.makeText(getApplicationContext(), "No Google Account Added...", Toast.LENGTH_LONG).show();
		} else {
            //perform the authentication for the first account
            authenticateAccount(acs[0]);
        }
    }


    /**
     * Use this method to login for Google Spreadsheets
     * @param account - gmail account, which will be used to authenticate
     */
    private void authenticateAccount(Account account)
    {
        // create AndroidAuthenticator for performing the authentication
        // chose the account for authenticate. Here we will use the first account from the list
        AndroidAuthenticator androidAuthenticator = new AndroidAuthenticator(this,account,manager);


        //init the connection to the spreadsheets
        MySpreadsheetClient.getInstance().init(androidAuthenticator, new SpreadsheetClientPrepareCallback() {
            @Override
            public void onPrepared() {

                // create the new spreadsheet
                MySpreadsheetClient.getInstance().createSpreadsheet(mSpreadsheetName, new SpreadsheetCreateCallback() {
                    @Override
                    public void onCreated(String name) {
                        Log.d(TAG, "spreadsheet " + name + " successfully created");
                        //once spreadsheet created, get the list of the spreadsheets and select required one to perform actions
                        MySpreadsheetClient.getInstance().getSpreadsheets(new SpreadsheetListCallback() {
                            @Override
                            public void onError(String message) {
                                Log.e(TAG, message);
                            }

                            @Override
                            public void onResult(ArrayList<SpreadSheet> spreadSheets) {
                                SpreadSheet createdSpreadsheet = null;
                                for (SpreadSheet spreadSheet : spreadSheets) {
                                    //display the list of the spreadsheets
                                    Log.d(TAG, spreadSheet.getTitle());
                                    if (spreadSheet.getTitle().equals(mSpreadsheetName))
                                        createdSpreadsheet = spreadSheet;
                                }
                                if (createdSpreadsheet != null) {
                                    Log.d(TAG, "found the spreadsheet");
                                    // you can view the example of usage the code in WorkWithSpreadsheetExample asyncTask
                                    WorkWithSpreadsheetExample example = new WorkWithSpreadsheetExample(createdSpreadsheet);
                                    example.execute();
                                }


                            }
                        });
                    }

                    @Override
                    public void onError(String message) {
                        Log.e(TAG, "spreadsheet wasn't  created");
                    }
                });

            }

            @Override
            public void onError(String message) {
                Log.e(TAG, message);
            }
        });
    }


}
