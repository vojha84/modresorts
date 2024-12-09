CONNECT TO PROD01;

LIST TABLES FOR SCHEMA DB2INST1;

CREATE TABLE MODRESORTSSTAFF (
    ID       BIGINT         NOT NULL,
    NAME     VARCHAR(40)    NOT NULL,
    AGE      INT            NOT NULL,
    BIO      VARCHAR(1024)  NOT NULL,
    PRIMARY KEY(ID)
);

INSERT INTO MODRESORTSSTAFF (ID,NAME,AGE,BIO) VALUES (1,'Jess',29,'Hi there! I''m Jess, a travel enthusiast dedicated to enhancing your travel experiences. As part of our incredible team, I work tirelessly to ensure that every journey you embark on is as enjoyable and rewarding as possible. With a passion for discovering new destinations and a keen eye for detail, I strive to bring instant benefits to your stay at every hotel. Over the past decade, I''ve gained valuable insights and expertise in the travel industry, which I now bring to our company to provide personalized, top-notch service. Let’s make your next trip the best one yet!');
INSERT INTO MODRESORTSSTAFF (ID,NAME,AGE,BIO) VALUES (2,'Daniel',45,'
Meet Daniel, a seasoned travel professional with over twenty years of industry expertise who is passionate about creating unforgettable experiences. At our company, he ensures that every hotel stay is packed with instant benefits and exceptional service. Daniel''s holistic approach to travel means he excels at uncovering hidden gems and tailoring personalized journeys to meet your unique needs. Outside of work, you’ll find him exploring new cultures, savoring local cuisines, and collecting stories from his global adventures.');
INSERT INTO MODRESORTSSTAFF (ID,NAME,AGE,BIO) VALUES (3,'Emily',26,'Emily is a vibrant travel specialist with a knack for turning ordinary trips into extraordinary adventures. With a keen eye for detail and a passion for discovering new destinations, Emily ensures that every journey is meticulously planned and flawlessly executed. Her enthusiasm for travel drives her to find unique experiences and tailor them to fit your specific needs. With Emily''s expertise and dedication, your next trip is bound to be unforgettable.');