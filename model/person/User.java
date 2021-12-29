package tamrinshop.model.person;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tamrinshop.model.Cart;
import tamrinshop.enumaration.UserRole;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Cart> carts;
    @OneToOne
    private Address address;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;


    public User(String username, String password, Address address, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
