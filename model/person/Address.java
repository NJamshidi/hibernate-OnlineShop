package tamrinshop.model.person;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "postal_code")
    private String postalCode;
    @OneToOne(mappedBy = "address")
    private User user;

    public Address(String postalCode) {
        this.postalCode = postalCode;
    }
}
