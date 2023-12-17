package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;

import java.util.ArrayList;

public class DAOFactory {
    private static CountryDAO countryDAO;
    private static SubdivisionDAO subdivisionDAO;

    private static CityDAO cityDAO;

    public static CountryDAO getCountryDAO() {
        if(countryDAO == null)
            countryDAO = new CountryDAO();

        return countryDAO;
    }

    public static SubdivisionDAO getSubdivisionDAO() {
        if(subdivisionDAO == null)
            subdivisionDAO = new SubdivisionDAO();

        return subdivisionDAO;
    }

    public static CityDAO getCityDAO() {
        if(cityDAO == null)
            cityDAO = new CityDAO();

        return cityDAO;
    }


}
