FROM python:3.9

COPY ./requirements.txt /bridgetalk-ai/requirements.txt

#COPY ./.env /fastApiProject/.env

WORKDIR /bridgetalk-ai

RUN pip install --no-cache-dir --upgrade -r /bridgetalk-ai/requirements.txt

COPY ./app /bridgetalk-ai/app

RUN cd /bridgetalk-ai/app

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]