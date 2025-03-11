package DataBase;

import java.sql.*;

public class EtudiantManager {
    Connection con = null;
    Statement st = null;
    public EtudiantManager(){
        try {
            Class.forName(Config.DRIVER_NAME);
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver");
        }

        // Connection to the database
        try {
            con = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            st = con.createStatement();

            System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public int insertEtudiant(int id, String name, String surname, double score) throws SQLException {
        // insertion dans la base
        if (st !=null){
            String req_insertion = "insert into Etudiant values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(req_insertion);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, surname);
            ps.setDouble(4, score);

            int a = ps.executeUpdate();
            if (a>0){
                System.out.println("insertion reussie");
            }
            else{
                System.out.println("insertion echouée");
            }
            return a;
        }
    }
    public void updateEtudiant( int id, String name, String surname, double score) throws SQLException {
        //mise a jour
        if (st !=null) {
            String req_update = "update Etudiant set name=?, surname=?, score=? where id=?";
            PreparedStatement ps = con.prepareStatement(req_update);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setDouble(3, score);
            ps.setInt(4, id);

            int a = ps.executeUpdate();
            if (a>0){
                System.out.println("mise a jour reussie");
            }
            else{
                System.out.println("mise a jour echouée");
            }
        }
    }
    public void deleteEtudiant(int id) throws SQLException {
        //suppression
        if (st !=null) {
            String req_delete = "delete from Etudiant where id=?";
            PreparedStatement ps = con.prepareStatement(req_delete);
            ps.setInt(1, id);

            int a = ps.executeUpdate();
            if (a>0){
                System.out.println("suppression reussie");
            }
            else{
                System.out.println("suppression echouée");
            }
        }
    }
    public void  selectEtudiant() throws SQLException {
        //selection et affichage
        if (st !=null) {
            String req_select = "select * from Etudiant";

            ResultSet rs=st.executeQuery(req_select);
            ResultSetMetaData rsmd = rs.getMetaData();
            int nb_col = rsmd.getColumnCount();
            System.out.println("selection reussie");

            while (rs.next()) {
                for (int i = 1; i <= nb_col; i++) {
                    System.out.print(rs.getString(i) + " " + "\t");
                }
            }
        }
    }
    public ResultSet selectEtudiant(String req) throws SQLException {
        if (st !=null) {
            try {
                return  st.executeQuery(req);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
