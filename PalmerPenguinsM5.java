// PalmerPenguinsM5.java
// Gabriela Hernandez
// 10/03/26
// Reads the CSV file and parses the data into arrays

import java.io.*;
import java.util.*;

public class PalmerPenguinsM5 {

    static final String FILE_NAME =  "PalmerPenguins.csv";
    
    // constants to represent the species and count
    public static final int NUM_SPECIES = 3;
    public static final String SP_CHINSTRAP = "Chinstrap";
    public static final String SP_GENTOO = "Gentoo";
    public static final String SP_ADELIE = "Adelie";
    
    public static void main(String[] args) {
    
        // constants representing  0-based numeric index of each species
        final int SP_CHINSTRAP_INDEX = 0;
        final int SP_GENTOO_INDEX = 1;
        final int SP_ADELIE_INDEX = 2;
    
        // Read the species data from column 1
        // TODO 1 Declare a String array named speciesData which stores the
        //        results of calling CSVReader.readFile to read column 1
        //        from the data file. Replace the ellipsis (...) with the 
        //        array declaration.
        String[] speciesData = CSVReader.readFile(FILE_NAME, 1);
        
        // store the counts of each species in this array
        // TODO 2 Declare an int array named speciesCount 
        //        with a size declarator of NUM_SPECIES.
        int[] speciesCount = new int[NUM_SPECIES];

        // exit if no data was found
        // TODO 3 encode an if statement to determine if the 
        //        length of the speciesData array is 0
            if (speciesData.length == 0) {
            System.out.println("Error: The file is empty or could not be read.");
            return;
        }

        // loop through the data and count the species
        // TODO 4 encode an enhanced for loop (also known as a foreach loop
        //        which iterates through the speciesData array using a loop
        //        variable named species of type String. Replace the 
        //        ellipsis (...) with the for loop header
        for (String species : speciesData) {
            if (species.equals(SP_CHINSTRAP)) {
                speciesCount[SP_CHINSTRAP_INDEX]++;
            } else if (species.equals(SP_GENTOO)) {
                speciesCount[SP_GENTOO_INDEX]++;
            } else if (species.equals(SP_ADELIE)) {
                speciesCount[SP_ADELIE_INDEX]++;
            }
        }
    
        // Print the results
        // TODO 5 print the value of each speciesCount array element
        //        using the index constants. Use three separate
        //        System.out.println statements.
         System.out.println(SP_CHINSTRAP + " count = " +
                 speciesCount[SP_CHINSTRAP_INDEX]);
                  
         System.out.println(SP_GENTOO + " count = " +
                 speciesCount[SP_GENTOO_INDEX]);
         
         System.out.println(SP_ADELIE + " count = " +
                 speciesCount[SP_ADELIE_INDEX]);
    }
}

class CSVReader {
    /**
     * readFile reads a file line by line, extracts the specified column from
     * each line, and returns an array containing the extracted values.
     * 
     * The file is expected to be delimited by commas. If a line does not contain
     * the requested column, an empty string is added for that line.
     *
     * @param FILE_NAME the name (or path) of the file to be read
     * @param column the zero-based index of the column to extract from each line
     * @return an array of strings containing the extracted column values from each row.
     *         If an error occurs while reading the file, an empty array is returned.
     */
     public static String[] readFile(String FILE_NAME, int column) {
        List<String> columnValues = new ArrayList<>();
        
        // Using try-with-resources to ensure the reader is closed properly.
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assumes the file is comma-delimited.
                String[] tokens = line.split(",");
                
                // If the specified column exists, add its value.
                // Otherwise, add an empty string.
                if (column >= 0 && column < tokens.length) {
                    columnValues.add(tokens[column]);
                } else {
                    columnValues.add(""); // Alternatively, you could skip this row.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Return an empty array if an error occurs.
            return new String[0];
        }
        
        // Convert the list to an array and return it.
        return columnValues.toArray(new String[0]);
    }
}