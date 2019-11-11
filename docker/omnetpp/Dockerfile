################################################################################
#   extending omnet-inet docker container with jenkins stuff                   #
#                                                                              #
#                                         Erstellt: 09.11.2019 Mikko Eberhatdt #
################################################################################

FROM ubuntu:18.04

ARG OMNETPP_DIR=/home/jenkins/

################################################################################
#  create jenkins user to be available in docker container                     #
################################################################################
RUN useradd -m -d /home/jenkins -s $(which bash) -G sudo jenkins
#RUN useradd -u 1000 jenkins

################################################################################
#  adding jenkins to root group                                                #
################################################################################
RUN sed -i 's/root:x:0:/root:x:0:jenkins:/' /etc/group

################################################################################
#  installing packages                                                         #
################################################################################
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get -qq -y install build-essential gcc g++ bison \
    flex perl-base python python3 tcl-dev libxml2-dev libxml2-utils zlib1g-dev \
    default-jre wget cmake python3

USER jenkins
