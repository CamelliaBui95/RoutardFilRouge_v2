package fr.routardfilrouge.routard.metier;

public class Language {
    private String idLanguage;
    private String LanguageName;
    public Language(String idLanguage, String languageName) {
        this.idLanguage = idLanguage;
        this.LanguageName = languageName;
    }

    public String getIdLanguage() {
        return idLanguage;
    }

    public String getLanguageName() {
        return LanguageName;
    }

    public void setIdLanguage(String idLanguage) {
        this.idLanguage = idLanguage;
    }

    public void setLanguageName(String languageName) {
        this.LanguageName = languageName;
    }

    @Override
    public String toString() {
        return LanguageName;
    }
}
