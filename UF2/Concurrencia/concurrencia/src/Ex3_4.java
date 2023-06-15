import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class Ex3_4 {

    public static Connection establishConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/unialexarredondo", "root",
                "patata");
        return connection;
    }
    public static void ExecuteOnDB(){
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
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ExecuteOnDB();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ExecuteOnDB();
            }
        });

        thread1.start();
        thread2.start();
    }
    
}
//sin semaforo o monitor se ejecutan de forma intercalada irregular