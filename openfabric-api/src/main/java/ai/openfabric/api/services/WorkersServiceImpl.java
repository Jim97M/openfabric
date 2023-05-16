package ai.openfabric.api.services;

import ai.openfabric.api.model.Worker;
import ai.openfabric.api.repository.WorkerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class WorkersServiceImpl<StatsResponse> implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private DockerClient dockerClient;



    @Override
    public Worker startWorker(Long id) {
        Optional<Worker> optionalWorker = workerRepository.findById(String.valueOf(id));
        if (optionalWorker.isPresent()) {
            Worker worker = optionalWorker.get();
            try {
                CreateContainerResponse container = dockerClient.createContainerCmd(worker.getImage())
                        .withName(worker.getName())
                        .withEnv("SPRING_DATASOURCE_URL=" + worker.getDatasourceUrl())
                        .exec();
                dockerClient.startContainerCmd(container.getId()).exec();

//                worker.setStatus(WorkerStatus.RUNNING);
                return workerRepository.save(worker);
            } catch (Exception e) {
                throw new RuntimeException("Failed to start worker with ID " + id, e);
            }
        } else {
            throw new RuntimeException("Worker with ID " + id + " not found");
        }
    }

    @Override
    public Worker stopWorker(Long id) {
        Optional<Worker> optionalWorker = workerRepository.findById(String.valueOf(id));
        if (optionalWorker.isPresent()) {
            Worker worker = optionalWorker.get();
            try {
                dockerClient.stopContainerCmd(worker.getName()).exec();
//                worker.setStatus(WorkerStatus.STOPPED);
                return workerRepository.save(worker);
            } catch (Exception e) {
                throw new RuntimeException("Failed to stop worker with ID " + id, e);
            }
        } else {
            throw new RuntimeException("Worker with ID " + id + " not found");
        }
    }

    @Override
    public Worker getWorker(Long id) {
        Optional<Worker> optionalWorker = workerRepository.findById(String.valueOf(id));
        if (optionalWorker.isPresent()) {
            return optionalWorker.get();
        } else {
            throw new RuntimeException("Worker with ID " + id + " not found");
        }
    }

    @Override
    public List<Worker> listWorkers(int page, int size) {
        return null;
    }

    @Override
    public String startWorker(String id) {

        return  id;
    }

    @Override
    public Worker stopWorker(String id) {
        return null;
    }


    @Override
    public Worker getWorker(String id) {
        return null;
    }

    @Override
    public Worker getWorkerById(String id) {
        return null;
    }

    @Override
    public Worker createWorker(Worker worker) {
        return null;
    }

    @Override
    public Worker updateWorker(Long id, Worker worker) {
        return null;
    }


    @Override
    public void onNext(Object statsResponse) {

    }

    @Override
    public void onError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onComplete() {
        // handle completion
    }
}
