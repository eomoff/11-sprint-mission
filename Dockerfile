FROM ubuntu:latest
LABEL authors="eom"

ENTRYPOINT ["top", "-b"]