package com.example.listview_fab;

import android.provider.BaseColumns;

public class TasbihHelper {
    private TasbihHelper(){}
    public static final class TasbihEntry implements BaseColumns{
        public static final String TABLE_NAME = "tasbihList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
    }
}
