package loginApp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import student.StudentController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private LoginModel loginModel = new LoginModel();
    @FXML
    private Label dbStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loginModel.isDatabaseConnected()) {
            dbStatus.setText("Connected to database");
        } else {
            dbStatus.setText("Not connected to database");
        }
        combobox.setItems(FXCollections.observableArrayList(Option.values()));
    }

    @FXML
    public void login(ActionEvent event) {
        try {
            if (loginModel.isLogin(username.getText(), password.getText(), combobox.getValue().toString())) {
               Stage stage = (Stage) loginButton.getScene().getWindow();
               stage.close();
               if (combobox.getValue().toString().equals("Admin")) {
                   adminLogin();
               } else {
                   studentLogin();
               }
            } else {
                loginStatus.setText("Wrong credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void studentLogin() {
        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        StudentController studentController = (StudentController) loader.getController();
        try {
            Pane root = (Pane) loader.load(getClass().getResource("/student/student.fxml").openStream());
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student Dashboard");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adminLogin() {
        Stage adminStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AdminController adminController = (AdminController) loader.getController();
        try {
            Pane root = (Pane) loader.load(getClass().getResource("/admin/admin.fxml").openStream());
            Scene scene = new Scene(root);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}