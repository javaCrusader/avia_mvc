package result.accountantResult;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SingleResult {

    private Integer idMember;

    private String name;

    private String funcName;

    private Long salaryInPeriod;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    private Integer salaryInHour;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public Integer getId() {
        return idMember;
    }

    public void setId(Integer id) {
        this.idMember = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalaryInPeriod() {
        return salaryInPeriod;
    }

    public void setSalaryInPeriod(Long salaryInPeriod) {
        this.salaryInPeriod = salaryInPeriod;
    }

    public Integer getSalaryInHour() {
        return salaryInHour;
    }

    public void setSalaryInHour(Integer salaryInHour) {
        this.salaryInHour = salaryInHour;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void calculate() {
        if (start == null || end == null || end.getTime() < start.getTime())
            return;
        long difference = (end.getTime() - start.getTime()) / 86400000;

        salaryInPeriod = difference * salaryInHour;
    }


}
