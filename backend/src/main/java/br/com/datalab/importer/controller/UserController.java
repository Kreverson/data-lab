package br.com.datalab.importer.controller;

import br.com.datalab.importer.model.User;
import br.com.datalab.importer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    private <T> Map<String, Object> timed (Supplier<T> supplier) {
        long start = System.currentTimeMillis();
        T data = supplier.get();
        long time = System.currentTimeMillis() - start;

        return Map.of(
                "timestamp", Instant.now(),
                "execution_time_ms", time,
                "data", data
        );
    }
    @PostMapping("/users")
    public Map<String, Object> load(@RequestBody List<User> users) {

        return timed(() -> {
            service.saveAll(users);
            return Map.of(
                    "Mensagem", "Arquivo recebido com sucesso",
                    "total_usuarios", service.findAll().size()
            );
        });
    }

    @GetMapping("/super-users")
    public Map<String, Object> getSuperusers() {
        return timed(service::getSuperUsers);
    }

    @GetMapping("/top-countries")
    public Map<String, Object> getTopCountries() {
        return timed(service::getTopCountries);
    }

    @GetMapping("/team-insights")
    public Map<String, Object> getTeamInsights() {
        return timed(service::getTeamInsights);
    }

    @GetMapping("/active-users-per-day")
    public Map<String, Object> getLoginPerDay(@RequestParam(defaultValue = "0") int min) {
        return timed(() -> service.getLoginPerDay(min));
    }


}
