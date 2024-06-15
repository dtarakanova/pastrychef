package org.example.repository;

import org.example.model.Cake;
import org.example.model.CakeOrder;

public interface OrderRepository {
    CakeOrder save(CakeOrder order);
}
