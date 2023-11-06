package domain_model.comparators;

import domain_model.Superhero;

import java.util.Comparator;

public class StrengthComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero hero1, Superhero hero2){
        return Double.compare(hero1.getStrength(), hero2.getStrength());
    }
}
