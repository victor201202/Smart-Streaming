[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/le3-5Dzg)
# proiect-poo-template

Desgin patternurile implementate sunt: Facade, Factory, Singleton, Command.
Am implementat Singleton pentru toate clasele de tip Factory si pentru clasa ApplicationData ce stocheaza datele din baza de date pentru a face prelucrarea lor mai usoara.
Am implementat Command pentru comenzile pot fi executate de useri si de streameri pentru ca este good practice sa poti tine usor evidenta comenzilor ce se executa.
Am implementat Factory pentru Streameri si Streamuri pentru a putea instantia usor si elegant fiecare tip de streamer sau de stream. Am decis sa fac asta deoarece pe viitor intr-o aplicatie reala sigur vom vrea ca de exemplu o comanda executata de un muzician sa fie diferita de una executata de un host de podcasturi, sau ne vom dorii sa adaugam metode in plus unuia din ei.
Am implementat Facade pentru prelucrarea datelor din baza de date. Am considerat ca stocarea informatiilor in fisiere te tip csv nu este tocmai cea mai buna metoda si ca pe viitor intr-o aplicatie sigur va fi schimbata. Avand un facade va fi extrem de usor sa modificam modul in care datele sunt stocate si prelucrate, deoarece trebuie modificate doar metodele din facade.

