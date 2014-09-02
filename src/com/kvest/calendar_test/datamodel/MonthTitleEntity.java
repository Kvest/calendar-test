package com.kvest.calendar_test.datamodel;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 02.09.14
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class MonthTitleEntity extends CalendarBaseEntity {
    private String title;

    public MonthTitleEntity(String title) {
        super(TYPE_MONTH_TITLE);

        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
