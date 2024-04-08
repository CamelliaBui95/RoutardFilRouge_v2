package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.ExigenceStatus;

import java.util.ArrayList;

public class DAOFactory {
    private static CountryDAO countryDAO;
    private static SubdivisionDAO subdivisionDAO;
    private static CityDAO cityDAO;
    private static InfoDAO infoDAO;
    private static ContinentDAO continentDAO;
    private static ClimateDAO climateDAO;
    private static PoiDAO poiDAO;
    private static LanguageDAO languageDAO;
    private static CurrencyDAO currencyDAO;
    private static AdministrativeRequirementDAO administrativeRequirementDAO;
    private static AdministrativeDocumentDAO administrativeDocumentDAO;
    private static ExigenceStatusDAO exigenceStatusDAO;

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
    public static PoiDAO getPoiDAO() {
        if(poiDAO == null)
            poiDAO = new PoiDAO();

        return poiDAO;
    }
    public static LanguageDAO getLanguageDAO() {
        if(languageDAO == null)
            languageDAO = new LanguageDAO();

        return languageDAO;
    }
    public static CurrencyDAO getCurrencyDAO(){
        if(currencyDAO == null)
            currencyDAO = new CurrencyDAO();
        return currencyDAO;
    }
    public static AdministrativeRequirementDAO getAdministrativeRequirementDAO() {
        if(administrativeRequirementDAO == null)
            administrativeRequirementDAO = new AdministrativeRequirementDAO();
        return administrativeRequirementDAO;
    }
    public static AdministrativeDocumentDAO getAdministrativeDocumentDAO() {
        if(administrativeDocumentDAO == null)
            administrativeDocumentDAO = new AdministrativeDocumentDAO();

        return administrativeDocumentDAO;
    }

    public static ExigenceStatusDAO getExigenceStatusDAO() {
        if(exigenceStatusDAO == null)
            exigenceStatusDAO = new ExigenceStatusDAO();

        return exigenceStatusDAO;
    }
}
