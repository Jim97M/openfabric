package ai.openfabric.api.controller;

import ai.openfabric.api.model.Worker;
import ai.openfabric.api.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class WorkerController<WorkerStats> {

    @Autowired
    private WorkerService workerService;

    @GetMapping("worker")
    public ResponseEntity<Page<Worker>> listWorkers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Worker> workers = (Page<Worker>) workerService.getWorker(String.valueOf(pageable));
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Long id) {
        Worker worker = workerService.getWorkerById(String.valueOf(id));
        if (worker == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(worker);
    }

    @PostMapping("createworker")
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
        worker = workerService.createWorker(worker);
        return ResponseEntity.status(HttpStatus.CREATED).body(worker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        Worker updatedWorker = workerService.updateWorker(id, worker);
        if (updatedWorker == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedWorker);
    }


    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startWorker(@PathVariable String id) {
        boolean started = Boolean.parseBoolean(workerService.startWorker(id));
        if (!started) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/stop")
    public ResponseEntity<Void> stopWorker(@PathVariable String id) {
        Worker stopped = workerService.stopWorker(id);

        return ResponseEntity.noContent().build();
    }


}
