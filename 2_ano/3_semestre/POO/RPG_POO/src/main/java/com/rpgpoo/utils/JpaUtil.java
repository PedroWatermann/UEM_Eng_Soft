package com.rpgpoo.utils;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class JpaUtil {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        String host = get("SUPABASE_DB_HOST");
        String port = getOrDefault("SUPABASE_DB_PORT", "5432");
        String dbName = getOrDefault("SUPABASE_DB_NAME", "postgres");

        Map<String, String> overrides = new HashMap<>();
        overrides.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://" + host + ":" + port + "/" + dbName);
        overrides.put("jakarta.persistence.jdbc.user", get("SUPABASE_DB_USER"));
        overrides.put("jakarta.persistence.jdbc.password", get("SUPABASE_DB_PASSWORD"));

        return Persistence.createEntityManagerFactory("rpgPooPU", overrides);
    }

    // Prioridade: variável de ambiente do SO > arquivo .env
    private static String get(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            value = dotenv.get(key);
        }
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Variável " + key + " não definida (nem no SO, nem no .env).");
        }
        return value;
    }

    private static String getOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null) value = dotenv.get(key);
        return value != null ? value : defaultValue;
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}