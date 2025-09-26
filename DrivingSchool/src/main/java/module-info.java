module edu.ijse.drivingschool {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jdk.xml.dom;
    requires java.security.jgss;
    requires jbcrypt;
    requires java.desktop;

    // Allow JavaFX runtime to access AppInitializer
    opens edu.ijse.drivingschool to javafx.graphics;

    // Allow FXML to access controllers
    opens edu.ijse.drivingschool.controller to javafx.fxml;

    // Allow Hibernate to access entities via reflection
    opens edu.ijse.drivingschool.entity to org.hibernate.orm.core;

    // Allow JavaFX TableView (PropertyValueFactory) to access your TM classes
    opens edu.ijse.drivingschool.dto.tm to javafx.base;
}
