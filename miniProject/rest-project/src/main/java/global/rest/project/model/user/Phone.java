package global.rest.project.model.user;

public class Phone {

    private String number;
    private String cityCode;
    private String countryCode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "{\"number\":\"" + number +
                "\",\"cityCode\":\"" + cityCode +
                "\",\"countryCode\":\"" + countryCode + "\"}";
    }
}
