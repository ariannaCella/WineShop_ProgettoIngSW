package com.example.democlientserver;
import Actors.*;
import RequestResponse.*;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import static com.example.democlientserver.ModelDBMS.listPurchaseDBMS;
import static com.example.democlientserver.ModelDBMS.listSaleDBMS;

/**
 *
 * The class {@code ServerThread} manages the interaction
 * with a client of the server.
 *
 **/

public class ServerThread implements Runnable
{
    private static final int MAX = 100;
    private static final long SLEEPTIME = 200;

    private Server server;
    private Socket socket;

    /**
     * Class constructor.
     *
     * @param s  the server.
     * @param c  the client socket.
     *
     **/
    public ServerThread(final Server s, final Socket c)
    {
        this.server = s;
        this.socket = c;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream is = null;
            ObjectOutputStream os = null;

            is = new ObjectInputStream(new BufferedInputStream(
                    this.socket.getInputStream()));
            //is = new ObjectInputStream(this.socket.getInputStream());
            String o;
            o = (String) is.readObject();

            System.out.format(" receives: %s ", o);

            if (os == null) {
                os = new ObjectOutputStream(new BufferedOutputStream(
                        this.socket.getOutputStream()));
                //os = new ObjectOutputStream(this.socket.getOutputStream());
            }
            if(o.equals("Vorrei connettermi")){
                String rs = "OK";
                Object o1 = (Object) rs;
                os.writeObject(o1);
                os.flush();
            }
            String cmd,rs;
            Object o1;
            ArrayList<Wine> listWines=new ArrayList<Wine>();
            ArrayList<Client> clientSurname=new ArrayList<Client>();
            Employee connectedEmployee=new Employee();
            Client connectedClient=new Client();
            Shipper connectedShipper=new Shipper();
            Supplier connectedSupplier=new Supplier();
            ArrayList<Wine> winesInPromo=new ArrayList<>();
            ArrayList<Sale> salesTot=new ArrayList<Sale>();
            ArrayList<Sale> SaleDate=new ArrayList<Sale>();
            ArrayList<Client> clientTot=new ArrayList<Client>();
            ArrayList<Purchase> purchaseTot=new ArrayList<Purchase>();
            Wine wineOrder=new Wine();
            ArrayList<Wine> listWinesEmployee=new ArrayList<Wine>();
            double priceOrder=0;
            Sale order=new Sale();
            int nBottleShop=0;
            while(true){
                cmd = (String) is.readObject();
                Thread.sleep(1000);
                switch (cmd){
                    case "Login":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestLogin req;
                        req = (RequestLogin) is.readObject();
                        String txtInput=req.getUsername();
                        String pswInput=req.getPassword();
                        String tab=req.getTable();
                        String pswUser=ModelDBMS.returnPassword(txtInput,tab);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        if((pswUser.equals("Utente inesistente"))){
                            String mes = "Nome utente errato, riprova o registrati";
                            Object m = (Object) mes;
                            os.writeObject(m);
                            os.flush();

                        }
                        if((pswUser.equals("errore"))){
                            String mes = "Servizio momentaneamente fuori servizio! Ci scusiamo per il disagio";
                            Object m = (Object) mes;
                            os.writeObject(m);
                            os.flush();
                        }
                        else{
                            if(pswUser.equals(pswInput)){
                                switch (tab){
                                    case "client":
                                        connectedClient=ModelDBMS.returnClientConnected(txtInput,pswInput,tab);
                                        break;
                                    case "supplier":
                                        connectedSupplier=ModelDBMS.returnSupplierConnected(txtInput,pswInput,tab);
                                        break;
                                    case "shipper":
                                        connectedShipper=ModelDBMS.returnShipperConnected(txtInput,pswInput,tab);
                                        break;
                                    case "employee":
                                        connectedEmployee=ModelDBMS.returnEmployeeConnected(txtInput,pswInput,tab);
                                        break;
                                }
                                String mes = "OK";
                                Object m = (Object) mes;
                                os.writeObject(m);
                                os.flush();
                                String mess=null;
                                if(tab.equals("Employee")){
                                    if(connectedEmployee.getAdmin()==0){
                                        mess="Employee";
                                    }
                                    else{
                                        mess="Administrator";
                                    }
                                    os.writeObject(mess);
                                    os.flush();
                                }

                            }
                            else{
                                String mes = "Password errata, riprova";
                                Object m = (Object) mes;
                                os.writeObject(m);
                                os.flush();
                            }
                        }
                        break;

                    case "newUser":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        Client newClient= (Client) is.readObject();
                        ModelDBMS.newUser(newClient);
                        String mes = "Add new client";
                        Object m = (Object) mes;
                        os.writeObject(m);
                        os.flush();
                        break;

                    case "searchWine":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestSearchWine r;
                        r = (RequestSearchWine) is.readObject();
                        String txt=r.getTxt();
                        String attribute=r.getAttribute();
                        listWines=ModelDBMS.searchWineDBMS(txt,attribute);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "View wines";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        break;

                    case "getListWines":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        listWines=ModelDBMS.listWineDBMS();
                        //os.writeObject(listWines);
                        //os.flush();
                        break;

                    case "viewPromoHome":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        if(winesInPromo.size()==0) winesInPromo=ModelDBMS.setPromoDefault();
                        os.writeObject(winesInPromo);
                        os.flush();
                        break;
                    /*case "setPromo":   sarà per impiegato, copia e incolla funzione precedente, da sistemare
                        public void setPromo(Wine w1,Wine w2,Wine w3){
                        winesInPromo.clear();
                        winesInPromo.add(w1);
                        winesInPromo.add(w2);
                        winesInPromo.add(w3);
                    }
                        break;*/

                    case "viewListWines":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        os.writeObject(listWines);
                        os.flush();
                        break;

                    case "searchWineEmployee":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestSearchWine re;
                        re = (RequestSearchWine) is.readObject();
                        String txt1=re.getTxt();
                        String attribute1=re.getAttribute();
                        listWinesEmployee=ModelDBMS.searchWineDBMS(txt1,attribute1);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "View wines";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        break;

                    case "getListWinesEmployee":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        os.writeObject(listWinesEmployee);
                        os.flush();
                        break;

                    case "shopWine":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestShop rq;
                        rq = (RequestShop) is.readObject();
                        int n=rq.getNumber();
                        Wine wine=rq.getWine();
                        String type=rq.getType();
                        int nButton=rq.getButton();
                        if(type.equals("Bottiglie")){
                            priceOrder=listWines.get(nButton).getPrice()*n;
                        }
                        else if (type.equals("Cassa da 6")){
                            n=n*6;
                            priceOrder=listWines.get(nButton).getPrice()*(0.98) *n;
                        }
                        else if (type.equals("Cassa da 12")){
                            n=n*12;
                            priceOrder=listWines.get(nButton).getPrice()*(0.95)*n;
                        }

                        if(listWines.get(nButton).getQuantity()>=n){
                            wineOrder=listWines.get(nButton);
                            nBottleShop=n;
                            rs = "payment";
                        }
                        else{
                            rs = "proposal purchase";
                        }
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        break;

                    case "shopWinePromo":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestShop request;
                        request = (RequestShop) is.readObject();
                        int nu=request.getNumber();
                        Wine winepromo=request.getWine();
                        String typepromo=request.getType();
                        int nButtonPromo=request.getButton();
                        if(typepromo.equals("Bottiglie")){
                            priceOrder=winesInPromo.get(nButtonPromo).getPrice()* nu;
                        }
                        else if (typepromo.equals("Cassa da 6")){
                            nu=nu*6;
                            priceOrder=winesInPromo.get(nButtonPromo).getPrice()*(0.98) *nu;
                        }
                        else if (typepromo.equals("Cassa da 12")){
                            nu=nu*12;
                            priceOrder=winesInPromo.get(nButtonPromo).getPrice()*(0.95) * nu;
                        }

                        if(winesInPromo.get(nButtonPromo).getQuantity()>=nu){
                            wineOrder=winesInPromo.get(nButtonPromo);
                            nBottleShop=nu;
                            rs = "payment";
                        }
                        else{
                            rs = "proposal purchase";
                        }
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        break;


                    case "generateOrder":
                        wineOrder.addSales(nBottleShop);
                        wineOrder.updateQuantity();
                        ModelDBMS.updateQuantity(nBottleShop,wineOrder);
                        ModelDBMS.addSales(nBottleShop,wineOrder);
                        System.out.println(connectedClient.getFiscalCode());
                        salesTot=ModelDBMS.listSaleDBMS();
                        order=new Sale(salesTot.size()+2, wineOrder.getWineId(), nBottleShop, false , false, connectedClient.getFiscalCode(), connectedClient.getAddress(), priceOrder);
                        ModelDBMS.newOrder(order);
                        break;

                    case "getWinesPromo":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        os.writeObject(winesInPromo);
                        os.flush();
                        break;

                    case "getListSales":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        salesTot=ModelDBMS.listSaleDBMS();
                        os.writeObject(salesTot);
                        os.flush();
                        break;

                    case "getListClient":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        clientTot=ModelDBMS.listClientDBMS();
                        os.writeObject(clientTot);
                        os.flush();
                        break;

                    case "getListPurchase":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        purchaseTot=ModelDBMS.listPurchaseDBMS();
                        os.writeObject(purchaseTot);
                        os.flush();
                        break;

                    case "modifyWine":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        String txtId=(String) is.readObject();
                        ModelDBMS.modifyWine(txtId);

                    case "searchWineId":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestSearchWineId r1;
                        r1 = (RequestSearchWineId) is.readObject();
                        int id = r1.getId();
                        Wine w=ModelDBMS.searchWineIdDBMS(id);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "View wines";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        break;

                    case "modifyPassw":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestChangePassword newreq= (RequestChangePassword) is.readObject();
                        ModelDBMS.ChangePassword(newreq);
                        String mess = "Change Password";
                        Object me = (Object) mess;
                        os.writeObject(me);
                        os.flush();
                        break;

                    case "viewOrder":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        Client infoClient=connectedClient;
                        Wine infoWine=wineOrder;
                        int data=0;
                        ResponseOrderRepilog res=new ResponseOrderRepilog(infoClient,infoWine,data,nBottleShop,priceOrder);
                        os.writeObject(res);
                        os.flush();
                        break;

                    case "searchClientSurname":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        String rscs;
                        rscs = (String) is.readObject();
                        clientSurname=ModelDBMS.searchClientSurname(rscs);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "View Clients";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        break;

                    case "getListSurnameClient":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        os.writeObject(clientSurname);
                        os.flush();
                        break;

                    case "SearchDate":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestDate reqd;
                        reqd = (RequestDate) is.readObject();
                        Date d1 =reqd.getBegin();
                        Date d2=reqd.getEnd();
                        SaleDate=ModelDBMS.SaleDateDBMS(d1,d2);
                        os.writeObject(salesTot);
                        os.flush();
                        break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.exit(0);
        }

    }}