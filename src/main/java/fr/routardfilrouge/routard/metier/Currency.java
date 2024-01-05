package fr.routardfilrouge.routard.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Currency {
    private StringProperty codeIsoName;
    private StringProperty nameCurrency;

    public Currency(String codeIsoName, String nameCurrency) {
        this.codeIsoName = new SimpleStringProperty(codeIsoName);
        this.nameCurrency = new SimpleStringProperty(nameCurrency);
    }

   public Currency(){
        this.codeIsoName = new SimpleStringProperty("");
        this.nameCurrency = new SimpleStringProperty("");
   }

    public String getCodeIsoName() {
        return codeIsoName.get();
    }

    public void setCodeIsoName(String codeIsoName) {
        this.codeIsoName.set(codeIsoName);
    }

    public String getNameCurrency() {
        return nameCurrency.get();
    }

    public void setNameCurrency(String nameCurrency) {
        this.nameCurrency.set(nameCurrency);
    }

    public String toString(){
        return nameCurrency.get();
    }
}
