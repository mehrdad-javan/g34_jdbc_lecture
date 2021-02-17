package se.lexicon;


import se.lexicon.dao.CityDao;
import se.lexicon.dao.CityDaoIml;
import se.lexicon.model.City;

public class App {
    public static void main(String[] args) {
        CityDao dao = new CityDaoIml();
        City city = dao.findById(11);
        System.out.println("city = " + city);
    }
}
