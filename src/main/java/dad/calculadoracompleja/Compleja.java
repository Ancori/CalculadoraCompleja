package dad.calculadoracompleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Compleja {

	private DoubleProperty real = new SimpleDoubleProperty();
	private DoubleProperty imaginario = new SimpleDoubleProperty();

	public DoubleProperty realProperty() {
		return this.real;
	}

	public double getReal() {
		return this.realProperty().get();
	}

	public void setReal(final double real) {
		this.realProperty().set(real);
	}

	public DoubleProperty imaginarioProperty() {
		return this.imaginario;
	}

	public double getImaginario() {
		return this.imaginarioProperty().get();
	}

	public void setImaginario(final double imaginario) {
		this.imaginarioProperty().set(imaginario);
	}
	public Compleja suma(Compleja c1, Compleja c2){
	    Compleja a=new Compleja();
	    a.setReal(c1.getReal()+c2.getReal());
	    a.setImaginario(c2.getImaginario()+c2.getImaginario());
	    return a;
	}


}
