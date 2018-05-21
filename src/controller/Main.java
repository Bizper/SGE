package controller;

import intf.Concept;
import service.ConceptFactory;
import service.Proc;

public class Main {

    public static void main(String args[]) {

        Concept concept = ConceptFactory.getInstance();

        Proc.print();

    }

}
