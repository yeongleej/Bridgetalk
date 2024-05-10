FROM python:3.9

RUN apt-get update && apt-get install -y \
    ffmpeg \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /bridgetalk-ai

COPY ./requirements.txt requirements.txt

#WORKDIR /bridgetalk-ai

RUN ls -al

RUN pip install --no-cache-dir --upgrade -r requirements.txt

COPY ./app app

RUN ls -al

#RUN cd /bridgetalk-ai

CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]