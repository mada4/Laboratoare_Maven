package Lab8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.System.exit;

public class MainApp {

    public static void afisare_persoane(Connection connection, String mesaj) throws SQLException {
        String sql = "select * from persoane";
        System.out.println("\n---"+mesaj+"---");
        Statement statement = connection.createStatement();
        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ",varsta="
                        + rs.getInt(3));
                afisare_excursii(connection, rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afisare_excursii(Connection connection, int id_persoana) throws SQLException {
        String sql = "select * from excursii where id_persoana = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_persoana);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next())
                System.out.println("    id_persoana=" + rs.getInt(1) + ",id_excursie=" + rs.getInt(2) + ",destinatie="
                        + rs.getString(3) + ",anul=" + rs.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afisare_excursii_dupa_nume(Connection connection, String nume) throws SQLException {
        String sql = "select * from persoane where nume = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nume);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next())
                afisare_excursii(connection, rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afisare_persoane_destinatie(Connection connection, String destinatie) throws SQLException {
        String sql_nr_destinatii = "select count(*) from excursii where destinatia = ? and id_persoana = ?";
        String sql_persoane = "select * from persoane";
        Statement statement = connection.createStatement();
        try (ResultSet rs_persoane = statement.executeQuery(sql_persoane)) {
            while(rs_persoane.next()) {
                PreparedStatement ps = connection.prepareStatement(sql_nr_destinatii);
                ps.setString(1, destinatie);
                ps.setInt(2, rs_persoane.getInt(1));
                ResultSet rs_nr_destinatii = ps.executeQuery();
                rs_nr_destinatii.next();
                if(rs_nr_destinatii.getInt(1) != 0) {
                    System.out.println("id=" + rs_persoane.getInt(1) + ", nume=" + rs_persoane.getString(2) + ",varsta="
                            + rs_persoane.getInt(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afisare_persoane_an(Connection connection, int anul) throws SQLException {
        String sql_nr_destinatii = "select count(*) from excursii where anul = ? and id_persoana = ?";
        String sql_persoane = "select * from persoane";
        Statement statement = connection.createStatement();
        try (ResultSet rs_persoane = statement.executeQuery(sql_persoane)) {
            while(rs_persoane.next()) {
                PreparedStatement ps = connection.prepareStatement(sql_nr_destinatii);
                ps.setInt(1, anul);
                ps.setInt(2, rs_persoane.getInt(1));
                ResultSet rs_nr_destinatii = ps.executeQuery();
                rs_nr_destinatii.next();
                if(rs_nr_destinatii.getInt(1) != 0) {
                    System.out.println("id=" + rs_persoane.getInt(1) + ", nume=" + rs_persoane.getString(2) + ",varsta="
                            + rs_persoane.getInt(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adaugare_persoana(Connection connection, String nume, int varsta) {
        String sql = "insert into persoane(nume,varsta) values (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setInt(2, varsta);
            int nr_randuri = ps.executeUpdate();
            System.out.println();
            System.out.println("Numar randuri afectate de adaugare=" + nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void adaugare_excursie(Connection connection, int id_persoana, String destinatia, int anul) {
        String sql = "insert into excursii(id_persoana, destinatia, anul) values (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id_persoana);
            ps.setString(2, destinatia);
            ps.setInt(3, anul);
            int nr_randuri = ps.executeUpdate();
            System.out.println();
            System.out.println("Numar randuri afectate de adaugare=" + nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static boolean verificare_id_persoana(Connection connection, int id_persoana) throws SQLException {
        String sql = "select count(*) from persoane where id_persoana = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_persoana);
        ResultSet nr_randuri = statement.executeQuery();
        nr_randuri.next();
        return nr_randuri.getInt(1) != 0;
    }

    public static boolean verificare_an_excursie(Connection connection, int id_persoana, int anul) throws SQLException {
        String sql = "select varsta from persoane where id_persoana = ?";
        PreparedStatement ps_persoana = connection.prepareStatement(sql);
        ps_persoana.setInt(1, id_persoana);
        ResultSet rs_persoana = ps_persoana.executeQuery();
        rs_persoana.next();
        int an_nastere_persoana = LocalDate.now().getYear() - rs_persoana.getInt(1);
        return anul >= an_nastere_persoana;
    }

    public static void stergere_persoana(Connection connection,int id){
        String sql="delete from persoane where id_persoana=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int nr_randuri=ps.executeUpdate();
            System.out.println();
            System.out.println("Numar randuri afectate de stergere="+nr_randuri);
        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void stergere_excursie(Connection connection,int id){
        String sql="delete from excursii where id_excursie=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int nr_randuri=ps.executeUpdate();
            System.out.println();
            System.out.println("Numar randuri afectate de stergere="+nr_randuri);
        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/pjlab8";
        Connection connection = DriverManager.getConnection (url, "root", "");
        Scanner scanner=new Scanner(System.in);
        String nume;
        String destinatie;
        int anul;
        int id_persoana;
        int id_excursie;
        boolean ok = false;
        int opt;
        System.out.println("0.Exit!");
        System.out.println("1.Adăugarea unei persoane în tabela persoane!");
        System.out.println("2.Adăugarea unei excursii în tabela excursii!");
        System.out.println("3.Afișarea tuturor persoanelor şi pentru fiecare persoană a excursiilor în care a fost!");
        System.out.println("4.Afișarea excursiilor în care a fost o persoană!");
        System.out.println("5.Afișarea tuturor persoanelor care au vizitat o anumita destinație!");
        System.out.println("6.Afișarea persoanelor care au făcut excursii într-un an introdus!");
        System.out.println("7.Ștergerea unei excursii!");
        System.out.println("8.Ștergerea unei persoane (împreună cu excursiile în care a fost)!");
        System.out.println();
        do{
            System.out.print("Introduceti optiunea: ");
            opt=scanner.nextInt();
            scanner.nextLine();
            switch (opt)
            {
                case 0:
                    exit(0);
                    break;
                case 1:
                    System.out.print("Nume: ");
                    nume = scanner.nextLine();
                    ok = false;
                    do {
                        try {
                            System.out.print("Varsta: ");
                            int varsta = Integer.parseInt(scanner.nextLine());
                            if(varsta <= 0 || varsta > 100)
                                throw new ExceptieVarsta("Varsta nu poate fi negativa sau mai mare decat 120");
                            adaugare_persoana(connection, nume, varsta);
                            ok = true;
                        } catch(NumberFormatException e) {
                            System.out.println("Intrare invalida");
                        } catch (ExceptieVarsta e){
                            System.out.println(e.getMessage());
                        }
                    } while(!ok);
                    break;
                case 2:
                    System.out.print("Id_persoana: ");
                    id_persoana = Integer.parseInt(scanner.nextLine());
                    if(verificare_id_persoana(connection, id_persoana)) {
                        System.out.print("Destinatia: ");
                        String destinatia = scanner.nextLine();
                        ok = false;
                        do {
                            try {
                                System.out.print("Anul: ");
                                anul = Integer.parseInt(scanner.nextLine());
                                if(anul > LocalDate.now().getYear())
                                    throw new ExceptieAnExcursie("Anul nu poate fi mai mare decat anul curent");
                                if(!verificare_an_excursie(connection, id_persoana, anul))
                                    throw new ExceptieAnExcursie("Anul nu poate fi mai mic decat anul de nastere al persoanei");
                                adaugare_excursie(connection, id_persoana, destinatia, anul);
                                ok = true;
                            } catch(NumberFormatException e) {
                                System.out.println("Intrare invalida");
                            } catch (ExceptieAnExcursie e) {
                                System.out.println(e.getMessage());
                            }
                        } while(!ok);
                    }
                    else System.out.println("Nu exista persoana in tabel");
                    break;
                case 3:
                    afisare_persoane(connection, "Persoane: ");
                    break;
                case 4:
                    System.out.print("Nume: ");
                    nume = scanner.nextLine();
                    System.out.println();
                    System.out.println("Excursii: ");
                    afisare_excursii_dupa_nume(connection, nume);
                    break;
                case 5:
                    System.out.print("Destinatie: ");
                    destinatie = scanner.nextLine();
                    System.out.println();
                    System.out.println("Persoane care au fost in excursie in " + destinatie + ":");
                    afisare_persoane_destinatie(connection, destinatie);
                    break;
                case 6:
                    System.out.print("Anul: ");
                    anul = Integer.parseInt(scanner.nextLine());
                    afisare_persoane_an(connection, anul);
                    break;
                case 7:
                    System.out.print("Id_excursie: ");
                    id_excursie = Integer.parseInt(scanner.nextLine());
                    stergere_excursie(connection, id_excursie);
                    break;
                case 8:
                    System.out.print("Id_persoana: ");
                    id_persoana = Integer.parseInt(scanner.nextLine());
                    stergere_persoana(connection, id_persoana);
                    break;
                default:
                    System.out.println("Optiune gresita!");
            }
        }while(opt!=0);
    }
}
