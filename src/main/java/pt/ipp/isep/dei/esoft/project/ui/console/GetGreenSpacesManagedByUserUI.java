package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GetGreenSpacesManagedByUserController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            for (GreenSpace greenSpace : greenSpaces.get()) {
                System.out.println("Name: " + greenSpace.getName() +
                        ", Address: " + greenSpace.getAddress() +
                        ", Area: " + greenSpace.getArea() +
                        ", Type: " + greenSpace.getType());
            }
        }
    }
}
