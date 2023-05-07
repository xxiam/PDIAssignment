/*
Jhon Gonzales - Programming Desgin and Implementation
 */
import java.util.*;

//PROV_TERR_EN,BENEFICIARY_NAME_EN,BENEFICIARY_BAND_NBR,ASSET_CLASS_EN,PROJECT_NAME_EN, PROJECT_DESC_EN, PROJECT_STAGE_EN,LATITUDE,LONGITUDE,COORD_SYS


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] data = FileIO.parseCsv("./csvFiles/data.csv"); //parsing 9000+ lines
        Project[] projectArray = new Project[data.length - 1];
        
        for (int i = 1; i < data.length; i++) {
            projectArray[i - 1] = new Project(data[i]);
        }

        //count unique provinces from projectArray
        ArrayList<String> provinces = new ArrayList<String>();
        for (int i = 0; i < projectArray.length; i++) {
            if (!provinces.contains(projectArray[i].getProvince())) {
                provinces.add(projectArray[i].getProvince());
            }
        }
        //source: https://stackoverflow.com/questions/5283047/integer-arraylist-to-int-array

        boolean running = true;
        while (running) {
            System.out.println("Welcome to the Investments in Indigenous community infrastruture Program. \nThere are a total of " + projectArray.length + " projects throughout Canada.\nPlease make a selection from the Menu below.");
            System.out.println("\n> All of Canada");
            for (int ii = 0; ii < provinces.size(); ii++) {
                System.out.println("> " + provinces.get(ii));
            }
            System.out.println("> Exit Program");
            
            String choice = sc.nextLine(); //user types in province
            System.out.println("\n");
            if (choice.equals("All of Canada")) {
                System.out.println("The total number of projects in Canada: " + projectArray.length);
                int ongoingProjects = 0;
                for (int ii = 0; ii < projectArray.length; ii++) {
                    if (projectArray[ii].getStage().equals("Ongoing")) {
                        ongoingProjects++;
                    }
                }
                System.out.println("The total number of Ongoing projects: " + ongoingProjects);
                int completedProjects = 0;
                for (int ii = 0; ii < projectArray.length; ii++) {
                    if (projectArray[ii].getStage().equals("Completed")) {
                        completedProjects++;
                    }
                }
                System.out.println("The total number of Completed projects: " + completedProjects);
                double percentage = (double)completedProjects / (double)projectArray.length * 100;
                System.out.println("The percentage of Completed projects: " + percentage + "%");
            }

            else if (choice.equals("Exit Program")) {
                System.out.println("Exiting...");
                running = false;
            }

            for (int ii = 0; ii < provinces.size(); ii++) {
                if (choice.equals(provinces.get(ii))) {
                    System.out.println("Please make a choice from the statistics below for " + provinces.get(ii) + ":");
                    System.out.println("1> Number of projects");
                    System.out.println("2> Percentage of all projects located in this province/territory");
                    System.out.println("3> Total number and percentage of Ongoing projects");
                    System.out.println("4> Total number and percentage of Completed projects");
                    System.out.println("5> All of the above statistics");
                    System.out.println("0> Return to main menu");

                    int choice2 = sc.nextInt();
                    System.out.println("\n");

                    if (choice2 == 1) {
                        int projectCount = fetchProjectCount(projectArray, provinces.get(ii));
                        System.out.println("There are a total of " + projectCount + " projects in " + provinces.get(ii));
                    }

                    if (choice2 == 2) {
                        int total = projectArray.length;
                        double percentage = (double)fetchProjectCount(projectArray, provinces.get(ii)) / (double)total * 100;
                        System.out.println("The percentage of all projects located in " + provinces.get(ii) + " is " + percentage + "%");
                    }

                    if (choice2 == 3) {
                        double percentage = fetchPercentageData(projectArray, provinces.get(ii), "Ongoing");
                        int ongoingCount = fetchStageCount(projectArray, provinces.get(ii), "Ongoing");
                     
                        System.out.println("There is a total of " + ongoingCount + " ongoing projects in " + provinces.get(ii) + " which is " + percentage + "% of all projects in " + provinces.get(ii));
                    }

                    if (choice2 == 4) {
                        double percentage = fetchPercentageData(projectArray, provinces.get(ii), "Completed");
                        int completedCount = fetchStageCount(projectArray, provinces.get(ii), "Completed");
                        
                        System.out.println("There is a total of " + completedCount + " completed projects in " + provinces.get(ii) + " which is " + percentage + "% of all projects in " + provinces.get(ii));
                    }

                    if (choice2 == 5) {
                        //all of the above
                        int projectCount = fetchProjectCount(projectArray, provinces.get(ii));
                        int ongoingCount = fetchStageCount(projectArray, provinces.get(ii), "Ongoing");
                        int compltedCount = fetchStageCount(projectArray, provinces.get(ii), "Completed");
                        double percentageOfProvince = (double)fetchProjectCount(projectArray, provinces.get(ii)) / (double)projectArray.length * 100;
                        double ongoingPercentage = fetchPercentageData(projectArray, provinces.get(ii), "Ongoing");
                        double completedPercentage = fetchPercentageData(projectArray, provinces.get(ii), "Completed");

                        System.out.println("All stats in " + provinces.get(ii) + ":");
                        System.out.println("> Total projects: " + projectCount);
                        System.out.println("> Percentage of all projects: " + percentageOfProvince + "%");
                        System.out.println("> Total ongoing projects: " + ongoingCount + " | " + ongoingPercentage + "%");
                        System.out.println("> Total completed projects: " + compltedCount + " | " + completedPercentage + "%");
                    }
                    
                    if (choice2 == 0) {
                        System.out.println("Returning to main menu...");
                    }

                    else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            }

            //if choice is not in the list
            if (!choice.equals("All of Canada") && !choice.equals("Exit Program")) {
                boolean found = false;
                for (int ii = 0; ii < provinces.size(); ii++) {
                    if (choice.equals(provinces.get(ii))) {
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Invalid input. Please try again.");
                }
            }
            
            System.out.println("\n");
        }
    }

    private static int fetchProjectCount(Project[] projectArray, String province) {
        int total = 0;
        for (int i = 0; i < projectArray.length; i++) {
            if (projectArray[i].getProvince().equals(province)) {
                total++;
            }
        }
        return total;
    }

    private static double fetchPercentageData(Project[] projectArray, String province, String stage) {
        //fetch total number and percentage of ongoing or complted projects
        double percetage = 0;
        int total = 0; //gets total projects in province
        for (int i = 0; i < projectArray.length; i++) {
            if (projectArray[i].getProvince().equals(province)) {
                total++;
            }
        }
        int stageTotal = 0; //gets total ongoing or completed projects in province
        for (int i = 0; i < projectArray.length; i++) {
            if (projectArray[i].getProvince().equals(province) && projectArray[i].getStage().equals(stage)) {
                stageTotal++;
            }
        }
        percetage = (double)stageTotal / (double)total * 100;
        return percetage;
    }

    private static int fetchStageCount(Project[] projectArray, String province, String stage) {
        int total = 0;
        for (int i = 0; i < projectArray.length; i++) {
            if (projectArray[i].getProvince().equals(province) && projectArray[i].getStage().equals(stage)) {
                total++;
            }
        }
        return total;
    }

}