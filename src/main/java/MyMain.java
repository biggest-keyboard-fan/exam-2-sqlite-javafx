import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyMain extends Application {
    public static Parent root;
    private static Connection connection;
    private static Statement stmt;
    public final static String DB_NAME_PATH ="my.db";

    @Override
    public void start(Stage primaryStage) throws Exception {
        try(FileInputStream fi = new FileInputStream("sql-label.fxml")){
            root = (Pane) new FXMLLoader().load(fi);
        }
        primaryStage.setTitle("sql-label-app");
        try {
            primaryStage.setScene(new Scene(root, 640, 480));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Label label = (Label) root.getScene().lookup("#label");
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+DB_NAME_PATH);
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        label.setText(stmt.executeQuery("SELECT name FROM main").getString(1));
    }

    public static void main(String[] args) { launch(args); }
}
