package domain_model.comparators;

import domain_model.Superhero;

import java.util.Comparator;

public class SuperPowerComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero hero1, Superhero hero2){
        return hero1.getSuperpower().compareTo(hero2.getSuperpower());
    }
}
