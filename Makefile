build:
	docker-compose build
pre:
	docker-compose -f docker-compose.yaml -f docker-compose.pre.yaml up -d
pro:
	docker-compose -f docker-compose.yaml -f docker-compose.pro.yaml up -d
down:
	docker-compose down