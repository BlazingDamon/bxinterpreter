// Author: David Smith
// April 28, 2016
// CC0 License

// How to use this:
// save this text as "Interpret.java"
// put your current instruction set into the same directory as this new file
// type "javac Interpret.java"
// type "java Interpret 0 nameOfCurrentInstructionSet nameOfTranslatedInstructions"
// edit translated instructions (in hex) as you see fit
// type "java Interpret 1 nameOfTranslatedInstructions nameOfNewInstructionSet"
//
// Note: 	Make sure you are loading the correct instruction set in your simulation
// Note: 	I am not sure if the length of the instrution set file will mess up the 
// 			simulation, so be careful of that

import java.io.*;

public class Interpret {

    public static String toHex(String binary) {
    String hex = "";
    switch(binary) {
        case "0000":    hex = "0"; break;
        case "0001":    hex = "1"; break;
        case "0010":    hex = "2"; break;
        case "0011":    hex = "3"; break;
        case "0100":    hex = "4"; break;
        case "0101":    hex = "5"; break;
        case "0110":    hex = "6"; break;
        case "0111":    hex = "7"; break;
        case "1000":    hex = "8"; break; 
        case "1001":    hex = "9"; break;
        case "1010":    hex = "A"; break;
        case "1011":    hex = "B"; break;
        case "1100":    hex = "C"; break;
        case "1101":    hex = "D"; break;
        case "1110":    hex = "E"; break;
        case "1111":    hex = "F"; break;
		}
    return hex;
    }

    public static String toBinary(String hex) {
    String binary = "";
    switch(hex) {
        case "0":    binary = "0000"; break;
        case "1":    binary = "0001"; break;
        case "2":    binary = "0010"; break;
        case "3":    binary = "0011"; break;
        case "4":    binary = "0100"; break;
        case "5":    binary = "0101"; break;
        case "6":    binary = "0110"; break;
        case "7":    binary = "0111"; break;
        case "8":    binary = "1000"; break;
        case "9":    binary = "1001"; break;
        case "A":    binary = "1010"; break;
        case "B":    binary = "1011"; break;
        case "C":    binary = "1100"; break;
        case "D":    binary = "1101"; break;
        case "E":    binary = "1110"; break;
        case "F":    binary = "1111"; break;
		}
    return binary;
    }

    public static void main(String [] args) {

    if (args.length<=2) {
        System.out.println("\nPlease use this format: \n\"java Interpret 0 fileToRead fileToWrite\"  (binary to hex)\n\"java Interpret 1 fileToRead fileToWrite\"  (hex to binary)\n");
        System.exit(0);
        }
    int chooseDirection = Integer.parseInt(args[0]);
    String fileInName = args[1];
    String fileOutName = args[2];

    String line = null;
    String out = "";

        try {
            FileReader fileReader = 
                new FileReader(fileInName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
        
        int lineNumber = 0;

        // Binary to hex
        if (chooseDirection == 0) {
            while((line = bufferedReader.readLine()) != null) {
            String nibble0 = null;
            String nibble1 = null;
            String nibble2 = null;
            String nibble3 = null;
            if (line.length()>=8) {
                nibble0 = line.substring(0,4);
                nibble1 = line.substring(4,8);
                nibble0 = toHex(nibble0);
                nibble1 = toHex(nibble1);
            }

            line = bufferedReader.readLine();
            lineNumber++;

            if (line.length()>=8) {
                nibble2 = line.substring(0,4);
                nibble3 = line.substring(4,8);
                nibble2 = toHex(nibble2);
                nibble3 = toHex(nibble3);
            }

            System.out.println(lineNumber+": \t"+nibble2+nibble3+nibble0+nibble1);
            out += nibble2+nibble3+nibble0+nibble1+"\n";

            lineNumber++;
            }
         }

        // Hex to binary
        if (chooseDirection == 1) {
            while((line = bufferedReader.readLine()) != null) {
            String char0 = null;
            String char1 = null;
            String char2 = null;
            String char3 = null;
            if (line.length()>=4) {
                char0 = String.valueOf(line.charAt(0));
                char1 = String.valueOf(line.charAt(1));
                char2 = String.valueOf(line.charAt(2));
                char3 = String.valueOf(line.charAt(3));
                char0 = toBinary(char0);
                char1 = toBinary(char1);
                char2 = toBinary(char2);
                char3 = toBinary(char3);
                System.out.println(lineNumber+": \t"+char2+" "+char3);
                lineNumber++;
                System.out.println(lineNumber+": \t"+char0+" "+char1);
                out += char2+char3+"\n"+char0+char1+"\n";
            }
            lineNumber++;
            }
         }

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileInName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileInName + "'");
        }

        try {
            FileWriter fileWriter =
                new FileWriter(fileOutName);

            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            bufferedWriter.write(out);

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileOutName + "'");
        }
    }
}
