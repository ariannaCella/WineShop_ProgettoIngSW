package com.example.democlientserver;



import Actors.*;
import RequestResponse.RequestChangePassword;
import RequestResponse.RequestModifyWine;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

public class ModelDBMS
{
    private static final String DBURL =
            "jdbc:mysql://localhost:3306/projectwineshop?";  //dovremo mettere il nostro

    private static final String LOGIN = "root";
    private static final String PASSWORD = "";



    //dato l'utente restituisce la sua password
    public static String returnPassword(String user,String table){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect= "select " + table+".Password from "+table + " where "+table+".Username='"+ user+ "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                String psw = rset.getString("Password");
                return psw;
            }
            return "Utente inesistente";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return "errore";
    }
    //ritorna Employee corrispondente a username e password
    public static Employee returnEmployeeConnected(String username,String password,String table){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            String strSelect= "select * from "+table + " where Username='"+ username+ "' and Password='"+password+"'";
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

            Employee c=new Employee(name,surname,fc,email,phone,address,admin,user,psw);



            return c;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    //ritorna Supplier corrispondente a username e password
    public static Supplier returnSupplierConnected(String username,String password,String table){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            String strSelect= "select * from "+table + " AS u where u.Username='"+ username+ "' and u.Password='"+password+"'";
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
            Supplier c=new Supplier(name,surname,fc,email,phone,address,0,user,psw);
            return c;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    //ritorna Shipper corrispondente a username e password
    public static Shipper returnShipperConnected(String username,String password,String table){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            String strSelect= "select * from "+table + " AS u where u.Username='"+username+ "' and u.Password='"+password+"'";
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
            Shipper c=new Shipper(name,surname,fc,email,phone,address,0,user,psw);
            return c;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    //ritorna cliente corrispondente a username e password
    public static Client returnClientConnected(String username,String password,String table){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {
            String strSelect= "select * from "+table + " where Username='"+ username+ "' and Password='"+password+"'";
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
            Client c=new Client(name,surname,fc,email,phone,address,admin,user,psw);
            return c;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }




   //nuovo cliente registrato
   public static void newUser(Client newClient){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public static void newEmployee(Employee newEmployee){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "insert into employee values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, newEmployee.getName());
            pstmt.setString(2, newEmployee.getSurname());
            pstmt.setString(3, newEmployee.getFiscalCode());
            pstmt.setString(4, newEmployee.getEmail());
            pstmt.setInt(5, newEmployee.getPhone());
            pstmt.setString(6, newEmployee.getAddress());
            pstmt.setBoolean(7,false);
            pstmt.setString(8, newEmployee.getUsername());
            pstmt.setString(9, newEmployee.getPassword());
            pstmt.addBatch();
            pstmt.executeBatch();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static ArrayList searchWineDBMS(String txt, String attribute){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image " +
                    "FROM wine AS w WHERE "+ attribute+ "='" + txt+"'";
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
                Wine v=new Wine(id,name,producer,origin,notes,vines,year,nsales,qnt,quality,price,img);
                wines.add(v);
            }
            return wines;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Wine searchWineIdDBMS(int ID){
        Wine v = null;
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image " +
                    "FROM wine AS w WHERE w.WineId = "+ID+"";
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

    public static Wine searchPriceCFSupWineDBMS(int ID){
        Wine v = null;
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image, w.CfSupplier " +
                    "FROM wine AS w WHERE w.WineId = "+ID+"";
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
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image " +
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
                Wine v=new Wine(id,name,producer,origin,notes,vines,year,nsales,qnt,quality,price,img);
                wines.add(v);
            }
            return wines;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Wine> setPromoDefault() {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT w.WineId, w.Name, w.Producer, w.Origin, w.Notes, w.Vines, w.Year, w.NSales, w.Quantity, w.Quality, w.Price, w.Image " +
                    "FROM wine AS w ";
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Wine> wines = new ArrayList<Wine>();
            for (int i=0;i<3;i++){
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
                Wine v=new Wine(id,name,producer,origin,notes,vines,year,nsales,qnt,quality,price,img);
                wines.add(v);
                //System.out.println(wines.get(i).infoWine());

            }

            return wines;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Sale> listSaleDBMS() {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT s.SaleId, s.FiscalCode, s.Address, s.WineId, s.Nbottles, s.Price, s.Date, s.Signature, s.Accepted " +
                    "FROM sale AS s ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Sale> arraySale = new ArrayList<Sale>();
            while (rset.next()) {
                int saleId = rset.getInt("SaleId");
                String fc = rset.getString("FiscalCode");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                Date date= rset.getDate("Date");
                boolean sign=rset.getBoolean("Signature");
                boolean acc=rset.getBoolean("Accepted");
                Sale s= new Sale(saleId,wid,nbott,sign,acc, fc,addr,price,date);
                arraySale.add(s);
                System.out.println(s.infoSale());
            }
            return arraySale;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Sale> listSaleShipperDBMS() {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT s.SaleId, s.Address, s.WineId, s.Nbottles, s.Price, s.Date, s.Signature, s.Accepted " +
                    "FROM sale AS s ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Sale> arraySale = new ArrayList<Sale>();
            while (rset.next()) {
                int saleId = rset.getInt("SaleId");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                double price = rset.getDouble("Price");
                Date date= rset.getDate("Date");
                boolean sign=rset.getBoolean("Signature");
                boolean acc=rset.getBoolean("Accepted");
                Sale s= new Sale(saleId,addr,wid,nbott,price,date,sign,acc);
                arraySale.add(s);
                System.out.println(s.infoSale());
            }
            return arraySale;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Purchase> listPurchaseDBMS() {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT p.PurchaseId, p.FiscalCode,p.FiscClient, p.Address, p.WineId, p.Nbottles, p.Price, p.Signature, p.Accepted " +
                    "FROM purchase AS p ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Purchase> arrayPurchase = new ArrayList<Purchase>();
            while (rset.next()) {
                int purchId = rset.getInt("PurchaseId");
                String fc = rset.getString("FiscalCode");
                String fClient=rset.getString("FiscClient");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                boolean sign=rset.getBoolean("Signature");
                boolean acc=rset.getBoolean("Accepted");
                Purchase s= new Purchase(purchId,fc,fClient,addr,wid,nbott,price,sign,acc);
                arrayPurchase.add(s);
                System.out.println(s.infoPurchase());
            }
            return arrayPurchase;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Purchase> listPurchaseSupplierDBMS(String fc) {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT p.PurchaseId, p.WineId, p.Nbottles, p.Price, p.Signature, p.Accepted " +
                    "FROM purchase AS p WHERE p.FiscalCode = '"+fc+"' AND p.Signature=1";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Purchase> arrayPurchase = new ArrayList<Purchase>();
            while (rset.next()) {
                int purchId = rset.getInt("PurchaseId");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                boolean sign=rset.getBoolean("Signature");
                boolean acc=rset.getBoolean("Accepted");
                Purchase s= new Purchase(purchId,wid,nbott,price,sign,acc);

                arrayPurchase.add(s);
                System.out.println(s.infoPurchase());
            }
            return arrayPurchase;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Purchase> listPurchaseClientDBMS(String cf) {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT p.Address, p.WineId, p.Nbottles, p.Price, p.Signature, p.Accepted " +
                    "FROM purchase AS p WHERE p.FiscClient = '"+cf+"' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Purchase> arrayPurchase = new ArrayList<Purchase>();
            while (rset.next()) {
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                boolean sign=rset.getBoolean("Signature");
                boolean acc=rset.getBoolean("Accepted");
                Purchase s= new Purchase(addr,wid,nbott,price,sign,acc);
                System.out.println(s.infoPurchase());
                arrayPurchase.add(s);

            }
            return arrayPurchase;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Client> listClientDBMS() {
        try (Connection conn = DriverManager.getConnection(DBURL,LOGIN,PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT c.Name, c.Surname, c.FiscalCode, c.Email, c.Phone, c.Address, c.Username " +
                    "FROM client AS c";
            ResultSet rse = stmt.executeQuery(strSelect);

            ArrayList<Client> tableClient =new ArrayList<>();
            while (rse.next()) {
                String name = rse.getString("Name");
                String surname = rse.getString("Surname");
                String fiscalCode = rse.getString("FiscalCode");
                String email = rse.getString("Email");
                int phone = rse.getInt("Phone");
                String address = rse.getString("Address");
                String username = rse.getString("Username");

                Client v=new Client(name,surname,fiscalCode,email,phone,address,username);
                tableClient.add(v);
            }
            return tableClient;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Employee> listEmployeeDBMS() {
        try (Connection conn = DriverManager.getConnection(DBURL,LOGIN,PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT e.Name, e.Surname, e.FiscalCode, e.Email, e.Phone, e.Address, e.Username " +
                    "FROM employee AS e";
            ResultSet rse = stmt.executeQuery(strSelect);

            ArrayList<Employee> tableEmployee =new ArrayList<>();
            while (rse.next()) {
                String name = rse.getString("Name");
                String surname = rse.getString("Surname");
                String fiscalCode = rse.getString("FiscalCode");
                String email = rse.getString("Email");
                int phone = rse.getInt("Phone");
                String address = rse.getString("Address");
                String username = rse.getString("Username");

                Employee v=new Employee(name,surname,fiscalCode,email,phone,address,0,username);
                System.out.println(v.getName());
                tableEmployee.add(v);
            }
            return tableEmployee;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void ChangePassword(RequestChangePassword req){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "UPDATE employee AS e SET e.Password= ? WHERE e.Username= ? AND e.Password = ?";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, req.getNewpassword());
            pstmt.setString(2, req.getUsername());
            pstmt.setString(3, req.getOldpassword());
            pstmt.addBatch();
            pstmt.executeBatch();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


//per modificare il vino tramite id, funzione dell'impiegato
    public static void modifyWine(int txtId, RequestModifyWine reqModifyWine) {
        try (Connection conn = DriverManager.getConnection(
                DBURL , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();)
        {
            if (!reqModifyWine.getQuantity().isBlank()){
                String strUpdate="UPDATE wine SET Quantity=Quantity+"+reqModifyWine.getQuantity()+ " WHERE WineId="+ txtId;
                stmt.executeUpdate(strUpdate);
            }
            if (!reqModifyWine.getPrice().isBlank()){
                String strUpdate="UPDATE wine SET Price="+reqModifyWine.getPrice()+ " WHERE WineId="+ txtId;
                stmt.executeUpdate(strUpdate);
            }
            if (!reqModifyWine.getNote().isBlank()){
                String strUpdate="UPDATE wine SET Notes='"+reqModifyWine.getNote()+ "' WHERE WineId="+ txtId;
                stmt.executeUpdate(strUpdate);
            }
            if (!reqModifyWine.getYear().isBlank()){
                String strUpdate="UPDATE wine SET Year="+reqModifyWine.getYear()+ " WHERE WineId="+ txtId;
                stmt.executeUpdate(strUpdate);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void addSales(int nSales, Wine wineOrder) {
        try (Connection conn = DriverManager.getConnection(
                DBURL , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();)
        {

            String strUpdate="UPDATE wine " +
                    "SET NSales= (SELECT w.NSales+"+nSales+" FROM wine w WHERE w.WineId="+wineOrder.getWineId()+")"+
                    ", Quality = (SELECT w.Quality+0.05*"+nSales+" FROM wine w WHERE w.WineId="+wineOrder.getWineId()+")"+
                    "WHERE wine.WineId="+ wineOrder.getWineId();
            stmt.executeUpdate(strUpdate);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateQuantity(int nSales,Wine wineOrder) {
        try (Connection conn = DriverManager.getConnection(
                DBURL , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();)
        {

        String strUpdate="UPDATE wine " +
                "SET Quantity=(SELECT w.Quantity-"+nSales+" FROM wine w WHERE w.WineId="+wineOrder.getWineId()+")"+
                "WHERE wine.WineId="+ wineOrder.getWineId();
        stmt.executeUpdate(strUpdate);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void newOrder(Sale order) {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "insert into sale values (?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, order.getSaleId());
            pstmt.setString(2, order.getFiscalCode());
            pstmt.setString(3, order.getAddress());
            pstmt.setInt(4, order.getWineId());
            pstmt.setInt(5, order.getnBottles());
            pstmt.setDouble(6, order.getPrice());
            pstmt.setDate(7, order.getD());
            pstmt.setBoolean(8, order.getSignature());
            pstmt.setBoolean(9, order.getAccepted());
            pstmt.addBatch();
            pstmt.executeBatch();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void newProposalPurchase(Purchase p) {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String insertSql = "insert into purchase values (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, p.getPurchaseId());
            pstmt.setString(2, p.getFiscalCode());
            pstmt.setString(3, p.getFiscClient());
            pstmt.setString(4, p.getAddress());
            pstmt.setInt(5, p.getWineId());
            pstmt.setInt(6, p.getnBottles());
            pstmt.setDouble(7, p.getPrice());
            pstmt.setBoolean(8, p.getSignature());
            pstmt.setBoolean(9, p.getAccepted());
            pstmt.setDate(10,p.getData());
            pstmt.addBatch();
            pstmt.executeBatch();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList searchClientSurname(String txt){
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT c.Name, c.Surname, c.FiscalCode, c.Email, c.Phone, c.Address, c.Username FROM client AS c WHERE c.Surname = '"+txt+"' ";
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
                Client v=new Client(name,surname,fiscalCode,email,phone,address,username);
                c.add(v);
            }
            return c;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Sale> SaleDateDBMS(Date d1,Date d2) {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="SELECT s.SaleId, s.FiscalCode, s.Address, s.WineId, s.Nbottles, s.Price, s.Date, s.Signature, s.Accepted " +
                    "FROM sale AS s WHERE s.Date> '"+d1+"' AND s.Date < '"+d2+"'";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Sale> arraySale = new ArrayList<Sale>();
            while (rset.next()) {
                int saleId = rset.getInt("SaleId");
                String fc = rset.getString("FiscalCode");
                String addr = rset.getString("Address");
                int wid = rset.getInt("WineId");
                int nbott = rset.getInt("Nbottles");
                float price = rset.getFloat("Price");
                Date date= rset.getDate("Date");
                boolean sign=rset.getBoolean("Signature");
                boolean acc=rset.getBoolean("Accepted");
                Sale s= new Sale(saleId,wid,nbott,sign,acc, fc,addr,price,date);
                arraySale.add(s);
                System.out.println(s.infoSale());
            }
            return arraySale;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static int signSales(int idsale) {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="UPDATE sale SET Signature = true WHERE SaleId = ?";

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
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="UPDATE sale SET Accepted = true WHERE SaleId = ?";

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
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="UPDATE purchase SET Signature = true WHERE PurchaseId = ?";

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
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            String strSelect="UPDATE purchase SET Accepted = true WHERE PurchaseId = ?";
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


    public static void SqlSelectDemo(int value) //il parametro è l'opzione da eseguire
    {
        try (Connection conn = DriverManager.getConnection(
                DBURL  , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();) {

            //prova
        /*
            String strSelect="select * from client";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next())
            {
                String n = rset.getString("Name");
                String s = rset.getString("Surname");
                int f   = rset.getInt("FiscalCode");
                String e=rset.getString("Email");
                int p   = rset.getInt("Phone");
                String ad=rset.getString("Address");
                System.out.println(n + ", " + s + ", " + f + ", " + e+ ", " + p + ", " +ad);
                //se vogliamo contare record rowCount++
            }*/
            String strSelect="";

            switch (value) {
                case 1:
                    strSelect="select * from client";
                    break;
                case 2:
                    strSelect="select client.Name from client";
                    break;
            }
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next())
            {
                String n = rset.getString("Name");
                String s = rset.getString("Surname");
                int f   = rset.getInt("FiscalCode");
                String e=rset.getString("Email");
                int p   = rset.getInt("Phone");
                String ad=rset.getString("Address");
                System.out.println(n + ", " + s + ", " + f + ", " + e+ ", " + p + ", " +ad);
                //se vogliamo contare record rowCount++
            }

        /*defire la query, per parametro verrà passaro un valore che definisce
        qual è l'azione da fare, dovremo fare uno switch in cui mettiamo le possibilità
        con le relative query, salvate come stringhe
        switch value:
        case :.....

        poi dobbiamo eseguirle con il comando
        ResultSet rset = stmt.executeQuery(strSelect); */

        /*
        per visualizzare i risultati della query
        in questo esempio li visualizza e basta noi dovremo salvare le tuple
        in un array di stringhe e resituirlo come risultato della chiamata
        in modo tale che il cliente possa poi visualizzare i risultati
        while (rset.next())
      {
        String title = rset.getString("title");
        double price = rset.getDouble("price");
        int copies   = rset.getInt("copies");
        System.out.println(title + ", " + price + ", " + copies);
        //se vogliamo contare record rowCount++
      }
       */
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }


    public static void SqlUpdateDemo(int value)
    {
        try (Connection conn = DriverManager.getConnection(
                DBURL , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();)
        {


        /* in base al valore di value facciamo switch con le query associate
        per modificare update .....

        poi eseguiamo con int countUpdated = stmt.executeUpdate(strUpdate);  questo ritorna righe cambiate se non sbaglio
        in questo caso abbiamo solo effettuato modifica non ci interessa che restituista qualcosa
        */
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void SqlInsertDemo(int value)
    {
        try (Connection conn = DriverManager.getConnection(
                DBURL , LOGIN, PASSWORD);
             Statement stmt = conn.createStatement();)
        {
        /*
        //riportato per completezza e perchè cosi sappiamo che esiste un modo per creare liste velocemente
        //ma in teoria il parametro che passiamo è già il valore da inserire
         var books = List.of(
          new Book(1001, "Java for dummies", "Tan Ah Teck", 11.11f, 11),
          new Book(1002, "More Java for dummies", "Tan Ah Teck", 22.22f, 22),
          new Book(1003, "More Java for more dummies", "Mohammad Ali", 33.33f, 33),
          new Book(1004, "A Cup of Java", "Kumar", 44.44f, 44),
          new Book(1005, "A Teaspoon of Java", "Kevin Jones", 55.55f, 55));
      String insertSql = "insert into books values (?, ?, ?, ?, ?)";
      PreparedStatement pstmt = conn.prepareStatement(insertSql);
      for (Book b : books) {
        pstmt.setString(1, Integer.toString(b.getId()));
        pstmt.setString(2, b.getTitle());
        pstmt.setString(3, b.getAuthor());
        pstmt.setString(4, Float.toString(b.getPrice()));
        pstmt.setString(5, Integer.toString(b.getCopies()));
        pstmt.addBatch();
    }
    pstmt.executeBatch();
        */

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}

