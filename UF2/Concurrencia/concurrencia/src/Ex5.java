import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class Ex5 {

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
        Monitor monitor = new Monitor();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread1:");
                monitor.execute();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread2:");
                monitor.execute();
            }
        });

        thread1.start();
        thread2.start();
    }

    static class Monitor {
        private boolean executing = false;

        public synchronized void execute() {
            while (executing) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            executing = true;

            ExecuteOnDB();

            executing = false;

            notifyAll();
        }
    }
}
