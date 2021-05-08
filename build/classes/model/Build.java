package model;

import java.text.DecimalFormat;
import java.util.List;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Build {

	private ObservableList<Part> parts;
        private DoubleProperty total = new SimpleDoubleProperty();
        
	public ObservableList<Part> getParts() {
            return this.parts;
	}

	public Build() {

            parts = FXCollections.observableArrayList();

	}

	public void addPart(Part part) {
            parts.add(part);
            getTotalPrice();
	}

	public boolean isValid() {
            return hasPartOfType("cpu") && hasPartOfType("motherboard") && hasPartOfType("memory") && hasPartOfType("case")
                            && hasPartOfType("storage");
	}

	public double getTotalPrice() {
            double sum = 0.0;
            for (Part p : parts)
                sum += p.getPrice();
            total.set(sum);
            return sum;
	}
        
        public final double getTotal(){
            return total.get();
        }
        
        public DoubleProperty totalPriceProperty(){
            getTotalPrice();
            return total;
        }

	public boolean hasPartOfType(String type) {
		for (Part p : parts) {
			if (p.hasType(type))
				return true;
		}
		return false;
	}

	public void remove(Part p) {
            this.parts.remove(p);
            getTotalPrice();
	}

	public void remove(List<Part> selectedItems) {

		this.parts.removeAll(selectedItems);
                getTotalPrice();

	}

	public void addParts(List<Part> selectedItems) {

		this.parts.addAll(selectedItems);
                getTotalPrice();

	}

}
