module edu.ijse.drivingschool {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jdk.xml.dom;

    // Allow JavaFX runtime to access AppInitializer
    opens edu.ijse.drivingschool to javafx.graphics;

    // Allow FXML to access controllers
    opens edu.ijse.drivingschool.controller to javafx.fxml;
}
