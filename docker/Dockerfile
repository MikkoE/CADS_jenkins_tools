FROM ubuntu:latest
MAINTAINER Mikko Eberhardt <mikko.eberhardt@haw-hamburg.de>
LABEL Description="Docker image for OMNeT++ Network Simulator to run Packetdrill Tests"

RUN apt-get update

# General dependencies
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y \
git \
wget \
vim \
build-essential \
clang \
bison \
flex \
perl \
tcl-dev \
tk-dev \
libxml2-dev \
zlib1g-dev \
default-jre \
graphviz \
libwebkitgtk-1.0-0 \
xvfb

################################################################################
# QT4 components
################################################################################
RUN apt-get install -y \
  qt5-default \
  qt5-qmake \
  qtbase5-dev \
  openscenegraph-3.4 \
  libopenscenegraph-3.4-dev \
  #libopenscenegraph-dev \
  openscenegraph-plugin-osgearth \
  libosgearth-dev \
  openscenegraph-plugin-osgearth \
  osgearth-data

################################################################################
# OMNeT++ 5
#
# Create working directory
################################################################################
RUN mkdir -p /usr/omnetpp
WORKDIR /usr/omnetpp

################################################################################
# Fetch Omnet++ source
# (Official mirror "doesn't support" command line downloads. Fortunately, we don't care)
################################################################################
#RUN wget --header="Accept: text/html" \
#--user-agent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:21.0) Gecko/20100101 Firefox/21.0" \
#--referer="https://omnetpp.org" \
#--output-document=omnetpp-5.2-src.tgz \
#https://omnetpp.org/omnetpp/send/30-omnet-releases/2317-omnetpp-5-2-linux
COPY omnetpp-5.4.1-src-linux.tgz omnetpp-5.4.1-src-linux.tgz

RUN tar -xf omnetpp-5.4.1-src-linux.tgz

################################################################################
# Compilation requires path to be set
################################################################################
ENV PATH $PATH:/usr/omnetpp/omnetpp-5.4.1/bin

################################################################################
# Graphical interface disable
################################################################################
#RUN cd omnetpp-5.2 && sed -i 's/WITH_TKENV=yes/WITH_TKENV=no/g' configure.user
#RUN cd omnetpp-5.2 && sed -i 's/WITH_QTENV=yes/WITH_QTENV=no/g' configure.user
#RUN cd omnetpp-5.2 && sed -i 's/PREFER_QTENV=yes/PREFER_QTENV=no/g' configure.user
#RUN cd omnetpp-5.2 && sed -i 's/WITH_OSG=yes/WITH_OSG=no/g' configure.user
#RUN cd omnetpp-5.2 && sed -i 's/WITH_OSGEARTH=yes/WITH_OSGEARTH=no/g' configure.user

################################################################################
# Configure and compile omnet++
################################################################################
RUN cd omnetpp-5.4.1 && \
xvfb-run ./configure && \
make

################################################################################
# INET Installation
################################################################################
# add credentials on build
ARG SSH_PRIVATE_KEY
RUN mkdir /root/.ssh/
RUN echo "${SSH_PRIVATE_KEY}" > /root/.ssh/id_rsa

# make sure your domain is accepted
RUN touch /root/.ssh/known_hosts
RUN ssh-keyscan github.com >> /root/.ssh/known_hosts

RUN git clone git@github.com:Transport-Protocol/inet-private.git

################################################################################
# Cleanup
################################################################################
RUN apt-get clean && \
rm -rf /var/lib/apt && \
rm /usr/omnetpp/omnetpp-5.4.1-src-linux.tgz
