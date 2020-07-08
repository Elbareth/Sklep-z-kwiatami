package com.example.sklepZKwiatami.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class DateBetweenDTO {
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    public DateBetweenDTO(LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public DateBetweenDTO() {
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateBetweenDTO)) return false;
        DateBetweenDTO that = (DateBetweenDTO) o;
        return getDateStart().equals(that.getDateStart()) &&
                getDateEnd().equals(that.getDateEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateStart(), getDateEnd());
    }

    @Override
    public String toString() {
        return "DateBetweenDTO{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
