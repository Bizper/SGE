package controller;

import controller.scanner.MapLoader;
import impl.action.Spell;
import impl.person.Character;
import service.ConceptFactory;
import service.Proc;

public class Main {

    public static void main(String args[]) {

        Character main = ConceptFactory.getInstance(Character.class);
        main.setName("Thread");
        main.setMaxHp(500);

        Proc.printAll();

        Spell spell = ConceptFactory.getInstance(Spell.class);
        spell.setName("火球术");
        spell.setAction((sel, tar) -> {
            tar.hurt(50);
            sel.setMp(sel.getMp() - 10);
        });

        main.addSpell(spell);

        main.spell(0, main);

        Proc.printAll();

        //MapLoader.load("C://Users//needf//Documents//SGE package//");

    }

}
