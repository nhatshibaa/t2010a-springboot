package com.example.t2010aspringboot.seeder;

import com.example.t2010aspringboot.entity.*;
import com.example.t2010aspringboot.enums.OrderStatus;
import com.example.t2010aspringboot.enums.ProductStatus;
import com.example.t2010aspringboot.enums.UserStatus;
import com.example.t2010aspringboot.repository.OrderRepository;
import com.example.t2010aspringboot.repository.ProductRepository;
import com.example.t2010aspringboot.repository.UserRepository;
import com.example.t2010aspringboot.util.RandomLocalDateTime;
import com.example.t2010aspringboot.util.SlugHelper;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class DataSeeder implements CommandLineRunner {
    boolean createData =false;
    final OrderRepository orderRepository;
    final ProductRepository productRepository;
    final UserRepository userRepository;

    Random random = new Random();

    Faker faker = new Faker();

    int numberOfProduct = 200;
    int numberOfOrder = 20000;
    int numberOfUser = 100;

    public DataSeeder(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (createData){
            orderRepository.deleteAll();
            productRepository.deleteAll();
            seedUser();
            seedProduct();
            seedOrder();
        }
    }

    private void seedUser() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < numberOfUser; i++) {
            System.out.println(i + 1);
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setFullName(faker.name().fullName());
            user.setPhone(faker.phoneNumber().phoneNumber());
            user.setEmail(faker.name().username() + "@gmail.com"); // text
            user.setIdentityNumber(faker.idNumber().invalid());
            user.setAddress(faker.address().fullAddress());
            user.setCreatedBy("0");
            user.setUpdatedBy("0");
            user.setStatus(UserStatus.ACTIVE);
            userList.add(user);
            System.out.println(user);
        }
        userRepository.saveAll(userList);
    }
    private void seedOrder() {
        List<Product> products = productRepository.findAll();
        List<User> users = userRepository.findAll();
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < numberOfOrder; i++) {
            Order order = new Order();
            User user = users.get(random.nextInt(users.size()));
            order.setUser(user);
            order.setId(UUID.randomUUID().toString());
            order.setCreatedAt(RandomLocalDateTime.generateLocalDate());
            Set<OrderDetail> orderDetails = new HashSet<>();
            int randomOrderDetailNumber = faker.number().numberBetween(1, 5);
            HashSet<String> existingProductId = new HashSet<>();
            for (int j = 0; j < randomOrderDetailNumber; j++) {
                OrderDetail orderDetail = new OrderDetail();
                Product randomProduct = products.get(
                        faker.number().numberBetween(0, products.size() - 1));
                if (existingProductId.contains(randomProduct.getId())) {
                    continue;
                }
                existingProductId.add(String.valueOf(randomProduct.getId()));
                orderDetail.setId(new OrderDetailId(order.getId(), randomProduct.getId()));
                orderDetail.setOrder(order);
                orderDetail.setProduct(randomProduct);
                orderDetail.setUnitPrice(randomProduct.getPrice());
                orderDetail.setQuantity(faker.number().numberBetween(1, 5));
                orderDetails.add(orderDetail);
            }
            order.setOrderDetails(orderDetails);
            order.setStatus(OrderStatus.DONE);
            orders.add(order);
        }
        orderRepository.saveAll(orders);
    }

    private void seedProduct() {
        List<Product> listProduct = new ArrayList<>();
        for (int i = 0; i < numberOfProduct; i++) {
            System.out.println(i + 1);
            Product product = new Product();
            product.setId(UUID.randomUUID().toString());
            product.setName(faker.name().title());
            product.setSlug(SlugHelper.toSlug(product.getName()));
            product.setDescription(faker.lorem().sentence()); // text
            product.setPrice(faker.number().numberBetween(100, 200) * 10000);
            product.setCreatedBy("0");
            product.setUpdatedBy("0");
            product.setDetail(faker.lorem().sentence());
            product.setThumbnail(faker.avatar().image());
            product.setStatus(ProductStatus.ACTIVE);
            listProduct.add(product);
        }
        productRepository.saveAll(listProduct);
    }
}
