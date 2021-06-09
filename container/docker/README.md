# Yavin Docker Images
Yavin can be easily deployed using docker containers. We maintain multiple dockerfiles for different types of images that can be found on [DockerHub](https://hub.docker.com/repository/docker/verizonmedia/yavin/general).

- **Dockerfile.base:**  Alpine Linux based image with all the required Yavin dependencies
- **Dockerfile.demo-config:**  Yavin image with demo configuration and dataset
- **Dockerfile**:  Production Yavin image with that supports custom configuration

## How To Run Yavin Demo Image

```
docker run -p 9999:8080 verizonmedia/yavin_demo:latest
```
## How To Run Yavin Image With Custom Configuration

```
docker run -v <your path>:/etc/yavin -p 9999:8080 verizonmedia/yavin:latest
```
## Launch in Docker using PWD

[![Click to Launch in Docker instantly](https://raw.githubusercontent.com/play-with-docker/stacks/master/assets/images/button.png)](https://labs.play-with-docker.com/?stack=https://raw.githubusercontent.com/anupkumangodan/navi/pwd_1/container/docker/docker-compose.yml)

You will be taken to "play with docker" UI where you might have to sign-in (or register first) to continue.
After the initial docker compose and swarm is setup, proceed to click on the port (8080) hyperlink in PWD.

PWD is not production grade platform, so it can show unexpected results. When the above button is clicked and after you sign-in, docker swarm build will start up in a small pop up window. The process will complete in few mins as shown.

![swarm build](images/swarm_build.png)

If the above step completed successfully, click the close button on the popup window.Then you can click (hyperlink) 8080 at the top as shown below.

![ready to open](images/ready_open.png)# Yavin Docker Images
