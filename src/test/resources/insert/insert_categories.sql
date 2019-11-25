INSERT INTO categories(
    SELECT * FROM CSVREAD('C:\Users\user\Downloads\shop\rest-api\src\test\resources\csv\categories.csv'));
