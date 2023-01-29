package com.example.democlientserver;
import Actors.*;
import RequestResponse.*;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;

import static com.example.democlientserver.ModelDBMS.*;

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
            ArrayList<Purchase> PurchaseDate=new ArrayList<Purchase>();
            ArrayList<Client> clientTot=new ArrayList<Client>();
            ArrayList<Employee> employeeTot=new ArrayList<Employee>();
            ArrayList<Purchase> purchaseTot=new ArrayList<Purchase>();
            Wine wineOrder=new Wine();
            ArrayList<Wine> listWinesEmployee=new ArrayList<Wine>();
            ArrayList <Purchase> proposalPurchase=new ArrayList();
            double priceOrder=0;
            Sale order=new Sale();
            int nBottleShop=0;
            int wineIdModify=0;
            int monthReport=0,yearReport=0;

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
                                String mes = "OK";
                                Object m = (Object) mes;
                                os.writeObject(m);
                                os.flush();
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
                                        String mess;
                                        if(connectedEmployee.getAdmin()==0){
                                             mess="Employee";
                                        }
                                        else{
                                             mess="Administrator";
                                        }
                                        os.writeObject(mess);
                                        os.flush();
                                        break;
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

                    case "isAdministrator":
                        /*if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }*/
                        if(connectedEmployee.getAdmin()==1){
                            os.writeObject(1);
                            os.flush();
                        }
                        else{os.writeObject(0);
                        os.flush();}
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

                    case "newEmployee":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        Employee newEmployee= (Employee) is.readObject();
                        ModelDBMS.newEmployee(newEmployee);
                        String mess = "Add new employee";
                        Object me = (Object) mess;
                        os.writeObject(me);
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

                    case "getListWineTot":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        ArrayList<Wine> listWine=ModelDBMS.listWineDBMS();
                        os.writeObject(listWine);
                        os.flush();
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
                        double sconto=0;
                        if(type.equals("Bottiglia")){
                            priceOrder=listWines.get(nButton).getPrice()*n;
                        }
                        else if (type.equals("Cassa da 6")){
                            //sconto 5%, se nBottigle>1 del 7%
                            if(n>1){sconto=0.93;}
                            else{sconto=0.95;}
                            n=n*6;
                            priceOrder=listWines.get(nButton).getPrice()*sconto *n;
                        }
                        else if (type.equals("Cassa da 12")){
                            //sconto 10%, se nBottiglie>1 del 13%
                            if(n>1){sconto=0.87;}
                            else{sconto=0.90;}
                            n=n*12;
                            priceOrder=listWines.get(nButton).getPrice()*sconto*n;
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
                        double scontoPromo=0;
                        if(typepromo.equals("Bottiglia")){
                            priceOrder=winesInPromo.get(nButtonPromo).getPrice()* nu;
                        }
                        else if (typepromo.equals("Cassa da 6")){
                            if(nu>1){scontoPromo=0.93;}
                            else{scontoPromo=0.95;}
                            nu=nu*6;
                            priceOrder=winesInPromo.get(nButtonPromo).getPrice()*scontoPromo *nu;
                        }
                        else if (typepromo.equals("Cassa da 12")){
                            if(nu>1){scontoPromo=0.87;}
                            else{scontoPromo=0.90;}
                            nu=nu*12;
                            priceOrder=winesInPromo.get(nButtonPromo).getPrice()*scontoPromo * nu;
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
                        salesTot= listSaleDBMS();
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
                        salesTot= listSaleDBMS();
                        os.writeObject(salesTot);
                        os.flush();
                        break;

                    case "getListSalesShipper":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        salesTot= listSaleShipperDBMS();
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

                    case "getListEmployee":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        ArrayList<Employee> employeeT=ModelDBMS.listEmployeeDBMS();
                        os.writeObject(employeeT);
                        os.flush();
                        break;


                    case "getListPurchase":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        purchaseTot= listPurchaseDBMS();
                        os.writeObject(purchaseTot);
                        os.flush();
                        break;

                    case "getListPurchaseSupplier":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        String fc= connectedSupplier.getFiscalCode();
                        purchaseTot= listPurchaseSupplierDBMS(fc);
                        os.writeObject(purchaseTot);
                        os.flush();
                        break;

                    case "getListPurchaseClient":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        String cfclient=connectedClient.getFiscalCode();
                        System.out.println(cfclient+"\n");
                        ArrayList<Purchase> purchaseT = listPurchaseClientDBMS(cfclient);
                        os.writeObject(purchaseT);
                        os.flush();
                        break;

                    case "ReqModifyWineId":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        wineIdModify= Integer.parseInt((String) is.readObject());
                        break;

                    case "ModifyWineById":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        RequestModifyWine reqModifyWine= (RequestModifyWine) is.readObject();
                        ModelDBMS.modifyWine(wineIdModify,reqModifyWine);
                        break;

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
                        String message = "Change Password";
                        Object mex = (Object) message;
                        os.writeObject(mex);
                        os.flush();
                        break;

                    case "viewOrder":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        Client infoClient=connectedClient;
                        Wine infoWine=wineOrder;
                        Date data=new Date(System.currentTimeMillis());
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

                    case "removeEmployee":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        String rsce;
                        rsce = (String) is.readObject();
                        ModelDBMS.RemoveEmployee(rsce);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "Removed";
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

                        PurchaseDate=ModelDBMS.PurchaseDateDBMS(d1,d2);
                        os.writeObject(PurchaseDate);
                        os.flush();
                        break;


                    case "SignIdSale":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        int ids = (Integer) is.readObject();
                        int result= ModelDBMS.signSales(ids);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        if (result==1){
                            rs = "Updated";
                            o1 = (Object) rs;
                            os.writeObject(o1);
                            os.flush();
                            break;
                        }

                    case "AcceptIdSale":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        int idas = (Integer) is.readObject();
                        int results= ModelDBMS.acceptSales(idas);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        if (results==1){
                            rs = "Updated";
                            o1 = (Object) rs;
                            os.writeObject(o1);
                            os.flush();
                        }
                        break;

                    case "SignIdPurchase":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        int idp = (Integer) is.readObject();
                        int resultp= ModelDBMS.signPurchase(idp);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        if (resultp==1){
                            rs = "Updated";
                            o1 = (Object) rs;
                            os.writeObject(o1);
                            os.flush();
                        }
                        break;

                    case "AcceptIdPurchase":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        rs = "OK";
                        o1 = (Object) rs;
                        os.writeObject(o1);
                        os.flush();
                        int idps = (Integer) is.readObject();
                        int resultps= ModelDBMS.acceptPurchase(idps);
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        if (resultps==1){
                            rs = "Updated";
                            o1 = (Object) rs;
                            os.writeObject(o1);
                            os.flush();
                        }
                        break;

                    case "Create Proposal Purchase":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        RequestProposalPurchase reqProp= (RequestProposalPurchase) is.readObject();
                        String address=reqProp.getAddress();
                        int idWine=reqProp.getIdWine();
                        int num=reqProp.getNumberBottles();
                        int cas=reqProp.getCasse();
                        Wine wProp=searchPriceCFSupWineDBMS(idWine);
                        double priceProposal=0,scontoProm=0;
                        if(cas==0){priceProposal=wProp.getPrice()*num;}
                        else if(cas==6){
                            if(num>1){scontoProm=0.93;}
                            else{scontoProm=0.95;}
                            num=num*6;
                            priceProposal=wProp.getPrice()*num*scontoProm;
                        }
                        else if(cas==12){
                            if(num>1){scontoProm=0.87;}
                            else{scontoProm=0.90;}
                            num=num*12;
                            priceProposal=wProp.getPrice()*num*scontoProm;
                        }

                        Date date = new Date(System.currentTimeMillis());
                        Purchase p=new Purchase(3+proposalPurchase.size(), wProp.getFcSupplier(), connectedClient.getFiscalCode(),address,idWine,num, priceProposal, false,false, date);
                        proposalPurchase.add(p);
                        ModelDBMS.newProposalPurchase(p);
                        break;

                    case "Create Proposal Purchase Employee":
                        if (os == null) {
                            os = new ObjectOutputStream(this.socket.getOutputStream());
                        }
                        RequestProposalPurchase reqPropEmployee= (RequestProposalPurchase) is.readObject();
                        int idWineE=reqPropEmployee.getIdWine();
                        int numE=reqPropEmployee.getNumberBottles();
                        Wine wPropE=searchPriceCFSupWineDBMS(idWineE);
                        Date dateE = new Date(System.currentTimeMillis());
                        Purchase pE=new Purchase(3+proposalPurchase.size(), wPropE.getFcSupplier(), connectedEmployee.getFiscalCode(),connectedEmployee.getAddress(),idWineE,numE, wPropE.getPrice()*numE, false,false, dateE);
                        proposalPurchase.add(pE);
                        ModelDBMS.newProposalPurchase(pE);
                        break;

                    case "SaveReport":
                        RequestMonthYearReport reqMonthYear= (RequestMonthYearReport) is.readObject();
                        monthReport=reqMonthYear.getMonth();
                        yearReport=reqMonthYear.getYear();
                        break;

                    case "getReport":
                        //(introiti, spese, numero bottiglie vendute e disponibili alla vendita, numero di vendite per i diversi vini,
                        //valutazione dei dipendenti
                        double income=0,expenses=0;
                        int nBottleSold=0,nBottleAvailable=0;
                        ArrayList <WineSold> winesSold= new ArrayList<>();
                        winesSold=ModelDBMS.getWinesSold(yearReport,monthReport);
                        income= ModelDBMS.getIncome(yearReport,monthReport);
                        expenses=ModelDBMS.getExpenses(yearReport,monthReport);
                        nBottleSold=ModelDBMS.getBottleSold(yearReport,monthReport);
                        nBottleAvailable=ModelDBMS.getBottleAvailable();
                        ResponseReport report =new ResponseReport(income,expenses,nBottleAvailable,nBottleSold, winesSold);
                        os.writeObject(report);
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