package com.example.democlientserver;

import Actors.*;
import RequestResponse.RequestChangePassword;
import RequestResponse.RequestModifyWine;
import Actors.WineSold;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;

public class ModelDBMS {
    private static final String DBURL =
            "jdbc:mysql://localhost:3306/projectwineshop?";  //dovremo mettere il nostro

    private static final String LOGIN = "root";
    private static final String PASSWORD = "";


    //dato l'utente restituisce la sua password
    public static String returnPassword(String user, String table) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "select " + table + ".Password from " + table + " where " + table + ".Username='" + user + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                String psw = rset.getString("Password");
                return psw;
            }
            return "Utente inesistente";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "errore";
    }

    //ritorna Employee corrispondente a username e password
    public static Employee returnEmployeeConnected(String username, String password, String table) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            String strSelect = "select * from " + table + " where Username='" + username + "' and Password='" + password + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            rset.next();
            String name = rset.getString("Name");
            String surname = rset.getString("Surname");
            String fc = rset.getString("FiscalCode");
            String email = rset.getString("Email");
            int phone = Integer.parseInt(rset.getString("Phone"));
            String address = rset.getString("Address");
            int admin = Integer.parseInt(rset.getString("Admin"));
            String user = rset.getString("Username");
            String psw = rset.getString("Password");

            Employee c = new Employee(name, surname, fc, email, phone, address, admin, user, psw);


            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //ritorna Supplier corrispondente a username e password
    public static Supplier returnSupplierConnected(String username, String password, String table) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            String strSelect = "select * from " + table + " AS u where u.Username='" + username + "' and u.Password='" + password + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            rset.next();
            String name = rset.getString("Name");
            String surname = rset.getString("Surname");
            String fc = rset.getString("FiscalCode");
            String email = rset.getString("Email");
            int phone = Integer.parseInt(rset.getString("Phone"));
            String address = rset.getString("Address");
            //int admin = Integer.parseInt(rset.getString("Admin"));
            String user = rset.getString("Username");
            String psw = rset.getString("Password");
            Supplier c = new Supplier(name, surname, fc, email, phone, address, 0, user, psw);
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //ritorna Shipper corrispondente a username e password
    public static Shipper returnShipperConnected(String username, String password, String table) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            String strSelect = "select * from " + table + " AS u where u.Username='" + username + "' and u.Password='" + password + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            rset.next();
            String name = rset.getString("Name");
            String surname = rset.getString("Surname");
            String fc = rset.getString("FiscalCode");
            String email = rset.getString("Email");
            int phone = Integer.parseInt(rset.getString("Phone"));
            String address = rset.getString("Address");
            //int admin = Integer.parseInt(rset.getString("Admin"));
            String user = rset.getString("Username");
            String psw = rset.getString("Password");
            Shipper c = new Shipper(name, surname, fc, email, phone, address, 0, user, psw);
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //ritorna cliente corrispondente a username e password
    public static Client returnClientConnected(String username, String password, String table) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            String strSelect = "select * from " + table + " where Username='" + username + "' and Password='" + password + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            rset.next();
            String name = rset.getString("Name");
            String surname = rset.getString("Surname");
            String fc = rset.getString("FiscalCode");
            String email = rset.getString("Email");
            int phone = Integer.parseInt(rset.getString("Phone"));
            String address = rset.getString("Address");
            int admin = Integer.parseInt(rset.getString("Admin"));
            String user = rset.getString("Username");
            String psw = rset.getString("Password");
            Client c = new Client(name, surname, fc, email, phone, address, admin, user, psw);
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //nuovo cliente registrato
    public static void newUser(Client newClient) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "insert into client values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, newClient.getName());
            pstmt.setString(2, newClient.getSurname());
            pstmt.setString(3, newClient.getFiscalCode());
            pstmt.setString(4, newClient.getEmail());
            pstmt.setInt(5, newClient.getPhone());
            pstmt.setString(6, newClient.getAddress());
            pstmt.setString(7, newClient.getUsername());
            pstmt.setString(8, newClient.getPassword());
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void newEmployee(Employee newEmployee) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "insert into employee values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, newEmployee.getName());
            pstmt.setString(2, newEmployee.getSurname());
            pstmt.setString(3, newEmployee.getFiscalCode());
            pstmt.setString(4, newEmployee.getEmail());
            pstmt.setInt(5, newEmployee.getPhone());
            pstmt.setString(6, newEmployee.getAddress());
            pstmt.setBoolean(7, false);
            pstmt.setString(8, newEmployee.getUsername());
            pstmt.setString(9, newEmployee.getPassword());
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList searchWineDBMS(String txt, String attribute) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image " +
                    "FROM wine AS w WHERE " + attribute + "='" + txt + "'";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Wine> wines = new ArrayList<Wine>();
            while (rset.next()) {
                int id = rset.getInt("WineId");
                String name = rset.getString("Name");
                String producer = rset.getString("Producer");
                String origin = rset.getString("Origin");
                String notes = rset.getString("Notes");
                String vines = rset.getString("Vines");
                int year = rset.getInt("Year");
                int nsales = rset.getInt("NSales");
                int qnt = rset.getInt("Quantity");
                int quality = rset.getInt("Quality");
                double price = rset.getDouble("Price");
                String img = rset.getString("Image");
                Wine v = new Wine(id, name, producer, origin, notes, vines, year, nsales, qnt, quality, price, img);
                wines.add(v);
            }
            return wines;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Wine searchWineIdDBMS(int ID) {
        Wine v = null;
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image " +
                    "FROM wine AS w WHERE w.WineId = " + ID + "";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                int id = rset.getInt("WineId");
                String name = rset.getString("Name");
                String producer = rset.getString("Producer");
                String origin = rset.getString("Origin");
                String notes = rset.getString("Notes");
                String vines = rset.getString("Vines");
                int year = rset.getInt("Year");
                int nsales = rset.getInt("NSales");
                int qnt = rset.getInt("Quantity");
                int quality = rset.getInt("Quality");
                double price = rset.getDouble("Price");
                String img = rset.getString("Image");
                v = new Wine(id, name, producer, origin, notes, vines, year, nsales, qnt, quality, price, img);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return v;

    }

    public static Wine searchPriceCFSupWineDBMS(int ID) {
        Wine v = null;
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image, w.CfSupplier " +
                    "FROM wine AS w WHERE w.WineId = " + ID + "";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                int id = rset.getInt("WineId");
                String name = rset.getString("Name");
                String producer = rset.getString("Producer");
                String origin = rset.getString("Origin");
                String notes = rset.getString("Notes");
                String vines = rset.getString("Vines");
                int year = rset.getInt("Year");
                int nsales = rset.getInt("NSales");
                int qnt = rset.getInt("Quantity");
                int quality = rset.getInt("Quality");
                double price = rset.getDouble("Price");
                String img = rset.getString("Image");
                String cfSup = rset.getString("CfSupplier");
                v = new Wine(id, name, producer, origin, notes, vines, year, nsales, qnt, quality, price, img, cfSup);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return v;
    }


    public static ArrayList<Wine> listWineDBMS() {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image, w.CfSupplier " +
                    "FROM wine AS w ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Wine> wines = new ArrayList<Wine>();
            while (rset.next()) {
                int id = rset.getInt("WineId");
                String name = rset.getString("Name");
                String producer = rset.getString("Producer");
                String origin = rset.getString("Origin");
                String notes = rset.getString("Notes");
                String vines = rset.getString("Vines");
                int year = rset.getInt("Year");
                int nsales = rset.getInt("NSales");
                int qnt = rset.getInt("Quantity");
                int quality = rset.getInt("Quality");
                int price = rset.getInt("Price");
                String img = rset.getString("Image");
                String fcS = rset.getString("CfSupplier");
                Wine v = new Wine(id, name, producer, origin, notes, vines, year, nsales, qnt, quality, price, img, fcS);
                wines.add(v);
            }
            return wines;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Wine> setPromoDefault() {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image " +
                    "FROM wine AS w ";
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Wine> wines = new ArrayList<Wine>();
            for (int i = 0; i < 3; i++) {
                rset.next();
                int id = rset.getInt("WineId");
                String name = rset.getString("Name");
                String producer = rset.getString("Producer");
                String origin = rset.getString("Origin");
                String notes = rset.getString("Notes");
                String vines = rset.getString("Vines");
                int year = rset.getInt("Year");
                int nsales = rset.getInt("NSales");
                int qnt = rset.getInt("Quantity");
                int quality = rset.getInt("Quality");
                int price = rset.getInt("Price");
                String img = rset.getString("Image");
                Wine v = new Wine(id, name, producer, origin, notes, vines, year, nsales, qnt, quality, price, img);
                wines.add(v);
                //System.out.println(wines.get(i).infoWine());

            }

            return wines;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Sale> listSaleDBMS() {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT s.SaleId, s.FiscalCode, s.Address, s.WineId, s.Nbottles as Bottles, s.Price, s.Date, s.Signature, s.Accepted " +
                    "FROM sale AS s ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Sale> arraySale = new ArrayList<Sale>();
            while (rset.next()) {
                int saleId = rset.getInt("SaleId");
                String fc = rset.getString("FiscalCode");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Bottles");
                float price = rset.getFloat("Price");
                Date date = rset.getDate("Date");
                boolean sign = rset.getBoolean("Signature");
                boolean acc = rset.getBoolean("Accepted");
                Sale s = new Sale(saleId, wid, nbott, sign, acc, fc, addr, price, date);
                arraySale.add(s);
                System.out.println(s.infoSale());
            }
            return arraySale;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Sale> listSaleShipperDBMS() {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT s.SaleId, s.Address, s.WineId, s.Nbottles, s.Price, s.Date, s.Signature, s.Accepted " +
                    "FROM sale AS s WHERE s.Signature= true ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Sale> arraySale = new ArrayList<Sale>();
            while (rset.next()) {
                int saleId = rset.getInt("SaleId");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                double price = rset.getDouble("Price");
                Date date = rset.getDate("Date");
                boolean sign = rset.getBoolean("Signature");
                boolean acc = rset.getBoolean("Accepted");
                Sale s = new Sale(saleId, addr, wid, nbott, price, date, sign, acc);
                arraySale.add(s);
                System.out.println(s.infoSale());
            }
            return arraySale;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Purchase> listPurchaseDBMS() {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT p.PurchaseId, p.FiscalCode,p.FiscClient, p.Address, p.WineId, p.Nbottles, p.Price, p.Signature, p.Accepted " +
                    "FROM purchase AS p ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Purchase> arrayPurchase = new ArrayList<Purchase>();
            while (rset.next()) {
                int purchId = rset.getInt("PurchaseId");
                String fc = rset.getString("FiscalCode");
                String fClient = rset.getString("FiscClient");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                boolean sign = rset.getBoolean("Signature");
                boolean acc = rset.getBoolean("Accepted");
                Purchase s = new Purchase(purchId, fc, fClient, addr, wid, nbott, price, sign, acc);
                arrayPurchase.add(s);
                System.out.println(s.infoPurchase());
            }
            return arrayPurchase;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Purchase> listPurchaseSupplierDBMS(String fc) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT p.PurchaseId, p.WineId, p.Nbottles, p.Price, p.Signature, p.Accepted " +
                    "FROM purchase AS p WHERE p.FiscalCode = '" + fc + "' AND p.Signature=1";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Purchase> arrayPurchase = new ArrayList<Purchase>();
            while (rset.next()) {
                int purchId = rset.getInt("PurchaseId");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                boolean sign = rset.getBoolean("Signature");
                boolean acc = rset.getBoolean("Accepted");
                Purchase s = new Purchase(purchId, wid, nbott, price, sign, acc);

                arrayPurchase.add(s);
                System.out.println(s.infoPurchase());
            }
            return arrayPurchase;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Purchase> listPurchaseClientDBMS(String cf) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT p.PurchaseId, p.Address, p.WineId, p.Nbottles, p.Price, p.Signature, p.Accepted " +
                    "FROM purchase AS p WHERE p.FiscClient = '" + cf + "' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Purchase> arrayPurchase = new ArrayList<Purchase>();
            while (rset.next()) {
                int p=rset.getInt("PurchaseId");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                boolean sign = rset.getBoolean("Signature");
                boolean acc = rset.getBoolean("Accepted");
                Purchase s = new Purchase(p,addr, wid, nbott, price, sign, acc);
                System.out.println(s.infoPurchase());
                arrayPurchase.add(s);

            }
            return arrayPurchase;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Client> listClientDBMS() {
        try (Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT c.Name, c.Surname, c.FiscalCode, c.Email, c.Phone, c.Address, c.Username " +
                    "FROM client AS c";
            ResultSet rse = stmt.executeQuery(strSelect);

            ArrayList<Client> tableClient = new ArrayList<>();
            while (rse.next()) {
                String name = rse.getString("Name");
                String surname = rse.getString("Surname");
                String fiscalCode = rse.getString("FiscalCode");
                String email = rse.getString("Email");
                int phone = rse.getInt("Phone");
                String address = rse.getString("Address");
                String username = rse.getString("Username");

                Client v = new Client(name, surname, fiscalCode, email, phone, address, username);
                tableClient.add(v);
            }
            return tableClient;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Employee> listEmployeeDBMS() {
        try (Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT e.Name, e.Surname, e.FiscalCode, e.Email, e.Phone, e.Address, e.Username " +
                    "FROM employee AS e";
            ResultSet rse = stmt.executeQuery(strSelect);

            ArrayList<Employee> tableEmployee = new ArrayList<>();
            while (rse.next()) {
                String name = rse.getString("Name");
                String surname = rse.getString("Surname");
                String fiscalCode = rse.getString("FiscalCode");
                String email = rse.getString("Email");
                int phone = rse.getInt("Phone");
                String address = rse.getString("Address");
                String username = rse.getString("Username");

                Employee v = new Employee(name, surname, fiscalCode, email, phone, address, 0, username);
                System.out.println(v.getName());
                tableEmployee.add(v);
            }
            return tableEmployee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void ChangePassword(RequestChangePassword req) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "UPDATE employee AS e SET e.Password= ? WHERE e.Username= ? AND e.Password = ?";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, req.getNewpassword());
            pstmt.setString(2, req.getUsername());
            pstmt.setString(3, req.getOldpassword());
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //per modificare il vino tramite id, funzione dell'impiegato
    public static void modifyWine(int txtId, RequestModifyWine reqModifyWine) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            if (!reqModifyWine.getQuantity().isBlank()) {
                String strUpdate = "UPDATE wine SET Quantity=Quantity+" + reqModifyWine.getQuantity() + " WHERE WineId=" + txtId;
                stmt.executeUpdate(strUpdate);
            }
            if (!reqModifyWine.getPrice().isBlank()) {
                String strUpdate = "UPDATE wine SET Price=" + reqModifyWine.getPrice() + " WHERE WineId=" + txtId;
                stmt.executeUpdate(strUpdate);
            }
            if (!reqModifyWine.getNote().isBlank()) {
                String strUpdate = "UPDATE wine SET Notes='" + reqModifyWine.getNote() + "' WHERE WineId=" + txtId;
                stmt.executeUpdate(strUpdate);
            }
            if (!reqModifyWine.getYear().isBlank()) {
                String strUpdate = "UPDATE wine SET Year=" + reqModifyWine.getYear() + " WHERE WineId=" + txtId;
                stmt.executeUpdate(strUpdate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addSales(int nSales, Wine wineOrder) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strUpdate = "UPDATE wine " +
                    "SET NSales= (SELECT w.NSales+" + nSales + " FROM wine w WHERE w.WineId=" + wineOrder.getWineId() + ")" +
                    ", Quality = (SELECT w.Quality+0.05*" + nSales + " FROM wine w WHERE w.WineId=" + wineOrder.getWineId() + ")" +
                    "WHERE wine.WineId=" + wineOrder.getWineId();
            stmt.executeUpdate(strUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateQuantity(int nSales, Wine wineOrder) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strUpdate = "UPDATE wine " +
                    "SET Quantity=(SELECT w.Quantity-" + nSales + " FROM wine w WHERE w.WineId=" + wineOrder.getWineId() + ")" +
                    "WHERE wine.WineId=" + wineOrder.getWineId();
            stmt.executeUpdate(strUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newOrder(Sale order) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "insert into sale values (?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, order.getSaleId());
            pstmt.setString(2, order.getFiscalCode());
            pstmt.setString(3, order.getAddress());
            pstmt.setInt(4, order.getWineId());
            pstmt.setInt(5, order.getBottles());
            pstmt.setDouble(6, order.getPrice());
            pstmt.setDate(7, order.getD());
            pstmt.setBoolean(8, order.getSignature());
            pstmt.setBoolean(9, order.getAccepted());
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newProposalPurchase(Purchase p) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "insert into purchase values (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, p.getPurchaseId());
            pstmt.setString(2, p.getFiscalCode());
            pstmt.setString(3, p.getFiscClient());
            pstmt.setString(4, p.getAddress());
            pstmt.setInt(5, p.getWineId());
            pstmt.setInt(6, p.getBottles());
            pstmt.setDouble(7, p.getPrice());
            pstmt.setBoolean(8, p.getSignature());
            pstmt.setBoolean(9, p.getAccepted());
            pstmt.setDate(10, p.getData());
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList searchClientSurname(String txt) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT c.Name, c.Surname, c.FiscalCode, c.Email, c.Phone, c.Address, c.Username FROM client AS c WHERE c.Surname = '" + txt + "' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Client> c = new ArrayList<Client>();
            while (rset.next()) {
                String name = rset.getString("Name");
                String surname = rset.getString("Surname");
                String fiscalCode = rset.getString("FiscalCode");
                String email = rset.getString("Email");
                int phone = rset.getInt("Phone");
                String address = rset.getString("Address");
                String username = rset.getString("Username");
                Client v = new Client(name, surname, fiscalCode, email, phone, address, username);
                c.add(v);
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Client getAgency() {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT c.Name, c.Surname, c.FiscalCode, c.Email, c.Phone, c.Address, c.Username FROM client AS c WHERE c.FiscalCode = 'aziendaWineShop' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            Client c = new Client();
            while (rset.next()) {
                String name = rset.getString("Name");
                String surname = rset.getString("Surname");
                String fiscalCode = rset.getString("FiscalCode");
                String email = rset.getString("Email");
                int phone = rset.getInt("Phone");
                String address = rset.getString("Address");
                String username = rset.getString("Username");
                c = new Client(name, surname, fiscalCode, email, phone, address, username);
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void RemoveEmployee(String username) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "DELETE FROM employee WHERE username = ? ";
            PreparedStatement pstmt = conn.prepareStatement(strSelect);
            pstmt.setString(1, username);
            pstmt.addBatch();
            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Purchase> PurchaseDateDBMS(Date d1, Date d2) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT p.PurchaseId, p.FiscalCode, p.FiscClient, p.Address, p.WineId, p.Nbottles, p.Price, p.Data, p.Signature, p.Accepted " +
                    "FROM purchase AS p WHERE p.Data> '" + d1 + "' AND p.Data < '" + d2 + "'";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Purchase> arrayPurchase = new ArrayList<Purchase>();
            while (rset.next()) {
                int saleId = rset.getInt("PurchaseId");
                String fc = rset.getString("FiscalCode");
                String fcClient = rset.getString("FiscClient");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                Date date = rset.getDate("Data");
                boolean sign = rset.getBoolean("Signature");
                boolean acc = rset.getBoolean("Accepted");
                Purchase s = new Purchase(saleId, fc,fcClient,addr,wid,nbott,price, sign, acc,date);
                arrayPurchase.add(s);

            }
            return arrayPurchase;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int signSales(int idsale) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "UPDATE sale SET Signature = true WHERE SaleId = ?";

            PreparedStatement pstmt = conn.prepareStatement(strSelect);
            pstmt.setInt(1, idsale);
            pstmt.addBatch();
            pstmt.executeBatch();
            System.out.println(strSelect);
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int acceptSales(int idsale) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "UPDATE sale SET Accepted = true WHERE SaleId = ?";

            PreparedStatement pstmt = conn.prepareStatement(strSelect);
            pstmt.setInt(1, idsale);
            pstmt.addBatch();
            pstmt.executeBatch();
            System.out.println(strSelect);
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int signPurchase(int idpurchase) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "UPDATE purchase SET Signature = true WHERE PurchaseId = ?";

            PreparedStatement pstmt = conn.prepareStatement(strSelect);
            pstmt.setInt(1, idpurchase);
            pstmt.addBatch();
            pstmt.executeBatch();
            System.out.println(strSelect);
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int acceptPurchase(int idpurchase) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "UPDATE purchase SET Accepted = true WHERE PurchaseId = ?";
            PreparedStatement pstmt = conn.prepareStatement(strSelect);
            pstmt.setInt(1, idpurchase);
            pstmt.addBatch();
            pstmt.executeBatch();
            strSelect="SELECT Nbottles,WineId FROM purchase WHERE PurchaseId="+ idpurchase;
            ResultSet rset = stmt.executeQuery(strSelect);
            int nbottles=0,wineid=0;
            while (rset.next()) {
                nbottles = rset.getInt("Nbottles");
                wineid=rset.getInt("WineId");
            }
            String strSelect2 = "UPDATE wine SET Quantity = Quantity+? WHERE WineId =?";
            pstmt = conn.prepareStatement(strSelect2);
            pstmt.setInt(1, nbottles);
            pstmt.setInt(2, wineid);
            pstmt.addBatch();
            pstmt.executeBatch();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static double getIncome(int yearReport,int monthReport) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT sum(s.Price) as Income " +
                    "FROM sale AS s WHERE year(Date)="+yearReport+" AND month(Date)="+monthReport;
            ResultSet rset = stmt.executeQuery(strSelect);

            double income=0;
            while (rset.next()) {
                income=rset.getDouble("Income");
            }
            return income;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getExpenses(int yearReport,int monthReport) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT sum(Price) as expenses " +
                    "FROM purchase WHERE year(Data)="+yearReport+" AND month(Data)="+monthReport ;
            ResultSet rset = stmt.executeQuery(strSelect);

            double expenses=0;
            while (rset.next()) {
                expenses=rset.getDouble("expenses");
                System.out.println("expenses"+expenses);
            }
            return expenses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getBottleSold(int yearReport,int monthReport) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT sum(Nbottles) as bottlesSold " +
                    "FROM sale WHERE year(Date)="+yearReport+" AND month(Date)="+monthReport;
            ResultSet rset = stmt.executeQuery(strSelect);

            int bottlesSold=0;
            while (rset.next()) {
                bottlesSold=rset.getInt("bottlesSold");
            }
            return bottlesSold;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getBottleAvailable() {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT sum(Quantity) as bottlesAvailable " +
                    "FROM wine ";
            ResultSet rset = stmt.executeQuery(strSelect);

            int bottlesAvailable=0;
            while (rset.next()) {
                bottlesAvailable=rset.getInt("bottlesAvailable");
            }
            return bottlesAvailable;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ArrayList<WineSold> getWinesSold(int yearReport, int monthReport) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT w.WineId, sum(s.Nbottles) as sold " +
                    "FROM wine as w JOIN sale as s ON w.WineId=s.WineId "+
                    "WHERE year(Date)="+yearReport+" AND month(Date)="+monthReport+
                    " GROUP BY w.WineId";
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<WineSold> temp=new ArrayList<WineSold>();
            int id=0, s=0;
            while (rset.next()) {
                id=rset.getInt("WineId");
                s=rset.getInt("sold");
                WineSold t=new WineSold(id,s);
                temp.add(t);
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Purchase getPurchaseById(String idP) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT *  " +
                    "FROM purchase WHERE PurchaseId="+idP;
            ResultSet rset = stmt.executeQuery(strSelect);
            Purchase purch=new Purchase();
            while (rset.next()) {
                int id=rset.getInt("PurchaseId");
                String cf=rset.getString("FiscalCode");
                String cfC=rset.getString("FiscClient");
                String add=rset.getString("Address");
                int idW=rset.getInt("WineId");
                int n=rset.getInt("Nbottles");
                Double price=rset.getDouble("Price");
                Boolean s=rset.getBoolean("Signature");
                Boolean a=rset.getBoolean("Accepted");
                Date d= rset.getDate("Data");
                purch=new Purchase(id,cf,cfC,add,idW,n,price,s,a,d);
            }
            return purch;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deletePurchase(Purchase purch) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "DELETE FROM purchase WHERE PurchaseId = ? ";
            PreparedStatement pstmt = conn.prepareStatement(strSelect);
            pstmt.setInt(1, purch.getPurchaseId());
            pstmt.addBatch();
            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newValutation(Valutation v) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "insert into valutation values (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, v.getVote());
            pstmt.setDate(2, v.getDate());
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getAverageValutation(int yearReport, int monthReport) {
        try (Connection conn = DriverManager.getConnection(
                DBURL, LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect = "SELECT AVG(Vote) as averageValutation FROM valutation WHERE year(Data)="+yearReport+" AND month(Data)="+monthReport;
            ResultSet rset = stmt.executeQuery(strSelect);
            Double avg= null;
            while (rset.next()) {
                avg=rset.getDouble("averageValutation");
            }
            return avg;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

