# FROM node:18-alpine

# #WORKDIR oznacava u kom direktorijumu ce se izvrsavati RUN komande
# WORKDIR /app


# #COPY ce se izvrisiti u /app; . znaci u trenutno fajlu
# # COPY . .
# COPY . .

# RUN npm install

# RUN npm install @vue/cli@3.7.0 -g

# EXPOSE 8080
# #U CMD se specificiraju komande koje ce se pokrenuti nakon sto se slika podigne i kontejner pokrene
# CMD ["npm", "run", "serve"]

FROM node:18-alpine

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app/
RUN npm install

# Bundle app source
COPY . /usr/src/app

EXPOSE 8080
CMD [ "npm", "run", "serve"]