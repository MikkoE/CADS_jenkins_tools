FROM jenkins/jenkins:lts
USER root
RUN apt-get update
RUN apt -y install apt-transport-https \
                    ca-certificates \
                    curl \
                    gnupg2 \
                    software-properties-common
RUN apt install -y aptitude
RUN aptitude search docker
RUN uname -a

# RUN apt-get install -y docker.io
# RUN usermod -a -G docker jenkins
# USER jenkins
