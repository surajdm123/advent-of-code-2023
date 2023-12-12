package helper;

import org.checkerframework.checker.regex.qual.Regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static List<Long> longs(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        List<Long> numberList = new ArrayList<>();
        while (matcher.find()) {
            numberList.add(Long.parseLong(matcher.group()));
        }

        return numberList;
    }
}
