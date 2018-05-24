package controller;

import impl.action.Spell;
import impl.person.Character;
import service.ConceptFactory;
import service.Proc;

public class Main {

    public static void main(String args[]) {

        for(int i=0; i<5; i++) {
            if(i < 2) {
                Character npc = (Character) ConceptFactory.getInstance(Character.class);
                npc.setName("Thread");
                npc.setMaxHp(500);
            } else {
                Spell spell = (Spell) ConceptFactory.getInstance(Spell.class);
                spell.setName("火球术");
                spell.setAction((sel, tar) -> {
                    tar.hurt(50);
                    sel.setMp(sel.getMp() - 50);
                });
            }

        }

        Proc.printAll();

    }

}
