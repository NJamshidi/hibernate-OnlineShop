package tamrinshop.service;

import tamrinshop.dao.CartDao;
import tamrinshop.model.Cart;
import tamrinshop.enumaration.CartStatus;
import tamrinshop.model.person.User;
import tamrinshop.model.products.Product;

import java.util.List;

public class CartService {
    CartDao cartDao = new CartDao();
    ProductService productService = new ProductService();


    public void addNewCart(Cart cart) {
        cartDao.create(cart);
    }

    public void increaseTheCountOfAvailableProduct(Product product, int count) {
        productService.increaseTheCountOfProduct(product, count);
    }

    public List<Cart> getNotCompletedCart(User user) {
        return cartDao.getCartsByStatus(user, CartStatus.NOT_COMPLETED);
    }

    public void removeCart(Cart cart) {
        int count = cart.getCount();
        Product product = cart.getProduct();
        cartDao.remove(cart);
        increaseTheCountOfAvailableProduct(product, count);
    }

    public List<Cart> getCompletedCart(User user) {
        return cartDao.getCartsByStatus(user, CartStatus.COMPLETED);
    }

    public void confirmCarts(User user) {
        List<Cart> carts = user.getCarts();
        carts.forEach(cart -> cart.setCartStatus(CartStatus.COMPLETED));
        cartDao.updateStatus(carts);
    }
    public int findCountOfItemsByUserId(int id) {
        return cartDao.findCountOfItemsByUserId(id);
    }
}

