package day5;

import com.sun.jdi.Value;
import helper.FileHelper;
import helper.StringUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws IOException {
        FileHelper helper = new FileHelper();
        final String input = helper.readFileAsString("day5.txt");

        final String[] categories = input.trim().split("\n\n");
        List<Long> seeds = StringUtil.longs(categories[0]);
        List<List<ValueMap>> categoryMaps = Arrays.stream(categories)
                .map(category -> category.lines()
                        .skip(1) // Skip the title
                        .map(StringUtil::longs))
                .map(parts -> parts.map(p -> new ValueMap(p.get(0), p.get(1), p.get(2)))
                        .toList()).toList();

        long minValue = Long.MAX_VALUE;

        for(Long seed : seeds) {
            long value = seed;

            for(List<ValueMap> valueMaps : categoryMaps) {
                for(ValueMap valueMap : valueMaps) {
                    if (valueMap.source <= value && value <= valueMap.source + valueMap.count) {
                        value = valueMap.destination + (value - valueMap.source);
                        break;
                    }
                }
            }

            minValue = Math.min(minValue, value);
        }

        System.out.println("Part 1: " + minValue);

    }

    record ValueMap(long destination, long source, long count) {
    }
}
