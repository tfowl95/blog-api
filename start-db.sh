#!/bin/bash
# start-db.sh

echo "Starting PostgreSQL Docker container..."
docker run --name blog-postgres \
-e POSTGRES_USER=bloguser \
-e POSTGRES_PASSWORD=blogpass \
-e POSTGRES_DB=blogdb \
-p 5432:5432 \
-d postgres:16

echo "Waiting for PostgreSQL to start..."
sleep 10

docker exec -i blog-postgres psql -U bloguser -d blogdb <<-EOSQL
    CREATE TABLE IF NOT EXISTS posts (
        id SERIAL PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        content TEXT NOT NULL,
        category VARCHAR(100) NOT NULL,
        tags TEXT[] NOT NULL,
        created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
    );
EOSQL

echo "Database and scheme initialized successfully."