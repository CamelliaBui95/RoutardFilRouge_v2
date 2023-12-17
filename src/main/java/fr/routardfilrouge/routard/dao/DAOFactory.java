package fr.routardfilrouge.routard.dao;

public class DAOFactory {
    private static CountryDAO countryDAO;
    private static SubdivisionDAO subdivisionDAO;

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
}
