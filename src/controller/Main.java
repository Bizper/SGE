package controller;

import intf.Concept;
import service.ConceptFactory;
import service.Proc;

public class Main {

    public static void main(String args[]) {

        for(int i=0; i<5; i++) {
            Concept concept = ConceptFactory.getInstance();
        }

        ConceptFactory.printAll();

        Proc.print();

    }

}
