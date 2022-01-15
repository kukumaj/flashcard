package pl.gredel.flashcard.ui;

import lombok.SneakyThrows;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Component;
import pl.gredel.flashcard.User;
import pl.gredel.flashcard.flashcard.Flashcard;
import pl.gredel.flashcard.flashcard.FlashcardService;
import pl.gredel.flashcard.flashcard.Side;
import pl.gredel.flashcard.user.AddressUser;
import pl.gredel.flashcard.user.Gender;
import pl.gredel.flashcard.user.UserService;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MenuApp {

    UserService userService;
    FlashcardService flashcardService;

    public MenuApp(UserService userService, FlashcardService flashcardService) {
        this.userService = userService;
        this.flashcardService = flashcardService;
    }

    @SneakyThrows
    public void menuStart() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Login");
        System.out.println("     2. Rejestracja");
        System.out.println("     0. Koniec");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        while (choice != 0) {
            switch (choice) {
                case 1:
                    login();
                    break;

                case 2:
                    register();

                    break;


            }
        }
        menuStart();
        System.in.read();
    }

    private void menuLogged(User user) throws IOException {
            System.out.println("     Zalogowano jako: "+ user.getLogin() );
            System.out.println("     ****************************************");
            System.out.println("     *                 MENU                 *");
            System.out.println("     ****************************************");
            System.out.println("     1. Przeglądaj fiszki");
            System.out.println("     2. Zarządzaj fiszkami");
            System.out.println("     9. Zmien haslo");
            System.out.println("     0. Wyloguj");

            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            while (choice != 0) {
                switch (choice) {
                    case 1:
                        viewFlashcardMenu(user);
                        break;

                    case 2:
                        manageFlashcardMenu(user);
                        break;
                    case 9:
                        resetPassword(user);
                        break;

                }

            }
            menuStart();
            System.in.read();
        }



    private void manageFlashcardMenu(User user) throws IOException {
        System.out.println("     Zalogowano jako: "+ user.getLogin() );
        System.out.println("     ****************************************");
        System.out.println("     *          Zarządzaj fiszkami          *");
        System.out.println("     ****************************************");
        System.out.println("     1. Dodaj");
        System.out.println("     2. Edytuj");
        System.out.println("     3. Usuń");
        System.out.println("     0. Menu");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        while (choice != 0) {
            switch (choice) {
                case 1:
                    addFlashcard(user);
                    break;

                case 2:
                    manageFlashcardMenu(user);
                    break;
            }
        }
        menuLogged(user);
        System.in.read();
    }

    //TODO
    private void viewFlashcardMenu(User user) throws IOException {
        System.out.println("     Zalogowano jako: "+ user.getLogin() );
        System.out.println("     ****************************************");
        System.out.println("     *                Fiszki                *");
        System.out.println("     ****************************************");
        System.out.println("     1. Wszystkie fiszki");
        System.out.println("     2. Kategorie");
        System.out.println("     0. Menu");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        while (choice != 0) {
            switch (choice) {
                case 1:
                    showAllFlashcards();
                    break;

                case 2:
                    showCategories(user);

                    break;

                case 0:
                    menuLogged(user);

            }
        }
        menuLogged(user);

    }



    private void showCategories(User user) throws IOException {
        System.out.println("Moje kategorie: ");
        flashcardService.getCategories(user.getLogin()).forEach(category -> System.out.println(category));

        System.out.println("Wpisz nazwe kategorii żeby wyświetlić (0 żeby opuścić) ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        while(!choice.equals("0")) {
            flashcardService.getFlashcardsByCategory(choice, user.getLogin());
            choice = in.nextLine();
        }
        viewFlashcardMenu(user);

    }

    private void showAllFlashcards() {
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        while (choice != 0) {
            flashcardService.getAllFlashcards().forEach(flashcard -> System.out.println(flashcard));
        }
    }

    private void addFlashcard (User user) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Podaj tytul");
        String title = in.nextLine();

        System.out.println("Podaj kategorie");
        String category = in.nextLine();

        System.out.println("Podaj nagłowek przedniej strony");
        String frontTitle = in.nextLine();

        System.out.println("Podaj opis przedniej strony");
        String frontDesc = in.nextLine();

        System.out.println("Podaj nagłowek tylniej strony");
        String backTitle = in.nextLine();

        System.out.println("Podaj opis tylniej strony");
        String backDesc = in.nextLine();

        Side front = new Side(frontTitle, frontDesc);
        Side back = new Side(backTitle, backDesc);
        Flashcard flashcard = new Flashcard(title, category, front , back);

        flashcardService.addFlashcard(flashcard, user.getLogin());

        System.out.println("Dodano fiszkę!");
        viewFlashcardMenu(user);

    }
    //TODO
    private void register () throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Podaj login");
        String login = in.nextLine();

        System.out.println("Podaj hasło");
        String password = in.nextLine();

        System.out.println("Podaj email");
        String email = in.nextLine();

        System.out.println("Podaj płeć [MALE / FEMALE]");
        String gender = in.nextLine();
        System.out.println("Podaj miasto");
        String city = in.nextLine();

        System.out.println("Podaj ulice");
        String street = in.nextLine();

        System.out.println("Podaj kod pocztowy:");
        String postalCode = in.nextLine();

        System.out.println("Podaj numer mieszkania:");
        String flatNumber = in.nextLine();

        AddressUser address = new AddressUser(
                city,
                street,
                postalCode,
                flatNumber
        );
        User user = new User(
                login,
                password,
                email,
                Gender.valueOf(gender),
                address
        );

        if (userService.register(user)) menuLogged(user);
        else {
            System.out.println("Taki użytkownik już istnieje");
            menuStart();
        }
    }

    //TODO
    private void login() throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Login:");
        String login = in.nextLine();

        System.out.println("Podaj hasło:");
        String password = in.nextLine();

        Optional<User> user = userService.login(login, password);

        if(user.isPresent())
            menuLogged(user.get());
        else {
            System.out.println("Błędne dane!");
            menuStart();
        }
    }
    private void resetPassword(User user) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Wprowadź stare hasło");
        String oldPwd = in.nextLine();
        System.out.println("Nowe hasło");
        String newPwd = in.nextLine();
        System.out.println("Powtórz hasło");
        String confirmPwd = in.nextLine();

        if(!oldPwd.equals(user.getPassword())) {
            System.out.println("Błędne hasło");
            menuLogged(user);
        }
        else {
            if (newPwd.equals(confirmPwd)){
                userService.resetPassword(user, newPwd);
                System.out.println("Hasło zostało zmienione");
                menuLogged(user);
            }
            else {
                System.out.println("Podane hasła różnią się!");
                menuLogged(user);
            }
        }
    }


}


