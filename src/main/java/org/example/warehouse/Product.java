package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

        private final UUID uuid;
        private final String name;
        private final Category category;
        private final BigDecimal price;

        public Product(UUID uuid, String name, Category category, BigDecimal price) {
            this.uuid = uuid;
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public UUID getUuid() {
            return uuid;
        }

        public String name() {
            return name;
        }

        public Category category() {
            return category;
        }

        public BigDecimal price() {
            return price;
        }




    public UUID uuid() {
        return uuid;
    }
}