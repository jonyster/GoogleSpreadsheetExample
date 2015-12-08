package dev.trident.googlespreadsheet;

import android.os.AsyncTask;
import android.util.Log;

import com.pras.SpreadSheet;
import com.pras.WorkSheet;
import com.pras.WorkSheetCell;
import com.pras.WorkSheetRow;
import com.pras.table.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Worldtrack 08.12.15.
 */
public class WorkWithSpreadsheetExample extends AsyncTask<Void,Void,Void> {

    SpreadSheet spreadSheet;
    private static String TAG = "WorkWithSpreadsheetExample";
    public WorkWithSpreadsheetExample(SpreadSheet spreadSheet)
    {
        this.spreadSheet = spreadSheet;
    }
    @Override
    protected Void doInBackground(Void... params) {

//        /////////////////////////////////////////
//        // get the worksheets of the spreadsheet
//        ArrayList<WorkSheet> workSheets = spreadSheet.getAllWorkSheets();
//        for (WorkSheet workSheet : workSheets) {
//            Log.i("Worksheet", workSheet.getTitle());
//            // get the columns of the worksheet
//            String[] worksheetColumns = workSheet.getColumns();
//            for (String column : worksheetColumns) {
//                Log.i("Column", column);
//            }
//            // get the worksheet rows
//            ArrayList<WorkSheetRow> workSheetRows = workSheet.getData(false);
//            for (WorkSheetRow workSheetRow : workSheetRows) {
//                Log.i("Worksheetrows", workSheetRow.toString());
//                //get the worksheet row cells
//                ArrayList<WorkSheetCell> cells = workSheetRow.getCells();
//                for (WorkSheetCell cell : cells) {
//                    Log.i("WorksheetCells", cell.toString());
//                }
//            }
//
//
//        }
//        /////////////////////////////////////////

//        ////////////////////////////////////////
//        // add rows
//        HashMap<String, String> rs = new HashMap<String, String>();
//        rs.put("a", "1st Jan 2011");  //specify the name of the column (for example "a")
//        rs.put("b", "Item3");
//        rs.put("c", "250");
//        WorkSheetRow addedRow = workSheets.get(0).addListRow(rs);
//        ///////////////////////////////////////


//        /////////////////////////////////////////
//        // delete the record
//       workSheets.get(0).deleteListRow(spreadSheet.getKey(),addedRow);

//        /////////////////////////////////////////
//        //update the record
//        HashMap<String, String> updatedValue = new HashMap<String, String>();
//        updatedValue.put("a", "hello");  //specify the name of the column (for example "a")
//        updatedValue.put("b", "android");
//        updatedValue.put("c", "world");
//        workSheets.get(0).updateListRow(spreadSheet.getKey(),addedRow,updatedValue);
//        ////////////////////////////////////////


        return null;
    }


}
