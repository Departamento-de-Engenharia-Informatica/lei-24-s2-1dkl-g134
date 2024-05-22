package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GetGreenSpacesManagedByUserController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GetGreenSpacesManagedByUserUI implements Runnable {

    private GetGreenSpacesManagedByUserController controller = new GetGreenSpacesManagedByUserController();

    @Override
    public void run() {
        System.out.println("\n >>>>>>>>>> LIST OF GREEN SPACES MANAGED BY YOU <<<<<<<<<< \n");

        System.out.println("Your name is: "+ ApplicationSession.getInstance().getCurrentSession().getUserName());
        System.out.println("Your email is: "+ ApplicationSession.getInstance().getCurrentSession().getUserEmail());


        Optional<ArrayList<GreenSpace>> greenSpaces = controller.getGreenSpacesManagedByCurrentUser();

        if (greenSpaces.isEmpty()) {
            System.out.println("You do not manage any green spaces.");
        } else {
            System.out.println("NAME | ADDRESS | AREA | TYPE");

            for (GreenSpace greenSpace : greenSpaces.get()) {
                System.out.println(greenSpace.getName() + " | " +greenSpace.getAddress() + " | "+ greenSpace.getArea() + " | "+greenSpace.getType());
            }
            Scanner in = new Scanner(System.in);
            System.out.println("\nPress ENTER to continue.");
            in.nextLine();
        }









        }
    }

