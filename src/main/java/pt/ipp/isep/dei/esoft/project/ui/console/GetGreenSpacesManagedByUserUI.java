package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GetGreenSpacesManagedByUserController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;

import java.util.*;

public class GetGreenSpacesManagedByUserUI implements Runnable {

    private GetGreenSpacesManagedByUserController controller = new GetGreenSpacesManagedByUserController();

    /**
     * Runs this functionality.
     */
    @Override
    public void run() {
        System.out.println("\n >>>>>>>>>> LIST OF GREEN SPACES MANAGED BY YOU <<<<<<<<<< \n");

        System.out.println("Your name is: "+ ApplicationSession.getInstance().getCurrentSession().getUserName());
        System.out.println("Your email is: "+ ApplicationSession.getInstance().getCurrentSession().getUserEmail());

        Optional<ArrayList<GreenSpaceDTO>> greenSpaces = Optional.empty();
        try{
            greenSpaces = controller.getGreenSpacesManagedByCurrentUser();
        }catch(Exception e){
            System.out.println("Error on getting green spaces managed by you!");
            System.out.println(e.getMessage());
            return;
        }

        if (greenSpaces.isEmpty()) {
            System.out.println("You do not manage any green spaces.");
        } else {
            String sortingAlgorithm = "";
            try{
                sortingAlgorithm = ApplicationSession.getInstance().getProperties().getProperty("Sorting.Algorithm");
            }catch(Exception e){
                System.out.println("Error on getting the sorting algorithm defined in the configuration file!");
                System.out.println(e.getMessage());
                return;
            }
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

            for (GreenSpaceDTO greenSpace : greenSpaces.get()) {
                System.out.println(greenSpace.name + " | " +greenSpace.address + " | "+ greenSpace.area + " | "+greenSpace.type);
            }
            Scanner in = new Scanner(System.in);
            System.out.println("\nPress ENTER to continue.");
            in.nextLine();
        }
    }

    /**
     * Performs the default java collections sort method on a list of green spaces,
     * sorting them in reverse order by their area.
     * @param greenSpaces The list of green spaces to sort.
     */
    private void defaultSort(ArrayList<GreenSpaceDTO> greenSpaces){
        greenSpaces.sort(Comparator.comparingInt((GreenSpaceDTO o) -> o.area).reversed());
    }

    /**
     * Performs a reverse bubble sort by area on the provided list of green spaces.
     * @param greenSpaces The list of green spaces to sort.
     * @return The sorted list of green spaces.
     */
    private ArrayList<GreenSpaceDTO> bubbleSort(ArrayList<GreenSpaceDTO> greenSpaces){
        for(int i = 0; i < greenSpaces.size(); i++){
            for(int j = 0; j < greenSpaces.size()-i; j++){
                if(greenSpaces.get(j).area < greenSpaces.get(j+1).area){
                    GreenSpaceDTO temp = greenSpaces.get(j+1);
                    greenSpaces.set(j+1, greenSpaces.get(j));
                    greenSpaces.set(j, temp);
                }
            }
        }
        return greenSpaces;
    }
}

