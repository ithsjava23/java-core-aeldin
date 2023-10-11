package org.example.warehouse;

public class Category {
    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        return new Category(name.substring(0, 1).toUpperCase() + name.substring(1));
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Category category = (Category) obj;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
