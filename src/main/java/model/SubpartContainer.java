package model;

import model.Part;

import java.io.Serializable;

public class SubpartContainer implements Serializable {
    private final Long quantity;
    private final Part part;

    public SubpartContainer(Long quantity, Part part) {
        this.quantity = quantity;
        this.part = part;
    }

    public Long getQuantity() {
        return quantity;
    }
    public Part getPart() {
        return part;
    }
}
