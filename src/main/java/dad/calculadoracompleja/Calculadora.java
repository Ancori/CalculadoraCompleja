package dad.calculadoracompleja;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Calculadora extends Application {

	// view
	private ComboBox<String> operacion;
	private TextField real1text;
	private TextField imaginario1text;
	private TextField real2text;
	private TextField imaginario2text;
	private TextField realresultadotext;
	private TextField imaginarioresultadotext;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        Compleja n1=new Compleja();
        Compleja n2=new Compleja();
        Compleja n3=new Compleja();
		Separator separator = new Separator();
		operacion = new ComboBox<String>();
		operacion.getItems().addAll("+", "-", "x", "/");
		real1text = new TextField();
		imaginario1text = new TextField();
		real2text = new TextField();
		imaginario2text = new TextField();
		realresultadotext = new TextField();
		imaginarioresultadotext = new TextField();
		realresultadotext.setEditable(false);
		imaginarioresultadotext.setEditable(false);

		HBox a = new HBox();
		a.setSpacing(5);
		a.getChildren().addAll(real1text, new Label("+"), imaginario1text, new Label("i"));
		HBox b = new HBox();
		b.setSpacing(5);
		b.getChildren().addAll(real2text, new Label("+"), imaginario2text, new Label("i"));
		HBox c = new HBox();
		c.setSpacing(5);
		c.getChildren().addAll(realresultadotext, new Label("+"), imaginarioresultadotext, new Label("i"));

		VBox esc1 = new VBox();
		esc1.setAlignment(Pos.CENTER);
		esc1.getChildren().addAll(operacion);
		VBox esc2 = new VBox();
		esc2.setSpacing(5);
		esc2.setAlignment(Pos.CENTER);
		esc2.getChildren().addAll(a, b, separator, c);
		HBox root = new HBox();
		root.getChildren().addAll(esc1, esc2);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(5);

		Scene scene = new Scene(root, 540, 280);

		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	    //bindeos
		Bindings.bindBidirectional(real1text.textProperty(),n1.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(real2text.textProperty(),n2.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(realresultadotext.textProperty(),n3.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(imaginario1text.textProperty(),n1.imaginarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(imaginario2text.textProperty(),n2.imaginarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(imaginarioresultadotext.textProperty(),n3.imaginarioProperty(), new NumberStringConverter());
		
        //Cuando seleccionamos un elemento del combo
		operacion.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
				if(nv=="+") {//operacion.getSelectionModel().getSelectedItem()
					n3.realProperty().bind(n1.realProperty().add(n2.realProperty()));
					n3.imaginarioProperty().bind(n1.imaginarioProperty().add(n2.imaginarioProperty()));
				}
				else if(nv=="-") {
					n3.realProperty().bind(n1.realProperty().subtract(n2.realProperty()));
					n3.imaginarioProperty().bind(n1.imaginarioProperty().subtract(n2.imaginarioProperty()));
				}
				else if(nv=="x") {
					n3.realProperty().bind((n1.realProperty().multiply(n2.realProperty()))
							.subtract((n1.imaginarioProperty().multiply(n2.imaginarioProperty()))));
					n3.imaginarioProperty().bind((n1.realProperty().multiply(n2.imaginarioProperty()))
							.subtract((n2.realProperty().multiply(n1.imaginarioProperty()))));
				}
				else if(nv=="/") {
					n3.realProperty().bind((n1.realProperty().multiply(n2.realProperty()).add((n1.imaginarioProperty()
							.multiply(n2.imaginarioProperty())))
							.divide((n2.realProperty().multiply(n2.realProperty())))
							.add((n2.imaginarioProperty().multiply(n2.imaginarioProperty())))));
					n3.imaginarioProperty().bind((n1.imaginarioProperty().multiply(n2.realProperty()).subtract((n1.realProperty()
							.multiply(n2.imaginarioProperty())))
							.divide((n2.realProperty().multiply(n2.realProperty())))
							.add((n2.imaginarioProperty().multiply(n2.imaginarioProperty())))));
				}
		});
	    
	}


	public static void main(String[] args) {
		launch(args);
	}

}
