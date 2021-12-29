package tamrinshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tamrinshop.enumaration.CartStatus;
import tamrinshop.model.person.User;
import tamrinshop.model.products.Product;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Product product;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    private CartStatus cartStatus;
    private int count;
}
