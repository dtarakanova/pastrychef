package org.example.repository;

import org.example.model.Cake;
import org.example.model.CakeOrder;
import org.example.model.Ingredient;
import org.example.model.IngredientRef;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private JdbcOperations jdbcOperations;
    public JdbcOrderRepository (JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    @Override
    @Transactional
    public CakeOrder save(CakeOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO Cake_Order (placed_at, first_name, last_name, city, address) " +
                        "VALUES (?,?,?,?,?)",
                Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt((new Date()));
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                order.getPlacedAt(),
                order.getFirstName(),
                order.getLastName(),
                order.getCity(),
                order.getAddress()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Cake> cakes = order.getCakes();
        int i = 0;
        for (Cake cake : cakes) {
            saveCake(orderId,i++,cake);
        }
        return order;
    }

    private long saveCake(Long orderId, int orderKey, Cake cake) {
        cake.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO Cake (created_at, inscription, cake_order, cake_order_key) " +
                        "VALUES (?,?,?,?)",
                Types.TIMESTAMP, Types.VARCHAR, Types.BIGINT, Types.BIGINT
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                cake.getCreatedAt(),
                cake.getInscription(),
                orderId,
                orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long cakeId = keyHolder.getKey().longValue();
        cake.setId(cakeId);

        List<IngredientRef> ingredientRefs = cake.getIngredients().stream()
                .map(this::convertToIngredientRef)
                .collect(Collectors.toList());
        saveIngredientRefs(cakeId, ingredientRefs);
        return cakeId;
    }

    private IngredientRef convertToIngredientRef(Ingredient ingredient) {
        return new IngredientRef(ingredient.getId());
   }

        private void saveIngredientRefs(long cakeId, List<IngredientRef> ingredientRefs) {
            int key = 0;
            for (IngredientRef ingredientRef : ingredientRefs) {
                jdbcOperations.update(
                        "INSERT INTO Ingredient_Ref(ingredient, cake, cake_key) VALUES(?,?,?)",
                        ingredientRef.getIngredient(), cakeId, key++);
            }
    }
}
