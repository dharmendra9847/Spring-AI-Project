CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS vector_store (

                                            id TEXT PRIMARY KEY,

                                            content TEXT NOT NULL,

                                            metadata JSONB DEFAULT '{}'::jsonb,

                                            embedding VECTOR(768) NOT NULL,

                                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE INDEX IF NOT EXISTS vector_store_embedding_idx
    ON vector_store
        USING HNSW (embedding vector_cosine_ops);