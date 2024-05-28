package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GetGreenSpacesManagedByUserController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.*;

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

            String sortingAlgorithm = ApplicationSession.getInstance().getProperties().getProperty("Sorting.Algorithm");
            if(sortingAlgorithm == null){
                System.out.println("Error: Sorting algorithm not defined.");
                System.out.println("Cannot list green spaces without a sorting algorithm. Aborting list.");
                return;
            }
            if(sortingAlgorithm.equals("DefaultSort")){
                defaultSort(greenSpaces.get());
            }else if(sortingAlgorithm.equals("BubbleSort")){
                greenSpaces = Optional.of(bubbleSort(greenSpaces.get()));
            }else{
                System.out.println("Error: Invalid sorting algorithm in configurations.");
                System.out.println("Cannot list green spaces without a sorting algorithm. Aborting list.");
                return;
            }

            System.out.println("NAME | ADDRESS | AREA | TYPE");

            for (GreenSpace greenSpace : greenSpaces.get()) {
                System.out.println(greenSpace.getName() + " | " +greenSpace.getAddress() + " | "+ greenSpace.getArea() + " | "+greenSpace.getType());
            }
            Scanner in = new Scanner(System.in);
            System.out.println("\nPress ENTER to continue.");
            in.nextLine();
        }
    }

    private void defaultSort(ArrayList<GreenSpace> greenSpaces){
        greenSpaces.sort(Comparator.comparing(GreenSpace::getArea, Comparator.reverseOrder()));
    }

    private ArrayList<GreenSpace> bubbleSort(ArrayList<GreenSpace> greenSpaces){
        for(int i = 0; i < greenSpaces.size(); i++){
            for(int j = 0; j < greenSpaces.size()-i; j++){
                if(greenSpaces.get(j).getArea() < greenSpaces.get(j+1).getArea()){
                    GreenSpace temp = greenSpaces.get(j+1);
                    greenSpaces.set(j+1, greenSpaces.get(j));
                    greenSpaces.set(j, temp);
                }
            }
        }
        return greenSpaces;
    }
}

