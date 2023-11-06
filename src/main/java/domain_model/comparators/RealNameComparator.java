package domain_model.comparators;

import domain_model.Superhero;

import java.util.Comparator;

public class RealNameComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero hero1, Superhero hero2){
        return hero1.getRealName().compareTo(hero2.getRealName());
    }
}
