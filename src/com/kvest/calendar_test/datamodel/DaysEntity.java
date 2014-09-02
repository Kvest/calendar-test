package com.kvest.calendar_test.datamodel;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 02.09.14
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
public class DaysEntity extends CalendarBaseEntity {
    private int[] days;

    public DaysEntity(int[] days) {
        super(TYPE_DAYS);

        this.days = days;
    }

    public int getDayNumber(int i) {
        return days[i];
    }
}
