package benakka.inventoryservice;

import benakka.inventoryservice.entities.Product;
import benakka.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(34000)
                    .quantity(12)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smartphone")
                    .price(15000)
                    .quantity(25)
                    .build());
            productRepository.save(Product.builder()
                    .name("Tablet")
                    .price(8000)
                    .quantity(18)
                    .build());
            productRepository.save(Product.builder()
                    .name("Headphones")
                    .price(1200)
                    .quantity(50)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smartwatch")
                    .price(3500)
                    .quantity(30)
                    .build());
        };
    }
}
