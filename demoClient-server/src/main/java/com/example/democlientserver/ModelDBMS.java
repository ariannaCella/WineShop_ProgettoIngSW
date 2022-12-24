package com.example.democlientserver;



import Actors.*;

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
            String strSelect= "select * from "+table + " where u.Username='"+ username+ "' and u.Password='"+password+"'";
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
            String strSelect= "select * from "+table + " where u.Username='"+ username+ "' and u.Password='"+password+"'";
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
            Supplier c=new Supplier(name,surname,fc,email,phone,address,admin,user,psw);
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
            String strSelect= "select * from "+table + " where u.Username='"+ username+ "' and u.Password='"+password+"'";
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
            Shipper c=new Shipper(name,surname,fc,email,phone,address,admin,user,psw);
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
            String strSelect= "select * from "+table + " where u.Username='"+ username+ "' and u.Password='"+password+"'";
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
                int date= rset.getInt("Date");
                boolean sign=rset.getBoolean("Signature");
                boolean acc=rset.getBoolean("Accepted");
                Sale s= new Sale(saleId,fc,addr,wid,nbott,price,date,sign,acc);
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

