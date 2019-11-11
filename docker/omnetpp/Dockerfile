################################################################################
#   extending omnet-inet docker container with jenkins stuff                   #
#                                                                              #
#                                         Erstellt: 09.11.2019 Mikko Eberhatdt #
################################################################################

FROM ubuntu:18.04

ENV OMNETPP_DIR=/home/jenkins/

################################################################################
#  create jenkins user to be available in docker container                     #
################################################################################
RUN useradd -m -s $(which bash) -G sudo jenkins
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

################################################################################
#  installing omnetpp                                                          #
################################################################################
# Omnet++
WORKDIR $OMNETPP_DIR
RUN wget https://github.com/omnetpp/omnetpp/releases/download/omnetpp-5.4.1/omnetpp-5.4.1-src-linux.tgz \
    && tar -xzf omnetpp-5.4.1-src-linux.tgz \
    && rm omnetpp-5.4.1-src-linux.tgz \
    && mv omnetpp-5.4.1 $OMNETPP_DIRomnetpp
WORKDIR $OMNETPP_DIRomnetpp
ENV PATH=$PATH:$OMNETPP_DIRomnetpp/bin
RUN ./configure WITH_TKENV=no WITH_QTENV=no WITH_OSG=no WITH_OSGEARTH=no WITH_PARSIM=no \
    && make -j$(grep -c proc /proc/cpuinfo)


################################################################################
#  installing inet                                                             #
################################################################################
RUN mkdir -p $OMNETPP_DIRmodels
WORKDIR $OMNETPP_DIRmodels
RUN wget https://github.com/inet-framework/inet/releases/download/v4.1.0/inet-4.1.0-src.tgz \
    && tar -xzf inet-4.1.0-src.tgz && rm inet-4.1.0-src.tgz && mv inet4 inet
WORKDIR $OMNETPP_DIRmodels/inet
RUN make makefiles && make -j$(grep -c proc /proc/cpuinfo) && make MODE=debug -j$(grep -c proc /proc/cpuinfo)

################################################################################
#  want to call jenkins pipeline as root                                       #
################################################################################
COPY sudoers /etc/sudoers
RUN cat /etc/sudoers
