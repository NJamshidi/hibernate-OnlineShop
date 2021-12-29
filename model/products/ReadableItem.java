package tamrinshop.model.products;

import lombok.Data;
import lombok.NoArgsConstructor;
import tamrinshop.enumaration.TypeOfProducts;
import tamrinshop.enumaration.TypeOfReadableItem;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "readable_items")
public class ReadableItem extends Product {
    private int countOfPages;
    @Enumerated(value = EnumType.STRING)
    private TypeOfReadableItem typeOfReadableItem;

    public ReadableItem(int count, double cost, int countOfPages, TypeOfReadableItem typeOfReadableItem) {
        super(count, cost);
        this.countOfPages = countOfPages;
        this.typeOfReadableItem = typeOfReadableItem;
        typeOfProducts = TypeOfProducts.READABLE_ITEMS;
    }

    @Override
    public String toString() {
        return "ReadableItem{" +
                super.toString() +
                ", BrandOfDevice=" + countOfPages + ' ' +
                ", typeOfReadableItem=" + typeOfReadableItem.toString().toLowerCase() +
                '}';
    }
}
