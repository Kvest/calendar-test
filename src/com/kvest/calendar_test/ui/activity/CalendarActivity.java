package com.kvest.calendar_test.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.kvest.calendar_test.R;
import com.kvest.calendar_test.ui.adapter.CalendarAdapter;

public class CalendarActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        CalendarAdapter adapter = new CalendarAdapter(this);
        ((ListView)findViewById(R.id.calendar_view)).setAdapter(adapter);
    }
}
