package tamrinshop.service;

import tamrinshop.dao.UserDao;
import tamrinshop.enumaration.UserRole;
import tamrinshop.model.person.Address;
import tamrinshop.model.person.User;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();
    CartService cartService = new CartService();

    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void addNewUser(User user) {
        userDao.create(user);
    }

    public CartService accessToCartService() {
        return cartService;
    }

    public List<User> returnAllUsers() {
        return userDao.readAll();
    }
    public int findCountOfItemsInUserCart(User user) {
        return cartService.findCountOfItemsByUserId(user.getId());
    }

    private void createAdmin() {
        Address address = new Address();
        address.setPostalCode("111111111");
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        user.setUserRole(UserRole.ADMIN);
        user.setAddress(address);
        address.setUser(user);
        userDao.create(user);
    }
}
