package me.dio.sdw24.adapters.out;

import me.dio.sdw24.domain.model.Champion;
import me.dio.sdw24.domain.ports.ChampionsRepositoty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionsJdbcRepository implements ChampionsRepositoty {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Champion> championRowMapper;

    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
        this.championRowMapper = (rs, rowNum) -> new Champion(

                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("lore"),
                rs.getString("image_url")
        );
    }
    @Override
    public List<Champion> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS", championRowMapper);
    }

    @Override
    public Optional<Champion> findById(Long id) {
        String sql = "SELECT * FROM CHAMPIONS WHERE ID = ?";
        List <Champion> champion = jdbcTemplate.query(sql,championRowMapper,id);
        return champion.stream().findAny();
    }
}
