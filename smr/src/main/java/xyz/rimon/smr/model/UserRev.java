package xyz.rimon.smr.model;

import java.util.Date;

/**
 * Created by SAyEM on 5/12/17.
 */

public class UserRev {
    private Long currentMonthInteractionPoints;
    private Long currentBalance;
    private Long previousMonthInteractionPoints;
    private Long previousMonthIncome;
    private Long from;
    private Long to;

    public Long getCurrentMonthInteractionPoints() {
        if (currentMonthInteractionPoints == null) return 0L;
        return currentMonthInteractionPoints;
    }

    public void setCurrentMonthInteractionPoints(Long currentMonthInteractionPoints) {
        this.currentMonthInteractionPoints = currentMonthInteractionPoints;
    }

    public Long getPreviousMonthInteractionPoints() {
        if (previousMonthInteractionPoints == null) return 0L;
        return previousMonthInteractionPoints;
    }

    public void setPreviousMonthInteractionPoints(Long previousMonthInteractionPoints) {
        this.previousMonthInteractionPoints = previousMonthInteractionPoints;
    }

    public Long getPreviousMonthIncome() {
        if (previousMonthIncome == null) return 0L;
        return previousMonthIncome;
    }

    public void setPreviousMonthIncome(Long previousMonthIncome) {
        this.previousMonthIncome = previousMonthIncome;
    }

    public Date getFrom() {
        Date date = new Date();
        date.setTime(this.from);
        return date;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Date getTo() {
        Date date = new Date();
        date.setTime(this.to);
        return date;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }
}
