package admin;

import dbUtil.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private Button addStudentButton;
    @FXML
    private Button loadDataButton;
    @FXML
    private TableView<StudentData> studentsTable;
    @FXML
    private TableColumn<StudentData, String> idColumn;
    @FXML
    private TableColumn<StudentData, String> firstNameColumn;
    @FXML
    private TableColumn<StudentData, String> lastNameColumn;
    @FXML
    private TableColumn<StudentData, String> emailColumn;
    @FXML
    private TableColumn<StudentData, String> dateOfBirthColumn;

    private ObservableList<StudentData> data;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudentData();
    }

    private void loadStudentData() {
        try {
            Connection connection = DatabaseConnector.getDatabaseConnection();
            data = FXCollections.observableArrayList();
            String sql = "SELECT * " +
                    "FROM students;";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                data.add(new StudentData(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("FIRST_NAME"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("LAST_NAME"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("EMAIL"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("DATE_OF_BIRTH"));

        studentsTable.setItems(null);
        studentsTable.setItems(data);
    }

    @FXML
    public void updateData(ActionEvent e) {
        loadStudentData();
    }

    @FXML
    public void addStudent(ActionEvent event) {
        String sql = "INSERT INTO students(first_name, last_name, email, date_of_birth)" +
                "VALUES(?, ?, ?, ?);";
        try {
            Connection connection = DatabaseConnector.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName.getText());
            preparedStatement.setString(2, lastName.getText());
            preparedStatement.setString(3, email.getText());
            preparedStatement.setString(4, dateOfBirth.getEditor().getText());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}