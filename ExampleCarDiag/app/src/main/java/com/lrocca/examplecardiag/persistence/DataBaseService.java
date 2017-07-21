package com.lrocca.examplecardiag.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lrocca.examplecardiag.Solution;
import com.lrocca.examplecardiag.Step;

import java.util.ArrayList;

import static com.lrocca.examplecardiag.persistence.StepsContract.*;

/**
 * Created by lrocca on 14/07/2017.
 */

public class DataBaseService extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "CarDiag.db";
//    public static final String DATABASE_NAME = "CarDiag";

    public DataBaseService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    private void insertSteps(ArrayList<Step> steps) {

       for (Step s : steps){
           this.insertStep(s);
       }
        this.insertSolution(  new Solution("Solucion",new ArrayList<Step>()));
    }

    private void insertSolution(Solution solution) {
      this.insertSteps(solution.getSteps());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SolutionEntry.DESCRIPTION, " ");
        values.put(SolutionEntry.NAME, solution.getName());
        values.put(SolutionEntry.PRIORITY,String.valueOf(solution.getPriority()));
// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SolutionEntry.TABLE_NAME, null, values);
        solution.setId(newRowId);
        this.insertStepSolutions(solution);
        db.close();
    }

    private void insertStepSolutions(Solution solution) {
        for (Step s :solution.getSteps()){

        }
    }

    public void insertDummySteps() {
        this.insertStep(new Step("s0101","Revisar cuidadosamente el circuito del banco 1 / circuito de VCT(sincronización variable del árbol de levas)"+
                ", sistema de cableado y conectores, como lo indica el manual de reparación"));
        this.insertStep( new Step("s0102","Con el motor caliente, cheque la operación de la OCV (válvula de control)"));
        this.insertStep( new Step("s0103","sustitución / repare o reemplace según sea necesario"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private void insertStep(Step step) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StepEntry.DESCRIPTION, step.getDesc());
        values.put(StepEntry.PATH, step.getImgId());
        values.put(StepEntry.ORDER,String.valueOf(step.getPosition()));
// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(StepEntry.TABLE_NAME, null, values);
        step.setId(newRowId);
        db.close();
    }


    public ArrayList<Step> getSteps(int id) {
       SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Step> ls = new ArrayList<Step>();
// Define a projection that specifies which columns from the database you will actually use after this query.
     String[] projection = {
                StepEntry._ID,
                StepEntry.PATH,
                StepEntry.DESCRIPTION,
                StepEntry.ORDER
        };
// Filter results WHERE "title" = 'My Title'
    //    String selection = StepEntry._ID + " != 100 ";
        String selection =" 100 = 100 ";
        String[] selectionArgs = { "100" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                StepEntry.ORDER + " ASC";

        Cursor c = db.query(
                StepEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
             null,//   selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
            sortOrder                                 // The sort order
        );
        int index=0;

        if (c.moveToFirst()) {
            do {
                Step  step = new Step( c.getString(1),c.getString(2));
                step.setPosition(index); index++;
                ls.add(step);
            } while(c.moveToNext());
        }
        db.close();
        return ls;
    }
}
