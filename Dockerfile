FROM java:8
ENV LANGUAGE=en_US.utf8
ENV TMPDIR=/tmp
ENV LANG_WHICH en
ENV LANG_WHERE US
ENV ENCODING UTF-8
ENV LANGUAGE ${LANG_WHICH}_${LANG_WHERE}.${ENCODING}
ENV LANG ${LANGUAGE}

# Download and install chrome
RUN echo "Acquire::Check-Valid-Until \"false\";"  > /etc/apt/apt.conf
RUN echo "deb http://archive.debian.org/debian jessie-backports main" > /etc/apt/sources.list.d/jessie-backports.list
RUN apt-get update -y
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 1397BC53640DB551
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update -qqy && \
    apt-get -qqy install google-chrome-stable && \
    rm /etc/apt/sources.list.d/google-chrome.list && \
    rm -rf /var/lib/apt/lists/* /var/cache/apt/*

# Install chromedriver
RUN wget -N https://chromedriver.storage.googleapis.com/LATEST_RELEASE -P ~/Downloads && \
    wget -N "http://chromedriver.storage.googleapis.com/`cat ~/Downloads/LATEST_RELEASE`/chromedriver_linux64.zip" -P ~/Downloads && \
    unzip ~/Downloads/chromedriver_linux64.zip -d ~/Downloads && \
    chmod +x ~/Downloads/chromedriver && \
    mv -f ~/Downloads/chromedriver /usr/local/share/chromedriver && \
    ln -s /usr/local/share/chromedriver /usr/local/bin/chromedriver && \
    ln -s /usr/local/share/chromedriver /usr/bin/chromedriver

## Download and install multi-langs
RUN apt-get -qqy update \
    && apt-get -qqy --no-install-recommends install \
    fonts-liberation \
    libfontconfig \
    fonts-wqy-zenhei \
    && rm -rf /var/lib/apt/lists/* \
    && apt-get -qyy clean
WORKDIR /test
