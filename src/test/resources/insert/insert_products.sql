INSERT INTO products(
    SELECT * FROM CSVREAD('C:\Users\user\Downloads\shop\rest-api\src\test\resources\csv\products.csv'));