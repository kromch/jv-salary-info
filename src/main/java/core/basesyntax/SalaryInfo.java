package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws ParseException {
        StringBuilder res = new StringBuilder();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = formatter.parse(dateFrom);
            Date date2 = formatter.parse(dateTo);
            if (date2.compareTo(date1) < 0) {
                throw new ParseException("DateFrom occurs after DateTo", 0);
            }
            DateFormat dateFormat;
            dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMANY);
            res.append("Report for period " + dateFormat.format(date1) + " - " + dateFormat.format(date2) + "\n");
            for (String n: names) {
                int money = 0;
                for (String d: data) {
                    var temp = d.split(" ");
                    if ((date1.compareTo(formatter.parse(temp[0])) < 0 || date1.compareTo(formatter.parse(temp[0])) == 0) 
                        && (date2.compareTo(formatter.parse(temp[0])) > 0 || date2.compareTo(formatter.parse(temp[0])) == 0)
                        && n.trim().equals(temp[1].trim())) {
                        money += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
                    }
                }
                res.append(n.trim() + " - " + String.format("%d", money) + "\n");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return res.toString();
    }
}
