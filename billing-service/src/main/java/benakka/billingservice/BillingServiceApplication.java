package benakka.billingservice;

import benakka.billingservice.entities.Bill;
import benakka.billingservice.entities.ProductItem;
import benakka.billingservice.feign.CustomerRestClient;
import benakka.billingservice.feign.ProductRestClient;
import benakka.billingservice.model.Customer;
import benakka.billingservice.model.Product;
import benakka.billingservice.repository.BillRepository;
import benakka.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients(basePackages = "benakka.billingservice.feign")
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BillRepository  billRepository,
                                        ProductItemRepository productItemRepository
                                        /*CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient*/){

        return args -> {
            /*
            Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
            Collection<Product> products = productRestClient.getAllProducts().getContent();
            */

            List<Long> customersIds = List.of(1L, 2L, 3L);
            List<Long> productsIds = List.of(1L, 2L, 3L);
            customersIds.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer)
                        .build();
                billRepository.save(bill);
                productsIds.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(product)
                            .quantity(1+new Random().nextInt(10))
                            .unitPrice(1+new Random().nextInt(100))
                            .build();
                    productItemRepository.save(productItem);
                });
            });
        };
    }

}