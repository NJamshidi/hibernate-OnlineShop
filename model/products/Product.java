package tamrinshop.model.products;

import lombok.Data;
import lombok.NoArgsConstructor;
import tamrinshop.enumaration.TypeOfProducts;

import javax.persistence.*;

@Data
    @NoArgsConstructor
    @Entity
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public class Product {
        protected int count;
        protected double cost;
        @Enumerated(value = EnumType.STRING)
        protected TypeOfProducts typeOfProducts;
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        private int id;

        public Product(int count, double cost) {
            this.count = count;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "id=" + id + ' ' +
                    ", count=" + count + ' ' +
                    ", cost=" + cost + ' ' +
                    ", typeOfProducts=" + typeOfProducts.toString().toLowerCase() + ' ';
        }

        public void buy(int count) {
            this.count -= count;
        }
    }

