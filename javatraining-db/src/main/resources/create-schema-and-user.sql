CREATE SCHEMA if not exists moksiakova;
DO
$do$
    BEGIN
        IF NOT EXISTS (
                SELECT FROM pg_catalog.pg_roles
                WHERE  rolname = 'usr') THEN

            CREATE ROLE usr LOGIN PASSWORD 'pass';
            GRANT ALL ON SCHEMA moksiakova TO usr;
        END IF;
    END
$do$;
