package fr.routardfilrouge.routard.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

@Data
public class Currency {
    private StringProperty currenyCode;
    private StringProperty currencyName;

    public Currency(String currenyCode, String currencyName) {
        this.currenyCode = new SimpleStringProperty(currenyCode);
        this.currencyName = new SimpleStringProperty(currencyName);
    }

   public Currency(){
        this.currenyCode = new SimpleStringProperty("");
        this.currencyName = new SimpleStringProperty("");
   }

    public String getCurrenyCode() {
        return currenyCode.get();
    }

    public void setCurrenyCode(String currenyCode) {
        this.currenyCode.set(currenyCode);
    }

    public String getCurrencyName() {
        return currencyName.get();
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName.set(currencyName);
    }

    @Override
    public String toString() {
        return this.currencyName.get();
    }
}
