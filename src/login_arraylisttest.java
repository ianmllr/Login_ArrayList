import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
public class login_arraylisttest {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Velkommen. Har du en konto? ('ja' eller 'nej')");

        String newUsername = "";
        String newPassword = "";
        boolean newUser = false;
        if (existsOrNot().equals("nej")) {
            newUser = true;
            System.out.println("Indtast et nyt brugernavn.");
            newUsername = input.next();
            System.out.println("Navn modtaget. Dit navn er: " + newUsername);
            System.out.println("Indtast et nyt kodeord.");
            newPassword = input.next();
            System.out.println("Kodeord modtaget. Din bruger er nu oprettet.");
        }

        System.out.println("Indtast brugernavn.");
        int choice = checkUsername(goodUsernames(newUsername, newUser));
        System.out.println("Brugernavn korrekt. Angiv adgangskode. Du har 3 forsøg.");
        checkPassword(goodPasswords(newPassword, newUser), choice);

    } // main slut

    // denne metode spørger om brugeren findes eller ej og returnerer svaret
    public static String existsOrNot() {
        boolean answered = false;
        String userInput = "";
        while (!answered) {
            userInput = input.next();
            if (userInput.toLowerCase().equals("ja") || userInput.equals("nej")) {
                answered = true;
            } else {
                System.out.println("Ugyldigt svar. Prøv igen.");
            }
        }
        return userInput;
    }

    // denne metode som indeholder et array med de godkendte brugernavne
    public static ArrayList<String> goodUsernames(String newUsername, boolean newUser) {
        ArrayList<String> approvedNames = new ArrayList<>();
        approvedNames.add("admin");
        approvedNames.add("useradmin");
        approvedNames.add("sysadmin");
        if (newUser) {
            approvedNames.add(newUsername);
        }
        return approvedNames;
    }

    // denne metode som indeholder et array med de godkendte passwords
    public static ArrayList<String> goodPasswords(String newPassword, boolean newUser) {
        ArrayList<String> approvedPasswords = new ArrayList<>();
        approvedPasswords.add("password");
        approvedPasswords.add("1234");
        approvedPasswords.add("pw123");
        if (newUser) {
            approvedPasswords.add(newPassword);
        }
        return approvedPasswords;
    }

    // denne metode tjekker om brugeren har indtastet et godkendt brugernavn
    public static int checkUsername(ArrayList<String> goodUsernames) { // vi henter goodUsernames ArrayListen
        boolean goodName = false;
        int choice = 0;

        while (!goodName) { // gentager loopet indtil brugeren skriver et godkendt navn
            String userInput = input.next();

            if (userInput.equals(goodUsernames.get(0))) { // tjekker om navnet er rigtigt og sætter choice
                goodName = true;
                choice = 0;
            } else if (userInput.equals(goodUsernames.get(1))) {
                goodName = true;
                choice = 1;
            } else if (userInput.equals(goodUsernames.get(2))) {
                goodName = true;
                choice = 2;
            } else if (userInput.equals(goodUsernames.get(3))) {
                goodName = true;
                choice = 3;
            } else {
                System.out.println("Brugernavnet findes ikke. Prøv igen.");
            }
        }
        return choice;
    }

    // denne metode henter goodPasswords[] og og nameChoice og tjekker om kodeordet svarer til brugernavnet
    public static void checkPassword(ArrayList<String> goodPasswords, int nameChoice) {
        boolean goodPass = false;
        int count = 0;
        LocalTime tid = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // gør så tiden bliver vist i sekunder
        LocalDate dato = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // retter formatet til dd-mm-yyyy

        while (!goodPass && count < 3) { // kører loopet indtil brugeren har skrevet kodeordet korrekt eller brugt 3 forsøg.
            String userInput = input.next();
            if (userInput.equals(goodPasswords.get(nameChoice))) {
                goodPass = true;
                System.out.println("Kodeord korrekt. Du loggede ind klokken " + tid + " den " + dato.format(formatter) + ".");
            } else {
                count++;
                System.out.println("Forkert kodeord. Du har " + (3 - count) + " forsøg tilbage.");
            }
        }
        if (!goodPass) {
            System.out.println("Du har brugt alle dine forsøg. Din konto er låst.");
        }

    }


}







