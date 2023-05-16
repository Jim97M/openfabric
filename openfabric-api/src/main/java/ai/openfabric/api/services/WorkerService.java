package ai.openfabric.api.services;

import ai.openfabric.api.model.Worker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface WorkerService<WorkerStats> {

    Worker startWorker(Long id);

    Worker stopWorker(Long id);

    Worker getWorker(Long id);

    List<Worker> listWorkers(int page, int size);


    Worker getWorkerById(String id);

    Worker createWorker(Worker worker);

    Worker updateWorker(Long id, Worker worker);

    String startWorker(String id);

    Worker stopWorker(String id);

    Worker getWorker(String id);


    abstract void onNext(Object statsResponse);

    void onError(Throwable throwable);

    void onComplete();
}
