package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {
     private final String name;

     private static final Map<String, Category> cachedCategories = new HashMap<>();

     private Category(String name) {
         this.name = name;
     }

     public static Category of(String name) {
         if (name == null) {
             throw new IllegalArgumentException("Category name can't be null");
         }
         String formattedName = makeFirstCharUppercase(name);
         Category category = cachedCategories.get(formattedName);
         if ( category == null) {
             category = new Category(formattedName);
             cachedCategories.put(formattedName, category);
         }
         return category;
     }

     public String getName() {
         return name;
     }

     private static String makeFirstCharUppercase(String name) {
         String firstChar = name.substring(0, 1);
         return firstChar.toUpperCase() + name.substring(1);
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
