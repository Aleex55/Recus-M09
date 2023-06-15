import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Ex6 {

    public static Connection establishConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/unialexarredondo", "root", "patata");
        return connection;
    }

    public static void ExecuteOnDB() {
        try {
            Connection connection = establishConnection();

            TimeUnit.SECONDS.sleep(3);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT ALUMN_DNI, ALUMN_NOM FROM alumnes");

            while (resultSet.next()) {
                String dni = resultSet.getString("ALUMN_DNI");
                String nombre = resultSet.getString("ALUMN_NOM");

                System.out.println("DNI: " + dni + ", Nombre: " + nombre);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphore.acquire();
                    System.out.println("Thread1:");
                    ExecuteOnDB();
                    semaphore.release();
                    System.out.println("Permisos disponibles: " + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphore.acquire();
                    System.out.println("Thread2:");
                    ExecuteOnDB();
                    semaphore.release();
                    System.out.println("Permisos disponibles: " + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
