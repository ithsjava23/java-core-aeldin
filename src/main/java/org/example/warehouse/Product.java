package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {

        private final UUID uuid;
        private final String name;
        private final Category category;
        private final BigDecimal price;
        private boolean changed;




        public Product(UUID uuid, String name, Category category, BigDecimal price) {
            this.uuid = uuid;
            this.name = name;
            this.category = category;
            this.price = price;
            this.changed = false;


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

        public boolean isChanged() {
            return changed;
        }

        public void setChanged(boolean changed) {
            this.changed = changed;
        }


    public UUID uuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(uuid, product.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }


}