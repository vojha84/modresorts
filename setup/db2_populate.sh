# Establish DB connection and create/populate tables
su - db2inst1 -c 'db2 -tvf /populate.sql'