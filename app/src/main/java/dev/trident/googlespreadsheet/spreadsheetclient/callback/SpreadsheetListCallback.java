package dev.trident.googlespreadsheet.spreadsheetclient.callback;

import com.pras.SpreadSheet;

import java.util.ArrayList;

/**
 * Worldtrack 08.12.15.
 */
public interface SpreadsheetListCallback extends SpreadsheetCallback{
    void onResult(ArrayList<SpreadSheet> spreadSheets);
}
