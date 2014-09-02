package com.kvest.calendar_test.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kvest.calendar_test.R;
import com.kvest.calendar_test.datamodel.*;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 02.09.14
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
public class CalendarAdapter extends BaseAdapter {
    private Context context;
    private List<CalendarBaseEntity> items;

    private int calendarDividerHeight;
    private int monthTitlePadding;

    public CalendarAdapter(Context context) {
        super();

        this.context = context;
        items = new ArrayList<CalendarBaseEntity>();

        initResources(context.getResources());

        //init adapter with test data
        initTest();
    }

    private void initResources(Resources resources) {
        calendarDividerHeight = resources.getDimensionPixelSize(R.dimen.calendar_divider_height);
        monthTitlePadding = resources.getDimensionPixelSize(R.dimen.month_title_padding);
    }

    private void initTest() {
        DividerEntity dividerEntity = new DividerEntity();
        DayNamesEntity dayNamesEntity = new DayNamesEntity();
        //calendar for 300 years
        for (int i = 0; i < (12 * 300); ++i) {
            //month title
            items.add(new MonthTitleEntity("Month " + i));

            //day names
            items.add(dayNamesEntity);

            //divider
            items.add(dividerEntity);

            //for test we have 4 weeks
            for (int j = 0; j < 4; ++j) {
                int[] days = new int[7];
                for (int k = 1; k <= days.length; ++k) {
                    days[k - 1] = j * 7 + k;
                }
                items.add(new DaysEntity(days));
            }
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        //create view
        if (convertView == null) {
            convertView = createView(type);
        }

        //set content
        switch (type) {
            case CalendarBaseEntity.TYPE_MONTH_TITLE :
                MonthTitleEntity monthTitleEntity = (MonthTitleEntity)getItem(position);
                ((TextView)convertView).setText(monthTitleEntity.getTitle());
                break;
            case CalendarBaseEntity.TYPE_DAYS :
                DaysEntity daysEntity = (DaysEntity)getItem(position);
                DaysViewHolder holder = (DaysViewHolder) convertView.getTag();
                for (int i = 0; i < holder.cells.length; ++i) {
                    holder.cells[i].setText(Integer.toString(daysEntity.getDayNumber(i)));
                }
                break;
        }

        return convertView;
    }

    private View createView(int type) {
        switch (type) {
            case CalendarBaseEntity.TYPE_MONTH_TITLE :
                return createMonthTitleView();
            case CalendarBaseEntity.TYPE_DIVIDER :
                return createDividerView();
            case CalendarBaseEntity.TYPE_DAY_NAMES :
                return createDayNamesView();
            case CalendarBaseEntity.TYPE_DAYS :
                return createDaysView();
        }

        return null;
    }

    private View createMonthTitleView() {
        TextView monthTitle = new TextView(context);
        monthTitle.setPadding(monthTitlePadding, monthTitlePadding, monthTitlePadding, 0);
        return monthTitle;
    }

    private View createDividerView() {
        View divider = new View(context);
        divider.setBackgroundColor(Color.GRAY);

        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, calendarDividerHeight);
        divider.setLayoutParams(layoutParams);

        return divider;
    }

    private View createDayNamesView() {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setWeightSum(7);

        int firstWeekDay  = Calendar.getInstance().getFirstDayOfWeek();
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        String[] dayNames = symbols.getShortWeekdays();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        for (int i = 0; i < 7; ++i) {
            TextView tw = new TextView(context);
            tw.setText(dayNames[1 + (firstWeekDay + i - 1) % 7]);
            tw.setGravity(Gravity.CENTER);
            container.addView(tw, params);
        }

        return container;
    }

    private View createDaysView() {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setWeightSum(7);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        DaysViewHolder holder = new DaysViewHolder();

        for (int i = 0; i < 7; ++i) {
            holder.cells[i] = new TextView(context);
            holder.cells[i].setGravity(Gravity.CENTER);
            container.addView(holder.cells[i], params);
        }

        container.setTag(holder);

        return container;
    }

    @Override
    public int getViewTypeCount() {
        return CalendarBaseEntity.TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    private static class DaysViewHolder {
        TextView[] cells;

        public DaysViewHolder () {
            cells = new TextView[7];
        }
    }
}
