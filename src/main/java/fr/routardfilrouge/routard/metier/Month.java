package fr.routardfilrouge.routard.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Month {
    private int idMonth;
    private StringProperty monthName;

    public Month(int idMonth, String monthName) {
        this.idMonth = idMonth;
        this.monthName = new SimpleStringProperty(monthName);
    }

    public Month() {
        this.idMonth = 0;
        this.monthName = new SimpleStringProperty("");
    }

    public int getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(int idMonth) {
        this.idMonth = idMonth;
    }

    public String getMonthName() {
        return monthName.get();
    }

    public StringProperty monthNameProperty() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName.set(monthName);
    }

    @Override
    public String toString() {
        return getMonthName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month = (Month) o;
        return idMonth == month.idMonth && Objects.equals(monthName.get(), month.monthName.get());
    }
}
