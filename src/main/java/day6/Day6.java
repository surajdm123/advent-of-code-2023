package day6;

import helper.FileHelper;
import helper.StringUtil;

import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        FileHelper helper = new FileHelper();
        String[] lines = helper.getLines("day6.txt");

        List<Long> times = StringUtil.longs(lines[0]);
        List<Long> records = StringUtil.longs(lines[1]);

        long ans = 1;

        for(int i = 0; i<times.size(); i++) {
            long time = times.get(i);
            long record = records.get(i);

            ans *= getCount(time, record);
        }

        System.out.println(ans);
    }

    public static long getCount(long time, long record) {
        int count = 0;
        for(int t=1; t<time; t++) {
            if(distance(t, time - t) > record) {
                count++;
            }
        }
        return count;
    }

    public static long distance(long speed, long time) {
        return speed * time;
    }

}
