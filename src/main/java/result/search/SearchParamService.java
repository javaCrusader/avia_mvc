package result.search;

import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class SearchParamService {

    public boolean setStartEndDateFromString(SearchParam searchparam) {
        if (searchparam == null)
            return false;
        try {
            searchparam.setStartDate(searchparam.getDateFmt().parse(searchparam.getStartStringDate()));
            searchparam.setEndDate(searchparam.getDateFmt().parse(searchparam.getEndStringDate()));
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
