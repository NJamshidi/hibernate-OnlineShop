package tamrinshop;

import tamrinshop.model.Cart;
import tamrinshop.enumaration.*;
import tamrinshop.model.person.Address;
import tamrinshop.model.person.User;
import tamrinshop.model.products.ElectronicDevice;
import tamrinshop.model.products.Product;
import tamrinshop.model.products.ReadableItem;
import tamrinshop.model.products.Shoe;
import tamrinshop.service.ProductService;
import tamrinshop.service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    static UserService userService = new UserService();
    static ProductService productService = new ProductService();

    public static void main(String[] args) {
        System.out.print("Enter your username: ");
        String username = input.nextLine();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            System.out.print("you are not register");
            String answer = "";
            while (!answer.equalsIgnoreCase("n")) {
                System.out.print("do you want to?(y/n): ");
                answer = input.nextLine();
                if ("y".equals(answer)) {
                    System.out.print("Enter your password: ");
                    String password = input.nextLine();
                    System.out.print("Enter your postal code: ");
                    String postalCode = input.nextLine();
                    Address address = new Address(postalCode);
                    user = new User(username, password, address, UserRole.USER);
                    userService.addNewUser(user);
                    break;
                } else if (!"n".equals(answer)) {
                    System.out.println("invalid data");
                }
            }
        } else {
            System.out.print("Enter your password: ");
            String password = input.nextLine();
            if (user.getPassword().equals(password)) {
                if (user.getUserRole().equals(UserRole.USER)) {
                    showUserMenu(user);
                } else {
                    showAdminMenu();
                }
            } else {
                System.out.println("password is incorrect");
            }
        }
    }

    private static void showAdminMenu() {
        int select;
        select:
        do {
            System.out.print(
                    "1.Add new product  \n" +
                            "2.Remove product  \n" +
                            "3.Show products  \n" +
                            "4.Show users  \n" +
                            "5.exit");
            select = input.nextInt();
            switch (select) {
                case 1:
                    addNewProduct();
                    break;

                case 2:
                    removeProduct();
                    break;

                case 3:
                    showAllProducts(productService.returnAllProducts());
                    break;

                case 4:
                    showAllUsers();
                    break;

                case 5:
                    break select;

                default:
                    System.out.println("invalid value");
            }

        } while (true);

    }

    private static void removeProduct() {
        System.out.print("which group of product do you want remove? 1.electronics 2.readable items 3.shoes");
        int productType = input.nextInt();
        input.nextLine();
        switch (productType) {
            case 1:
                removeElectronicProduct();
                break;
            case 2:
                removeReadableProduct();
                break;
            case 3:
                removeShoeProduct();
                break;
            default:
                System.out.println("invalid value");
                break;

        }
    }


    private static Product getRemovingProduct(String tableName) {
        System.out.print("enter id: ");
        int id = input.nextInt();
        return productService.findProductById(tableName, id);
    }

    private static void removeElectronicProduct() {
        ElectronicDevice electronicDevice = (ElectronicDevice) getRemovingProduct("ElectronicDevice");
        if (electronicDevice == null)
            System.out.println("we have n't this product");
        else {
            productService.removeElectronicDevice(electronicDevice);
        }
    }

    private static void removeReadableProduct() {
        ReadableItem readableItem = (ReadableItem) getRemovingProduct("ReadableItem");
        if (readableItem == null)
            System.out.println("we have n't this product");
        else {
            productService.removeReadableItem(readableItem);
        }
    }

    private static void removeShoeProduct() {
        Shoe shoe = (Shoe) getRemovingProduct("Shoe");
        if (shoe == null)
            System.out.println("we have n't this product");
        else {
            productService.removeShoe(shoe);
        }
    }

    private static void showAllUsers() {
        List<User> users = userService.returnAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void addNewProduct() {
        System.out.print("which group of product do you want? 1.electronics 2.readable items 3.shoes");
        int productT = input.nextInt();
        switch (productT) {
            case 1:
                addNewElectronicProduct();
                break;
            case 2:
                addNewReadableProduct();
                break;
            case 3:
                addNewShoeProduct();
                break;
            default:
                System.out.println("invalid value");
                break;
        }
    }

    private static void addNewElectronicProduct() {
        BrandOfDevice brandOfDevice;
        do {
            System.out.print("brand: ");
            brandOfDevice = BrandOfDevice.valueOf(input.nextLine().toUpperCase());
            break;
        } while (true);

        System.out.print("count: ");
        int count = input.nextInt();

        System.out.print("cost: ");
        double cost = input.nextDouble();

        productService.addNewElectronicProduct(new ElectronicDevice(count, cost, brandOfDevice));
    }

    private static void addNewReadableProduct() {
        TypeOfReadableItem typeOfReadableItem;
        do {
            System.out.print("type Of Readable Item: ");
            typeOfReadableItem = TypeOfReadableItem.valueOf(input.nextLine().toUpperCase());
            break;
        } while (true);

        System.out.print("count: ");
        int count = input.nextInt();

        System.out.print("cost: ");
        double cost = input.nextDouble();

        System.out.print("count of pages: ");
        int countOfPages = input.nextInt();

        productService.addNewReadableProduct(new ReadableItem(count, cost, countOfPages, typeOfReadableItem));
    }

    private static void addNewShoeProduct() {
        TypeOfShoe typeOfShoe;
        do {
            System.out.print("type Of Shoe: ");
            typeOfShoe = TypeOfShoe.valueOf(input.nextLine().toUpperCase());
            break;
        } while (true);

        System.out.print("count: ");
        int count = input.nextInt();

        System.out.print("cost: ");
        double cost = input.nextDouble();

        System.out.print("size of shoe: ");
        int sizeOfShoe = input.nextInt();

        input.nextLine();
        System.out.print("color: ");
        String color = input.nextLine();

        productService.addNewShoeProduct(new Shoe(count, cost, sizeOfShoe, color, typeOfShoe));
    }

    private static void showUserMenu(User user) {
        int select;
        select:
        do {
            System.out.print(
                    "1.Add product to cart  \n" +
                            "2.Remove product from cart  \n" +
                            "3.Show a list of products \n" +
                            "4.Show the total cart  \n" +
                            "5.Show your products sold  \n" +
                            "6.Confirm your cart  \n" +
                            "7.exit\n");

            select = input.nextInt();
            switch (select) {
                case 1:
                    addNewProductToCart(user);
                    break;

                case 2:
                    removeItemFromCart(user);
                    break;

                case 3:
                    showAllProducts(productService.returnAllProducts());
                    break;

                case 4:
                    getTotalPriceOfCartsForThisUser(user);
                    break;

                case 5:
                    showYourCarts(user);
                    break;

                case 6:
                    confirmOrders(user);
                    break;

                case 7:
                    break select;

                default:
                    System.out.println("invalid value");
            }

        } while (true);
    }


    private static void confirmOrders(User user)  {
        System.out.println("here is your list:");
        showCarts(returnNotCompletedCart(user));
        getTotalPriceOfCartsForThisUser(user);

        while (true) {
            System.out.print("are you sure that you wanna confirm and pay?(y/n):");
            input.nextLine();

                String answer = input.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    userService.accessToCartService().confirmCarts(user);
                    break;
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println("ok... come back later :)");
                    break;
                } else
                    throw new RuntimeException("invalid answer!");
        }
        System.out.println("Done :)");
    }

    private static void showYourCarts(User user) {
        List<Cart> notCompletedCart = returnNotCompletedCart(user);
        List<Cart> completedCart = returnCompletedCart(user);
        System.out.println("your past products sold:");
        for (Cart cart : completedCart) {
            System.out.println(cart.toString());
        }
        System.out.println("your now products sold:");
        for (Cart cart : notCompletedCart) {
            System.out.println(cart.toString());
        }
    }

    private static List<Cart> returnCompletedCart(User user) {
        return userService.accessToCartService().getCompletedCart(user);
    }

    private static void getTotalPriceOfCartsForThisUser(User user) {
        List<Cart> productsSold = returnNotCompletedCart(user);
        System.out.println("the total cost is: " + calTotalCost(productsSold));
    }

    private static double calTotalCost(List<Cart> productsSold) {
        double totalCost = 0;
        for (Cart cart : productsSold) {
            Product product = cart.getProduct();
            totalCost = totalCost + (product.getCost() * product.getCount());
        }
        return totalCost;
    }

    private static void removeItemFromCart(User user) {
        List<Cart> productsSold = returnNotCompletedCart(user);
        showCarts(productsSold);
        int numberToRemove;
        while (true) {
            System.out.print("enter the number of cart to remove it: ");
            numberToRemove = input.nextInt();
            handleExceptionForIdToRemove(productsSold, numberToRemove);
            break;
        }
        removeCart(productsSold.get(numberToRemove - 1));
    }

    private static void handleExceptionForIdToRemove(List<Cart> productsSold, int idToRemove) {
        if (idToRemove > productsSold.size())
            throw new RuntimeException("invalid input");
    }

    private static void removeCart(Cart cart) {
        userService.accessToCartService().removeCart(cart);
    }

    private static void showCarts(List<Cart> productsSold) {
        for (Cart cart : productsSold) {
            System.out.println(cart.toString());
        }
    }

    private static List<Cart> returnNotCompletedCart(User user) {
        return userService.accessToCartService().getNotCompletedCart(user);
    }

    private static void addNewProductToCart(User user) {
        int count = userService.findCountOfItemsInUserCart(user);
        if (count < 5) {
            System.out.printf("your cart has %o items so you can add %o items%n", count, (5 - count));
            List<Product> products = productService.returnAllProducts();
            int productsSize = showAllProducts(products);

            System.out.print("Enter the number of product: ");
            int number = input.nextInt();
            if (number > productsSize + 1) {
                System.out.println("invalid number");
                return;
            }

            Product product = returnProductInListWithNumber(products, number - 1);
            if (product == null)
                return;
            if (product.getCount() == 0) {
                System.out.println("is not in store!");
                return;
            }

            System.out.println("you selection : " + product.toString());
            System.out.print("Enter the count of it: ");
            int countOfOrder = input.nextInt();
            while (product.getCount() < countOfOrder) {
                System.out.println("it is more than the allowed count");
                System.out.print("Enter the count of it: ");
                countOfOrder = input.nextInt();
            }
            System.out.println(product.getTypeOfProducts().toString().toLowerCase());
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setCount(countOfOrder);
            cart.setCartStatus(CartStatus.NOT_COMPLETED);
            user.getCarts().add(cart);
            product.buy(countOfOrder);
            userService.accessToCartService().addNewCart(cart);
        }


    }
    private static Product returnProductInListWithNumber(List<Product> lists, int number) {
        return lists.get(number);
    }

    private static int showAllProducts(List<Product> lists) {
        int index = 0;
        for (Product list : lists) {
            System.out.println((++index) + ")" + list);
        }
        return index;
    }


}



