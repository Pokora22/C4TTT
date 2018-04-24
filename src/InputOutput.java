import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Scanner;

public class InputOutput {

    public Scanner sc;

    public InputOutput(){
        sc = new Scanner(System.in);
    }

    /**
     * Validates that the user input is a number
     * @return integer value of user input
     */
    public int readNumber(){
        while(true){
            String input = sc.nextLine();
            if(input.matches("^\\d+$"))
                return Integer.valueOf(input);
        }
    }

    /**
     * Validates that the user input is a number
     * @param prompt - Message to be displayed as a prompt to the user
     * @return integer value of user input
     */
    public int readNumber(String prompt, String errorMsg){
        while(true){
            System.out.print(prompt);
            String input = sc.nextLine();

            if(input.matches("^\\d+$"))
                return Integer.valueOf(input);
            else System.out.println(errorMsg);
        }
    }

    /**
     * Validates that the user input is a number and is in defined bounds
     * @param lowerLimit - lower limit to check for (>=)
     * @param upperLimit - upper limit to check for (<)
     * @return integer value of user input
     */
    public int readNumber(int lowerLimit, int upperLimit, String errorMsg){
        while(true){
            String input = sc.nextLine();
            if(input.matches("^\\d+$") && Integer.valueOf(input) >= lowerLimit && Integer.valueOf(input) < upperLimit)
                return Integer.valueOf(input);
            else System.out.println(errorMsg);
        }
    }


    public int readNumber(String prompt, int lowerLimit, int upperLimit, String errorMsg){
        while(true){
            System.out.print(prompt);
            String input = sc.nextLine();
            if(input.matches("^\\d+$") && Integer.valueOf(input) >= lowerLimit && Integer.valueOf(input) < upperLimit)
                return Integer.valueOf(input);
            else System.out.println(errorMsg);
        }
    }

    /**
     * Returns first character from the user input as long as it exists and is not an empty space
     * @param prompt - Message to be displayed as a prompt to the user
     * @return first character of the user input
     */
    public char readFirstChar(String prompt){
        String charStr = "";
        while (true){
            System.out.print(prompt);
            charStr = sc.nextLine();
            if(!charStr.equals("") && charStr.charAt(0) != ' ')
                return charStr.charAt(0);
        }
    }

    public char readFirstChar(String prompt, String errorMsg){
        String charStr = "";
        while(true) {
            System.out.print(prompt);
            charStr = sc.nextLine();
            if (charStr.equals("") || charStr.charAt(0) != ' ')
                System.out.println(errorMsg);
            else return charStr.charAt(0);
        }
    }

    public String readString(){
        return sc.nextLine();
    }

    /**
     * Returns a string of user input as long as it's not empty
     * @param prompt - Message to be displayed as a prompt to the user
     * @return String representation of user input
     */
    public String readString(String prompt){
        String output = "";
        do{
            System.out.print(prompt);
            output = sc.nextLine();
        }while(output.equals(""));

        return output;
    }

    /**
     * Returns a string of user input as long as it's not empty
     * @param prompt - Message to be displayed as a prompt to the user
     * @param errorMsg - Error message to be displayed in case of input being empty
     * @return String representation of user input
     */
    public String readString(String prompt, String errorMsg){
        String output = "";
        while(true){
            System.out.print(prompt);
            output = sc.nextLine();

            if(output.equals(""))
                System.out.println(errorMsg);
            else return output;
        }
    }

    public void out(String msg){
        System.out.print(msg);
    }

    public void outLine(String msg){
        System.out.println(msg);
    }
}
