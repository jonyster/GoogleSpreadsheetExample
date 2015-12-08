package dev.trident.googlespreadsheet;

import android.os.AsyncTask;
import android.util.Log;

import com.pras.SpreadSheet;
import com.pras.WorkSheet;
import com.pras.WorkSheetCell;
import com.pras.WorkSheetRow;
import com.pras.sp.Entry;
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




        // add worksheet
        WorkSheet workSheet = addWorksheet(spreadSheet,"worksheet-test",new String[]{"a","b","c"});

        //create the row data
        HashMap<String, String> row_data = new HashMap<String, String>();
        row_data.put("a", "1");
        row_data.put("b", "Samsung Ace");
        row_data.put("c", "Samsung");

        //add the row
        WorkSheetRow workSheetRow = addTheRowToTheWorksheet(workSheet,row_data);

        //create the updated data
        HashMap<String, String> updatedValue = new HashMap<String, String>();
        updatedValue.put("a", "hello");  //specify the name of the column (for example "a")
        updatedValue.put("b", "android");
        updatedValue.put("c", "world");

        //update the row
        WorkSheetRow workSheetRowUpdated = editTheRow(spreadSheet, workSheet, updatedValue,workSheetRow);

//        //delete the row
//        deleteWorksheetRow(spreadSheet,workSheet,workSheetRowUpdated);


        return null;
    }

    /**
     *
     * @param spreadSheet - the file to work with
     * @param columns - the columns which will be created
     * @param worksheetName - the name of the worksheet
     * @return worksheet created in this file
     */
    public WorkSheet addWorksheet(SpreadSheet spreadSheet,String worksheetName,String[] columns)
    {
        return spreadSheet.addListWorkSheet(worksheetName, 10, new String[]{"a","b","c"});
    }

    /**
     *
     * @param workSheet - worksheet where the row will be added
     * @param row_data - the row which will be added
     * @return worksheet row
     */
    public WorkSheetRow addTheRowToTheWorksheet(WorkSheet workSheet,HashMap<String, String> row_data)
    {
        return workSheet.addListRow(row_data);
    }

    /**
     *
     * @param workSheet - worksheet where the row will be updated
     * @param row_data - new data
     * @param workSheetRow - the row which will be updated
     * @return the edited row
     */
    public WorkSheetRow editTheRow(SpreadSheet spreadSheet,WorkSheet workSheet,HashMap<String, String> row_data,WorkSheetRow workSheetRow)
    {
        return workSheet.updateListRow(spreadSheet.getKey(), workSheetRow, row_data);
    }

    /**
     *
     * @param spreadSheet - the file
     * @param workSheet - the worksheet
     * @param workSheetRow - the row to be deleted
     */
    public void deleteWorksheetRow(SpreadSheet spreadSheet,WorkSheet workSheet,WorkSheetRow workSheetRow)
    {
        workSheet.deleteListRow(spreadSheet.getKey(), workSheetRow);
    }





}
