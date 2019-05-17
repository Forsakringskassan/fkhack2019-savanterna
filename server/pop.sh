#!/bin/bash

# Skapa users
curl -X POST http://127.0.0.1:8080/rest/v1/user/skapa -H "Content-Type: application/json" -d '{ "id" : "66115683", "namn" : "Alexandra Eriksson" }'
curl -X POST http://127.0.0.1:8080/rest/v1/user/skapa -H "Content-Type: application/json" -d '{ "id" : "66126629", "namn" : "Lucas Andersson"}'
curl -X POST http://127.0.0.1:8080/rest/v1/user/skapa -H "Content-Type: application/json" -d '{ "id" : "66120403", "namn" : "Martin Gunnarsson" }'
curl -X POST http://127.0.0.1:8080/rest/v1/user/skapa -H "Content-Type: application/json" -d '{ "id" : "66126490", "namn" : "Joakim Edvinsson" }'

# Skapa kategorier
curl -X POST http://127.0.0.1:8080/rest/v1/kategori/skapa -H "Content-Type: application/json" -d '{ "id" : "1", "namn" : "Systemutvecklare" }'
curl -X POST http://127.0.0.1:8080/rest/v1/kategori/skapa -H "Content-Type: application/json" -d '{ "id" : "2", "namn" : "Produktion" }'
curl -X POST http://127.0.0.1:8080/rest/v1/kategori/skapa -H "Content-Type: application/json" -d '{ "id" : "3", "namn" : "Handläggare" }'

# Skapa behörigheter
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "GWinSuperUser", "beskrivning" : "Windows 10 användare", "kategorier": [{ "id" : "1", "namn" : ""}], "granskare": { "id" : "66115683"} }'
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "Testaren är bäst", "beskrivning" : "Testare är bäst och så är det", "kategorier": [{ "id" : "1", "namn" : ""}], "granskare": { "id" : "66115683"} }'
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "Kaffedarren", "beskrivning" : "Kaffe till alla!", "kategorier": [{ "id" : "1", "namn" : ""}], "granskare": { "id" : "66115683"} }'
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "StashUser", "beskrivning" : "Tillgång till bitbucket/stash", "kategorier": [{ "id" : "1", "namn" : ""}], "granskare": { "id" : "66115683"} }'

curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "Semester", "beskrivning" : "Semesterbehörigheter", "kategorier": [{ "id" : "2", "namn" : ""}], "granskare": { "id" : "66126629"} }'
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "NågonBehörighet", "beskrivning" : "Lite text här", "kategorier": [{ "id" : "2", "namn" : ""}], "granskare": { "id" : "66126629"} }'
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "GAllaBehörigheter", "beskrivning" : "Tittut", "kategorier": [{ "id" : "3", "namn" : ""}], "granskare": { "id" : "66126629"} }'
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "ProduktionDB", "beskrivning" : "Produktion databas", "kategorier": [{ "id" : "3", "namn" : ""}], "granskare": { "id" : "66126629"} }'
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "Wimi", "beskrivning" : "Wimi behörighet", "kategorier": [{ "id" : "3", "namn" : ""}], "granskare": { "id" : "66126629"} }'
curl -X POST http://127.0.0.1:8080/rest/v1/behorighet/skapa -H "Content-Type: application/json" -d '{ "id" : "", "namn" : "Ärenden", "beskrivning" : "Tillgång till ärenden", "kategorier": [{ "id" : "3", "namn" : ""}], "granskare": { "id" : "66126629"} }'
