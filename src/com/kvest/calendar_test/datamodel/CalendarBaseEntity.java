package com.kvest.calendar_test.datamodel;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 02.09.14
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public abstract class CalendarBaseEntity {
    public static final int TYPE_COUNT = 4;

    public static final int TYPE_MONTH_TITLE = 0;
    public static final int TYPE_DAY_NAMES = 1;
    public static final int TYPE_DIVIDER = 2;
    public static final int TYPE_DAYS = 3;

    private int type;

    public CalendarBaseEntity(int type) {
        super();

        this.type = type;
    }

    public int getType() {
        return type;
    }
}
