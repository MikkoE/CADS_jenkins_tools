################################################################################
#   Using jenkins image and extend with:                                       #
#                                  docker                                      #
#                                                                              #
#                                         Erstellt: 09.11.2019 Mikko Eberhatdt #
################################################################################

FROM jenkins/jenkins:lts

################################################################################
#  adding docker                                                               #
################################################################################

USER root
RUN apt-get update && \
apt-get -y install apt-transport-https \
    ca-certificates \
    curl \
    gnupg2 \
    software-properties-common && \
curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
    $(lsb_release -cs) \
    stable" && \
apt-get update && \
apt-get -y install docker-ce
RUN apt-get install -y docker-ce
RUN usermod -a -G docker jenkins

################################################################################
#  want to call jenkins pipeline as root                                       #
################################################################################
COPY sudoers /etc/sudoers

################################################################################
#  changing back to original jenkins user                                      #
################################################################################
USER jenkins
