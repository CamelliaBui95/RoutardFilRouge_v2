package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Currency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CurrencyDAO extends DAO<Currency, Currency> {
    public ArrayList<Currency> getAll() {
        ArrayList<Currency> currencies = new ArrayList<>();
        String rq = "SELECT * FROM MONNAIE";
        try (
                Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(rq);
            while (rs.next())
                currencies.add(new Currency(rs.getString("CODE_ISO_MONNAIE"), rs.getString("NOM_DEVISE")));
            rs.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        return currencies;
    }

    @Override
    public ArrayList<Currency> getLike(Currency searchObject) {
        return null;
    }

    @Override
    public boolean update(Currency object) {
        return false;
    }

    @Override
    public boolean delete(Currency object) {
        return false;
    }

    @Override
    public boolean post(Currency currency) {
        String req = "{call ps_insertMonnaie(?,?)}";
        try (PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, currency.getCodeIsoName());
            stm.setString(2, currency.getNameCurrency());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
