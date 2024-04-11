package fr.routardfilrouge.routard.dao;

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
    private static EntryRequirementDAO entryRequirementDAO;
    private static EntryReqTypeDAO entryReqTypeDAO;
    private static ExigenceStatusDAO exigenceStatusDAO;
    private static VisaExemptionDAO visaExemptionDAO;

    private static UserDAO userDAO;

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
    public static EntryRequirementDAO getEntryRequirementDAO() {
        if(entryRequirementDAO == null)
            entryRequirementDAO = new EntryRequirementDAO();
        return entryRequirementDAO;
    }

    public static ExigenceStatusDAO getExigenceStatusDAO() {
        if(exigenceStatusDAO == null)
            exigenceStatusDAO = new ExigenceStatusDAO();

        return exigenceStatusDAO;
    }

    public static EntryReqTypeDAO getEntryReqTypeDAO() {
        if(entryReqTypeDAO == null)
            entryReqTypeDAO = new EntryReqTypeDAO();

        return entryReqTypeDAO;
    }

    public static VisaExemptionDAO getVisaExemptionDAO() {
        if(visaExemptionDAO == null)
            visaExemptionDAO = new VisaExemptionDAO();

        return visaExemptionDAO;
    }

    public static UserDAO getUserDAO() {
        if(userDAO == null)
            userDAO = new UserDAO();

        return userDAO;
    }
}
