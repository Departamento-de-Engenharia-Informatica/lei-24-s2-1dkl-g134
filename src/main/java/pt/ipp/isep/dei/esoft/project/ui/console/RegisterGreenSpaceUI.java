package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class RegisterGreenSpaceUI implements Runnable {
    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;
    private RegisterGreenSpaceController registerGreenSpaceController = new RegisterGreenSpaceController();

    public RegisterGreenSpaceController getRegisterGreenSpaceController() {return registerGreenSpaceController;}

    /**
     * Run this functionality.
     */
    public void run() {
        System.out.println("\n >>>>>>>>>> REGISTER GREEN SPACE  <<<<<<<<<< \n");

       while (true) {
            requestData();
            if (!confirmData()) {
                continue;
            }
            submitData();
            break;
        }
    }

    /**
     * Requests all data and assigns it to its respective variable.
     */
    private void requestData() {
        name = requestName();
        address = requestAddress();
        area = requestArea();
        type = requestType();
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData() {
        System.out.println("Name:" + name);
        System.out.println("Address:" + address);
        System.out.println("Area:" + area);
        System.out.println("Type:" + type);

        return Utils.confirm("Do you wish proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData() {
        try {
            GreenSpaceDTO greenSpaceDTO = new GreenSpaceDTO();
            greenSpaceDTO.name = name;
            greenSpaceDTO.address = address;
            greenSpaceDTO.area = area;
            greenSpaceDTO.type = type;
            Optional<GreenSpace> newGreenSpace = getRegisterGreenSpaceController().registerGreenSpace(greenSpaceDTO);
            if (newGreenSpace.isEmpty()) {
                System.out.println("Failed to add new Green Space!");
            } else {
                System.out.println("Green Space added successfully!");
            }
        } catch (Exception e) {
            System.out.println("Failed to add new Green Space");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Requests the green space's name.
     * @return The green space's name.
     */
    private String requestName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        return input.nextLine();
    }

    /**
     * Requests the green space's address.
     * @return The green space's address.
     */
    private String requestAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("Address: ");
        return input.nextLine();
    }

    /**
     * Requests the green space's area.
     * @return The green space's area.
     */
    private int requestArea() {
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Area (in hectares): ");
                return Integer.parseInt(input.nextLine());
            }catch(Exception e){
                System.out.println("Area value must be a number.");
            }
        }
    }

    /**
     * Requests the green space's type.
     * @return The green space's type.
     */
    private GreenSpaceType requestType() {
        Scanner input = new Scanner(System.in);
        ArrayList<GreenSpaceType> allTypes = GreenSpaceType.getAllTypes();
        System.out.println("Choose a type from the following list of their respective titles:\n");
        for(int i = 0; i < allTypes.size(); i++){
            System.out.println((i+1) + "- "+allTypes.get(i));
        }
        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a type.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > allTypes.size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return allTypes.get(option-1);
    }

}
