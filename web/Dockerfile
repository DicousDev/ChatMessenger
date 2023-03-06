FROM node:16

WORKDIR /app

COPY package.json .

RUN npm i

COPY . .

ENV VITE_APP_IP=localhost:8080

EXPOSE 5173

CMD [ "npm", "run", "dev" ]