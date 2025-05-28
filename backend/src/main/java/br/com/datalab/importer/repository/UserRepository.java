package br.com.datalab.importer.repository;

import br.com.datalab.importer.model.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepository {

    private final Map<UUID, User> db = new ConcurrentHashMap<>();

    public void saveAll(Collection<User> users) {
        users.forEach( u -> {
            db.put(u.getId(), u);
        });
    }

    public Collection<User> findAll() {
        return db.values();
    }
}
