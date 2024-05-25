package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

public class Main {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try{
                Repositories.save();
                System.out.println("Serialization successful.");
            }catch(Exception e){
                System.out.println("Serialization failed.");
                System.out.println(e.getMessage());
            }
        }));

        Bootstrap bootstrap = new Bootstrap();

        try{
            Repositories.load();
            System.out.println("Deserialization successful.");
            System.out.println("Loading bootstrap authentication repository.");
            bootstrap.addUsers();
        }catch(Exception e){
            System.out.println("Deserialization failed.");
            System.out.println(e.getMessage());
            System.out.println("Loading bootstrap data.");
            bootstrap.run();
        }

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            System.out.println("A fatal error occurred!");
            System.out.println(e.getMessage());
            System.out.println("Attempting serialization...");
            try{
                Repositories.save();
                System.out.println("Serialization successful!");
            }catch(Exception newE){
                System.out.println("Serialization failed!");
                System.out.println(newE.getMessage());
            }
        }
    }
}