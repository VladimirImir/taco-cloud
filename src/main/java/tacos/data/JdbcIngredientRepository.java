package tacos.data;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/** @Repository - Это одна из нескольких стереотипных аннотаций, которые опредиляют фреймворк Spring*/
/** включая @Controller и @Component. */
/** Добавляя аннотацию @Repository, мы заявляем, что JdbcIngredientRepository должен автоматически  */
/** обнаруживаться при сканировании компонентов и создаваться как bean-компонент в контексте прилодения Spring.*/

/** Когда фрефмворк Spring будет создавать bean-компонент JdbcIngredientRepository, он внедрит в него */
/** экземпляр JdbcTemplate. */

/** Когда имеется один конструктор, Spring неявно применяет автоматическое связывание зависимостей через */
/** параметры этого конструктора. Если имеется более одного конструктора или если нужно, чтобы автоматическое */
/** связывание опредилялось явно, можно к конструктору добавить аннотацию @Autowired. */

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /** Оба метожа findAll() и findById(), испрользуют эеземпляр JdbcTemplate аналогичним образом. */
    /** Метод findAll(), как ожидается, возвращает коллекцию объектов и использует метод query() */
    /** экземпляра JdbcTemplate. Метод query() принимает SQL-запрос, а так же реализацию Rowmapper */
    /** из фреймворка Spring для отображения каждой записи из набора результатов в объект. Также метод query()*/
    /** принимает дополнительные аргументы со значениями для параметров в запросе. */
    /** Но в данном случает таких параметров нет. */
    /**  */

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id){
        List<Ingredient> results = jdbcTemplate.query(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }
    /** Если по какой то причине нужно явно создать RowMapper, то используем вот такую реализацию findById()
     *
    @Override
    public Ingredient findById(String id) {
        return jdbcTemplate.queryForObject(
                "select id, name, type from Ingredient where id=?",
                new RowMapper<Ingredient>() {
                    public Ingredient mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        return new Ingredient(
                                rs.getString("id"),
                                rs.getString("name"),
                                Ingredient.Type.valueOf(rs.getString("type")));
                    };
                }, id);
    }*/

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type")));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) value (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

}
