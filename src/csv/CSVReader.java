package csv;

import java.io.*;
import java.util.*;

public class CSVReader {

    /** Map that holds all enrollees **/
    private static Map<String, List<Enrollee>> allEnrollees = new HashMap<>();

    public static void main(String[] args) {
        String line = "";
        String commaDelimiter = ",";

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter file location (ex. C:\\Users\\Amanda\\csvDemo.csv)");
        String fileLocation = scan.next();
        scan.close();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            System.out.println("Reading File at location " + fileLocation);
            while ((line = br.readLine()) != null) {
                Enrollee tempEnrollee = createEnrollee(Arrays.asList(line.split(commaDelimiter)));
                if (tempEnrollee.version == Integer.MIN_VALUE || tempEnrollee.userId == null ||
                        tempEnrollee.name == null || tempEnrollee.insuranceCompany == null) {
                    System.out.println("An enrollee was not formatted correctly, please check the file.");
                } else if (tempEnrollee.version == -1) {
                    System.out.println("csv.Enrollee: " + tempEnrollee.name +
                            "for insurance company: " + tempEnrollee.insuranceCompany +
                            " does not have a valid version. Skipping.");
                } else {
                    addTempEnrollee(tempEnrollee);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Failed reading file at location " + fileLocation);
        }

        System.out.println("Finished reading File.");
        writeToFileByInsuranceCompany();
    }

    /**
     * Checks input line is the correct size and assigns appropriate values to Enrollee object
     *
     * @param inputLine
     * @return Enrollee
     */
    private static Enrollee createEnrollee(List<String> inputLine) {
        String userId = null;
        String name = null;
        int version;
        String insuranceCompany = null;

        if (inputLine.size() == 4 ) {
            userId = inputLine.get(0).trim().equals("") ? null : inputLine.get(0);
            name = inputLine.get(1).trim().equals("") ? null : inputLine.get(1);
            try {
                version = Integer.parseInt(inputLine.get(2));
            } catch (NumberFormatException nfe) {
                version = -1;
            }
            insuranceCompany = inputLine.get(3).trim().equals("") ? null : inputLine.get(3);
        } else {
            version = Integer.MIN_VALUE;
        }

        return new Enrollee(userId, name, version, insuranceCompany);
    }

    /**
     * Adds an enrollee. Checks first to make sure that the insurance company doesn't already exist.
     * If insurance company does exist, adds enrollee to existing list if the user id doesn't already exist.  If user id
     * exists, checks the version number to make sure the version added is newer.  If insurance company doesn't exist,
     * creates new list for that insurance company with the enrollee.
     *
     * @param aTempEnrollee
     */
    private static void addTempEnrollee(Enrollee aTempEnrollee) {
        List<Enrollee> insuranceCompanyEnrollees = allEnrollees.get(aTempEnrollee.insuranceCompany);
        if (insuranceCompanyEnrollees == null) {
            allEnrollees.put(aTempEnrollee.insuranceCompany, new ArrayList<>(Arrays.asList(aTempEnrollee)));
        } else {
            for (ListIterator<Enrollee> iterator = insuranceCompanyEnrollees.listIterator(); iterator.hasNext();) {
                Enrollee e = iterator.next();
                // If there are duplicate User Ids, then only the enrollee with the highest version should be included.
                if (e.userId.equals(aTempEnrollee.userId)) {
                    if (e.version < aTempEnrollee.version) {
                        iterator.remove();
                        iterator.add(aTempEnrollee);
                    }
                } else {
                    iterator.add(aTempEnrollee);
                }
            }
            allEnrollees.put(aTempEnrollee.insuranceCompany, insuranceCompanyEnrollees);
        }
    }

    /**
     * Writes a CSV file based on insurance company sorted by last name first name.
     */
    private static void writeToFileByInsuranceCompany() {
        for (Iterator<Map.Entry<String, List<Enrollee>>> entries = allEnrollees.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry<String, List<Enrollee>> entry = entries.next();
            List<Enrollee> currentEnrollees = entry.getValue();
            // Sort the enrollees by last and first name (ascending).
            currentEnrollees.sort(Comparator.comparing(Enrollee::formatNameForSort));
            System.out.println("Writing to new file for insurance company: " + entry.getKey());
            try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(entry.getKey() + ".csv")))){
                for(Enrollee e : currentEnrollees) {
                    writer.write(e.toString() + System.lineSeparator());
                }
            } catch (IOException e) {
                System.out.println("Failed writing to file for insurance company " + entry.getKey());
            }
        }
        System.out.println("Done writing files");
    }
}

