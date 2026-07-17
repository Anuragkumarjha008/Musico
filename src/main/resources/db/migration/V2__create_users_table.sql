CREATE TABLE users.users
(
    id UUID PRIMARY KEY,

    username VARCHAR(50) NOT NULL UNIQUE,

    email VARCHAR(255) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    display_name VARCHAR(100) NOT NULL,

    bio TEXT,

    profile_picture VARCHAR(500),

    status VARCHAR(20) NOT NULL,

    role VARCHAR(20) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

ALTER TABLE users.users
    ADD CONSTRAINT chk_user_status
        CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED'));

ALTER TABLE users.users
    ADD CONSTRAINT chk_user_role
        CHECK (role IN ('USER'));

COMMENT ON TABLE users.users IS
'Stores application users.';