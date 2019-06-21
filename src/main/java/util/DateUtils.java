package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static void main(String[] args) {
        int year = 2019;
        int month = 2;
        getLastDay(year, month);
        System.out.println(getLastDayOfMonth(year, month));
    }

    private static void getLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, actualMaximum);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));

        /*Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 02);
        int  day = calendar.getActualMaximum(Calendar.DATE);

        calendar.set(Calendar.DAY_OF_MONTH, day);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));*/
    }


    public static String getLastDayOfMonth(int year,int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }
}
