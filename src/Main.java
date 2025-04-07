public class Main {
    public static void main(String[] args) {
        try {
            new Bureau();
        } catch (java.sql.SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}