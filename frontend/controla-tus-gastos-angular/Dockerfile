FROM node:20.10.0-alpine3.18 as builder

WORKDIR /app

COPY ./ /app

RUN npm install
RUN npm run build --prod

FROM nginx:1.25.3-alpine

COPY --from=builder /app/dist/controla-tus-gastos-angular/browser /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/sites-available/default

EXPOSE 80
