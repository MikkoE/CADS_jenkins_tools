################################################################################
#   extending omnet-inet docker container with jenkins stuff                   #
#                                                                              #
#                                         Erstellt: 09.11.2019 Mikko Eberhardt #
################################################################################

FROM ubuntu:18.04

################################################################################
#  create jenkins user to be available in docker container                     #
################################################################################
RUN useradd -m -d /home/jenkins -s $(which bash) -G sudo jenkins

################################################################################
#  installing packages                                                         #
################################################################################
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get -qq -y install build-essential gcc g++ bison \
    flex perl-base python python3 tcl-dev libxml2-dev libxml2-utils zlib1g-dev \
    default-jre wget cmake python3 zip

################################################################################
#  adding omnet to the path variable                                           #
################################################################################
ENV PATH=/var/jenkins_home/workspace/TestSuite/omnetpp/bin:$PATH

################################################################################
#  switching back to normalen jenkins user                                     #
################################################################################
USER jenkins
