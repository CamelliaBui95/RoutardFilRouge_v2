package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;

import java.util.ArrayList;

public class DAOFactory {
    private static CountryDAO countryDAO;
    private static SubdivisionDAO subdivisionDAO;
    private static CityDAO cityDAO;
    private static InfoDAO infoDAO;
    private static ContinentDAO continentDAO;
    private static ClimateDAO climateDAO;

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
    public static InfoDAO getInfoDAO() {
        if(infoDAO == null)
            infoDAO = new InfoDAO();

        return infoDAO;
    }
    public static ContinentDAO getContinentDAO() {
        if(continentDAO == null)
            continentDAO = new ContinentDAO();

        return continentDAO;
    }
    public static ClimateDAO getClimateDAO() {
        if(climateDAO == null)
            climateDAO = new ClimateDAO();

        return climateDAO;
    }
}
