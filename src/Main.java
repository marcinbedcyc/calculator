import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private DoubleProperty fontSize = new SimpleDoubleProperty(10);
    private DoubleProperty resultFontSize = new SimpleDoubleProperty(20);

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 320, 400);
        MainWindowController mainWindowController = loader.<MainWindowController>getController();
        VBox child = mainWindowController.getMainVBox();
        TextField child2 = mainWindowController.getResultTextField();

        fontSize.bind(scene.heightProperty().add(scene.widthProperty()).divide(50));
        resultFontSize.bind(scene.heightProperty().add(scene.widthProperty()).divide(20));
        child.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        child2.styleProperty().bind(Bindings.concat("-fx-font-size: ", resultFontSize.asString(), ";",
                                                          "-fx-text-fill: ", "white", ";",
                                                          "-fx-background-color: ", "#424242", ";"));

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(320);
        primaryStage.setMinHeight(210);
        primaryStage.setResizable(true);
        primaryStage.show();

        mainWindowController.focus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
