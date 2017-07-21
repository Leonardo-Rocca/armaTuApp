package com.lrocca.examplecardiag.persistence;

import android.provider.BaseColumns;

/**
 * Created by lrocca on 14/07/2017.
 */

public final  class StepsContract {

    private StepsContract() {}

    public static class StepEntry implements BaseColumns {
        public static final String TABLE_NAME ="step";

        public static final String ID = "id_step";
        public static final String DESCRIPTION = "description";
        public static final String PATH = "path";
        public static final String ORDER = "step_order";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StepEntry.TABLE_NAME + " (" +
                    StepEntry._ID + " INTEGER PRIMARY KEY," +
                    StepEntry.DESCRIPTION+ TEXT_TYPE + COMMA_SEP +
                    StepEntry.PATH + TEXT_TYPE + COMMA_SEP +
                    StepEntry.ORDER +" INTEGER"+ " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StepEntry.TABLE_NAME;



    public static final String SQL_CREATE_STEP_SOL_ENTRIES =
            "CREATE TABLE " + StepSolEntry.TABLE_NAME + " (" +
                    StepSolEntry.IDSOL + " INTEGER NOT NULL," +
                    StepSolEntry.IDSTEP+ " INTEGER NOT NULL,"+
                    StepSolEntry.ORDER +" INTEGER,"+
                    " PRIMARY KEY(patente,horallegada)"+ " )";


    public static class StepSolEntry implements BaseColumns {
        public static final String TABLE_NAME ="step_solution";

        public static final String IDSTEP = "id_step";
        public static final String IDSOL = "id_solution";
        public static final String ORDER = "step_order";
    }


    public static class SolutionEntry implements BaseColumns {
        public static final String TABLE_NAME ="solution";

        public static final String IDSOL = "id_solution";
        public static final String DESCRIPTION = "description";  //Va??
        public static final String NAME = "name";
        public static final String PRIORITY = "priority";
    }

}
