import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {
    //create list of numbers entered
    public static ArrayList<Float> numbers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        //run method to collect entered numbers
        getNumbers();
    }
    
    public static void getNumbers() {
        //create scanner to get user entered numbers
        Scanner keyboard = new Scanner(System.in);
        float input = 1;

        //clear numbers list so that values are not added to any old numbers in the list
        numbers.clear();
        //prompt users to enter numbers
        System.out.println("Please enter the numbers, one in each line. Type 'done' once all numbers are entered:");

        //check if user is done entering numbers
        while (!keyboard.hasNext("done")) {
            //if user has not entered "done" add the next entered number to list
            if (keyboard.hasNextFloat()) {
                input = keyboard.nextFloat();
                if (input > Float.MAX_VALUE || input < Float.MIN_VALUE) {
                    //ensure that input is not beyond the limits of float
                    System.out.println("Value too small or too large!");
                    keyboard.nextLine();
                } else {
                    //if input has been validated, add it to the list
                    numbers.add(input);
                    keyboard.nextLine();
                }
            } else {
                //if entered number is invalid, tell that to the user
                System.out.println("Invalid input!");
                keyboard.nextLine();
            }
        }

        //if numbers list is empty, tell user to enter at least one number
        if (numbers.isEmpty()) {
            System.out.println("No numbers were entered. Please try again.");
            getNumbers();
        }

        //show main menu that lets user select what operations they want to perform on the data set
        mainMenu();
    }

    public static void mainMenu() {
        Scanner keyboard = new Scanner(System.in);
        int option;
        //show list of options
        System.out.println("##########################################################################");
        System.out.println("# Please enter the number of the option you would like to compute:       #");
        System.out.println("# 1 - View Data Set                                                      #");
        System.out.println("# 2 - Sort set Ascending                                                 #");
        System.out.println("# 3 - Sort set Descending                                                #");
        System.out.println("# 4 - Mean                                                               #");
        System.out.println("# 5 - Standard deviation                                                 #");
        System.out.println("# 6 - Median                                                             #");
        System.out.println("# 7 - Minimum                                                            #");
        System.out.println("# 8 - First quartile                                                     #");
        System.out.println("# 9 - Third quartile                                                     #");
        System.out.println("# 10 - Maximum                                                           #");
        System.out.println("# 11 - Enter new data set                                                #");
        System.out.println("# 12 - Exit                                                              #");
        System.out.println("##########################################################################");
        while (!keyboard.hasNextInt()) {
            System.out.println("Invalid input!");
            keyboard.nextLine();
        }
        option = keyboard.nextInt();
        //selection statements to check what option number the user has entered and perform the corresponding operation on the set
        if (option == 1) {
            viewSet();
        } else if (option == 2) {
            sortSet(1);
        } else if (option == 3) {
            sortSet(2);
        } else if (option == 4) {
            mean();
        } else if (option == 5) {
            stddev();
        } else if (option == 6) {
            median();
        } else if (option == 7) {
            min();
        } else if (option == 8) {
            firstQuartile();
        } else if (option == 9) {
            thirdQuartile();
        } else if (option == 10) {
            max();
        } else if (option == 11) {
            getNumbers();
        } else if (option == 12) {
            System.exit(0);
        } else {
            //if users enters an invalid option item, the program will prompt for the user to select another number
            System.out.println("Invalid option item selected!");
            //return to main menu
            mainMenu();
        }
        keyboard.close();
    }

    public static void viewSet() {
        
        System.out.println("-------------------------------");
        //iterate through all numbers in number set and show all numbers
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }

        //return to main menu once complete
        mainMenu();
    }

    public static void sortSet(int option) {
        //create a set that will be sorted
        ArrayList<Float> sortedList = new ArrayList<>();
        
        //copy numbers set to sortedList set
        for (int i = 0; i < numbers.size(); i++) {
            sortedList.add(numbers.get(i));
        }
        
        //sort sortedList set
        Collections.sort(sortedList);

        System.out.println("-----------------------------");
        
        if (option == 1) {
            //iterate through values in sortedList and print out the values in ascending order
            for (int i = 0; i < numbers.size(); i++) {
                System.out.println(sortedList.get(i));
            }
        } else {
            //iterate through values in sortedList and print out the values in descending order
            for (int i = numbers.size() - 1; i >= 0; i--) {
                System.out.println(sortedList.get(i));
            }
        }

        //return to main menu once complete
        mainMenu();
    }

    public static void mean() {
        //initialize total variable
        float total = 0;

        //find sum of all numbers in numbers set
        for (int i = 0; i < numbers.size(); i++) {
            total += numbers.get(i);
        }

        //find average of numbers in data set by dividing sum of numbers by the number of numbers
        float meanValue = total / numbers.size();

        //print value of mean
        System.out.println("Mean is " + meanValue);

        //return to main menu
        mainMenu();
    }
    
    public static void stddev() {
        //find mean of numbers in data set
        float total = 0;
        for (int i = 0; i < numbers.size(); i++) {
            total += numbers.get(i);
        }
        float meanValue = total / numbers.size();

        //find standard deviation of numbers by using standard deviation formula
        float stddevValue = 0;

        for (int i = 0; i < numbers.size(); i++) {
            stddevValue += Math.pow(numbers.get(i) - meanValue, 2);
        }

        stddevValue = (float) Math.sqrt(stddevValue / numbers.size());

        //print out standard deviation value
        System.out.println("Standard Deviation is " + stddevValue);

        //return to main menu
        mainMenu();
    }

    public static void median() {
        float median;
        
        //create a sorted list of the values in numbers list
        ArrayList<Float> sortedList = new ArrayList<>();
        
        for (int i = 0; i < numbers.size(); i++) {
            sortedList.add(numbers.get(i));
        }
        
        Collections.sort(sortedList);

        //if the list has an odd number of values, the middle number will be the median
        if (numbers.size() % 2 == 1) {
            median = sortedList.get((int) Math.ceil(numbers.size() / 2));
        } else {
            //if the list has an even number of values, the average of the two middle numbers will be the median
            median = (sortedList.get(numbers.size() / 2) + sortedList.get(numbers.size() / 2 - 1)) / 2;
        }

        //print median value
        System.out.println("Median is " + median);

        //return to main menu
        mainMenu();
    }

    public static void min() {
        //create sorted list
        ArrayList<Float> sortedList = new ArrayList<>();
        
        for (int i = 0; i < numbers.size(); i++) {
            sortedList.add(numbers.get(i));
        }

        Collections.sort(sortedList);

        //get minimum value in sorted list, which will just be the first value
        float min = sortedList.get(0);

        //print min value
        System.out.println("Minimum value is " + min);

        //return to main menu
        mainMenu();
    }

    public static void firstQuartile() {
        float firstQuartileNum;
        
        //created sorted numbers list
        ArrayList<Float> sortedList = new ArrayList<>();
        
        for (int i = 0; i < numbers.size(); i++) {
            sortedList.add(numbers.get(i));
        }

        Collections.sort(sortedList);

        //create a list of only the first half of the values in sortedList
        ArrayList<Float> firstHalf = new ArrayList<>();

        if (numbers.size() % 2 == 1) {
            //if odd number of values in numbers list
            for (int i = 0; i < (numbers.size() - 1)/2; i++) {
                firstHalf.add(sortedList.get(i));
            }
        } else {
            //if even number of values in numbers list
            for (int i = 0; i < numbers.size() / 2; i++) {
                firstHalf.add(sortedList.get(i));
            }
        }

        //find median of first half list; this will be the first quartile
        if (firstHalf.size() % 2 == 1) {
            firstQuartileNum = firstHalf.get((int) Math.ceil(firstHalf.size() / 2));
        } else {
            firstQuartileNum = (firstHalf.get(firstHalf.size() / 2) + firstHalf.get(firstHalf.size() / 2 - 1)) / 2;
        }

        //print first quartile value
        System.out.println("First quartile is " + firstQuartileNum);

        //return to main menu
        mainMenu();
    }

    public static void thirdQuartile() {
        float thirdQuartileNum;
        
        //sort numbers list
        ArrayList<Float> sortedList = new ArrayList<>();
        
        for (int i = 0; i < numbers.size(); i++) {
            sortedList.add(numbers.get(i));
        }

        Collections.sort(sortedList);
        //create a list of only the second half of the values in sortedList
        ArrayList<Float> secondHalf = new ArrayList<>();

        if (numbers.size() % 2 == 1) {
            //if odd number of values in numbers list
            for (int i = (numbers.size() - 1)/2 + 1; i < numbers.size(); i++) {
                secondHalf.add(sortedList.get(i));
            }
        } else {
            //if even number of values in numbers list
            for (int i = numbers.size()/2; i < numbers.size(); i++) {
                secondHalf.add(numbers.get(i));
            }
        }

        //find median of second half list; this will be the third quartile
        if (secondHalf.size() % 2 == 1) {
            thirdQuartileNum = secondHalf.get((int) Math.ceil(secondHalf.size() / 2));
        } else {
            thirdQuartileNum = (secondHalf.get(secondHalf.size() / 2) + secondHalf.get(secondHalf.size() / 2 - 1)) / 2;
        }

        //print third quartile value
        System.out.println("Third quartile is " + thirdQuartileNum);
        mainMenu();
    }

    public static void max() {
        //create sorted list of numbers list
        ArrayList<Float> sortedList = new ArrayList<>();
        
        for (int i = 0; i < numbers.size(); i++) {
            sortedList.add(numbers.get(i));
        }

        Collections.sort(sortedList);

        //max number in sorted list will simply be the last number in the list
        float max = sortedList.get(numbers.size() - 1);

        //print out maximum number in sorted list
        System.out.println("Maximum value is " + max);

        //return to main menu
        mainMenu();
    }
}
