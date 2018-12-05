package pl.msoroka.zad05.service;

import pl.msoroka.zad05.domain.Producer;

import java.util.List;

public interface ProducerManager {

    void addProducer(Producer producer);

    List<Producer> getAllProducers();

    Producer findById(long id);

    void updateProducer(Producer producer);

    void deleteProducer(Producer producer);

    void assignPlane(Long planeId, Long producerId);
}
