package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car volga = new Car("Volga", 21);
        Car mers = new Car("Mersedes", 120);
        Car porsche = new Car("Porsche", 911);
        Car nimbus = new Car("Nimbus", 2000);
        carService.add(volga);
        carService.add(mers);
        carService.add(porsche);
        carService.add(nimbus);

        userService.add(new User("Johnny", "Silverhand", "cyber@mail.ru", porsche));
        userService.add(new User("Lev", "Trotsky", "bronstein@mail.ru", mers));
        userService.add(new User("Issa", "Pliev", "issa45@mail.ru", volga));
        userService.add(new User("Harry", "Potter", "harry@mail.ru", nimbus));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        String REQUEST_MODEL = "Volga";
        int REQUEST_SERIES = 21;
        User reqestUser = userService.getUserByCar(REQUEST_MODEL, REQUEST_SERIES);
        System.out.printf("The user with the requested machine(%s model and %d series) is: \n", REQUEST_MODEL, REQUEST_SERIES);
        System.out.println("Id = " + reqestUser.getId());
        System.out.println("First Name = " + reqestUser.getFirstName());
        System.out.println("Last Name = " + reqestUser.getLastName());
        System.out.println("Email = " + reqestUser.getEmail());

        context.close();
    }
}
