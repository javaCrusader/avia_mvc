package result.search;

import model.City;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SearchParam {

    @Transient
    private SimpleDateFormat dateFmt;
    {
        dateFmt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDate ;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDate;

    private String startStringDate;

    private String endStringDate;

    private City startCity;

    private City endCity;

    public String getStartStringDate() {
        return startStringDate;
    }

    public void setStartStringDate(String startStringDate) {
        this.startStringDate = startStringDate;
    }

    public String getEndStringDate() {
        return endStringDate;
    }

    public void setEndStringDate(String endStringDate) {
        this.endStringDate = endStringDate;
    }

    public SimpleDateFormat getDateFmt() {
        return dateFmt;
    }

    public void setDateFmt(SimpleDateFormat dateFmt) {
        this.dateFmt = dateFmt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public void setEndCity(City endCity) {
        this.endCity = endCity;
    }

}

