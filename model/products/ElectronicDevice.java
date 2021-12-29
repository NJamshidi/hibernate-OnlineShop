package tamrinshop.model.products;

import lombok.Data;
import lombok.NoArgsConstructor;
import tamrinshop.enumaration.BrandOfDevice;
import tamrinshop.enumaration.TypeOfProducts;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "electronic_devices")
public class ElectronicDevice extends Product {
    @Enumerated(value = EnumType.STRING)
    private BrandOfDevice brandOfDevice;

    public ElectronicDevice(int count, double cost, BrandOfDevice brandOfDevice) {
        super(count, cost);
        this.brandOfDevice = brandOfDevice;
        typeOfProducts = TypeOfProducts.ELECTRONIC_DEVICES;
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                super.toString() +
                ", BrandOfDevice=" + brandOfDevice.toString().toLowerCase() +
                '}';
    }
}