package domain_model.comparators;

import domain_model.Superhero;

import java.util.Comparator;

public class IsHumanComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero hero1, Superhero hero2){
        return Boolean.compare(hero1.getIsHuman(), hero2.getIsHuman());
    }
}
