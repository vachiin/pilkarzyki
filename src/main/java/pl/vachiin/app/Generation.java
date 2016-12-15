package pl.vachiin.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Generation {

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    private static AtomicInteger genNumber = new AtomicInteger(0);

    private Date genDate;
    private Integer genInt;

    public Generation() {
    }

    public Generation(Generation aGeneration) {
        genInt = aGeneration.genInt;
        genDate = aGeneration.genDate;
    }

    public Integer getGenInt() {
        return genInt;
    }

    public void incGenNumber() {
        genInt = genNumber.incrementAndGet();
    }

    public Date getGenDate() {
        return genDate;
    }

    public String getGenDateString() {
        return df.format(genDate);
    }

    public void setGenDate(Date aGenDate) {
        genDate = aGenDate;
    }
}
