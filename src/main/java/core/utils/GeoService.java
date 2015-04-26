package core.utils;

import com.google.common.collect.Lists;
import core.point.legacy.LegacyPoint;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class GeoService {

    public List<String> loadFile(InputStream event) {
        List<String> lines = Lists.newArrayList();
        InputStreamReader ipsr = new InputStreamReader(event);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        try {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public LegacyPoint splitIternal(String line) {
        String[] splitted = line.split(" ");
        List<String> splittedList = Lists.newArrayList();
        for (String s : splitted) {
            if (!s.contentEquals(""))
                splittedList.add(s);
        }
        if (splittedList.size() < 5) {
            while (splittedList.size() < 5) {
                splittedList.add("0");
            }
        }
        String name = splittedList.get(0);
        String code = splittedList.get(1);
        double x = Double.valueOf(splittedList.get(2));
        double y = Double.valueOf(splittedList.get(3));
        double z = Double.valueOf(splittedList.get(4));
        return new LegacyPoint(name, code, x, y, z);
    }
}

