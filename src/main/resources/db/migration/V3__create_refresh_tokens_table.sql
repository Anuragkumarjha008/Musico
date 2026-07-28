CREATE TABLE users.refresh_tokens (

                                id BIGSERIAL PRIMARY KEY,

                                refresh_token VARCHAR(255) NOT NULL UNIQUE,

                                user_id UUID NOT NULL,

                                expires_at TIMESTAMP WITH TIME ZONE NOT NULL,

                                revoked BOOLEAN NOT NULL DEFAULT FALSE,

                                created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                CONSTRAINT fk_refresh_token_user
                                    FOREIGN KEY (user_id)
                                        REFERENCES users.users(id)
                                        ON DELETE CASCADE
);