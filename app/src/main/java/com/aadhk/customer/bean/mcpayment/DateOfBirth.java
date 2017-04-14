package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */
public class DateOfBirth {
    /**
     * Year : 0
     * Month : 0
     * Day : 0
     */

    private int Year;
    private int Month;
    private int Day;

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int Day) {
        this.Day = Day;
    }

    @Override
    public String toString() {
        return "DateOfBirth{" +
                "Year=" + Year +
                ", Month=" + Month +
                ", Day=" + Day +
                '}';
    }
}
