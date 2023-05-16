package ai.openfabric.api.docker;
import org.springframework.context.annotation.Bean;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.transport.DockerHttpClient;
import com.github.dockerjava.okhttp.OkDockerHttpClient;

import java.util.Iterator;
import java.util.List;


public class DockerConf {


    private DockerClient dockerClient;


    //Default Client Configuration


    public DockerConf() {

        DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost("unix:///var/run/docker.sock").build();
        DockerHttpClient httpClient = new OkDockerHttpClient.Builder()
                .dockerHost(standard.getDockerHost())
                .sslConfig(standard.getSSLConfig())
                .build();
        this.dockerClient = DockerClientBuilder.getInstance(standard)
                .withDockerHttpClient(httpClient)
                .build();

        DockerClient dockerClient = DockerClientImpl.getInstance(standard, httpClient);

        CreateContainerResponse container = dockerClient.createContainerCmd("worker-image")
                .withName("worker-container")
                .exec();
        dockerClient.startContainerCmd(container.getId()).exec();

//        dockerClient.stopContainerCmd("worker-container").exec();
//        dockerClient.removeContainerCmd("worker-container").exec();

        //List Containers.
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
        Iterator<Container> it = containers.iterator();
        while (it.hasNext ())
        {
            Container containner = it.next();
            System.out.println(containner.getImage() + " " + containner.getStatus() );

        }
    }

    public DockerClient getDockerClient() {
        return dockerClient;
    }
}

