package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

// import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
// import static javafx.application.Application.launch;

/**
 * Created by asus-pc on 16.04.2017.
 */
public class Main extends Application {
    public static ResultSet resSet;
    public static Statement stt;


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String view = "";
        String url = "jdbc:sqlserver://127.0.0.1:1434;databaseName=PayJunction1;integratedSecurity=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url);
            stt = con.createStatement();
            ResultSet res = stt.executeQuery("SELECT * FROM CustomerDetails");
            while (res.next()) {
                System.out.println(res.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }
        public void start(Stage primaryStage) throws Exception{
        String table = "CustomerDetails";

        VBox boxvb = new VBox();
        HBox tophbox = new HBox();


        Label lbl = new Label("");
            Label lbl1 = new Label("Midterm Project");
            double MAX_FONT_SIZE = 30.0;
            lbl1.setFont(new Font(MAX_FONT_SIZE));


//            BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("123.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//            Background background = new Background(backgroundImage);
//            delete.setBackground(background);

            GridPane top = new GridPane();
        top.setMinSize(20, 20);
        top.setVgap(5);
        top.setHgap(5);
        top.add(lbl,5, 2);
            top.add(lbl1,70,1);
        Button prod = new Button("Products");
        Button ordproc = new Button("OrderProcessing");
        Button payments = new Button("Payments");
        Button orders = new Button("Orders");
        Button delivery = new Button("DeliveryInformation");
        Button customer = new Button("CustomerDetails");

        prod.setMinSize(150.0, Control.USE_PREF_SIZE);
        ordproc.setMinSize(150.0, Control.USE_PREF_SIZE);
        payments.setMinSize(150.0, Control.USE_PREF_SIZE);
        orders.setMinSize(150.0, Control.USE_PREF_SIZE);
        delivery.setMinSize(150.0, Control.USE_PREF_SIZE);
        customer.setMinSize(150.0, Control.USE_PREF_SIZE);


        Label line = new Label("link");
        Label line1 = new Label("link");
        Label line2 = new Label("link");
        Label line3 = new Label("link");

        GridPane griddd = new GridPane();
        griddd.setMinSize(20, 20);
        griddd.setVgap(5);
        griddd.setHgap(5);
        griddd.add(prod, 5, 0);
        griddd.add(ordproc, 5, 5);
        griddd.add(payments, 5, 10);
        griddd.add(orders, 5, 15);
        griddd.add(delivery, 5, 20);
        griddd.add(customer, 5, 25);
        payments.setOnAction(event -> {
            VBox boxvbpay = new VBox();
            HBox tophboxpay = new HBox();

            Label Pay_Label = new Label("Payment");
            Pay_Label.setFont(new Font(MAX_FONT_SIZE));

            ComboBox<Integer> myComboBox = new ComboBox<Integer>();
            myComboBox.getItems().addAll(1,2,3,4,5,6);

            GridPane toppay = new GridPane();
            toppay.setMinSize(20, 20);
            toppay.setVgap(5);
            toppay.setHgap(5);
            toppay.add(Pay_Label,24,10);
            toppay.add(myComboBox,30,10);

            Label Order_ID = new Label("ID of Order:");
            Label orderid = new Label("1");
            Label Payment_he = new Label("Payment amount:");
            Label payment_a = new Label("");
            Label dateof = new Label("Date: ");
            Label datee = new Label("");
            Label method = new Label("Method:");
            Label metpay = new Label("");
            Label quant = new Label("Quantity:");
            Label quantlast = new Label("");
            Label check = new Label("To pay: 0$");

            myComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue ov, Integer t, Integer t1) {
                    try {
                        ArrayList<String> smlist = new ArrayList<String>();
                        ObservableList<Person> quantity = ordproc();
                        Iterator list3 = quantity.iterator();
                        while(list3.hasNext()){
                            Person city = (Person) list3.next();
                            smlist.add(city.getC());
                        }
                        ObservableList<Person1> lastpay = Payment1(t1);
                        Iterator list2 = lastpay.iterator();
                        while(list2.hasNext()){
                            Person1 city = (Person1) list2.next();
                            System.out.println(city);
                            if(city!=null) {
                                orderid.setText(city.getLastName());
                                payment_a.setText(city.getA());
                                datee.setText(city.getB());
                                metpay.setText(city.getC());
                                quantlast.setText(smlist.get(Integer.parseInt(orderid.getText())-1));
                                check.setText("All you have to pay is: " + (Integer.parseInt(quantlast.getText()) * Integer.parseInt(payment_a.getText())) + "$");
                            }else {
                                orderid.setText("");
                                payment_a.setText("");
                                datee.setText("");
                                metpay.setText("");
                                quantlast.setText("");
                                check.setText("All you have to pay is: 0$");
                                System.out.println("returned");
                            }

                        }

//                        System.out.println(smlist.get(Integer.parseInt(orderid.getText())-1) +"This one");



                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            double fontfor = 15.0;
            orderid.setFont(new Font(fontfor));
            Payment_he.setFont(new Font(fontfor));
            payment_a.setFont(new Font(fontfor));
            Order_ID.setFont(new Font(fontfor));
            dateof.setFont(new Font(fontfor));
            datee.setFont(new Font(fontfor));
            method.setFont(new Font(fontfor));
            metpay.setFont(new Font(fontfor));
            quant.setFont(new Font(fontfor));
            quantlast.setFont(new Font(fontfor));
            check.setFont(new Font(fontfor));

            GridPane gridddpay = new GridPane();
            gridddpay.setMinSize(20, 20);
            gridddpay.setVgap(5);
            gridddpay.setHgap(5);
            gridddpay.add(Order_ID,20,10);
            gridddpay.add(orderid,25,10);
            gridddpay.add(dateof,20,15);
            gridddpay.add(datee,25,15);
            gridddpay.add(Payment_he,20,20);
            gridddpay.add(payment_a,25,20);
            gridddpay.add(method,20,25);
            gridddpay.add(metpay,25,25);
            gridddpay.add(quant,20,30);
            gridddpay.add(quantlast,25,30);

            GridPane lastgrid = new GridPane();
            lastgrid.setMinSize(20, 20);
            lastgrid.setVgap(5);
            lastgrid.setHgap(5);
            lastgrid.add(check,22,10);

            HBox lastthird = new HBox();
            lastthird.getChildren().add(lastgrid);

            HBox listhpay = new HBox();
            listhpay.getChildren().addAll(gridddpay);

            tophboxpay.getChildren().add(toppay);
            boxvbpay.getChildren().addAll(tophboxpay, listhpay,lastthird);

            Stage stage = new Stage();
            stage.setTitle("Payment");
            stage.setScene(new Scene(boxvbpay, 400, 500));
            stage.show();
        });



        HBox listh = new HBox();
            final TableView<Products1> list = new TableView<>();
            TableColumn firstNameCol1 = new TableColumn("Product_ID");
            firstNameCol1.setMinWidth(50);
            firstNameCol1.setCellValueFactory(
                    new PropertyValueFactory<>("firstName1"));
            TableColumn lastNameCol1 = new TableColumn("Discount");
            lastNameCol1.setMinWidth(50);
            lastNameCol1.setCellValueFactory(
                    new PropertyValueFactory<>("lastName1"));
            TableColumn a1 = new TableColumn("Product_name");
            a1.setMinWidth(50);
            a1.setCellValueFactory(
                    new PropertyValueFactory<>("a1"));
            TableColumn b1 = new TableColumn("PricePerUnit");
            b1.setMinWidth(50);
            b1.setCellValueFactory(
                    new PropertyValueFactory<>("b1"));
            TableColumn d1 = new TableColumn("Image");
            d1.setMinWidth(50);
            d1.setCellValueFactory(
                    new PropertyValueFactory<>("d1"));


            list.setPrefWidth(700);
            try {
                list.setItems(shh());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            list.getColumns().addAll( d1,firstNameCol1, lastNameCol1,a1,b1);
            listh.getChildren().addAll(list,griddd);

        orders.setOnAction(event1 -> {
            listh.getChildren().clear();
            griddd.getChildren().remove(line);
            griddd.getChildren().remove(line1);
            griddd.getChildren().remove(line2);
            griddd.getChildren().remove(line3);

            final TableView<Products> tablee = new TableView<>();
            TableColumn firstNameCol = new TableColumn("Order_ID");
            firstNameCol.setMinWidth(50);
            firstNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("firstName"));
            TableColumn lastNameCol = new TableColumn("Customer_ID");
            lastNameCol.setMinWidth(50);
            lastNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("lastName"));
            TableColumn a = new TableColumn("DeliveryMethodID");
            a.setMinWidth(50);
            a.setCellValueFactory(
                    new PropertyValueFactory<>("a"));
            TableColumn b = new TableColumn("Order_informationID");
            b.setMinWidth(50);
            b.setCellValueFactory(
                    new PropertyValueFactory<>("b"));

            tablee.setPrefWidth(700);
            try {
                tablee.setItems(order());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            griddd.add(line,3,5);
            griddd.add(line1,3,10);
            griddd.add(line2,3,25);
            griddd.add(line3,3,20);
            tablee.getColumns().addAll(firstNameCol, lastNameCol,a,b);
            listh.getChildren().addAll(tablee,griddd);

        });
        prod.setOnAction(event1 -> {
            griddd.getChildren().remove(line);
            griddd.getChildren().remove(line1);
            griddd.getChildren().remove(line2);
            griddd.getChildren().remove(line3);
            listh.getChildren().clear();

            final TableView<Products1> tablee = new TableView<>();
            TableColumn firstNameCol = new TableColumn("Product_ID");
            firstNameCol.setMinWidth(50);
            firstNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("firstName"));
            TableColumn lastNameCol = new TableColumn("Discount");
            lastNameCol.setMinWidth(50);
            lastNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("lastName"));
            TableColumn a = new TableColumn("Product_name");
            a.setMinWidth(50);
            a.setCellValueFactory(
                    new PropertyValueFactory<>("a"));
            TableColumn b = new TableColumn("PricePerUnit");
            b.setMinWidth(50);
            b.setCellValueFactory(
                    new PropertyValueFactory<>("b"));
            TableColumn c = new TableColumn("Image");
            c.setMinWidth(50);
            c.setCellValueFactory(
                    new PropertyValueFactory<>("photoC"));

            tablee.setPrefWidth(700);
            try {
                tablee.setItems(shh());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            griddd.add(line,3,5);

            tablee.getColumns().addAll(c, firstNameCol, lastNameCol,a,b);
            listh.getChildren().addAll(tablee,griddd);

        });
            delivery.setOnAction(event1 -> {
                listh.getChildren().clear();
                griddd.getChildren().remove(line);
                griddd.getChildren().remove(line1);
                griddd.getChildren().remove(line2);
                griddd.getChildren().remove(line3);

                final TableView<Person> tablee = new TableView<>();
                TableColumn firstNameCol = new TableColumn("DeliveryMethodID");
                firstNameCol.setMinWidth(50);
                firstNameCol.setCellValueFactory(
                        new PropertyValueFactory<>("firstName"));
                TableColumn lastNameCol = new TableColumn("Delivery_name");
                lastNameCol.setMinWidth(50);
                lastNameCol.setCellValueFactory(
                        new PropertyValueFactory<>("lastName"));
                TableColumn a = new TableColumn("Delivery_country");
                a.setMinWidth(50);
                a.setCellValueFactory(
                        new PropertyValueFactory<>("a"));
                TableColumn b = new TableColumn("Delivery_date");
                b.setMinWidth(50);
                b.setCellValueFactory(
                        new PropertyValueFactory<>("b"));
                TableColumn c = new TableColumn("Delivery_method");
                c.setMinWidth(50);
                c.setCellValueFactory(
                        new PropertyValueFactory<>("c"));
                TableColumn d = new TableColumn("Delivery_address");
                d.setMinWidth(50);
                d.setCellValueFactory(
                        new PropertyValueFactory<>("d"));
                TableColumn e = new TableColumn("Phone");
                e.setMinWidth(50);
                e.setCellValueFactory(
                        new PropertyValueFactory<>("e"));
                TableColumn f = new TableColumn("Shipping_cost");
                f.setMinWidth(50);
                f.setCellValueFactory(
                        new PropertyValueFactory<>("f"));
                TableColumn g = new TableColumn("Tax");
                g.setMinWidth(50);
                g.setCellValueFactory(
                        new PropertyValueFactory<>("g"));

                tablee.setPrefWidth(700);
                try {
                    tablee.setItems(deilvery());
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                griddd.add(line,3,15);
                tablee.getColumns().addAll(firstNameCol, lastNameCol,a,b,c,d,e,f,g);
                listh.getChildren().addAll(tablee,griddd);
            });
        customer.setOnAction(event1 -> {
            listh.getChildren().clear();
            griddd.getChildren().remove(line);
            griddd.getChildren().remove(line1);
            griddd.getChildren().remove(line2);
            griddd.getChildren().remove(line3);

                final TableView<Person1> tablee = new TableView<>();
                TableColumn firstNameCol = new TableColumn("Customer_ID");
                firstNameCol.setMinWidth(50);
                firstNameCol.setCellValueFactory(
                        new PropertyValueFactory<>("firstName"));
                TableColumn lastNameCol = new TableColumn("Name");
                lastNameCol.setMinWidth(50);
                lastNameCol.setCellValueFactory(
                        new PropertyValueFactory<>("lastName"));
                TableColumn a = new TableColumn("Company");
                a.setMinWidth(50);
                a.setCellValueFactory(
                        new PropertyValueFactory<>("a"));
                TableColumn b = new TableColumn("Address");
                b.setMinWidth(50);
                b.setCellValueFactory(
                        new PropertyValueFactory<>("b"));
                TableColumn c = new TableColumn("Country");
                c.setMinWidth(50);
                c.setCellValueFactory(
                        new PropertyValueFactory<>("c"));
                TableColumn d = new TableColumn("Phone");
                d.setMinWidth(50);
                d.setCellValueFactory(
                        new PropertyValueFactory<>("d"));
                TableColumn e = new TableColumn("Credit Card");
                e.setMinWidth(50);
                e.setCellValueFactory(
                        new PropertyValueFactory<>("e"));

                tablee.setPrefWidth(700);
                try {
                    tablee.setItems(cust());
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                griddd.add(line,3,15);
                tablee.getColumns().addAll(firstNameCol, lastNameCol,a,b,c,d,e);
                listh.getChildren().addAll(tablee,griddd);

        });
            ordproc.setOnAction(event1 -> {
                listh.getChildren().clear();
                griddd.getChildren().remove(line);
                griddd.getChildren().remove(line1);
                griddd.getChildren().remove(line2);
                griddd.getChildren().remove(line3);
//                try {
//                    final ListView<OrderProcessing> list1 = new ListView<>(ordproc());
//
//                    listh.getChildren().addAll(list1,griddd);
//
//
//
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                final TableView<Person> tablee = new TableView<>();
                TableColumn firstNameCol = new TableColumn("OrderInfID");
                firstNameCol.setMinWidth(50);
                firstNameCol.setCellValueFactory(
                        new PropertyValueFactory<>("firstName"));
                TableColumn lastNameCol = new TableColumn("Order ID");
                lastNameCol.setMinWidth(50);
                lastNameCol.setCellValueFactory(
                        new PropertyValueFactory<>("lastName"));
                TableColumn a = new TableColumn("Customer ID");
                a.setMinWidth(50);
                a.setCellValueFactory(
                        new PropertyValueFactory<>("a"));
                TableColumn b = new TableColumn("Product ID");
                b.setMinWidth(50);
                b.setCellValueFactory(
                        new PropertyValueFactory<>("b"));
                TableColumn c = new TableColumn("Quantity");
                c.setMinWidth(50);
                c.setCellValueFactory(
                        new PropertyValueFactory<>("c"));
                TableColumn d = new TableColumn("Order descr");
                d.setMinWidth(50);
                d.setCellValueFactory(
                        new PropertyValueFactory<>("d"));
                TableColumn e = new TableColumn("Order date");
                e.setMinWidth(50);
                e.setCellValueFactory(
                        new PropertyValueFactory<>("e"));
                TableColumn f = new TableColumn("Product Name");
                f.setMinWidth(50);
                f.setCellValueFactory(
                        new PropertyValueFactory<>("f"));
                TableColumn g = new TableColumn("Discount");
                g.setMinWidth(50);
                g.setCellValueFactory(
                        new PropertyValueFactory<>("g"));

                tablee.setPrefWidth(700);
                try {
                    tablee.setItems(ordproc());
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                griddd.add(line,3,0);
                griddd.add(line1,3,15);
                tablee.getColumns().addAll(firstNameCol, lastNameCol,a,b,c,d,e,f,g);
                listh.getChildren().addAll(tablee,griddd);

            });

        tophbox.getChildren().add(top);
        boxvb.getChildren().addAll(tophbox, listh);

        primaryStage.setTitle("Pay Junction");
        primaryStage.setScene(new Scene(boxvb, 910, 550));
        primaryStage.show();

    }
    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty a;
        private final SimpleStringProperty b;
        private final SimpleStringProperty c;
        private final SimpleStringProperty d;
        private final SimpleStringProperty e;
        private final SimpleStringProperty f;
        private final SimpleStringProperty g;

        private Person(String fName, String lName, String a, String b, String c, String d, String e, String f,String g) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.a = new SimpleStringProperty(a);
            this.b = new SimpleStringProperty(b);
            this.c = new SimpleStringProperty(c);
            this.d = new SimpleStringProperty(d);
            this.e = new SimpleStringProperty(e);
            this.f = new SimpleStringProperty(f);
            this.g = new SimpleStringProperty(g);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public String getA() {
            return a.get();
        }

        public String getB() {
            return b.get();
        }

        public String getC() {
            return c.get();
        }

        public String getD() {
            return d.get();
        }

        public String getE() {
            return e.get();
        }

        public String getF() {
            return f.get();
        }

        public String getG() {
            return g.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }
    }
    public static class Person1 {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty a;
        private final SimpleStringProperty b;
        private final SimpleStringProperty c;
        private final SimpleStringProperty d;
        private final SimpleStringProperty e;

        private Person1(String fName, String lName, String a, String b, String c, String d, String e) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.a = new SimpleStringProperty(a);
            this.b = new SimpleStringProperty(b);
            this.c = new SimpleStringProperty(c);
            this.d = new SimpleStringProperty(d);
            this.e = new SimpleStringProperty(e);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public String getA() {
            return a.get();
        }

        public String getB() {
            return b.get();
        }

        public String getC() {
            return c.get();
        }

        public String getD() {
            return d.get();
        }

        public String getE() {
            return e.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }
    }
    public static class Products1 {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty a;
        private final SimpleStringProperty b;
        private final SimpleObjectProperty photoC;

        private Products1(String fName, String lName, String a, String b, ImageView imgview) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.a = new SimpleStringProperty(a);
            this.b = new SimpleStringProperty(b);
            ImageView correctImg = imgview;
            correctImg.setFitWidth(128);
            correctImg.setFitHeight(72);
            this.photoC = new SimpleObjectProperty(correctImg);
        }

        public String getFirstName() {
            return firstName.get();
        }
        public SimpleObjectProperty photoCProperty() {return photoC;};
        public void setPhotoC(Object photoC){this.photoC.set(photoC);}

        public Object getPhotoC() {
            return photoC.get();
        }

        public String getA() {
            return a.get();
        }

        public String getB() {
            return b.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }
    }
    public static class Products {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty a;
        private final SimpleStringProperty b;

        private Products(String fName, String lName, String a, String b) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.a = new SimpleStringProperty(a);
            this.b = new SimpleStringProperty(b);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public String getA() {
            return a.get();
        }

        public String getB() {
            return b.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }
    }
    public static class User{
        public String login,password,name,surname;
        public User(String name, String surname, String login, String password){
            this.name = name;
            this.surname = surname;
            this.login = login;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        public String getSurname() {
            return surname;
        }

        public String getLogin() {
            return login;
        }
    }
    public static class Customer{
        public String login,password,name,surname,a,b,c;
        public Customer(String name, String surname, String login, String password, String a, String b, String c){
            this.name = name;
            this.surname = surname;
            this.login = login;
            this.password = password;
            this.a = a;
            this.b = b;
            this.c = c;
        }
        public String toString(){
            return name + " " + surname + " " + login + " " + password + " " + a + " " + b + " " + c;
        }

        public String getName() {
            return name;
        }
    }
    public static class OrderProcessing{
        public String login,password,name,surname,a,b,c,d,e;
        public OrderProcessing(String name, String surname, String login, String password, String a, String b, String c, String d, String e){
            this.name = name;
            this.surname = surname;
            this.login = login;
            this.password = password;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
        }
        public String toString(){
            return name + " " + surname + " " + login + " " + password + " " + a + " " + b + " " + c + " "+ d + " " + e;
        }

        public String getName() {
            return name;
        }
    }
    public static ObservableList<User> sh() throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<User> ussers = new ArrayList<>();

        resSet = stt.executeQuery("SELECT * FROM CustomerDetails");

        while(resSet.next()){
            User user = new User(resSet.getString("Customer_ID"), resSet.getString("name"),resSet.getString("company"), resSet.getString("address"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            User city = (User) list2.next();
            list1.add(city);
            System.out.println(list1);
        }
        return list1;
    }
    public static ObservableList<Products1> shh() throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Products1> ussers = new ArrayList<>();

        resSet = stt.executeQuery("SELECT * FROM Products");

        while(resSet.next()){
            Image img = new Image(resSet.getBinaryStream("Image"));
            ImageView smth = new ImageView(img);
            Products1 user = new Products1(resSet.getString("Product_ID"), resSet.getString("Discount"),resSet.getString("product_name"), resSet.getString("PricePerUnit"),smth);
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Products1 city = (Products1) list2.next();
            list1.add(city);
        }
        return list1;
    }
    public static ObservableList<Person1> Payment1(Integer number) throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Person1> ussers = new ArrayList<>();

        resSet = stt.executeQuery("Select * From Payments Where Payment_ID = " + number);

        while(resSet.next()){
            Person1 user = new Person1(resSet.getString("Payment_ID"), resSet.getString("Order_ID"), resSet.getString("Payment_amount"), resSet.getString("payment_date"), resSet.getString("method_of_payment"), resSet.getString("method_id"), resSet.getString("Smth"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Person1 city = (Person1) list2.next();
            list1.add(city);
        }
        return list1;
    }
    public static ObservableList<Products> order() throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Products> ussers = new ArrayList<>();

        resSet = stt.executeQuery("SELECT * FROM Orders");

        while(resSet.next()){
            Products user = new Products(resSet.getString("Order_ID"), resSet.getString("Customer_ID"),resSet.getString("DeliveryMethodID"), resSet.getString("Order_informationID"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Products city = (Products) list2.next();
            list1.add(city);
        }
        return list1;
    }
    public static ObservableList<Person1> cust() throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Person1> ussers = new ArrayList<>();

        resSet = stt.executeQuery("SELECT * FROM CustomerDetails");

        while(resSet.next()){
            Person1 user = new Person1(resSet.getString("Customer_ID"), resSet.getString("name"), resSet.getString("company"), resSet.getString("address"), resSet.getString("country"), resSet.getString("phone"), resSet.getString("CreditCard"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Person1 city = (Person1) list2.next();
            list1.add(city);
        }
        return list1;
    }
    public static ObservableList<Person1> cust1(Integer num) throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Person1> ussers = new ArrayList<>();

        resSet = stt.executeQuery("SELECT * FROM CustomerDetails WHERE CreditCard = " + num);

        while(resSet.next()){
            Person1 user = new Person1(resSet.getString("Customer_ID"), resSet.getString("name"), resSet.getString("company"), resSet.getString("address"), resSet.getString("country"), resSet.getString("phone"), resSet.getString("CreditCard"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Person1 city = (Person1) list2.next();
            list1.add(city);
        }
        return list1;
    }
    public static ObservableList<Person1> search(String ser) throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Person1> ussers = new ArrayList<>();

        resSet = stt.executeQuery("SELECT * FROM CustomerDetails WHERE name LIKE '%" + ser + "%'");

        while(resSet.next()){
            Person1 user = new Person1(resSet.getString("Customer_ID"), resSet.getString("name"), resSet.getString("company"), resSet.getString("address"), resSet.getString("country"), resSet.getString("phone"), resSet.getString("CreditCard"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Person1 city = (Person1) list2.next();
            list1.add(city);
        }
        return list1;
    }
    public static ObservableList<Person> ordproc() throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Person> ussers = new ArrayList<>();

        resSet = stt.executeQuery("SELECT * FROM OrderProcessing");

        while(resSet.next()){
            Person user = new Person(resSet.getString("OrderInformationID"), resSet.getString("Order_ID"), resSet.getString("Customer_ID"), resSet.getString("Product_ID"), resSet.getString("quantity"), resSet.getString("Order_description"), resSet.getString("Order_date"),resSet.getString("Product_name"),resSet.getString("Discount"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Person city = (Person) list2.next();
            list1.add(city);
        }
        return list1;
    }
    public static ObservableList<Person> deilvery() throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Person> ussers = new ArrayList<>();

        resSet = stt.executeQuery("SELECT * FROM DeliveryInformation");

        while(resSet.next()){
            Person user = new Person(resSet.getString("DeliveryMethodID"), resSet.getString("Delivery_name"), resSet.getString("Delivery_country"), resSet.getString("Delivery_date"), resSet.getString("Delivery_method"), resSet.getString("Delivery_address"), resSet.getString("phone"),resSet.getString("Shipping_cost"),resSet.getString("tax"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Person city = (Person) list2.next();
            list1.add(city);
        }
        return list1;
    }
}