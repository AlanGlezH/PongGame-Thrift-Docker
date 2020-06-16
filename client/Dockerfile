FROM ubuntu:20.04
WORKDIR /pongame/client
ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update && \ 
    apt-get install -y \
    python3-dev \
    python3-pip \
    libsdl2-dev libsdl2-2.0 \
    libsdl2-dev libsdl2-2.0 \
    libjpeg-dev libwebp-dev libtiff5-dev libsdl2-image-dev libsdl2-image-2.0-0 \
    libmikmod-dev libfishsound1-dev libsmpeg-dev liboggz2-dev libflac-dev libfluidsynth-dev libsdl2-mixer-dev libsdl2-mixer-2.0-0 \
    libfreetype6-dev libsdl2-ttf-dev libsdl2-ttf-2.0-0 
RUN python3 -m pip install \ 
    thrift \
    pygame==2.0.0.dev6
EXPOSE 8080
CMD ["python3", "main.py"]