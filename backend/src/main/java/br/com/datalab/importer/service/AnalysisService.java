package br.com.datalab.importer.service;

import br.com.datalab.importer.model.TeamInsight;
import br.com.datalab.importer.model.User;
import br.com.datalab.importer.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnalysisService {

    private final UserRepository repository;

    /**
     * GET /superusers
     * Filtro: score >= 900 e active = true
     * Retorna os dados e o tempo de processamento da requisição.
     * */

    public List<User> getSuperUsers() {
        return repository.findAll()
                .parallelStream()
                .filter( user -> user.getScore() >= 900 && user.getActive())
                .toList();
    }
    /**
     * GET /top-countries
     * Agrupa os superusuários por país.
     * Retorna os 5 países com maior número de superusuários.
     * */
    public List<Map<String, Object>> getTopCountries() {
        return getSuperUsers().parallelStream()
                .collect(Collectors.groupingBy(User::getCountry, Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry<String, Long>::getValue).reversed())
                .map(e -> Map.<String, Object>of("pais", e.getKey(), "total", e.getValue()))
                .toList();
    }

    /**
     * GET /team-insights
     * Agrupa por team.name.
     * Retorna: total de membros, líderes, projetos concluídos e % de membros ativos.
     * */
    public List<TeamInsight> getTeamInsights() {
        return repository.findAll().parallelStream()
                .collect(Collectors.groupingBy(u -> u.getTeam().getName()))
                .entrySet().stream()
                .map(e -> {
                    List<User> users = (List<User>) e.getValue();
                    int total_members = users.size();
                    int total_leaders = (int) users.stream().filter(u -> u.getTeam().getLeader()).count();
                    int total_completed_projects = users.stream()
                            .flatMap(u -> u.getTeam().getProjects().stream())
                            .mapToInt(p -> p.getCompleted() ? 1 : 0).sum();
                    int total_users_active = (int) users.stream().filter(User::getActive).count();
                    double active_percentage = total_members == 0 ? 0 : total_users_active * 100.0 / total_members;

                    return new TeamInsight(e.getKey(), total_members, total_leaders, total_completed_projects, active_percentage);
                }).toList();
    }

    /**
     * GET /active-users-per-day
     * Conta quantos logins aconteceram por data.
     * Query param opcional: ?min=3000 para filtrar dias com pelo menos 3.000 logins.
     * */

    
}
