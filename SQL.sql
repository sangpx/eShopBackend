USE ecommerceshopapp;

INSERT INTO categories (id, level, name, parent_category_id)
  VALUES (4, 4, 'May Tinh', 4);


SELECT * FROM products p;
SELECT * FROM categories c ;


DELETE FROM products
WHERE id = "202";

ALTER TABLE products DROP COLUMN num_ratings;

INSERT INTO sizes (id, name, quantity)
  VALUES (6, 'XXL', 5);

SELECT * FROM products p;

SELECT * FROM carts c;

SELECT * FROM cart_items ci;

DELETE FROM cart_items
WHERE id = "5";


