package fr.routardfilrouge.routard.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Currency {
    private String codeIsoName;
    private String nameCurrency;

    public Currency(String codeIsoName, String nameCurrency) {
        this.codeIsoName = codeIsoName;
        this.nameCurrency = nameCurrency;
    }

   public Currency(){
        this.codeIsoName = "";
        this.nameCurrency = "Currency";
   }

    public String getCodeIsoName() {
        return codeIsoName;
    }

    public void setCodeIsoName(String codeIsoName) {
        this.codeIsoName = codeIsoName;
    }

    public String getNameCurrency() {
        return nameCurrency;
    }

    public void setNameCurrency(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }
}
