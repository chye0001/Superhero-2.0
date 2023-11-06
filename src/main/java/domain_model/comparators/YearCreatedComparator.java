package domain_model.comparators;

import domain_model.Superhero;

import java.util.Comparator;

public class YearCreatedComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero hero1, Superhero hero2){
        return Integer.compare(hero1.getYearCreated(), hero2.getYearCreated());
    }
}
